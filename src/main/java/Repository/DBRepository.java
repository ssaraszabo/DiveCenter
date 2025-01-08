package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Generic Database Repository for CRUD operations.
 *
 * @param <T> The type of entity the repository manages.
 */
public class DBRepository<T> implements IRepository<T> {

    private final String connectionString;
    private final String tableName;
    private final Function<ResultSet, T> fromResultSet;
    private final Function<T, Object[]> toColumns;
    private final Function<T, Integer> getId;

    /**
     * Constructor for the DBRepository.
     *
     * @param connectionString Database connection string.
     * @param tableName        Name of the database table.
     * @param fromResultSet    Function to map a ResultSet row to an entity.
     * @param toColumns        Function to map an entity to column values.
     * @param getId            Function to extract the ID from an entity.
     */
    public DBRepository(String connectionString, String tableName,
                        Function<ResultSet, T> fromResultSet,
                        Function<T, Object[]> toColumns,
                        Function<T, Integer> getId) {
        this.connectionString = connectionString;
        this.tableName = tableName;
        this.fromResultSet = fromResultSet;
        this.toColumns = toColumns;
        this.getId = getId;
    }

    @Override
    public boolean create(T entity) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            List<String> columnNames = getNonIdentityColumnNames(connection);

            // Build the INSERT statement
            StringBuilder query = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
            for (int i = 0; i < columnNames.size(); i++) {
                query.append(columnNames.get(i));
                if (i < columnNames.size() - 1) {
                    query.append(", ");
                }
            }
            query.append(") VALUES (");
            for (int i = 0; i < columnNames.size(); i++) {
                query.append("?");
                if (i < columnNames.size() - 1) {
                    query.append(", ");
                }
            }
            query.append(")");

            Object[] values = toColumns.apply(entity);

            // Validate column count
            if (values.length != columnNames.size()) {
                throw new SQLException("Mismatch between column names and entity values. Expected: "
                        + columnNames.size() + ", Found: " + values.length);
            }

            // Execute the query
            try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
                setStatementParameters(statement, values);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//    /**
//     * Adds an entity and returns the generated ID (for use in cases like auto-increment primary keys).
//     *
//     * @param entity The entity to be added.
//     * @return The generated ID, or -1 if an error occurs.
//     */
//    public int addAndReturnGeneratedKey(T entity) {
//        try (Connection connection = DriverManager.getConnection(connectionString)) {
//            StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
//            Object[] values = toColumns.apply(entity);
//            for (int i = 0; i < values.length; i++) {
//                query.append("?");
//                if (i < values.length - 1) query.append(", ");
//            }
//            query.append(")");
//
//            try (PreparedStatement statement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS)) {
//                setStatementParameters(statement, values);
//                statement.executeUpdate();
//
//                // Retrieve generated keys
//                ResultSet keys = statement.getGeneratedKeys();
//                if (keys.next()) {
//                    return keys.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return -1; // In case of failure
//    }

    @Override
    public T read(int id) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            String primaryKeyColumn = getPrimaryKeyColumn(connection);
            String query = "SELECT * FROM " + tableName + " WHERE " + primaryKeyColumn + " = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return fromResultSet.apply(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> readAll() {
        List<T> entities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            String query = "SELECT * FROM " + tableName;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    entities.add(fromResultSet.apply(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public boolean update(T entity) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            List<String> columnNames = getNonIdentityColumnNames(connection);
            String primaryKeyColumn = getPrimaryKeyColumn(connection);

            // Build the UPDATE query
            StringBuilder query = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
            for (int i = 0; i < columnNames.size(); i++) {
                query.append(columnNames.get(i)).append(" = ?");
                if (i < columnNames.size() - 1) {
                    query.append(", ");
                }
            }
            query.append(" WHERE ").append(primaryKeyColumn).append(" = ?");

            Object[] values = toColumns.apply(entity);

            try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
                setStatementParameters(statement, values);
                statement.setInt(columnNames.size() + 1, getId.apply(entity));
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            String primaryKeyColumn = getPrimaryKeyColumn(connection);
            String query = "DELETE FROM " + tableName + " WHERE " + primaryKeyColumn + " = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Fetches column names for the table, excluding identity columns.
     */
    private List<String> getNonIdentityColumnNames(Connection connection) throws SQLException {
        List<String> columnNames = new ArrayList<>();
        try (ResultSet columns = connection.getMetaData().getColumns(null, null, tableName, null)) {
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if (!isIdentityColumn(connection, tableName, columnName)) {
                    columnNames.add(columnName);
                }
            }
        }
        return columnNames;
    }

    /**
     * Determines if a column is an identity column.
     */
    private boolean isIdentityColumn(Connection connection, String tableName, String columnName) throws SQLException {
        String query = "SELECT is_identity FROM sys.columns " +
                "WHERE object_id = OBJECT_ID(?) AND name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tableName);
            statement.setString(2, columnName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("is_identity");
                }
            }
        }
        return false;
    }

    /**
     * Retrieves the name of the primary key column.
     */
    private String getPrimaryKeyColumn(Connection connection) throws SQLException {
        try (ResultSet columns = connection.getMetaData().getColumns(null, null, tableName, null)) {
            if (columns.next()) {
                return columns.getString("COLUMN_NAME");
            }
        }
        throw new SQLException("No primary key column found for table: " + tableName);
    }

    /**
     * Sets parameters for a PreparedStatement.
     */
    private void setStatementParameters(PreparedStatement statement, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }
}

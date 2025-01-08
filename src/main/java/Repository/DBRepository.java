package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Database repository for CRUD operations.
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
     * Initializes a new instance of the DBRepository class.
     *
     * @param connectionString  The connection string for the database.
     * @param tableName         The name of the table.
     * @param fromResultSet     A function for converting a ResultSet row to an entity.
     * @param toColumns         A function for converting an entity to its column values.
     * @param getId             A function to extract the ID of an entity.
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
            StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
            Object[] values = toColumns.apply(entity);
            for (int i = 0; i < values.length; i++) {
                query.append("?");
                if (i < values.length - 1) query.append(", ");
            }
            query.append(")");
            try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
                setStatementParameters(statement, values);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Adds an entity and returns the generated ID (for use in cases like auto-increment primary keys).
     *
     * @param entity The entity to be added.
     * @return The generated ID, or -1 if an error occurs.
     */
    public int addAndReturnGeneratedKey(T entity) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
            Object[] values = toColumns.apply(entity);
            for (int i = 0; i < values.length; i++) {
                query.append("?");
                if (i < values.length - 1) query.append(", ");
            }
            query.append(")");

            try (PreparedStatement statement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS)) {
                setStatementParameters(statement, values);
                statement.executeUpdate();

                // Retrieve generated keys
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // In case of failure
    }

    @Override
    public T read(int id) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            String primaryKeyColumn = getFirstColumnName(connection);
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
            StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
            Object[] values = toColumns.apply(entity);
            for (int i = 1; i < values.length; i++) {
                query.append("column").append(i).append(" = ?");
                if (i < values.length - 1) query.append(", ");
            }
            query.append(" WHERE id = ?");
            try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
                setStatementParameters(statement, values);
                statement.setObject(values.length, getId.apply(entity));
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
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
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
     * Retrieves the name of the first column in the specified table.
     *
     * @param connection The database connection.
     * @return The name of the first column.
     * @throws SQLException If an error occurs while retrieving metadata.
     */
    private String getFirstColumnName(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            if (columns.next()) {
                return columns.getString("COLUMN_NAME");
            }
        }
        throw new SQLException("No columns found for table: " + tableName);
    }
    private void setStatementParameters(PreparedStatement statement, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }
}

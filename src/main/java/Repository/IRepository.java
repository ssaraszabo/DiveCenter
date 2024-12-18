package Repository;

import java.util.List;

public interface IRepository<T> {
    boolean create(T entity);
    T read(int id);
    List<T> readAll();
    boolean update(T entity);
    boolean delete(int id);
    int addAndReturnGeneratedKey(T entity); // Method to insert an entity and return the generated ID
}

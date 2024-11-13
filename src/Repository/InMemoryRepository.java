package Repository;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class InMemoryRepository<T> implements IRepository<T> {
    private Map<Integer, T> storage = new HashMap<>();
    private java.util.function.Function<T, Integer> idExtractor;

    public InMemoryRepository(java.util.function.Function<T, Integer> idExtractor) {    //constructor accepts a lambda to get ID
        this.idExtractor = idExtractor;
    }

    @Override
    public boolean create(T item) {
        int id = idExtractor.apply(item);
        if (storage.containsKey(id)) {
            return false; // ID already exists
        }
        storage.put(id, item);
        return true;
    }

    @Override
    public T read(int id) {
        return storage.get(id);
    }

    @Override
    public List<T> readAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean update(T item) {
        int id = idExtractor.apply(item);
        if (!storage.containsKey(id)) {
            return false; // ID does not exist
        }
        storage.put(id, item);
        return true;
    }

    @Override
    public boolean delete(int id) {
        if (!storage.containsKey(id)) {
            return false; // ID does not exist
        }
        storage.remove(id);
        return true;
    }
}

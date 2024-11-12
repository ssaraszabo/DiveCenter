package Repository;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class InMemoryRepository<T extends Identifiable> implements IRepository<T> {
    private Map<Integer, T> data = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(T entity) {
        entity.setId(currentId);
        data.put(currentId++, entity);
    }

    @Override
    public T read(int id) {
        return data.get(id);
    }

    @Override
    public List<T> readAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void update(T entity) {
        int id = entity.getId();
        if (data.containsKey(id)) {
            data.put(id, entity);
        } else {
            throw new IllegalArgumentException("Entity with ID " + id + " does not exist.");
        }
    }

    @Override
    public void delete(int id) {
        data.remove(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(data.values());
    }
}

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class InMemoryRepository<T> implements IRepository<T> {
    private Map<Integer, T> data = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(T entity) {
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
        //trebuie luat ID de la entity si pus in Map
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

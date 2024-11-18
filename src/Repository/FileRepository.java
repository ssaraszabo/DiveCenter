package Repository;

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class FileRepository<T> implements IRepository<T> {
    private final File file;
    private final Function<T, Integer> idExtractor;
    private final Function<String, T> fromString;
    private final Function<T, String> toString;

    public FileRepository(String filePath, Function<T, Integer> idExtractor, Function<String, T> fromString, Function<T, String> toString) {
        this.file = new File(filePath);
        this.idExtractor = idExtractor;
        this.fromString = fromString;
        this.toString = toString;

        try {
            if (!file.exists()) {       //ensure the file exists
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create the file: " + filePath, e);
        }
    }

    @Override
    public boolean create(T obj) {
        int id = idExtractor.apply(obj);

        if (read(id) != null) {
            return false;       //entity with the same ID already exists
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(toString.apply(obj));
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    @Override
    public T read(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T obj = fromString.apply(line);
                if (idExtractor.apply(obj) == id) {
                    return obj;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return null;        //entity with the given ID not found
    }

    @Override
    public List<T> readAll() {
        List<T> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                entities.add(fromString.apply(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return entities;
    }

    @Override
    public boolean update(T obj) {
        int id = idExtractor.apply(obj);
        List<T> entities = readAll();
        boolean updated = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (T entity : entities) {
                if (idExtractor.apply(entity) == id) {
                    writer.write(toString.apply(obj));
                    updated = true;
                } else {
                    writer.write(toString.apply(entity));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
        return updated;
    }

    @Override
    public boolean delete(int id) {
        List<T> entities = readAll();
        boolean deleted = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (T entity : entities) {
                if (idExtractor.apply(entity) == id) {
                    deleted = true;
                } else {
                    writer.write(toString.apply(entity));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
        return deleted;
    }
}

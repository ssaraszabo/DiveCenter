package Repository;

import java.io.*;
//import java.util.*;
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

}

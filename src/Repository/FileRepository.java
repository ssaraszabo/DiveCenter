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

//    @Override
//    public boolean create(T obj) {
//        int id = idExtractor.apply(obj);
//
//        if (read(id) != null) {
//            return false;       //entity with the same ID already exists
//        }
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
//            writer.write(toString.apply(obj));
//            writer.newLine();
//            return true;
//        } catch (IOException e) {
//            System.err.println("Error writing to file: " + e.getMessage());
//            return false;
//        }
//    }
//@Override
//public boolean create(T obj) {
//    int highestId = readAll().stream()
//            .mapToInt(entity -> idExtractor.apply(entity)) // Extract IDs
//            .max()
//            .orElse(-1); // Default to -1 if the file is empty
//
//    int id = idExtractor.apply(obj);
//
//    System.out.println("Attempting to create object with ID: " + id);
//    System.out.println("Highest existing ID: " + highestId);
//
//    // Debug: Log the ID of the object being created
//    System.out.println("Attempting to create object with ID: " + id);
//
//    int newId = highestId + 1;
//
//    // Check if an entity with the same ID already exists
//    if (read(id) != null) {
//        System.out.println("Object with ID " + id + " already exists. Creation aborted.");
//        return false;
//    }
//
//    // Append the new object to the file
//    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
//        String objectString = toString.apply(obj);
//        writer.write(objectString);
//        writer.newLine();
//        writer.flush(); // Ensure the contents are flushed to the file
//        System.out.println("Object with ID " + id + " successfully created: " + objectString);
//        return true;
//    } catch (IOException e) {
//        System.err.println("Error writing to file: " + e.getMessage());
//        return false;
//    }
//}

@Override
public boolean create(T obj) {
    // Find the highest ID in the file
    int highestId = readAll().stream()
            .mapToInt(entity -> idExtractor.apply(entity))
            .max()
            .orElse(-1); // Default to -1 if no entities exist

    // Assign the next ID
    int newId = highestId + 1;

    // Dynamically update the object's first parameter (ID)
    T newObj = updateFirstFieldWithId(obj, newId);

    // Write the new object to the file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
        writer.write(toString.apply(newObj));
        writer.newLine();
        System.out.println("Object successfully created with ID: " + newId);
        return true;
    } catch (IOException e) {
        System.err.println("Error writing to file: " + e.getMessage());
        return false;
    }
}

    /**
     * Dynamically updates the first field in the class hierarchy with the given ID.
     *
     * @param obj The object whose first field is to be updated.
     * @param newId The new ID to set.
     * @return The updated object.
     */
    private T updateFirstFieldWithId(T obj, int newId) {
        try {
            // Traverse the class hierarchy (including superclasses)
            Class<?> clazz = obj.getClass();
            while (clazz != null) {
                // Get all fields declared in this class
                java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
                if (fields.length > 0) {
                    // Find the field in the class that corresponds to ID (usually the first field)
                    for (java.lang.reflect.Field field : fields) {
                        if (field.getType() == int.class) {
                            // Make the field accessible if it's private or protected
                            field.setAccessible(true);

                            // Set the new ID in the first field
                            field.set(obj, newId);
                            return obj; // Return the updated object
                        }
                    }
                }
                // Move to the superclass
                clazz = clazz.getSuperclass();
            }

            // If no ID field is found, throw an exception (shouldn't happen)
            throw new IllegalStateException("No suitable field found in object: " + obj.getClass().getName());
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to update the ID field of the object", e);
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

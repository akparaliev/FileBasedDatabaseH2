package Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class WriteAheadLog<T> {
    private final String FILENAME;

    public WriteAheadLog(String filename) {
        FILENAME = filename;
        createNewFile();
    }

    public void LogAddOperation(Integer id, T entity, String entityType) {
        WriteAheadLogEntry<T> logEntry = new WriteAheadLogEntry<>("ADD", entityType, entity, id);
        Gson gson = new Gson();
        String entityJson = gson.toJson(logEntry);
        SaveToFile(entityJson);
    }

    public void LogUpdateOperation(T entity, String entityType) {
        WriteAheadLogEntry<T> logEntry = new WriteAheadLogEntry<>("UPDATE", entityType, entity);
        Gson gson = new Gson();
        String entityJson = gson.toJson(logEntry);
        SaveToFile(entityJson);
    }

    public void LogRemoveOperation(String entityType, Integer id) {
        WriteAheadLogEntry<T> logEntry = new WriteAheadLogEntry<>("REMOVE", entityType, id);
        Gson gson = new Gson();
        String entityJson = gson.toJson(logEntry);
        SaveToFile(entityJson);
    }

    private void SaveToFile(String logEntryJson) {
        try (FileWriter fw = new FileWriter(FILENAME, true)) {
             fw.write(logEntryJson);
            fw.write("\n");
        } catch (IOException e) {
            System.out.println("Error while writing to WAL file: " + e.getMessage());
        }
    }

     private void createNewFile() {
        File file = new File(FILENAME);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + FILENAME);
                } else {
                     System.out.println("File creation failed: " + FILENAME);
                }
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
}

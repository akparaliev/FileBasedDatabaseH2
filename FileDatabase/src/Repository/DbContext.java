package Repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class DbContext {
    private final String FILENAME;

    public DbContext(String fileName) {
        FILENAME = fileName;
        createNewFile();
    }

    public DbSet GetDatabase() {
        DbSet database = new DbSet();
         try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))){
                database = (DbSet) objectInputStream.readObject(); 
        } catch (EOFException e) {
           
        } 
        catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return database;
    }

    public void SaveChanges(DbSet dbSet) {
        try {
            FileOutputStream fStream = new FileOutputStream(FILENAME);
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);

            oStream.writeObject(dbSet);

            oStream.close();
            fStream.close();
        } catch (IOException e) {
            e.printStackTrace();
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

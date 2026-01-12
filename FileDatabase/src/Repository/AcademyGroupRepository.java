package Repository;

import Entities.AcademyGroup;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class AcademyGroupRepository implements IRepository<AcademyGroup> {
    private final String FILENAME;


    public AcademyGroupRepository(String fileName) {
        FILENAME = fileName;
        createNewFile();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AcademyGroup> GetAll() {
        List<AcademyGroup> academyGroups = new ArrayList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))){
                academyGroups = (List<AcademyGroup>) objectInputStream.readObject(); 
        } catch (EOFException e) {
           
        } 
        catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return academyGroups;
    }

    @Override
    public AcademyGroup GetById(int id) {
        List<AcademyGroup> academyGroups = GetAll();
        for (AcademyGroup academyGroup : academyGroups) {
            if (academyGroup.getId() == id) {
                return academyGroup;
            }
        }

        return null;
    }

    @Override
    public void Add(AcademyGroup entity) {
        List<AcademyGroup> academyGroups = GetAll();
        academyGroups.add(entity);
        SaveChanges(academyGroups);
    }

    @Override
    public void Update(AcademyGroup entity) {
        List<AcademyGroup> academyGroups = GetAll();
        for (int i = 0; i < academyGroups.size(); i++) {
            if (academyGroups.get(i).getId() == entity.getId()) {
                academyGroups.set(i, entity);
                break;
            }
        }

        SaveChanges(academyGroups);
    }

    @Override
    public void Remove(int id) {
        List<AcademyGroup> academyGroups = GetAll();
        academyGroups.removeIf(group -> group.getId() == id);
        SaveChanges(academyGroups);
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

    // List<AcademyGroup> academyGroups = [{0, "Group 2025"}, {1, "Group 2026"}];
    // [{0, "Group 2025"}, {1, "Group 2026"}]
    // AcademyGroup(all the field information)
    // Serialization
    public void SaveChanges(List<AcademyGroup> academyGroups) {
        try {
            FileOutputStream fStream = new FileOutputStream(FILENAME);
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);

            oStream.writeObject(academyGroups);

            oStream.close();
            fStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

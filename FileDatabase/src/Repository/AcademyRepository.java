package Repository;

import Entities.Academy;
import java.util.Map;

public class AcademyRepository implements IRepository<Academy>{
    DbContext context;

    public AcademyRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public Map<Integer, Academy> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return dbSet.getAcademies();
    }

    @Override
    public Academy GetById(Integer id) {
        Map<Integer, Academy> academies = GetAll();
        //for (Academy academy : academies) {
        //    if (academy.getId() == id) {
        //        return academy;
        //    }
        // } // O(n)
        if (academies.containsKey(id)) { // O(1)
            return academies.get(id);  // O(1)
        } // -> O(1)

        return null;
    }

    @Override
    public void Add(Academy entity) {
        Map<Integer, Academy> academies = GetAll();
        if (academies.containsKey(entity.getId())) {
            System.out.println("id is not valid");
            return;
        } 

        academies.put(entity.getId(), entity);
        SaveChanges(academies);
    }

    @Override
    public void Update(Academy entity) {
        Map<Integer, Academy> academies = GetAll();
        // for (int i = 0; i < academies.size(); i++) {
        //    if (academies.get(i).getId() == entity.getId()) {
        //        academies.set(i, entity);
        //        break;
        //    }
        // } -> O(n)

        if (academies.containsKey(entity.getId())) { // O(1)
            academies.put(entity.getId(), entity);  // O(1)
            SaveChanges(academies);
        } // -> O(1)
        else {
             System.out.println("id is not in the table");
        }
    }

    @Override
    public void Remove(Integer id) {
        Map<Integer, Academy> academies = GetAll(); // academies - map of objects - RAM
        // academies.removeIf(academy -> academy.getId() == id); // O(n);

        if (academies.containsKey(id)) { // O(1) - 
            academies.remove(id); // O(1) - we remove academy with this id from RAM
            SaveChanges(academies); // let's write to DB(in our case it is hard disk, SSD|HDD)
        } // -> O(1)
        else {
            System.out.println("id is not found");
        }
    }

    public void SaveChanges(Map<Integer, Academy> academies) {
        DbSet dbSet = context.GetDatabase();
        dbSet.setAcademies(academies);
        context.SaveChanges(dbSet);
    }
}

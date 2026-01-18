package Repository;

import Entities.Academy;
import java.util.List;

public class AcademyRepository implements IRepository<Academy>{
    DbContext context;

    public AcademyRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public List<Academy> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return dbSet.getAcademies();
    }

    @Override
    public Academy GetById(int id) {
        List<Academy> academies = GetAll();
        for (Academy academy : academies) {
            if (academy.getId() == id) {
                return academy;
            }
        }

        return null;
    }

    @Override
    public void Add(Academy entity) {
        List<Academy> academies = GetAll();
        academies.add(entity);
        SaveChanges(academies);
    }

    @Override
    public void Update(Academy entity) {
        List<Academy> academies = GetAll();
        for (int i = 0; i < academies.size(); i++) {
            if (academies.get(i).getId() == entity.getId()) {
                academies.set(i, entity);
                break;
            }
        }

        SaveChanges(academies);
    }

    @Override
    public void Remove(int id) {
        List<Academy> academies = GetAll();
        academies.removeIf(academy -> academy.getId() == id);
        SaveChanges(academies);
    }

    public void SaveChanges(List<Academy> academies) {
        DbSet dbSet = context.GetDatabase();
        dbSet.setAcademies(academies);
        context.SaveChanges(dbSet);
    }
}

package Repository;

import Entities.Group;
import java.util.List;


public class GroupRepository implements IRepository<Group> {
    DbContext context;

    public GroupRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public List<Group> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return dbSet.getGroups();
    }

    @Override
    public Group GetById(int id) {
        List<Group> groups = GetAll();
        for (Group group : groups) {
            if (group.getId() == id) {
                return group;
            }
        }

        return null;
    }

    @Override
    public void Add(Group entity) {
        List<Group> groups = GetAll();
        groups.add(entity);
        SaveChanges(groups);
    }

    @Override
    public void Update(Group entity) {
        List<Group> groups = GetAll();
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId() == entity.getId()) {
                groups.set(i, entity);
                break;
            }
        }

        SaveChanges(groups);
    }

    @Override
    public void Remove(int id) {
        List<Group> groups = GetAll();
        groups.removeIf(group -> group.getId() == id);
        SaveChanges(groups);
    }

    // List<AcademyGroup> academyGroups = [{0, "Group 2025"}, {1, "Group 2026"}];
    // [{0, "Group 2025"}, {1, "Group 2026"}]
    // AcademyGroup(all the field information)
    // Serialization
    public void SaveChanges(List<Group> groups) {
        DbSet dbSet = context.GetDatabase();
        dbSet.setGroups(groups);
        context.SaveChanges(dbSet);
    }
    
}

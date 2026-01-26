package Repository;

import Entities.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// clustured index on id field - select name, academyId from Groups where id = 1 (O(1))
// non clustured index on academyId field - select id, name from Groups where academyId = 0
public class GroupRepository implements IRepository<Group> {
    DbContext context;

    public GroupRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public Map<Integer, Group> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return dbSet.getGroups();
    }

    public List<Group> GetByAcademyId(Integer academyId) {
        DbSet dbSet = context.GetDatabase();
        Set<Integer> groupIndices = dbSet.getAcademyGroupAcademyIndex(academyId);
        List<Group> groups = new ArrayList<>();
        for (Integer index : groupIndices) {
            groups.add(dbSet.getGroups().get(index));
        }
        return groups;
    }

    @Override
    public Group GetById(Integer id) {
        Map<Integer, Group> groups = GetAll();
        
        return groups.getOrDefault(id, null);
    }

    // Group table: {{key: 0, value: {name: group 2026, academyId: 0},
    //                {key: 2, value: {name: group 2027, academyId: 0} }}
    // academy index: {{key: 0, value: [0, 2]}}
    @Override
    public void Add(Group entity) {
        DbSet dbSet = context.GetDatabase();

        // update Group table
        Map<Integer, Group> groups = dbSet.getGroups();
        groups.put(entity.getId(), entity);
        dbSet.setGroups(groups);

        // update academy index
        Set<Integer> indices = dbSet.getAcademyGroupAcademyIndex(entity.getAcademyId());
        indices.add(entity.getId());
        dbSet.setAcademyGroupAcademyIndex(entity.getAcademyId(), indices);

        SaveChanges(dbSet);
    }

    @Override
    public void Update(Group entity) {
        DbSet dbSet = context.GetDatabase();
       
        // update Group table
        Map<Integer, Group> groups = dbSet.getGroups();
        groups.put(entity.getId(), entity);
        dbSet.setGroups(groups);

        // update academy index
        Set<Integer> academyIndex = dbSet.getAcademyGroupAcademyIndex(entity.getAcademyId());
        academyIndex.add(entity.getId());
        dbSet.setAcademyGroupAcademyIndex(entity.getAcademyId(), academyIndex);

        SaveChanges(dbSet);
    }

    @Override
    public void Remove(Integer id) {
        DbSet dbSet = context.GetDatabase();

        // remove from Group table
        Map<Integer, Group> groups = dbSet.getGroups();
        groups.remove(id);
        dbSet.setGroups(groups);

        // remove from academy index
        Group entity = groups.get(id);
        Set<Integer> academyIndex = dbSet.getAcademyGroupAcademyIndex(entity.getAcademyId());
        academyIndex.remove(id);
        dbSet.setAcademyGroupAcademyIndex(entity.getAcademyId(), academyIndex);

        SaveChanges(dbSet);
    }

    public void SaveChanges(DbSet dbSet) {
        context.SaveChanges(dbSet);
    }
}

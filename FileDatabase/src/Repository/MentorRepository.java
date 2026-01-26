package Repository;

import Entities.Group;
import Entities.Mentor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MentorRepository implements IRepository<Mentor> {
    DbContext context;

    public MentorRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public Map<Integer, Mentor> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return dbSet.getMentors();
    }

    public List<Group> GetByAcademyId(Integer academyId) {
        DbSet dbSet = context.GetDatabase();
        Set<Integer> groupIndices = dbSet.getAcademyMentorAcademyIndex(academyId);
        List<Group> groups = new ArrayList<>();
        for (Integer index : groupIndices) {
            groups.add(dbSet.getGroups().get(index));
        }
        return groups;
    }

    @Override
    public Mentor GetById(Integer id) {
        Map<Integer, Mentor> mentors = GetAll();
        return mentors.getOrDefault(id, null);
    }

    @Override
    public void Add(Mentor entity) {
        DbSet db = context.GetDatabase();

        // update Mentor table
        Map<Integer, Mentor>  mentors = db.getMentors();
        mentors.put(entity.getId(), entity);
        db.setMentors(mentors);

        // update academy index
        Set<Integer> indices = db.getAcademyMentorAcademyIndex(entity.getAcademyId());
        indices.add(entity.getId());
        db.setAcademyMentorAcademyIndex(entity.getAcademyId(), indices);

        SaveChanges(db);
    }

    @Override
    public void Update(Mentor entity) {
        DbSet dbSet = context.GetDatabase();

        // update Mentor table
        Map<Integer, Mentor>  mentors = dbSet.getMentors();
        mentors.put(entity.getId(), entity);
        dbSet.setMentors(mentors);

        // update academy index
        Set<Integer> academyIndex = dbSet.getAcademyMentorAcademyIndex(entity.getAcademyId());
        academyIndex.add(entity.getId());
        dbSet.setAcademyMentorAcademyIndex(entity.getAcademyId(), academyIndex);

        SaveChanges(dbSet);
    }

    @Override
    public void Remove(Integer id) {
        DbSet dbSet = context.GetDatabase();

        // remove from Mentor table
        Map<Integer, Mentor>  mentors = dbSet.getMentors();
        mentors.remove(id);
        dbSet.setMentors(mentors);

        // remove from academy index
        Mentor mentor = GetById(id);
        Set<Integer> academyIndex = dbSet.getAcademyMentorAcademyIndex(mentor.getAcademyId());
        academyIndex.remove(id);
        dbSet.setAcademyMentorAcademyIndex(mentor.getAcademyId(), academyIndex);

        SaveChanges(dbSet);
    }

    public void SaveChanges(DbSet dbSet) {
        context.SaveChanges(dbSet);
    }
}

package Repository;

import Entities.Mentor;
import java.util.List;

public class MentorRepository implements IRepository<Mentor> {
    DbContext context;

    public MentorRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public List<Mentor> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return dbSet.getMentors();
    }

    @Override
    public Mentor GetById(int id) {
        List<Mentor> mentors = GetAll();
        for (Mentor mentor : mentors) {
            if (mentor.getId() == id) {
                return mentor;
            }
        }

        return null;
    }

    @Override
    public void Add(Mentor entity) {
        List<Mentor> mentors = GetAll();
        mentors.add(entity);
        SaveChanges(mentors);
    }

    @Override
    public void Update(Mentor entity) {
        List<Mentor> mentors = GetAll();
        for (int i = 0; i < mentors.size(); i++) {
            if (mentors.get(i).getId() == entity.getId()) {
                mentors.set(i, entity);
                break;
            }
        }

        SaveChanges(mentors);
    }

    @Override
    public void Remove(int id) {
        List<Mentor> mentors = GetAll();
        mentors.removeIf(mentor -> mentor.getId() == id);
        SaveChanges(mentors);
    }

    public void SaveChanges(List<Mentor> mentors) {
        DbSet dbSet = context.GetDatabase();
        dbSet.setMentors(mentors);
        context.SaveChanges(dbSet);
    }
}

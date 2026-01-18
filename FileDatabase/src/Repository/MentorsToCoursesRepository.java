package Repository;

import Entities.MentorsToCourses;
import java.util.List;

public class MentorsToCoursesRepository implements IRepository<MentorsToCourses>{
    DbContext context;

    public MentorsToCoursesRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public List<MentorsToCourses> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return dbSet.getMentorsToCourses();
    }

    @Override
    public MentorsToCourses GetById(int id) {
        List<MentorsToCourses> mentorsToCourses = GetAll();
        for (MentorsToCourses mtc : mentorsToCourses) {
            if (mtc.getId() == id) {
                return mtc;
            }
        }

        return null;
    }

    @Override
    public void Add(MentorsToCourses entity) {
        List<MentorsToCourses> mentorsToCourses = GetAll();
        mentorsToCourses.add(entity);
        SaveChanges(mentorsToCourses);
    }

    @Override
    public void Update(MentorsToCourses entity) {
        List<MentorsToCourses> mentorsToCourses = GetAll();
        for (int i = 0; i < mentorsToCourses.size(); i++) {
            if (mentorsToCourses.get(i).getId() == entity.getId()) {
                mentorsToCourses.set(i, entity);
                break;
            }
        }

        SaveChanges(mentorsToCourses);
    }

    @Override
    public void Remove(int id) {
        List<MentorsToCourses> mentorsToCourses = GetAll();
        mentorsToCourses.removeIf(mtc -> mtc.getId() == id);
        SaveChanges(mentorsToCourses);
    }

    public void SaveChanges(List<MentorsToCourses> mentorsToCourses) {
        DbSet dbSet = context.GetDatabase();
        dbSet.setMentorsToCourses(mentorsToCourses);
        context.SaveChanges(dbSet);
    }
}

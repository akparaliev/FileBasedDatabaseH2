package Repository;

import Entities.MentorsToCourses;
import java.util.Map;

public class MentorsToCoursesRepository implements IRepository<MentorsToCourses>{
    DbContext context;

    public MentorsToCoursesRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public Map<Integer, MentorsToCourses> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return dbSet.getMentorsToCourses();
    }

    @Override
    public MentorsToCourses GetById(Integer id) {
        Map<Integer, MentorsToCourses> mentorsToCourses = GetAll();
        return mentorsToCourses.getOrDefault(id, null);
    }

    @Override
    public void Add(MentorsToCourses entity) {
        Map<Integer, MentorsToCourses> mentorsToCourses = GetAll();
        mentorsToCourses.put(entity.getId(), entity);
        SaveChanges(mentorsToCourses);
    }

    @Override
    public void Update(MentorsToCourses entity) {
        Map<Integer, MentorsToCourses> mentorsToCourses = GetAll();
        mentorsToCourses.put(entity.getId(), entity);

        SaveChanges(mentorsToCourses);
    }

    @Override
    public void Remove(Integer id) {
        Map<Integer, MentorsToCourses> mentorsToCourses = GetAll();
        mentorsToCourses.remove(id);
        SaveChanges(mentorsToCourses);
    }

    public void SaveChanges(Map<Integer, MentorsToCourses> mentorsToCourses) {
        DbSet dbSet = context.GetDatabase();
        dbSet.setMentorsToCourses(mentorsToCourses);
        context.SaveChanges(dbSet);
    }
}

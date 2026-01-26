package Repository;

import Entities.Course;
import Entities.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseRepository implements IRepository<Course> {
    DbContext context;

    public CourseRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public Map<Integer, Course> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return dbSet.getCourses();
    }

    public List<Group> GetByAcademyId(Integer academyId) {
        DbSet dbSet = context.GetDatabase();
        Set<Integer> groupIndices = dbSet.getAcademyCourseAcademyIndex(academyId);
        List<Group> groups = new ArrayList<>();
        for (Integer index : groupIndices) {
            groups.add(dbSet.getGroups().get(index));
        }
        return groups;
    }

    @Override
    public Course GetById(Integer id) {
        Map<Integer, Course> courses = GetAll();
        
        return courses.getOrDefault(id, null);
    }

    @Override
    public void Add(Course entity) {
        DbSet dbSet = context.GetDatabase();

        // update Course table
        Map<Integer, Course> courses = GetAll();
        courses.put(entity.getId(), entity);
        dbSet.setCourses(courses);

        // update academy index
        Set<Integer> indices = dbSet.getAcademyCourseAcademyIndex(entity.getAcademyId());
        indices.add(entity.getId());
        dbSet.setAcademyCourseAcademyIndex(entity.getAcademyId(), indices); 

        SaveChanges(dbSet);
    }

    @Override
    public void Update(Course entity) {
        DbSet dbSet = context.GetDatabase();

        // update Course table
        Map<Integer, Course> courses = dbSet.getCourses();
        courses.put(entity.getId(), entity);
        dbSet.setCourses(courses);

        // update academy index
        Set<Integer> academyIndex = dbSet.getAcademyCourseAcademyIndex(entity.getAcademyId());
        academyIndex.add(entity.getId());
        dbSet.setAcademyCourseAcademyIndex(entity.getAcademyId(), academyIndex);

        SaveChanges(dbSet);
    }

    @Override
    public void Remove(Integer id) {
        DbSet dbSet = context.GetDatabase();

        // remove from Course table
        Map<Integer, Course> courses = dbSet.getCourses();
        courses.remove(id);
        dbSet.setCourses(courses);

        // remove from academy index
        Course course = GetById(id);
        Set<Integer> academyIndex = dbSet.getAcademyCourseAcademyIndex(course.getAcademyId());
        academyIndex.remove(id);
        dbSet.setAcademyCourseAcademyIndex(course.getAcademyId(), academyIndex);

        SaveChanges(dbSet);
    }

    public void SaveChanges(DbSet dbSet) {
        context.SaveChanges(dbSet);
    }
}

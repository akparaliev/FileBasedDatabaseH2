package Repository;

import Entities.Course;
import java.util.List;

public class CourseRepository implements IRepository<Course> {
    DbContext context;

    public CourseRepository(DbContext context) {
        this.context = context;
    }

    @Override
    public List<Course> GetAll() {
        DbSet dbSet = context.GetDatabase();
        return dbSet.getCourses();
    }

    @Override
    public Course GetById(int id) {
        List<Course> courses = GetAll();
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }

        return null;
    }

    @Override
    public void Add(Course entity) {
        List<Course> courses = GetAll();
        courses.add(entity);
        SaveChanges(courses);
    }

    @Override
    public void Update(Course entity) {
        List<Course> courses = GetAll();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == entity.getId()) {
                courses.set(i, entity);
                break;
            }
        }

        SaveChanges(courses);
    }

    @Override
    public void Remove(int id) {
        List<Course> courses = GetAll();
        courses.removeIf(course -> course.getId() == id);
        SaveChanges(courses);
    }

    public void SaveChanges(List<Course> courses) {
        DbSet dbSet = context.GetDatabase();
        dbSet.setCourses(courses);
        context.SaveChanges(dbSet);
    }
}

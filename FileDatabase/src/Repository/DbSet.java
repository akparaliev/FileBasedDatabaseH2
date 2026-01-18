package Repository;

import Entities.Academy;
import Entities.Course;
import Entities.Group;
import Entities.Mentor;
import Entities.MentorsToCourses;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// db.file
//  academies = [id: 0, name: GrowthHungry]
//  groups = [{id: 0, name: Group 2025, academyId: 0}, {id: 1, name: Group 2026, academyId: 0}]
//  courses = [{id: 0, name: CS, academyId: 0}, {id: 1, name: DS&A, academyId: 0}]
//  mentors = [{id: 0, name: Nurbek, academyId: 0}, {id: 1, name: Shabdan, academyId: 0}]

public class DbSet implements Serializable {
    private List<Academy> academies;
    private List<Group> groups;
    private List<Course> courses;
    private List<Mentor> mentors;
    private List<MentorsToCourses> mentorsToCourses;

    public DbSet() {
        academies = new ArrayList<>();
        groups = new ArrayList<>();
        courses = new ArrayList<>();
        mentors = new ArrayList<>();
        mentorsToCourses = new ArrayList<>();
    }

    public List<Academy> getAcademies() {
        return academies;
    }

    public void setAcademies(List<Academy> academies) {
        this.academies = academies;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public List<MentorsToCourses> getMentorsToCourses() {
        return mentorsToCourses;
    }

    public void setMentorsToCourses(List<MentorsToCourses> mentorsToCourses) {
        this.mentorsToCourses = mentorsToCourses;
    }
}

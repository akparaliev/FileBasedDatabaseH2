package Repository;

import Entities.Academy;
import Entities.Course;
import Entities.Group;
import Entities.Mentor;
import Entities.MentorsToCourses;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// db.file // old way without clustured index
//  academies = [id: 0, name: GrowthHungry]
//  groups = [{id: 0, name: Group 2025, academyId: 0}, {id: 1, name: Group 2026, academyId: 0}] - O(n) to get element by id
//  courses = [{id: 0, name: CS, academyId: 0}, {id: 1, name: DS&A, academyId: 0}]
//  mentors = [{id: 0, name: Nurbek, academyId: 0}, {id: 1, name: Shabdan, academyId: 0}]

// db.file // old way with clustured index
//  academies = [{key: 0, value: {id: 0, name: GrowthHungry}}]
//  groups = [{key: 0, value: {id: 0, name: Group 2025, academyId: 0}}, {key: 1, value: {name: Group 2026, academyId: 0}}] - O(1) to get element by id

public class DbSet implements Serializable {
    private Map<Integer, Academy> academies; // key id, value: academy
    private Map<Integer, Group> groups;
    private Map<Integer, Course> courses;
    private Map<Integer, Mentor> mentors;
    private Map<Integer, MentorsToCourses> mentorsToCourses;

    // non clustured indexes
    private final Map<Integer, Set<Integer>> groupsAcademyIndex;
    private final Map<Integer, Set<Integer>> coursesAcademyIndex;
    private final Map<Integer, Set<Integer>> mentorsAcademyIndex;

    public DbSet() {
        academies = new HashMap<>();
        groups = new HashMap<>();
        courses = new HashMap<>();
        mentors = new HashMap<>();
        mentorsToCourses = new HashMap<>();

        groupsAcademyIndex = new TreeMap<>();
        coursesAcademyIndex = new TreeMap<>();
        mentorsAcademyIndex = new TreeMap<>();
    }

    public Map<Integer, Academy> getAcademies() {
        return academies;
    }

    public void setAcademies(Map<Integer, Academy> academies) {
        this.academies = academies;
    }

    // call getGroups, select only groups with academyId - O(n) time complexity
    public Map<Integer, Group> getGroups() {
        return groups;
    }

    public void setGroups(Map<Integer, Group> groups) {
        this.groups = groups;
    }

    public Map<Integer, Course>getCourses() {
        return courses;
    }

    public void setCourses(Map<Integer, Course> courses) {
        this.courses = courses;
    }

    public Map<Integer, Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(Map<Integer, Mentor> mentors) {
        this.mentors = mentors;
    }

    public Map<Integer, MentorsToCourses> getMentorsToCourses() {
        return mentorsToCourses;
    }

    public void setMentorsToCourses(Map<Integer, MentorsToCourses> mentorsToCourses) {
        this.mentorsToCourses = mentorsToCourses;
    }

    public void setAcademyGroupAcademyIndex(Integer academyId, Set<Integer> academyGroups) {
        this.groupsAcademyIndex.put(academyId, academyGroups);
    }

    public Set<Integer> getAcademyGroupAcademyIndex(Integer academyId) {
        if (this.groupsAcademyIndex.containsKey(academyId)) {
            return new HashSet<>(this.groupsAcademyIndex.get(academyId));
        }

        return new HashSet<>();
    }

    public void setAcademyCourseAcademyIndex(Integer academyId, Set<Integer> academyCourses) {
        this.coursesAcademyIndex.put(academyId, academyCourses);
    }
    public Set<Integer> getAcademyCourseAcademyIndex(Integer academyId) {
        if (this.coursesAcademyIndex.containsKey(academyId)) {
            return new HashSet<>(this.coursesAcademyIndex.get(academyId));
        }

        return new HashSet<>();
    }

    public void setAcademyMentorAcademyIndex(Integer academyId, Set<Integer> academyMentors) {
        this.mentorsAcademyIndex.put(academyId, academyMentors);
    }
    public Set<Integer> getAcademyMentorAcademyIndex(Integer academyId) {
        if (this.mentorsAcademyIndex.containsKey(academyId)) {
            return new HashSet<>(this.mentorsAcademyIndex.get(academyId));
        }

        return new HashSet<>();
    }
}

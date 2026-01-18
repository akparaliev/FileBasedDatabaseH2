package Entities;

import java.io.Serializable;

public class Group implements Serializable {
    private int id;
    private String name;
    private int academyId;
    private transient int averageAge; // we don't want to store it in the file

    public Group(int id, String name, int academyId) {
        this.id = id;
        this.name = name;
        this.academyId = academyId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAverageAge() {
        return averageAge;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAverageAge(int averageAge) {
        this.averageAge = averageAge;
    }

    public int getAcademyId() {
        return academyId;
    }

    public void setAcademyId(int academyId) {
        this.academyId = academyId;
    }
}

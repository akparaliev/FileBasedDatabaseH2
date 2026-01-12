package Entities;

import java.io.Serializable;

public class AcademyGroup implements Serializable {
    private int id;
    private String name;
    private transient int averageAge; // we don't want to store it in the file

    public AcademyGroup(int id, String name) {
        this.id = id;
        this.name = name;
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
}

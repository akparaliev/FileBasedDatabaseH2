package Entities;

import java.io.Serializable;

public class Mentor  implements Serializable {
    private int id;
    private String name;
    private int academyId;

    public Mentor(int id, String name, int academyId) {
        this.id = id;
        this.name = name;
        this.academyId = academyId;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAcademyId() {
        return academyId;
    }

    public void setAcademyId(int academyId) {
        this.academyId = academyId;
    }
}

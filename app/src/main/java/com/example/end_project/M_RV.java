package com.example.end_project;

public class M_RV {
    private String name;
    private String floors_am;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFloors_am() {
        return floors_am;
    }

    public void setFloors_am(String floors_am) {
        this.floors_am = floors_am;
    }

    public M_RV(String name, String floors_am) {
        this.name = name;
        this.floors_am = floors_am;
    }
}

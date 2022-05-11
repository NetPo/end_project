package com.example.end_project;

public class M_RV {
    private String name;
    private String floors_am;
    private String coord_x;
    private String coord_y;

    public M_RV(String name, String floors_am, String coord_x, String coord_y) {
        this.name = name;
        this.floors_am = floors_am;
        this.coord_x = coord_x;
        this.coord_y = coord_y;
    }

    public M_RV() {
    }

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

    public String getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(String coord_x) {
        this.coord_x = coord_x;
    }

    public String getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(String coord_y) {
        this.coord_y = coord_y;
    }
}

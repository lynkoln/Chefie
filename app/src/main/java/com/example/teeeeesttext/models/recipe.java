package com.example.teeeeesttext.models;

public class recipe {
    private Integer ID;
    private String Name;
    private String Cuisine;
    private String Dificulty;
    private String Prep_time;

    public recipe(){

    }

    public recipe(Integer ID, String name, String cuisine, String dificulty, String prep_time) {
        this.ID = ID;
        Name = name;
        Cuisine = cuisine;
        Dificulty = dificulty;
        Prep_time = prep_time;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCuisine() {
        return Cuisine;
    }

    public void setCuisine(String cuisine) {
        Cuisine = cuisine;
    }

    public String getDificulty() {
        return Dificulty;
    }

    public void setDificulty(String dificulty) {
        Dificulty = dificulty;
    }

    public String getPrep_time() {
        return Prep_time;
    }

    public void setPrep_time(String prep_time) {
        Prep_time = prep_time;
    }
}

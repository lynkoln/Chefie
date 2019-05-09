package com.example.teeeeesttext.models;

public class item {
    private Integer ID;
    private String Name;
    private String Desc;

    public item(String name, String desc){
        this.Name = name;
        this.Desc = desc;
    }

    public item(Integer ID, String name, String desc) {
        this.ID = ID;
        this.Name = name;
        this.Desc = desc;
    }

    public item() {

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

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    @Override
    public String toString() {
        return "item{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Desc='" + Desc + '\'' +
                '}';
    }
}

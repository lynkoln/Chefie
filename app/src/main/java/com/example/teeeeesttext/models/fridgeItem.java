package com.example.teeeeesttext.models;

public class fridgeItem {

    private Integer ID;
    private String Name;
    private Integer Quantity;

    public fridgeItem(){

    }

    public fridgeItem(Integer ID, String name, Integer quantity) {
        this.ID = ID;
        Name = name;
        Quantity = quantity;
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

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}

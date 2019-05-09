package com.example.teeeeesttext.models;


public class shoppinglist {
    private Integer ID;
    private String Date;

    public shoppinglist(){

    }



    public shoppinglist(Integer ID, String Date) {
        this.ID = ID;
        this.Date = Date;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


}

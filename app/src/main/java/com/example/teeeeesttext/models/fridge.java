package com.example.teeeeesttext.models;

public class fridge {

    private Integer ID;
    private Integer Quantity;
    private Integer Item_ID;

    public fridge(){

    }

    public fridge(Integer ID, Integer quantity, Integer item_ID) {
        this.ID = ID;
        Quantity = quantity;
        Item_ID = item_ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getItem_ID() {
        return Item_ID;
    }

    public void setItem_ID(Integer item_ID) {
        Item_ID = item_ID;
    }
}

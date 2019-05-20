package com.example.teeeeesttext.models;
//TODO MODELS

public class itemlist {
    private Integer ID;
    private Integer Quantity;
    private Integer Item_ID;
    private Integer ShoppingList_ID;

    public itemlist(){

    }

    public itemlist(Integer ID, Integer quantity, Integer item_ID, Integer shoppingList_ID) {
        this.ID = ID;
        Quantity = quantity;
        Item_ID = item_ID;
        ShoppingList_ID = shoppingList_ID;
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

    public Integer getShoppingList_ID() {
        return ShoppingList_ID;
    }

    public void setShoppingList_ID(Integer shoppingList_ID) {
        ShoppingList_ID = shoppingList_ID;
    }
}

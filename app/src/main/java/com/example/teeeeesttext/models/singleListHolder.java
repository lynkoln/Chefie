package com.example.teeeeesttext.models;

public class singleListHolder {

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    private Integer ID;
    private Integer ShoppingList_ID;
    private Integer Item_ID;
    private String Item_Name;
    private Integer Quantity;

    public singleListHolder(){

    }

    public singleListHolder(Integer id, Integer shoppingList_ID, Integer item_ID, String item_Name, Integer quantity) {
        ID = id;
        ShoppingList_ID = shoppingList_ID;
        Item_ID = item_ID;
        Item_Name = item_Name;
        Quantity = quantity;
    }

    public Integer getShoppingList_ID() {
        return ShoppingList_ID;
    }

    public void setShoppingList_ID(Integer shoppingList_ID) {
        ShoppingList_ID = shoppingList_ID;
    }

    public Integer getItem_ID() {
        return Item_ID;
    }

    public void setItem_ID(Integer item_ID) {
        Item_ID = item_ID;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}

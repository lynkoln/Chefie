package com.example.teeeeesttext;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.example.teeeeesttext.models.item;
import com.example.teeeeesttext.models.recipe;
import com.example.teeeeesttext.models.shoppinglist;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    //Static Variables
    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "Chefie";

    // Table names
    private static final String TABLE_FRIDGE = "fridge";
    private static final String TABLE_ITEM = "item";
    private static final String TABLE_ITEMLIST = "itemlist";
    private static final String TABLE_RECIPE = "recipe";
    private static final String TABLE_RECIPEINGREDIENT = "recipeingredient";
    private static final String TABLE_RECIPESTEP = "recipestep";
    private static final String TABLE_SHOPPINGLIST = "shoppinglist";



    //Table fields
    //Global names
    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "Name";
    private static final String DESC_COLUMN = "Desc";
    private static final String QUANTITY_COLUMN = "Quantity";

    //Individual names
    private static final String SHOPPINGLIST_DATE = "Date";
    private static final String RECIPEINGREDIENT_UNIT = "Unit";
    private static final String RECIPE_CUISINE = "Cuisine";
    private static final String RECIPE_DIFFICULTY = "Difficulty";
    private static final String RECIPE_PREPTIME = "Prep_time";
    private static final String RECIPESTEP_STEPNO = "StepNo";

    //Foreign key names
    private static final String FOREIGN_ITEM_ID = "Item_ID";
    private static final String RECIPEINGREDIENT_REPLACEMENT = "Replacement";
    private static final String FOREIGN_SHOPPINGLIST_ID = "ShoppingList_ID";
    private static final String FOREIGN_RECIPE_ID ="Recipe_ID";


    public DBHandler(Context context) {
        super(context,DATABASE_NAME, null ,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //turning on foreign keys
        db.execSQL("PRAGMA foreign_keys = ON");

        String CREATE_RECIPE="CREATE TABLE "+TABLE_RECIPE+" ("
                +ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME_COLUMN+" TEXT,"
                +RECIPE_CUISINE+" TEXT,"
                +RECIPE_DIFFICULTY+" TEXT,"
                +RECIPE_PREPTIME+" TEXT)";
        String CREATE_ITEM= "CREATE TABLE "+TABLE_ITEM+" ("
                +ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME_COLUMN+ " TEXT,"
                +DESC_COLUMN+ " TEXT)";
        String CREATE_SHOPPINGLIST="CREATE TABLE "+TABLE_SHOPPINGLIST+" ("
                +ID_COLUMN+" INTEGER PRIMARY KEY,"
                +SHOPPINGLIST_DATE+ " DATE)";


        String CREATE_FRIDGE = "CREATE TABLE "+TABLE_FRIDGE+" ("
                + ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + QUANTITY_COLUMN+" INTEGER,"
                + FOREIGN_ITEM_ID+" INTEGER,"
                + "FOREIGN KEY("+FOREIGN_ITEM_ID+") REFERENCES "+TABLE_ITEM+ "(ID) "+")";

        String CREATE_ITEMLIST="CREATE TABLE "+TABLE_ITEMLIST+" ("
                +ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +QUANTITY_COLUMN+ " INTEGER,"
                +FOREIGN_ITEM_ID+" INTEGER,"
                +FOREIGN_SHOPPINGLIST_ID+" INTEGER,"
                +"FOREIGN KEY("+FOREIGN_ITEM_ID+") REFERENCES "+ TABLE_ITEM+ "(ID),"
                +"FOREIGN KEY("+FOREIGN_SHOPPINGLIST_ID+") REFERENCES "+TABLE_SHOPPINGLIST+ "(ID))";

        String CREATE_RECIPEINGREDIENT="CREATE TABLE "+TABLE_RECIPEINGREDIENT+" ("
                +ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +QUANTITY_COLUMN+ " INTEGER,"
                +RECIPEINGREDIENT_UNIT+" TEXT,"
                +FOREIGN_RECIPE_ID+" INTEGER,"
                +FOREIGN_ITEM_ID+" INTEGER,"
                +RECIPEINGREDIENT_REPLACEMENT+" INTEGER,"
                +"FOREIGN KEY("+FOREIGN_RECIPE_ID+") REFERENCES "+TABLE_RECIPE+"(ID),"
                +"FOREIGN KEY("+FOREIGN_ITEM_ID+") REFERENCES "+TABLE_ITEM+"(ID),"
                +"FOREIGN KEY( "+RECIPEINGREDIENT_REPLACEMENT+") REFERENCES "+TABLE_ITEM+"(ID))";
        String CREATE_RECIPESTEP="CREATE TABLE "+TABLE_RECIPESTEP+" ("
                +ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +RECIPESTEP_STEPNO+ " INTEGER,"
                +DESC_COLUMN+ "TEXT,"
                +FOREIGN_RECIPE_ID+" INTEGER,"
                +"FOREIGN KEY("+FOREIGN_RECIPE_ID +") REFERENCES "+TABLE_RECIPE+"(ID))";


        db.execSQL(CREATE_RECIPE);
        db.execSQL(CREATE_ITEM);
        db.execSQL(CREATE_SHOPPINGLIST);

        db.execSQL(CREATE_FRIDGE);
        db.execSQL(CREATE_ITEMLIST);
        db.execSQL(CREATE_RECIPEINGREDIENT);
        db.execSQL(CREATE_RECIPESTEP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPINGLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIDGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPEINGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPESTEP);

        // Create tables again
        onCreate(db);
    }

    // Adding new Item Information
    void addNewItem(item newItem) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ID_COLUMN, newItem.getID());
        values.put(NAME_COLUMN, newItem.getName());
        values.put(DESC_COLUMN, newItem.getDesc());


        // Inserting Row
        db.insert(TABLE_ITEM, null, values);
    }

    // Getting All Items
    public List<item> getAllItemList() {


        List<item> itemList = new ArrayList<item>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ITEM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                item itm = new item();
                itm.setID(Integer.parseInt(cursor.getString(0)));
                itm.setName(cursor.getString(1));
                itm.setDesc(cursor.getString(2));

                // Adding contact to list
                itemList.add(itm);

            } while (cursor.moveToNext());
        }

        // return contact list
        return itemList;
}

    //Adding new recipe
    void addNewRecipe(recipe newRecipe){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ID_COLUMN, newRecipe.getID());
        values.put(NAME_COLUMN, newRecipe.getName());
        values.put(RECIPE_CUISINE, newRecipe.getCuisine());
        values.put(RECIPE_DIFFICULTY, newRecipe.getDificulty());
        values.put(RECIPE_PREPTIME, newRecipe.getPrep_time());

        //inserting row
        db.insert(TABLE_RECIPE, null, values);
        db.close();
    }



    //Adding shopping list
    void addNewShoppingList(shoppinglist newShoppingList){
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();

    values.put(ID_COLUMN, newShoppingList.getID());
    values.put(SHOPPINGLIST_DATE, newShoppingList.getDate());

    db.insert(TABLE_SHOPPINGLIST, null, values);
    db.close();
    }

}

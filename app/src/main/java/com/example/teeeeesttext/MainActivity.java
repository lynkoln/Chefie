package com.example.teeeeesttext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myFridgeBtn = (Button)findViewById(R.id.myFridgeBtn);
        Button recipesBtn = (Button)findViewById(R.id.recipesBtn);
        Button shoppingListBtn = (Button)findViewById(R.id.shoppingListBtn);
        Button addItemBtn = (Button)findViewById(R.id.addItemBtn);

        myFridgeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MyFridge.class);
                startIntent.putExtra("SOMETHING", "Wooo");
                // information passing to another activity
                startActivity(startIntent);
            }
        });

        recipesBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
               Intent startIntent = new Intent(getApplicationContext(), Recipes.class);
               startIntent.putExtra("BOO","DOO!!");
               startActivity(startIntent);
           }
        });

        shoppingListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), ShoppingList.class);
                startIntent.putExtra("WOOO","LOOOOOO!!");
                startActivity(startIntent);
            }
        });
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), newItem.class);
                startActivity(startIntent);
            }
        });

        //TODO DATABASE DISPLAYING (??)
        //TODO ITEM ADDING
        //TODO RECIPE ADDING
        //TODO MANAGEMENT

    }
}

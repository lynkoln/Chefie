package com.example.teeeeesttext;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teeeeesttext.models.item;

import java.util.List;

public class MyFridge extends AppCompatActivity {

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fridge);
        db = new DBHandler(this);
        Button addFridgeItem;
        addFridgeItem = (Button) findViewById(R.id.addFridgeBtn);
        addFridgeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                LayoutInflater layoutInflater = LayoutInflater.from(MyFridge.this);
                View promptView = layoutInflater.inflate(R.layout.addprompt, null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyFridge.this);
                alertDialog.setView(promptView);
                final EditText quan = (EditText)findViewById(R.id.quantityField);

                Spinner spinner = promptView.findViewById(R.id.ItemSpinner);
                List<item> items = db.getAllItemList();
                ArrayAdapter<item> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, items);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapter);


                alertDialog.setCancelable(false)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                Integer quantity = Integer.parseInt(quan.getText().toString());
                                Toast.makeText(getApplicationContext(),quantity,Toast.LENGTH_SHORT).show();
                            }

                        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.create();
                alertDialog.show();
            }
        });

            }


}


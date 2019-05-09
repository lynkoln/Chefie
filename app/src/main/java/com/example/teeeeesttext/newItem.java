package com.example.teeeeesttext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ArrowKeyMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.teeeeesttext.models.item;

import java.util.List;


public class newItem extends AppCompatActivity {

    DBHandler db;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHandler(this);

       setContentView(R.layout.activity_new_item);

        TextView tvItemList = (TextView) findViewById(R.id.tvItemList);

        tvItemList.setMovementMethod(ArrowKeyMovementMethod.getInstance());
        tvItemList.setText("");
        tvItemList.setPadding(5, 2, 5, 2);

        List<item> itemsList = db.getAllItemList();

        for (item itm : itemsList) {

            String stdDetail = "\n\nID:" + itm.getID() + "\n\tNAME:" + itm.getName() + "\n\tDESC" + itm.getDesc();
            tvItemList.append("\n" + stdDetail);
            //	Log.i("TAG", log);
        }

        Button newItemBtn= findViewById(R.id.newItemButton);

        newItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                EditText fieldName = findViewById(R.id.itemNameField);
                EditText fieldDesc =  findViewById(R.id.itemDescField);

                String Name, Desc;
                Name = fieldName.getText().toString();
                Desc = fieldDesc.getText().toString();

                item insert = new item(Name, Desc);

                db.addNewItem(insert);
                finish();
            }
        });

    }
}



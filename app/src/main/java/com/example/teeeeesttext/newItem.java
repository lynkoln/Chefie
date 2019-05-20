package com.example.teeeeesttext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teeeeesttext.models.item;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class newItem extends AppCompatActivity {
    List<item> itemsList;
    DBHandler db;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHandler(this);


       setContentView(R.layout.activity_new_item);

        ListView tvItemList = (ListView) findViewById(R.id.listView_item);
        itemsList = db.getAllItemList();
        Collections.sort(itemsList, new Comparator<item>() {
            public int compare(item o1, item o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        ItemAdapter itemAdapter = new ItemAdapter();
        tvItemList.setAdapter(itemAdapter);


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
                    recreate();

            }
        });

    }

class ItemAdapter extends BaseAdapter{


    @Override
    public int getCount() {
        Integer size = db.getAllItemList().size();
        return size;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = getLayoutInflater().inflate(R.layout.recycler_view_item,null);

        TextView NameText = (TextView) convertView.findViewById(R.id.tv_view_name);
        final TextView QuanText = (TextView) convertView.findViewById(R.id.tv_view_quan);
        final TextView IdText = (TextView) convertView.findViewById(R.id.tv_view_id);
        Button removeButton = (Button) convertView.findViewById(R.id.removeFridgeButton);

        IdText.setText(itemsList.get(position).getID().toString());
        NameText.setText(itemsList.get(position).getName());
        QuanText.setText(itemsList.get(position).getDesc());
        removeButton.setVisibility(View.INVISIBLE);
        TextView label = (TextView)convertView.findViewById(R.id.Quantity);
        label.setText("Description");
        return convertView;
    }
}

}



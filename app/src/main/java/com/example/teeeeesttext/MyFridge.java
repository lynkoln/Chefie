package com.example.teeeeesttext;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teeeeesttext.models.fridge;
import com.example.teeeeesttext.models.fridgeItem;
import com.example.teeeeesttext.models.item;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MyFridge extends AppCompatActivity {

    DBHandler db;
    EditText quan;
    ListView listView;
    TextView mName, mDesc, mID;
    Integer selectedID, quantity, itemid;
    String selectedName, selectedDesc;
    List<item> items;
    List<fridgeItem> fridgeList;
    Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fridge);
        db = new DBHandler(this);
        Button addFridgeItem;
        addFridgeItem = (Button) findViewById(R.id.addFridgeBtn);
        fridgeList = db.getAllFridgeList();
        listView = (ListView)findViewById(R.id.fridgeList);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);



        addFridgeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                LayoutInflater layoutInflater = LayoutInflater.from(MyFridge.this);
                View promptView = layoutInflater.inflate(R.layout.addprompt, null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyFridge.this);
                alertDialog.setView(promptView);
                quan = promptView.findViewById(R.id.quantityField);
                items = db.getAllItemList();
                spinner = promptView.findViewById(R.id.ItemSpinner);

                mName = promptView.findViewById(R.id.mTv_name);
                mDesc = promptView.findViewById(R.id.mTv_desc);
                mID = promptView.findViewById(R.id.mTV_ID);

                ArrayAdapter<item> spin = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, items);
                spin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(spin);
                spinner.setOnItemSelectedListener(listener);



                alertDialog.setCancelable(false)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){

                                Toast.makeText(MyFridge.this,"Added Something apparently",Toast.LENGTH_LONG).show();
                                fridge fridge = new fridge();

                                quantity = Integer.parseInt(quan.getText().toString());
                                itemid = Integer.parseInt(mID.getText().toString());

                                fridge.setQuantity(quantity);
                                fridge.setItem_ID(itemid);
                                db.addNewFridgeEntry(fridge);
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


    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            Integer size = db.getAllFridgeList().size();
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

            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MyFridge.this, "super"+db.removeFridgeEntry(  Integer.parseInt(IdText.getText().toString()),Integer.parseInt(QuanText.getText().toString())-1   ), Toast.LENGTH_SHORT).show();
                }
            };
            removeButton.setOnClickListener(onClickListener);



            IdText.setText(fridgeList.get(position).getID().toString());
            NameText.setText(fridgeList.get(position).getName());
            QuanText.setText(fridgeList.get(position).getQuantity().toString());

            return convertView;
        }
    }


            public AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    parent.setSelection(position);
                    Toast.makeText(MyFridge.this, "Hello Toast "+id+" "+position, LENGTH_SHORT).show();

                    Integer selection = Integer.parseInt(Long.toString(spinner.getSelectedItemId()));

                    selectedName = items.get(selection).getName();
                    selectedDesc = items.get(selection).getDesc();
                    selectedID = items.get(selection).getID();

                    mName.setText(selectedName);
                    mDesc.setText(selectedDesc);
                    mID.setText(selectedID.toString());


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            };
}




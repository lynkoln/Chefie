package com.example.teeeeesttext;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teeeeesttext.models.fridge;
import com.example.teeeeesttext.models.item;
import com.example.teeeeesttext.models.singleListHolder;

import java.util.List;

public class singleShoppingList extends AppCompatActivity {

    Button addItem;
    EditText quan;
    List<item> items;
    List<singleListHolder> singleList;
    ListView listView;
    DBHandler db;
    Integer selectedID, quantity, itemid, intValue;
    String selectedName, selectedDesc;
    Spinner spinner;
    TextView mName, mDesc, mID;
    SpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_shopping_list);
        db = new DBHandler(this);

        listView = findViewById(R.id.singleShoppingListList);
        Intent mIntent = getIntent();
        intValue = mIntent.getIntExtra("ID", 0);
        singleList = db.getSingleShoppingList(intValue);


        singleShoppingList.CustomAdapter customAdapter = new singleShoppingList.CustomAdapter();
        listView.setAdapter(customAdapter);


        addItem = findViewById(R.id.addListItemBtn);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(singleShoppingList.this);
                View promptView = layoutInflater.inflate(R.layout.addprompt, null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(singleShoppingList.this);
                alertDialog.setView(promptView);
                quan = promptView.findViewById(R.id.quantityField);
                items = db.getAllItemList();
                spinner = promptView.findViewById(R.id.ItemSpinner);

                mName = promptView.findViewById(R.id.mTv_name);
                mDesc = promptView.findViewById(R.id.mTv_desc);
                mID = promptView.findViewById(R.id.mTV_ID);

                adapter = new SpinAdapter(singleShoppingList.this,
                        android.R.layout.simple_spinner_item,
                        items);


                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(listener);



                alertDialog.setCancelable(false)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){

                                if(quan.getText().toString().equals("")){
                                    Toast.makeText(singleShoppingList.this,"One of the fields is empty",Toast.LENGTH_LONG).show();
                                }
                                else {

                                    singleListHolder holder = new singleListHolder();

                                    holder.setShoppingList_ID(intValue);
                                    holder.setItem_ID(Integer.parseInt(mID.getText().toString()));
                                    holder.setQuantity(Integer.parseInt(quan.getText().toString()));

                                    db.addItemToShoppingList(holder);
                                    recreate();
                                }
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

    public AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            parent.setSelection(position);


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

    public class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return singleList.size();
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

            String local = singleList.get(position).getItem_Name();
            Integer bocal = singleList.get(position).getShoppingList_ID();
            final Integer cokal = singleList.get(position).getQuantity();
            final Integer iokal = singleList.get(position).getItem_ID();
            final Integer aokal = singleList.get(position).getID();

            IdText.setText(bocal.toString());
            NameText.setText(local);
            QuanText.setText(cokal.toString());

            removeButton.setText("Bought");
            removeButton.setVisibility(View.VISIBLE);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fridge fridge = new fridge();
                    fridge.setQuantity(cokal);
                    fridge.setItem_ID(iokal);

                    db.addNewFridgeEntry(fridge);
                    db.removeShoppingListItem(aokal);
                    recreate();
                }
            });

            return convertView;
        }
    }
}


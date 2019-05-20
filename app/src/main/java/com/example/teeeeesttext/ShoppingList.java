package com.example.teeeeesttext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teeeeesttext.models.shoppinglist;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ShoppingList extends AppCompatActivity {

    Button newList;
    DBHandler db;
    ListView listv;
    List<shoppinglist> lList;


    public boolean containsList(List<shoppinglist> c, String currDate) {
        Integer listSize = lList.size();
        String boi;

        for(Integer z = 0; z<listSize; z++){
            boi = c.get(z).getDate();
            boi.toString();
            if(boi.equals(currDate) ){
                return true;
            }
        }
        return false;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        db = new DBHandler(this);
        newList = findViewById(R.id.addShoppingListBtn);

        listv = findViewById(R.id.ListListList);
        lList = db.getAllShoppingLists();


        CustomAdapter customAdapter = new CustomAdapter();
        listv.setAdapter(customAdapter);

        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shoppinglist list = new shoppinglist();


                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
                String formattedDate = df.format(c);


                list.setDate(formattedDate);

                if(containsList(lList, formattedDate) == true){
                    Toast.makeText(ShoppingList.this, "Shopping List For This Date Already Exists!",  Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addNewShoppingList(list);
                    recreate();
                }
            }
        });

    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            Integer size = db.getAllShoppingLists().size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.recycler_view_list, null);
            Button removeListBtn = convertView.findViewById(R.id.removeListBtn);
            TextView tvDate = convertView.findViewById(R.id.ShoppingListDate);

            tvDate.setText(lList.get(position).getDate());

            removeListBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.removeShoppingList(lList.get(position).getID());
                    recreate();
                }
            });


            if(lList.get(position).getDate() == "No Shopping Lists In Database Detected"){
                removeListBtn.setVisibility(View.INVISIBLE);
                TextView tvLabel = convertView.findViewById(R.id.ShoppingListFromLabel);
                tvLabel.setVisibility(View.INVISIBLE);
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ShoppingList.this,"Added Something apparently",Toast.LENGTH_LONG).show();

                    Intent startIntent = new Intent(getApplicationContext(), singleShoppingList.class);
                    startIntent.putExtra("ID",lList.get(position).getID());
                    startActivity(startIntent);



                }
            });

            return convertView;
        }

    }
}

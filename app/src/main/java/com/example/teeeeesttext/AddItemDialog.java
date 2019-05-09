package com.example.teeeeesttext;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;


public class AddItemDialog extends DialogFragment {

    //interface
    interface AddItemDialogListener{

        void onSaveButtonClick(DialogFragment dialog);

    }


    //instance to deliver the action
    AddItemDialogListener addItemListener;
    Context context;


   @Override
   public void onAttach(Activity activity){
       super.onAttach(activity);

       try{
           addItemListener = (AddItemDialogListener) activity;
       } catch(ClassCastException e){
           throw new ClassCastException(activity.toString() + "must implement AddItemDialogListener");
       }
   }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Inflate and set the layout for dialog
        //pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_new_item, null))

        //add action buttons
        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addItemListener.onSaveButtonClick(AddItemDialog.this);
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddItemDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}

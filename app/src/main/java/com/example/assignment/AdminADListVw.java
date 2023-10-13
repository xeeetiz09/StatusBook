package com.example.assignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//Adapter list view for creating a layout for showing all users and their information.
public class AdminADListVw extends BaseAdapter {

    private Activity mainActvt;
    private Cursor user_crs;

    public AdminADListVw(Activity mainActvt, Cursor user_crs) {
        this.mainActvt = mainActvt;
        this.user_crs = user_crs;
    }

    static class DataHolder {

        //initializing all text fields
        TextView vrname;
        TextView vrdate;
        TextView vremail;
        TextView vrpsw;
        Button delbtn;

    }

    @Override
    public int getCount() {
        return user_crs.getCount();
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
    public View getView(int position, View view, ViewGroup main) {
        View views;
        LayoutInflater layoutInflater = (LayoutInflater) mainActvt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = layoutInflater.inflate(R.layout.admin_layout, main, false);

        DataHolder dataHolder = new DataHolder();
        dataHolder.vrname = views.findViewById(R.id.vrname); //fetching name text field from admin_layout.xml
        dataHolder.vremail = views.findViewById(R.id.vremail); //fetching email text field from admin_layout.xml
        dataHolder.vrdate = views.findViewById(R.id.vrdate); //fetching date text field from admin_layout.xml
        dataHolder.vrpsw = views.findViewById(R.id.vrpsw); //fetching password text field from admin_layout.xml
        dataHolder.delbtn = views.findViewById(R.id.delbtn); //fetching delete button from admin_layout.xml

        user_crs.moveToPosition(position);
        String users = user_crs.getString(1); // setting username in string
        String email = user_crs.getString(2); // setting emails of user in string
        String date = user_crs.getString(3); // setting date registered by a user in string
        String password = user_crs.getString(4); // setting passwords of user in string

        dataHolder.vrname.setText(users); //setting username in text field
        dataHolder.vrdate.setText(date); //setting date in text field
        dataHolder.vremail.setText(email); //setting email in text field
        dataHolder.vrpsw.setText(password); //setting password in text field


        //making delete button functionable i.e when clicked, deletes specific user with a toast message
        dataHolder.delbtn.setOnClickListener(viewer->{
            ForDataStore forDataStore = new ForDataStore(mainActvt);
            forDataStore.deleteStatus(user_crs.getString(1));  //for deleting user's comment
            forDataStore.deleteInfo(user_crs.getString(1));  //for deleting user
            user_crs.moveToPosition(position);
            Toast.makeText(views.getContext(), "User named "+ user_crs.getString(1)+" deleted successfully", Toast.LENGTH_SHORT).show();

            //for reloading the page
            mainActvt.finish();
            Intent intent = new Intent(mainActvt, mainActvt.getClass());
            mainActvt.startActivity(intent);
        });

        return views;
    }
}

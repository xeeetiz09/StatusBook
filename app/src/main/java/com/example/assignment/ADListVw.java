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


//Adapter list view for creating a layout for comments posted by user.
public class ADListVw extends BaseAdapter {

    private Activity mainActvt;
    private Cursor cmt_crs;

    public ADListVw(Activity mainActvt, Cursor cmt_crs) {
        this.mainActvt = mainActvt;
        this.cmt_crs = cmt_crs;
    }

    static class DataHolder {
        //initializing all text fields
        TextView vrcmt;
        TextView vrdate;
        TextView vrname;
    }

    @Override
    public int getCount() {
        return cmt_crs.getCount();
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
        views = layoutInflater.inflate(R.layout.layout_sample, main, false);

        DataHolder dataHolder = new DataHolder();
        dataHolder.vrcmt = views.findViewById(R.id.vrcmt);  //fetching comment text field from layout_sample.xml
        dataHolder.vrdate = views.findViewById(R.id.vrdate);  //fetching date text field from layout_sample.xml
        dataHolder.vrname = views.findViewById(R.id.vrname);  //fetching name text field from layout_sample.xml

        cmt_crs.moveToPosition(position);
        String comments = cmt_crs.getString(3); // setting comments from user in string
        String users = cmt_crs.getString(1); // setting username in string
        String date = cmt_crs.getString(2); // setting date of comment posted by user in string


        dataHolder.vrname.setText(users);  //setting username in text field
        dataHolder.vrdate.setText(date); //setting date in text field
        dataHolder.vrcmt.setText(comments); //setting comment in text field
        return views;
    }
}

package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyStatus extends AppCompatActivity {
ForDataStore dataStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_status);
        dataStore = new ForDataStore(this); //calling database class.
        TextView mystatus = (TextView) findViewById(R.id.mystatus);
        String urname = getIntent().getStringExtra("usrname"); // data binding done for fetching name from previous page/activity
        Cursor cursor = dataStore.all_status(urname); //for fetching the status of specific logged in users.
        StringBuffer strbfr = new StringBuffer();


        // fetching and showing all status posted by a specific logged in user.
        while(cursor.moveToNext()){
            strbfr.append("Status: "+cursor.getString(3));
            strbfr.append("\n");
            strbfr.append("Date: "+cursor.getString(2));
            strbfr.append("\n");
            strbfr.append("\n");
        }

//        setting string buffer to string and then text in textview with mystatus id.
        mystatus.setText(strbfr.toString());
    }
}
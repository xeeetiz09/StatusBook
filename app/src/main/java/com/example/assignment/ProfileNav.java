package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ProfileNav extends AppCompatActivity {

    String usrname = "", emailadd = "", daterg = "", password = "";
    ForDataStore dataStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataStr = new ForDataStore(this);
        setContentView(R.layout.activity_profile_nav);
        String urname = getIntent().getStringExtra("name"); // data binding done for fetching name from previous page/activity
        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        TextView datereg = (TextView) findViewById(R.id.datereg);
        Button updatebtn = (Button) findViewById(R.id.updatebtn);
        Button stsbtn = (Button) findViewById(R.id.stsbtn);
        Button logout = (Button) findViewById(R.id.logout);
        Cursor cursor = dataStr.one_user(urname);

        // fetching and showing every credentials of a specific logged in user.
        cursor.moveToFirst();
        usrname = cursor.getString(1);
        emailadd = cursor.getString(2);
        daterg = cursor.getString(3);
        password = cursor.getString(4);
        name.setText("Name: "+usrname);
        email.setText("Email: "+emailadd);
        datereg.setText("Date: "+daterg);


        //when clicked on update button, the users redirects to update page
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileNav.this, EditProfile.class);
                intent.putExtra("usrname", usrname);
                startActivity(intent);
            }
        });


        // when clicked on log out button, the users gets logged out and redirects to main activity.
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileNav.this, MainActivity.class);
                Toast.makeText(ProfileNav.this, "Logged Out Successfully!", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(intent);
            }
        });


        // when clicked, users redirects to page where only their status is shown.
        stsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileNav.this, MyStatus.class);
                intent.putExtra("usrname", usrname);
                startActivity(intent);
            }
        });

    }
}
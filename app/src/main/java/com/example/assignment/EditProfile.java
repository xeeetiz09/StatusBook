package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditProfile extends AppCompatActivity {
    ForDataStore forDataStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        forDataStore = new ForDataStore(this);
        Intent intent = getIntent();
        EditText editemail = (EditText) findViewById(R.id.editemail); //edit field being called for new email
        EditText editpassword = (EditText) findViewById(R.id.editpassword); //edit field being called for new password
        EditText editconfpasw = (EditText) findViewById(R.id.editconfpasw); //edit field being called for confirming new password
        Button updbtn = (Button) findViewById(R.id.updbtn);

        String urname = intent.getStringExtra("usrname");  // data binding done for fetching name from previous page/activity


        updbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalEmail = editemail.getText().toString();  //getting text from email field
                String currentTime = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date()); //getting time and date
                String finalPassword = editpassword.getText().toString(); // getting text from password field.
                String finalConfPassword = editconfpasw.getText().toString(); //getting text from confirm password field

                //checking condition if both passwords match or not
                if (finalPassword.equals(finalConfPassword)) {
                    forDataStore.updateInfo(urname, finalEmail, currentTime, finalPassword); //updating data of user
                    Intent intent = new Intent(EditProfile.this, MainActivity.class);
                    Toast.makeText(EditProfile.this, "Updated Successfully! Please log in again", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(EditProfile.this, "Passwords do not match! Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
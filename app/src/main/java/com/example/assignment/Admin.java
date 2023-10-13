package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Admin extends AppCompatActivity {
    final String ALL_USERS = "Users.txt"; //files will be saved as AllUsersData.txt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button exprtbtn = findViewById(R.id.exprtbtn);
        ForDataStore dataStr = new ForDataStore(this);  //calling database class
        ListView container = findViewById(R.id.container);
        Cursor crs = dataStr.fetchInfo();  //for fetching the information of all users.
        AdminADListVw adListVw = new AdminADListVw(this, crs);  // calling adapter list view class for showing name of all users, date registered, emails and passwords in a mannered sequence
        container.setAdapter(adListVw);  // setting adapter list view into listview

        exprtbtn.setOnClickListener(v -> {


            // for exporting all users into a file
            FileOutputStream filOutStr = null;

            try{

                filOutStr = openFileOutput(ALL_USERS, MODE_PRIVATE);

                filOutStr.write(container.toString().getBytes());
                // alerts the message of file being saved on specific directory.
                Toast.makeText(Admin.this, "Successfully saved to " + getFilesDir() + "/" + ALL_USERS, Toast.LENGTH_LONG).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally{
                if (filOutStr != null){
                    try{
                        filOutStr.close();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
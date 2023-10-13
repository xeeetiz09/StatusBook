package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimelineActivity extends AppCompatActivity {
    EditText comment;
    ForDataStore dataStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        dataStr = new ForDataStore(this);  // calling database class
        Intent intent = getIntent();
        comment = findViewById(R.id.editText);
        ListView container = findViewById(R.id.container);
        Cursor crs = dataStr.fetchStatus(); //for fetching status of all users.
        ADListVw adListVw = new ADListVw(this, crs); //calling adapter list view class (ADlistVw).
        container.setAdapter(adListVw); // setting adListVw data in ListView with id 'container'.
        Button post = (Button) findViewById(R.id.post);
        Button profile = findViewById(R.id.profile);

        // when clicked on the image, users redirects to their profile.
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = intent.getStringExtra("name");
                Intent intent = new Intent(TimelineActivity.this, ProfileNav.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });


        // when clicked on post button after writing status, the status got posted and shows in page.
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String showComment = comment.getText().toString();
                String userName = intent.getStringExtra("name");
                String currentTime = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date());


                // if the post field is empty and post button is clicked, it prompts a message.
                if (TextUtils.isEmpty(showComment)){
                    Toast.makeText(TimelineActivity.this,"Comment field cannot be empty!",Toast.LENGTH_SHORT).show();
                }
                else{

                    // if the post field is filled and post button is clicked, the data gets stored in database and the page reloads
                    Boolean insertData = dataStr.postStatus(userName, currentTime, showComment.trim());
                    if (insertData == true) {
                        Toast.makeText(TimelineActivity.this, "Comment Posted!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                    }
                }
            }
        });

    }
}
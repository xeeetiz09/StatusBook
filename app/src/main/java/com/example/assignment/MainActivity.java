package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView signclick;
    EditText username, password;
    Intent intent;
    ForDataStore dataStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signclick = (TextView) findViewById(R.id.signclick);
        button = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        dataStr = new ForDataStore(this);

        //when clicked to sign in button, users redirecting to sign in page.
        signclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        //when clicked on login button with valid credentials, users redirecting to timeline page.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(MainActivity.this,"Your name is required!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pass)){
                    Toast.makeText(MainActivity.this,"Password is required!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean verify = dataStr.usnpComp(name,pass);
                    if (name.equals("admin") && pass.equals("admin")){
                        Toast.makeText(MainActivity.this,"Welcome Admin",Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, Admin.class);
                        startActivity(intent);
                    }
                    else if (verify == true){
                        Toast.makeText(MainActivity.this,"Welcome to timeline",Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainActivity.this, TimelineActivity.class);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Your username or password is incorrect!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
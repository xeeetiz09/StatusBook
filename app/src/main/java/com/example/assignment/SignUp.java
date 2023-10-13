package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    Button button;
    EditText flname, email, password, cnfpassword, date;
    ForDataStore dataStr;
    private static final Pattern EML_PATTERN = Pattern.compile("[A-Za-z0-9_.-]+@+[a-z]+\\.+[a-z]+");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        String dateandtime = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date());
        button = (Button) findViewById(R.id.button);
        flname = (EditText) findViewById(R.id.flname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        cnfpassword = (EditText) findViewById(R.id.cnfpassword);
        date = (EditText) findViewById(R.id.date);
        date.setText(dateandtime);
        dataStr = new ForDataStore(this);


        //when clicked on sign up button after inserting valid credentials (incl. proper format of email), the user's info will be saved into database.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = flname.getText().toString();
                String emailAdd = email.getText().toString();
                String rDate = date.getText().toString();
                String pass = password.getText().toString();
                String cnfpass = cnfpassword.getText().toString();


                //prompts message accordingly
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(SignUp.this,"Enter your name!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(emailAdd)){
                    Toast.makeText(SignUp.this,"Enter valid email address!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(rDate)){
                    Toast.makeText(SignUp.this,"Enter registered date!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pass)){
                    Toast.makeText(SignUp.this,"Enter password!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(cnfpass)){
                    Toast.makeText(SignUp.this,"Confirm your password!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean emailCheck = !emailAdd.equals("") && EML_PATTERN.matcher(emailAdd).matches();
                    if(emailCheck == true) {
                        if (pass.equals(cnfpass)) {
                            Boolean nameCheck = dataStr.compUsername(name);
                            if (nameCheck == false) {
                                Boolean setData = dataStr.setInfo(name, emailAdd, rDate, pass);

                                // if the credentials are inserted into database, then users can log in with such credentials
                                if (setData == true) {
                                    Toast.makeText(SignUp.this, "You can now log in to timeline", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SignUp.this, "Sign Up failed!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignUp.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUp.this, "Passwords do not match! Please try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignUp.this,"Check your email!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
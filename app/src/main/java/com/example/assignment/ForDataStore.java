package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLException;

public class ForDataStore extends SQLiteOpenHelper {
    public ForDataStore(@Nullable Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase storeUsers) {
        //creating table for storing data of all users
        storeUsers.execSQL("Create Table informationOfUser(id integer PRIMARY KEY AUTOINCREMENT, username texts, email email, date date, password text)");

        //creating table for storing comments from all users
        storeUsers.execSQL("Create Table statusOfUser(id integer PRIMARY KEY AUTOINCREMENT, username text, date date, status text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase storeUsers, int a, int b) {
        //query for deleting/dropping table

        storeUsers.execSQL("Drop Table If Exists informationOfUser");
        storeUsers.execSQL("Drop Table If Exists statusOfUser");
        onCreate(storeUsers);
    }


    // for storing comments done by user
    public Boolean postStatus(String username, String date, String status){
        SQLiteDatabase storeUsers=this.getWritableDatabase();
        ContentValues information = new ContentValues();
        information.put("username", username);
        information.put("date", date);
        information.put("status", status);
        long result = storeUsers.insert("statusOfUser",null,information);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }


    //for deleting comments
    public Boolean deleteStatus(String username){
        SQLiteDatabase storeUsers=this.getWritableDatabase();
        Cursor cursor = storeUsers.rawQuery("Select * from statusOfUser where username=?",new String[] {username});
        if(cursor.getCount() > 0){
            long result = storeUsers.delete("statusOfUser","username=?", new String[] {username} );
            if (result == -1){
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return false;
        }

    }


    //for storing all data of every users who signs into application
    public Boolean setInfo(String username, String email, String date, String password){
        SQLiteDatabase storeUsers=this.getWritableDatabase();
        ContentValues information = new ContentValues();
        information.put("username", username);
        information.put("date", date);
        information.put("email", email);
        information.put("password", password);
        long result = storeUsers.insert("informationOfUser",null,information);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }


    // for getting status of a specific user/individual
    public Cursor all_status(String username) {
        SQLiteDatabase storeUsers = this.getWritableDatabase();
        return storeUsers.rawQuery("SELECT * FROM statusOfUser WHERE username=?", new String[]{username});
    }


    //getting all information like name, email, password, date registered of a specific user/individual
    public Cursor one_user(String username) {
        SQLiteDatabase storeUsers = this.getWritableDatabase();
        return storeUsers.rawQuery("SELECT * FROM informationOfUser WHERE username=?", new String[]{username});
    }


    //for updating data of a user
    public void updateInfo(String username, String email, String date, String password){
        SQLiteDatabase storeUsers=this.getWritableDatabase();
        ContentValues information = new ContentValues();
        information.put("username",username);
        information.put("email", email);
        information.put("date", date);
        information.put("password",password);
        storeUsers.update("informationOfUser",information, "username=?", new String[] {username} );
    }




    //for deleting users
    public Boolean deleteInfo(String username){
        SQLiteDatabase storeUsers=this.getWritableDatabase();
        Cursor cursor = storeUsers.rawQuery("Select * from informationOfUser where username=?",new String[] {username});
        if(cursor.getCount() > 0){
            long result = storeUsers.delete("informationOfUser","username=?", new String[] {username} );
            if (result == -1){
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return false;
        }

    }


    //for getting status posted by users
    public Cursor fetchStatus(){
        SQLiteDatabase storeUsers=this.getWritableDatabase();
        Cursor cursor = storeUsers.rawQuery("Select * from statusOfUser", null);
        return cursor;
    }


    //for getting data of all users
    public Cursor fetchInfo(){
        SQLiteDatabase storeUsers=this.getWritableDatabase();
        Cursor cursor = storeUsers.rawQuery("Select * from informationOfUser", null);
        return cursor;
    }


    //for checking if username exists or not
    public Boolean compUsername(String username) {
        SQLiteDatabase storeUsers = this.getWritableDatabase();
        Cursor cursor = storeUsers.rawQuery("Select * from informationOfUser where username=?",new String[] {username});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }


    // to check if the provided credentials are valid or not in login page.
    public Boolean usnpComp(String username,String password) {
        SQLiteDatabase storeUsers = this.getWritableDatabase();
        Cursor cursor= storeUsers.rawQuery("Select * from informationOfUser where username=? and password=?",new String[] {username,password});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
package com.example.com594_cw2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseAdapter {

    // DATABASE VERSION
    private static final int DATABASE_VERSION = 1;

    // name
    private static final    String DATABASE_NAME = "Login.db";

    // database instance
    private static SQLiteDatabase db;

    // Database helper
    private DataBaseHelper dataBaseHelper;

    public DataBaseAdapter(Context context){
        dataBaseHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public  DataBaseAdapter open() throws SQLException{
        db = dataBaseHelper.getWritableDatabase();
        return this;

    }

    public void close(){
        db.close();

    }

    // gets instance of the database
    public SQLiteDatabase getDb(){
        return db;
    }

    // TODO change to meal

    public void insertEntry(String un, String pw){
        ContentValues newValues = new ContentValues();
        //Assign values for each row
        newValues.put("userName", un);
        newValues.put("userPassword", pw);
        // Insert the pow into table
        db.insert("User", null, newValues);

    }

    // TODO change to meal
    public String getSingleEntry(String un){
        String getPassword = "";
        db =dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query("User", null, "userName=?", new String[]{un},
                null, null, null);

        if(cursor.getCount() < 1){

            cursor.close();
            return "NOT EXIST";
        }

        cursor.moveToFirst();
        int index = cursor.getColumnIndex("userPassword");
        getPassword = cursor.getString(index);
        // Closing connections
        cursor.close();
        db.close();
        //returning get Password;
        return getPassword;

    }
}

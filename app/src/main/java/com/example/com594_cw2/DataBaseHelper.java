package com.example.com594_cw2;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TODO: add food values here
        String CREATE_USER_TABLE = "create table User (userID integer primary key autoincrement, "
                + "userName text, "
                + "userPassword text);";

        try{
            sqLiteDatabase.execSQL((CREATE_USER_TABLE));
        } catch (SQLException e) {
            Log.e("Error : ", "exception");
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //DROP of already exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "User");

        // create table
        onCreate(sqLiteDatabase);

    }
}

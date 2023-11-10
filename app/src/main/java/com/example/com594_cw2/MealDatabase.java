package com.example.com594_cw2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MealEntity.class}, version = 1)
public abstract class MealDatabase extends RoomDatabase {
    private static final String dbName = "Meal_DB";
    private static MealDatabase mealDatabase;

    public static synchronized MealDatabase getMealDatabase(Context context){
        if (mealDatabase == null){
            mealDatabase = Room.databaseBuilder(context, MealDatabase.class, dbName)
                    .fallbackToDestructiveMigration().build();
        }
        return mealDatabase;
    }
    public abstract MealDao mealDao();
}

package com.example.com594_cw2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MealDao {
    @Insert void storeMeal(MealEntity mealEntity);

    @Query("SELECT * from table_meal where Area=(:mArea)")
    MealEntity retrieveMeal(String mArea);
}

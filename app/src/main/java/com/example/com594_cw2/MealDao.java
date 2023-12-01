package com.example.com594_cw2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealDao {
    @Insert void storeMeal(MealEntity mealEntity);

    @Query("SELECT * from table_meal WHERE LOWER(Meal) LIKE '%' || LOWER(:searchKeyword) || '%'\n" +
            "   OR LOWER(Ingredient) LIKE '%' || LOWER(:searchKeyword) || '%';")
    List<MealEntity> retrieveMeal(String searchKeyword);

    @Query("SELECT * from table_meal ORDER By Meal Asc")
    LiveData<List<MealEntity>> getMeals();
}

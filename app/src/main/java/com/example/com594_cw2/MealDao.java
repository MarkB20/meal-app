package com.example.com594_cw2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealDao {
    @Insert void storeMeal(MealEntity mealEntity);

    // TODO Part 5
    @Query("SELECT * from table_meal WHERE LOWER(Meal) LIKE '%' || LOWER(:searchKeyword) || '%'\n" +
            "   OR LOWER(Ingredient) LIKE '%' || LOWER(:searchKeyword) || '%';")
    List<MealEntity> retrieveMeal(String searchKeyword);

}

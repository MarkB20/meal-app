package com.example.com594_cw2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MealRepository {
    MealDatabase mealDatabase;
    MealDao mealDao;
    private LiveData<List<MealEntity>>  listMeal;

    public MealRepository(Application application){
        mealDatabase=
        MealDatabase.getMealDatabase(application);
        mealDao = mealDatabase.mealDao();
        listMeal =mealDao.getMeals();

    }

    public LiveData<List<MealEntity>> getListMeal() {
        return listMeal;
    }
}

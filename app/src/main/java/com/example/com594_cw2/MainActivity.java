package com.example.com594_cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {
    public static final String json_string = "{\"Meal\":\"Sweet and Sour Pork\", \"DrinkAlternate\":null, \"Category\":\"Pork\", " +
            "\"Area\":\"Chinese\", \"MealThumb\":\"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/1529442316.jpg\"}";

    MealDatabase mealDatabase;
    MealDao mealDao;

    String mealName = "";
    String drinkAlternate = "";
    String category = "";
    String area = "";
    String mealthumb = "";

    DataBaseAdapter dataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // room database
        mealDatabase = MealDatabase.getMealDatabase(getApplicationContext());
        mealDao = mealDatabase.mealDao();


        // SQLite database for "Add Meals to DB"
        dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter = dataBaseAdapter.open();

        JSONObject jObj = null;
        try {
            jObj = new JSONObject(json_string);
            mealName = jObj.getString("Meal");
            drinkAlternate = jObj.getString("DrinkAlternate");
            category = jObj.getString("Category");
            area = jObj.getString("Area");
            mealthumb = jObj.getString("MealThumb");

            System.out.println("Meal Name = " + mealName);
            System.out.println("drinkAlternate = " + drinkAlternate);
            System.out.println("area = " + area);
            System.out.println("mealthumb = " + mealthumb);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public void addMeals(View view){
        //TODO add meals and remove str1 and str2
        String str1 = "Username";
        String str2 = "Password";
        dataBaseAdapter.insertEntry(str1, str2);


    }


    public void storeData(View view){
        MealEntity mealEntity = new MealEntity();
        mealEntity.setMeal(mealName);
        mealEntity.setdAlternate(drinkAlternate);
        mealEntity.setCategory(category);
        mealEntity.setArea(area);
        mealEntity.setMealThumb(mealthumb);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.storeMeal(mealEntity);
            }
        }).start();

    }

    public void getData(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MealEntity mealEntity = mealDao.retrieveMeal("Chinese");
                if (mealEntity == null){
                    System.out.println("Sorry");
                }
                else{
                    String meal_name = mealEntity.meal;
                    System.out.println("Meal name = "+ meal_name);
                }
            }
        }).start();
    }

}
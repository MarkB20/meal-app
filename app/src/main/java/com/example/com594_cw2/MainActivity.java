package com.example.com594_cw2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    MealDatabase mealDatabase;
    MealDao mealDao;
    JSONHelper jsoNhelper = new JSONHelper();


    Button mealIngredient;
    Button mealDB;

    Button webSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // room database
        mealDatabase = MealDatabase.getMealDatabase(getApplicationContext());
        mealDao = mealDatabase.mealDao();

        mealIngredient = findViewById(R.id.searchIngredient_btn);
        mealDB = findViewById(R.id.search_btn);
        webSearch = findViewById(R.id.webSearch_btn);


        mealIngredient.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), mealByIngredients.class);
            Log.d("MainActivity", "Button clicked, starting mealByIngredients activity");
            startActivity(intent);

        });



        mealDB.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), SearchMeals.class);

            startActivity(intent);
        });

        webSearch.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), SearchAll.class);

            startActivity(intent);

        });

       System.out.println(jsoNhelper.callVolley("https://www.themealdb.com/api/json/v1/1/search.php?s=chicken", getApplicationContext()));

    }

    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Handle configuration changes, if needed
    }



    public void storeData(View view){

        //sends the string to be conversed into a json object so be stored in a database
        jsoNhelper.stringToRoom(getResources().getString(R.string.json1), mealDao);


    }



}
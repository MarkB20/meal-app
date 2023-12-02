package com.example.com594_cw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    MealDatabase mealDatabase;
    MealDao mealDao;
    JSONHelper jsoNhelper = new JSONHelper();


    Button mealIngredient;
    Button mealDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // room database
        mealDatabase = MealDatabase.getMealDatabase(getApplicationContext());
        mealDao = mealDatabase.mealDao();

        mealIngredient = findViewById(R.id.searchIngredient_btn);
        mealDB = findViewById(R.id.search_btn);


        mealIngredient.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), mealByIngredients.class);

            startActivity(intent);
        });

        mealDB.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), SearchMeals.class);

            startActivity(intent);
        });


    }



    public void storeData(View view){

        //sends the string to be conversed into a json object so be stored in a database
        jsoNhelper.stringToRoom(getResources().getString(R.string.json1), mealDao);


    }

}
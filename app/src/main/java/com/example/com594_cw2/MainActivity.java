package com.example.com594_cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    MealDatabase mealDatabase;
    MealDao mealDao;
    JSONhelper jsoNhelper = new JSONhelper();


    Button mealIngredient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // room database
        mealDatabase = MealDatabase.getMealDatabase(getApplicationContext());
        mealDao = mealDatabase.mealDao();

        mealIngredient = (Button) findViewById(R.id.searchIngredient_btn);


        mealIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), mealByIngredients.class);

                startActivity(intent);
            }
        });


    }



    public void storeData(View view){

        //sends the string to be conversed into a json object so be stored in a database
        jsoNhelper.stringToRoom(getResources().getString(R.string.json1), mealDao);


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
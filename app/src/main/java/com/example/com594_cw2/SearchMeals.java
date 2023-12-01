package com.example.com594_cw2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SearchMeals extends AppCompatActivity {

    MealDatabase mealDatabase;
    MealDao mealDao;

    MealRepository mealRepository;
    EditText mealEdt;
    Button searchBtn;
    ImageButton backBtn;
    ImageButton forwardBtn;
    TextView noOfResultsTxt;
    ImageView mealImg;
    TextView mealOutput;
    int indent =0;

    List<MealEntity> meals;

    StringBuilder stringBuilder = new StringBuilder();
    String[] mealTxt;
    String[] ingredientTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_meals);

        mealDatabase = MealDatabase.getMealDatabase(getApplicationContext());
        mealDao = mealDatabase.mealDao();


        mealEdt = findViewById(R.id.mealEdt);
        searchBtn = findViewById(R.id.searchBtn);
        mealImg =  findViewById(R.id.mealImg);
        mealOutput = findViewById(R.id.mealOutput);
        backBtn = findViewById(R.id.backBtn2);
        forwardBtn = findViewById(R.id.forwardBtn2);
        noOfResultsTxt = findViewById(R.id.noOfResultsTxt2);




        searchBtn.setOnClickListener(view -> {
            if(mealEdt.getText().length() == 0){
                Toast.makeText(getApplicationContext(), "Enter something in the text box", Toast.LENGTH_LONG).show();//display instructions

            }else{

                new Thread(() -> {
                    try {
                        List<MealEntity> meals = mealDao.retrieveMeal(mealEdt.getText().toString());
                        mealTxt = new String[meals.size()];
                        ingredientTxt = new String[meals.size()];
                        for (int i= 0; i < meals.size(); i ++) {
                            mealTxt[i] = meals.get(i).meal;
                            ingredientTxt[i] = meals.get(i).getIngredient().toString();
                        }





                        cycle();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }


        });

        backBtn.setOnClickListener(view -> {
            if(mealTxt[0].isEmpty()){
                Toast.makeText(getApplicationContext(), "retrieve something first", Toast.LENGTH_LONG).show();//display instructions
            }else{
                if(indent == 0){
                    indent = meals.size() - 1;

                }else{
                    indent--;
                }
                cycle();
            }
        });

        forwardBtn.setOnClickListener(view -> {
            if(mealTxt[indent].isEmpty()){
                Toast.makeText(getApplicationContext(), "retrieve something first", Toast.LENGTH_LONG).show();//display instructions
            }else{
                if(indent == mealTxt.length -1){
                    indent = 0;

                }else{
                    indent ++;
                }
                cycle();
            }

        });




    }

    private void cycle() {

        runOnUiThread(() -> {
            System.out.println(mealTxt[indent] + "\n" + ingredientTxt[indent]);
            Toast.makeText(getApplicationContext(), mealTxt[indent] + "\n" + ingredientTxt[indent], Toast.LENGTH_LONG).show();
            mealOutput.setText("Meal: "+mealTxt[indent] + "\n" + "ingredients: "+ingredientTxt[indent]);
        });

        noOfResultsTxt.setText(indent+1 + "/" + mealTxt.length);

    }
}


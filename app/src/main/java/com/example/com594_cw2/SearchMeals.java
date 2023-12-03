package com.example.com594_cw2;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class SearchMeals extends AppCompatActivity {

    MealDatabase mealDatabase;
    MealDao mealDao;
    EditText mealEdt;
    Button searchBtn;
    ImageButton backBtn;
    ImageButton forwardBtn;
    TextView noOfResultsTxt;
    ImageView mealImg;
    Bitmap bitmap;
    TextView mealOutput;
    String mealOutputTxt;
    String fullCounter;
    int indent =0;
    String[] mealTxt;
    String[] ingredientTxt;
    String[] URLTxt;


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

        // searches the database and runs cycle at indent 0
        searchBtn.setOnClickListener(view -> {
            if(mealEdt.getText().length() == 0){
                Toast.makeText(getApplicationContext(), "Enter something in the text box", Toast.LENGTH_LONG).show();//display instructions

            }else{
                new Thread(() -> {
                    try {
                        List<MealEntity> meals = mealDao.retrieveMeal(mealEdt.getText().toString());

                        if ((!meals.isEmpty())) {
                            mealTxt = new String[meals.size()];
                            ingredientTxt = new String[meals.size()];
                            URLTxt = new String[meals.size()];
                            for (int i= 0; i < meals.size(); i ++) {
                                mealTxt[i] = meals.get(i).getMeal();
                                ingredientTxt[i] = meals.get(i).getIngredient().toString();
                                URLTxt[i] = meals.get(i).getMealThumb();
                            }
                            cycle();
                        }else{
                            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "No matching results", Toast.LENGTH_LONG).show());


                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }


        });

        // back button for selection of meals
        backBtn.setOnClickListener(view -> {
            if(mealTxt[indent].isEmpty()){
                Toast.makeText(getApplicationContext(), "retrieve something first", Toast.LENGTH_LONG).show();//display instructions
            }else{
                // checks if the selection is at the start if ture cycles to the last item
                if(indent == 0){
                    indent = mealTxt.length - 1;

                }else{
                    // indent minus one before running cycle so it displays the previous item
                    indent--;
                }
                cycle();
            }
        });

        // forward button for selection of meals
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

    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Handle configuration changes, if needed
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("indent", indent);
        outState.putStringArray("mealTxt",  mealTxt);
        outState.putStringArray("ingredientTxt", ingredientTxt);
        outState.putStringArray("URLTxt", URLTxt);


    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
         indent = savedInstanceState.getInt("indent", 0);
         mealTxt= savedInstanceState.getStringArray("mealTxt");
         ingredientTxt= savedInstanceState.getStringArray("ingredientTxt");
         URLTxt= savedInstanceState.getStringArray("URLTxt");




        if (mealTxt != null && ingredientTxt != null && URLTxt != null && mealTxt.length > 0) {
            if (!mealTxt[indent].isEmpty()) {
                cycle();
            }
        } else {
            // Handle the case where one or more arrays are null or empty
            // You might want to display a message or handle it in a way that makes sense for your app
        }


    }








    private void cycle() {

        runOnUiThread(() -> {
            mealOutputTxt = "Meal: " + mealTxt[indent] + "\n" + formatIngredients(ingredientTxt[indent]);
            System.out.println(mealOutputTxt);
            mealOutput.setText(mealOutputTxt);

            new Thread(() -> {
                try {

                    System.out.println("URL: " + new URL(URLTxt[indent]));
                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(URLTxt[indent]).getContent());
                    mealImg.setImageBitmap(bitmap);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();

        });

        fullCounter = indent+1 + "/" + mealTxt.length;
        noOfResultsTxt.setText(fullCounter);

    }
    private String formatIngredients(String ingredients) {
        // If the ingredients string is not empty, split it into an array and format each non-empty ingredient on a new line
        if (ingredients != null && !ingredients.isEmpty()) {
            // removing the array brackets "[]"
            String[] ingredientArray = ingredients.substring(1, ingredients.length() - 1).split(", ");

            StringBuilder formattedIngredients = new StringBuilder("Ingredients:\n");

            for (String ingredient : ingredientArray) {
                // don't add any empty ingredients
                if (!ingredient.trim().isEmpty()) {
                    formattedIngredients.append("- ").append(ingredient.trim()).append("\n");
                }
            }

            return formattedIngredients.toString();
        } else {
            return "N/A";
        }
    }





}


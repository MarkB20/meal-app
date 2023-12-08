package com.example.com594_cw2;


import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class mealByIngredients extends AppCompatActivity implements Helper.VolleyCallback  {
    String mealName = "";
    String drinkAlternate = "";
    String category = "";
    String area = "";
    String mealThumb = "";
    String tag = "";
    String youtube  = "";
    ArrayList<String> ingredients = new ArrayList<>();
    ArrayList<String> measures = new ArrayList<>();

    String source = "";
    String imageSource = "";
    String creativeCommonsConfirmed = "";
    String dateModified = "";




    int indent =0;
    String JSONResponse;
    private final String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=";

    EditText ingredientTxt;

    Button retrieveMealsBtn;

    Button saveMealsBtn;

    ImageButton backBtn;

    ImageButton forwardBtn;

    TextView noOfResultsTxt;


    TextView jsonOutputTxt;


    MealDatabase mealDatabase;
    MealDao mealDao;
    Helper jsonHelper = new Helper();
    JSONObject obj = null;
    JSONArray jArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_by_ingredients);

        mealDatabase = MealDatabase.getMealDatabase(getApplicationContext());
        mealDao = mealDatabase.mealDao();

        ingredientTxt = findViewById(R.id.ingredientTxt);
        retrieveMealsBtn = findViewById(R.id.retrieveMealsBtn);
        saveMealsBtn = findViewById(R.id.saveMealsBtn);
        jsonOutputTxt = findViewById(R.id.jsonOutputTxt);
        backBtn = findViewById(R.id.backBtn);
        forwardBtn = findViewById(R.id.forwardBtn);
        noOfResultsTxt = findViewById(R.id.noOfResultsTxt);



            backBtn.setOnClickListener(view -> {
                if(jArray == null){
                    Toast.makeText(getApplicationContext(), "retrieve something first", Toast.LENGTH_LONG).show();//display instructions
                }else{
                    if(indent == 0){
                        indent = jArray.length() - 1;

                    }else{
                        indent--;
                    }
                    cycle();
                }
            });

            forwardBtn.setOnClickListener(view -> {
                if(jArray == null){
                    Toast.makeText(getApplicationContext(), "retrieve something first", Toast.LENGTH_LONG).show();//display instructions
                }else{
                    if(indent == jArray.length() -1){
                        indent = 0;

                    }else{
                        indent ++;
                    }
                    cycle();
                }
            });



            retrieveMealsBtn.setOnClickListener(view -> {
                if(ingredientTxt.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Enter something in the text box", Toast.LENGTH_LONG).show();//display instructions

                }else{
                    // TODO Part 3.5
                    indent = 0;
                    jsonHelper.callVolley(url + ingredientTxt.getText(), this, this);
                }
            });

            saveMealsBtn.setOnClickListener(view -> {
                if(JSONResponse.isEmpty()){
                    Toast.makeText(getApplicationContext(), "retrieve something first", Toast.LENGTH_LONG).show();//display instructions
                }else{
                    // TODO Part 4
                    jsonHelper.stringToRoom(JSONResponse,mealDao);

                }
            });
    }

    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Handle configuration changes, if needed
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // saves the json array as a string as it can't be saved as itself

        outState.putInt("indent", indent);
        if (jArray != null) {
            outState.putString("jArray", jArray.toString());
        }



    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        indent = savedInstanceState.getInt("indent", 0);
        String jsonArray = savedInstanceState.getString("jArray");
        try {
            jArray = new JSONArray(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        cycle();

    }

    @SuppressLint("SetTextI18n")
    public void cycle(){
        JSONObject jObj;
        try {
            ingredients.clear();
            measures.clear();
            jObj = jArray.getJSONObject(indent);
            mealName = jObj.getString("strMeal");
            drinkAlternate = jObj.getString("strDrinkAlternate");
            category = jObj.getString("strCategory");
            area = jObj.getString("strArea");
            mealThumb = jObj.getString("strMealThumb");
            tag = jObj.getString("strTags");
            youtube = jObj.getString("strYoutube");

            for (int x = 1; x <= 20; x++) {
                ingredients.add(jObj.getString("strIngredient" + (x)));
            }

            for (int x = 1; x <= 20; x++) {
                measures.add(jObj.getString("strMeasure" + (x)));
            }
            source = jObj.getString("strSource");
            imageSource = jObj.getString("strImageSource");
            creativeCommonsConfirmed = jObj.getString("strCreativeCommonsConfirmed");
            dateModified = jObj.getString("dateModified");
            jsonOutputTxt.setText(
                    "mealName: " + mealName + "\n" +
                            "Category: " + category +  "\n" +
                            "area: " + area +  "\n" +
                            jsonHelper.formatIngredients(ingredients.toString()) +  "\n");
            noOfResultsTxt.setText(indent +1 +"/"+ jArray.length());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



    }

    // on API call calls onSuccess or error depending to connection
    @Override
    public void onSuccess(String result) {
        JSONResponse = result;
        if (!Objects.equals(result, "{\"meals\":null}")) {
            try {
                obj = new JSONObject(result);
                System.out.println(obj);
                jArray = obj.getJSONArray("meals");
                cycle();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            Toast.makeText(getApplicationContext(), "nothing matching", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onError(VolleyError error) {
        System.out.println("mealByIngredients error: "+error);
    }
}


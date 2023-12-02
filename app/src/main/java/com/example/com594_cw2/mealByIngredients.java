package com.example.com594_cw2;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class mealByIngredients extends AppCompatActivity {
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
    JSONHelper jsoNhelper = new JSONHelper();
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
                if(JSONResponse.isEmpty()){
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
                if(JSONResponse.isEmpty()){
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
                    indent = 0;
                    callVolley(url + ingredientTxt.getText() );
                }
            });

            saveMealsBtn.setOnClickListener(view -> {
                if(JSONResponse.isEmpty()){
                    Toast.makeText(getApplicationContext(), "retrieve something first", Toast.LENGTH_LONG).show();//display instructions
                }else{
                    jsoNhelper.stringToRoom(JSONResponse,mealDao);

                }
            });
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
                            "ingredients: " + ingredients +  "\n");
            noOfResultsTxt.setText(indent +1 +"/"+ jArray.length());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



    }

    public void callVolley(String newURL){
        RequestQueue queue = Volley.newRequestQueue(this);
        //creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newURL,
                response -> {
                    JSONResponse = response;
                    try {
                         obj = new JSONObject(response);
                         System.out.println(obj);
                         jArray = obj.getJSONArray("meals");
                        cycle();



                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }, error -> {

                });
        queue.add(stringRequest);
    }

}
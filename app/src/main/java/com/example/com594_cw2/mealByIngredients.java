package com.example.com594_cw2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class mealByIngredients extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

   //TODO refactor later
   String Meal;
    String DrinkAlternate;
    String Category;
    String Area;
    String MealThumb;
    String Tags;
    String Youtube;
    String Ingredient;
    String Measure;
    String Source;
    String ImageSource;
    String CreativeCommonsConfirmed;
    String DateModified;


    String mealName = "";
    String drinkAlternate = "";
    String category = "";
    String area = "";
    String mealthumb = "";
    String tag = "";
    String youtube  = "";
    ArrayList<String> ingredients = new ArrayList<String>();
    ArrayList<String> measures = new ArrayList<String>();

    String source = "";
    String imageSource = "";
    String creativeCommonsConfirmed = "";
    String dateModified = "";




    int indent =0;

    String ingredient = "chicken";
    String JSONResponse;
    private String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=";

    EditText ingredientTxt;

    Button retrieveMealsBtn;

    Button saveMealsBtn;

    ImageButton backBtn;

    ImageButton forwardBtn;

    TextView noOfResultsTxt;


    TextView jsonOutputTxt;


    MealDatabase mealDatabase;
    MealDao mealDao;
    JSONhelper jsoNhelper = new JSONhelper();
    JSONObject obj = null;
    JSONArray jArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_by_ingredients);

        mealDatabase = MealDatabase.getMealDatabase(getApplicationContext());
        mealDao = mealDatabase.mealDao();

        ingredientTxt = (EditText) findViewById(R.id.ingredientTxt);
        retrieveMealsBtn = (Button) findViewById(R.id.retrieveMealsBtn);
        saveMealsBtn = (Button) findViewById(R.id.saveMealsBtn);
        jsonOutputTxt = (TextView) findViewById(R.id.jsonOutputTxt);
        backBtn =  (ImageButton) findViewById(R.id.backBtn);
        forwardBtn = (ImageButton) findViewById(R.id.forwardBtn);
        noOfResultsTxt = (TextView) findViewById(R.id.noOfResultsTxt);



            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
                }
            });

            forwardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
                }
            });



            retrieveMealsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ingredientTxt.getText().length() == 0){
                        Toast.makeText(getApplicationContext(), "Enter something in the text box", Toast.LENGTH_LONG).show();//display instructions

                    }else{
                       // getData();
                        indent = 0;
                        callVolley(url + ingredientTxt.getText() );
                    }
                }
            });

            saveMealsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(JSONResponse.isEmpty()){
                        Toast.makeText(getApplicationContext(), "retrieve something first", Toast.LENGTH_LONG).show();//display instructions
                    }else{
                        jsoNhelper.stringToRoom(JSONResponse,mealDao);

                    }
                }
            });
    }

    public void cycle(){
        JSONObject jObj = null;
        try {
            ingredients.clear();
            measures.clear();
            jObj = jArray.getJSONObject(indent);
            mealName = jObj.getString("strMeal");
            drinkAlternate = jObj.getString("strDrinkAlternate");
            category = jObj.getString("strCategory");
            area = jObj.getString("strArea");
            mealthumb = jObj.getString("strMealThumb");
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
                            "Category: " + Category +  "\n" +
                            "area: " + area +  "\n" +
                            "ingredients: " + ingredients +  "\n");
            noOfResultsTxt.setText(indent +1 +"/"+ jArray.length());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



    }


//    private void getData() {
//        // RequestQueue initialized
//        mRequestQueue = Volley.newRequestQueue(this);
//
//        // String Request initialized
//        mStringRequest = new StringRequest(Request.Method.GET, url + ingredientTxt.getText(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                System.out.println(response.toString());
//                jsoNhelper.stringToRoom(response.toString(),mealDao);
//                System.out.println("Response :" + response.toString());
//                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i(TAG, "Error :" + error.toString());
//            }
//        });
//
//        mRequestQueue.add(mStringRequest);
//    }
    public void callVolley(String newURL){
        RequestQueue queue = Volley.newRequestQueue(this);
        //creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONResponse = response;
                        try {
                             obj = new JSONObject(response);
                             System.out.println(obj.toString());
                             jArray = obj.getJSONArray("meals");
                             //TODO create as its own method
                            cycle();
//                                JSONObject jObj = jArray.getJSONObject(0);
//                                mealName = jObj.getString("strMeal");
//                                drinkAlternate = jObj.getString("strDrinkAlternate");
//                                category = jObj.getString("strCategory");
//                                area = jObj.getString("strArea");
//                                mealthumb = jObj.getString("strMealThumb");
//                                tag = jObj.getString("strTags");
//                                youtube = jObj.getString("strYoutube");
//
//                                for (int x = 1; x <= 20; x++) {
//                                    ingredients.add(jObj.getString("strIngredient" + (x)));
//                                }
//
//                                for (int x = 1; x <= 20; x++) {
//                                    measures.add(jObj.getString("strMeasure" + (x)));
//                                }
//                                source = jObj.getString("strSource");
//                                imageSource = jObj.getString("strImageSource");
//                                creativeCommonsConfirmed = jObj.getString("strCreativeCommonsConfirmed");
//                                dateModified = jObj.getString("dateModified");
//                                jsonOutputTxt.append(
//                                        "mealName: " + mealName + "\n" +
//                                        "Category: " + Category +  "\n" +
//                                        "area: " + area +  "\n" +
//                                        "ingredients: " + ingredients +  "\n");
//


                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

}
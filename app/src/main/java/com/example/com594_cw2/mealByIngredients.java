package com.example.com594_cw2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class mealByIngredients extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    String ingredient = "chicken";
    private String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=";

    EditText ingredientTxt;

    Button retrieveMealsBtn;

    Button saveMealsBtn;


    MealDatabase mealDatabase;
    MealDao mealDao;
    JSONhelper jsoNhelper = new JSONhelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_by_ingredients);

        mealDatabase = MealDatabase.getMealDatabase(getApplicationContext());
        mealDao = mealDatabase.mealDao();

        ingredientTxt = (EditText) findViewById(R.id.ingredientTxt);
        retrieveMealsBtn = (Button) findViewById(R.id.retrieveMealsBtn);
        saveMealsBtn = (Button) findViewById(R.id.saveMealsBtn);





            retrieveMealsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ingredientTxt.getText().length() == 0){
                        Toast.makeText(getApplicationContext(), "Enter something in the text box", Toast.LENGTH_LONG).show();//display the response on screen

                    }else{
                        getData();
                    }
                }
            });
    }

    private void getData() {
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url + ingredientTxt.getText(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response.toString());
                jsoNhelper.stringToRoom(response.toString(),mealDao);
                System.out.println("Response :" + response.toString());
                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}
package com.example.com594_cw2;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class JSONHelper {


    public void stringToRoom(String json_string, MealDao mealDao){



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


        String mealName;
        String drinkAlternate;
        String category;
        String area;
        String mealThumbs;
        String tag;
        String youtube;
        ArrayList<String> ingredients;
        ArrayList<String> measures;

        String source;
        String imageSource;
        String creativeCommonsConfirmed;
        String dateModified;


        JSONObject jObj;
        JSONArray jArray;
        try {
            JSONObject jObjInitializer = new JSONObject(json_string);
            jArray = jObjInitializer.getJSONArray("meals");


            for (int i = 0; i < jArray.length(); i++) {
                jObj = jArray.getJSONObject(i);

                ingredients = new ArrayList<>();
                measures = new ArrayList<>();


                try {

                    if (jObj.has("Meal")) {
                        Meal = "Meal";
                        DrinkAlternate = "DrinkAlternate";
                        Category = "Category";
                        Area = "Area";
                        MealThumb = "MealThumb";
                        Tags = "Tags";
                        Youtube = "Youtube";
                        Ingredient = "Ingredient";
                        Measure = "Measure";
                        Source = "Source";
                        ImageSource = "ImageSource";
                        CreativeCommonsConfirmed = "CreativeCommonsConfirmed";
                    } else {

                        Meal = "strMeal";
                        DrinkAlternate = "strDrinkAlternate";
                        Category = "strCategory";
                        Area = "strArea";
                        MealThumb = "strMealThumb";
                        Tags = "strTags";
                        Youtube = "strYoutube";
                        Ingredient = "strIngredient";
                        Measure = "strMeasure";
                        Source = "strSource";
                        ImageSource = "strImageSource";
                        CreativeCommonsConfirmed = "strCreativeCommonsConfirmed";
                    }
                    DateModified = "dateModified";

                    mealName = jObj.getString(Meal);
                    drinkAlternate = jObj.getString(DrinkAlternate);
                    category = jObj.getString(Category);
                    area = jObj.getString(Area);
                    mealThumbs = jObj.getString(MealThumb);
                    tag = jObj.getString(Tags);
                    youtube = jObj.getString(Youtube);

                    for (int x = 1; x <= 20; x++) {
                        ingredients.add(jObj.getString(Ingredient + (x)));
                    }

                    for (int x = 1; x <= 20; x++) {
                        measures.add(jObj.getString(Measure + (x)));
                    }
                    source = jObj.getString(Source);
                    imageSource = jObj.getString(ImageSource);
                    creativeCommonsConfirmed = jObj.getString(CreativeCommonsConfirmed);
                    dateModified = jObj.getString(DateModified);


                } catch (JSONException e) {
                    System.out.println("ERROR  AT = " + i + jObj);
                    throw new RuntimeException(e);

                }


                MealEntity mealEntity = new MealEntity();
                mealEntity.setMeal(mealName);
                mealEntity.setDAlternate(drinkAlternate);
                mealEntity.setCategory(category);
                mealEntity.setArea(area);
                mealEntity.setMealThumb(mealThumbs);
                mealEntity.setTags(tag);
                mealEntity.setYoutube(youtube);
                mealEntity.setIngredient(ingredients);
                mealEntity.setMeasure(measures);
                mealEntity.setSource(source);
                mealEntity.setImageSource(imageSource);
                mealEntity.setCreativeCommonsConfirmed(creativeCommonsConfirmed);
                mealEntity.setDateModified(dateModified);

                System.out.println("call NO.= " + i + jObj);

                new Thread(() -> {
                    mealDao.storeMeal(mealEntity);
                    System.out.println(mealEntity.getMeal());
                    System.out.println(mealEntity.getDAlternate());
                    System.out.println(mealEntity.getCategory());
                    System.out.println(mealEntity.getArea());
                    System.out.println(mealEntity.getMealThumb());
                    System.out.println(mealEntity.getIngredient());
                    System.out.println(mealEntity.getMeasure());
                    System.out.println(mealEntity.getSource());
                    System.out.println(mealEntity.getCreativeCommonsConfirmed());
                    System.out.println(mealEntity.getDateModified());
                }).start();

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

    public String callVolley(String newURL,  Context context){

        RequestQueue queue = Volley.newRequestQueue(context);
        //creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newURL,
                response -> {

                }, error -> {

        });
        queue.add(stringRequest);
       return  stringRequest.toString();
    }

}

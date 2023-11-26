package com.example.com594_cw2;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONhelper {

    //MealDao mealDao;
   // MealDatabase mealDatabase;
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


        JSONObject jObj = null;
        JSONArray jArray = null;
        try {
            JSONObject jObjInitializer = new JSONObject(json_string);
            jArray = jObjInitializer.getJSONArray("meals");


            for (int i = 0; i < jArray.length(); i++) {
                jObj = jArray.getJSONObject(i);

                try {

                    //jObjInitializer = new JSONObject(json_string);
                    //JSONArray jArray = jObjInitializer.getJSONArray("meals");




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
                        DateModified = "dateModified";
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
                        DateModified = "dateModified";
                    }

                    mealName = jObj.getString(Meal);
                    drinkAlternate = jObj.getString(DrinkAlternate);
                    category = jObj.getString(Category);
                    area = jObj.getString(Area);
                    mealthumb = jObj.getString(MealThumb);
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
                    System.out.println("ERROR  AT = " + i + jObj.toString());
                    throw new RuntimeException(e);

                }


                MealEntity mealEntity = new MealEntity();
                mealEntity.setMeal(mealName);
                mealEntity.setdAlternate(drinkAlternate);
                mealEntity.setCategory(category);
                mealEntity.setArea(area);
                mealEntity.setMealThumb(mealthumb);
                mealEntity.setTags(tag);
                mealEntity.setYoutube(youtube);
                mealEntity.setIngredient(ingredients);
                mealEntity.setMeasure(measures);
                mealEntity.setSource(source);
                mealEntity.setImageSource(imageSource);
                mealEntity.setCreativeCommonsConfirmed(creativeCommonsConfirmed);
                mealEntity.setDateModified(dateModified);

                System.out.println("call NO.= " + i + jObj.toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mealDao.storeMeal(mealEntity);
                        System.out.println(mealEntity.meal);
                        System.out.println(mealEntity.dAlternate);
                        System.out.println(mealEntity.category);
                        System.out.println(mealEntity.area);
                        System.out.println(mealEntity.mealThumb);
                        System.out.println(mealEntity.getIngredient());
//                        System.out.println(mealEntity.getMeasure());
                        System.out.println(mealEntity.getSource());
                        System.out.println(mealEntity.getCreativeCommonsConfirmed());
                        System.out.println(mealEntity.getDateModified());
                    }
                }).start();
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

}

package com.example.com594_cw2;

import android.content.Context;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONhelper {

    //MealDao mealDao;
   // MealDatabase mealDatabase;
    public void stringToRoom(String json_string, MealDao mealDao){


        //TODO sort out JSON array
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
        try {
            System.out.println(json_string.charAt(13));
            jObj = new JSONObject(json_string);

            System.out.println(jObj.toString());

            if (jObj.has("Meal")) {
                 Meal= "Meal";
                 DrinkAlternate = "DrinkAlternate";
                 Category = "Category";
                 Area= "Area" ;
                 MealThumb = "MealThumb";
                 Tags = "Tags";
                 Youtube = "Youtube";
                 Ingredient = "Ingredient";
                 Measure = "Measure";
                 Source = "Source";
                 ImageSource = "ImageSource" ;
                 CreativeCommonsConfirmed = "CreativeCommonsConfirmed";
                 DateModified ="dateModified" ;
            } else  {

                Meal= "strMeal";
                DrinkAlternate = "strDrinkAlternate";
                Category = "strCategory";
                Area= "strArea" ;
                MealThumb = "strMealThumb";
                Tags = "strTags";
                Youtube = "strYoutube";
                Ingredient = "strIngredient";
                Measure = "strMeasure";
                Source = "strSource";
                ImageSource = "strImageSource" ;
                CreativeCommonsConfirmed = "strCreativeCommonsConfirmed";
                DateModified ="dateModified" ;
            }

            mealName = jObj.getString(Meal);
            drinkAlternate = jObj.getString(DrinkAlternate);
            category = jObj.getString(Category);
            area = jObj.getString(Area);
            mealthumb = jObj.getString(MealThumb);
            tag = jObj.getString(Tags);
            youtube = jObj.getString(Youtube);

            for(int i = 1; i <= 20; i++){
                ingredients.add(jObj.getString(Ingredient + (i)));
            }

            for(int i = 1; i <= 20; i++){
                measures.add(jObj.getString(Measure + (i)));
            }
            source = jObj.getString(Source);
            imageSource = jObj.getString(ImageSource);
            creativeCommonsConfirmed = jObj.getString(CreativeCommonsConfirmed);
            dateModified = jObj.getString(DateModified);



        } catch (JSONException e) {
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
                System.out.println(mealEntity.getMeasure());
                System.out.println(mealEntity.getSource());
                System.out.println(mealEntity.getCreativeCommonsConfirmed());
                System.out.println(mealEntity.getDateModified());
            }
        }).start();

    }

}

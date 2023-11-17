package com.example.com594_cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String json_string1 = "{" +
            "\"Meal\":\"Sweet and Sour Pork\",\n" +
            "\"DrinkAlternate\":null,\n" +
            "\"Category\":\"Pork\",\n" +
            "\"Area\":\"Chinese\",\n" +
            "\"MealThumb\":\"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/15294423" +
            "16.jpg\",\n" +
            "\"Tags\":\"Sweet\",\n" +
            "\"Youtube\":\"https:\\/\\/www.youtube.com\\/watch?v=mdaBIhgEAMo\",\n" +
            "\"Ingredient1\":\"Pork\",\n" +
            "\"Ingredient2\":\"Egg\",\n" +
            "\"Ingredient3\":\"Water\",\n" +
            "\"Ingredient4\":\"Salt\",\n" +
            "\"Ingredient5\":\"Sugar\",\n" +
            "\"Ingredient6\":\"Soy Sauce\",\n" +
            "\"Ingredient7\":\"Starch\",\n" +
            "\"Ingredient8\":\"Tomato Puree\",\n" +
            "\"Ingredient9\":\"Vinegar\",\n" +
            "\"Ingredient11\":\"\",\n" +
            "\"Ingredient12\":\"\",\n" +
            "\"Ingredient13\":\"\",\n" +
            "\"Ingredient14\":\"\",\n" +
            "\"Ingredient15\":\"\",\n" +
            "\"Ingredient16\":\"\",\n" +
            "\"Ingredient17\":\"\",\n" +
            "\"Ingredient18\":\"\",\n" +
            "\"Ingredient19\":\"\",\n" +
            "\"Ingredient20\":\"\",\n" +
            "\"Measure1\":\"200g\",\n" +
            "\"Measure2\":\"1\",\n" +
            "\"Measure3\":\"Dash\",\n" +
            "\"Measure4\":\"1\\/2 tsp\",\n" +
            "\"Measure5\":\"1 tsp \",\n" +
            "\"Measure6\":\"10g\",\n" +
            "\"Measure7\":\"10g\",\n" +
            "\"Measure8\":\"30g\",\n" +
            "\"Measure9\":\"10g\",\n" +
            "\"Measure10\":\"Dash\",\n" +
            "\"Measure11\":\"\",\n" +
            "\"Measure12\":\"\",\n" +
            "\"Measure13\":\"\",\n" +
            "\"Measure14\":\"\",\n" +
            "\"Measure15\":\"\",\n" +
            "\"Measure16\":\"\",\n" +
            "\"Measure17\":\"\",\n" +
            "\"Measure18\":\"\",\n" +
            "\"Measure19\":\"\",\n" +
            "\"Measure20\":\"\",\n" +
            "\"Source\":\"https:\\/\\/www.chinahighlights.com\\/travelguide\\/chinese\u0002food\\/cooking\\/sweet-sour-pork.htm\",\n" +
            "\"ImageSource\":null,\n" +
            "\"CreativeCommonsConfirmed\":null,\n" +
            "\"dateModified\":null" +
            "}";

    public static final String json_string2 ="{" +
            "\"Meal\":\"Chicken Marengo\",\n" +
            "\"DrinkAlternate\":null,\n" +
            "\"Category\":\"Chicken\",\n" +
            "\"Area\":\"French\",\n" +
            "\"MealThumb\":\"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/qpxvuq15" +
            "11798906.jpg\",\n" +
            "\"Tags\":null,\n" +
            "\"Youtube\":\"https:\\/\\/www.youtube.com\\/watch?v=U33HYUr-0Fw\",\n" +
            "\"Ingredient1\":\"Olive Oil\",\n" +
            "\"Ingredient2\":\"Mushrooms\",\n" +
            "\"Ingredient3\":\"Chicken Legs\",\n" +
            "\"Ingredient4\":\"Passata\",\n" +
            "\"Ingredient5\":\"Chicken Stock Cube\",\n" +
            "\"Ingredient6\":\"Black Olives\",\n" +
            "\"Ingredient7\":\"Parsley\",\n" +
            "\"Ingredient8\":\"\",\n" +
            "\"Ingredient9\":\"\",\n" +
            "\"Ingredient10\":\"\",\n" +
            "\"Ingredient11\":\"\",\n" +
            "\"Ingredient12\":\"\",\n" +
            "\"Ingredient13\":\"\",\n" +
            "\"Ingredient14\":\"\",\n" +
            "\"Ingredient15\":\"\",\n" +
            "\"Ingredient16\":\"\",\n" +
            "\"Ingredient17\":\"\",\n" +
            "\"Ingredient18\":\"\",\n" +
            "\"Ingredient19\":\"\",\n" +
            "\"Ingredient20\":\"\",\n" +
            "\"Measure1\":\"1 tbs\",\n" +
            "\"Measure2\":\"300g\",\n" +
            "\"Measure3\":\"4\",\n" +
            "\"Measure4\":\"500g\",\n" +
            "\"Measure5\":\"1\",\n" +
            "\"Measure6\":\"100g \",\n" +
            "\"Measure7\":\"Chopped\",\n" +
            "\"Measure8\":\"\",\n" +
            "\"Measure9\":\"\",\n" +
            "\"Measure10\":\"\",\n" +
            "\"Measure11\":\"\",\n" +
            "\"Measure12\":\"\",\n" +
            "\"Measure13\":\"\",\n" +
            "\"Measure14\":\"\",\n" +
            "\"Measure15\":\"\",\n" +
            "\"Measure16\":\"\",\n" +
            "\"Measure17\":\"\",\n" +
            "\"Measure18\":\"\",\n" +
            "\"Measure19\":\"\",\n" +
            "\"Measure20\":\"\",\n" +
            "\"Source\":\"https:\\/\\/www.bbcgoodfood.com\\/recipes\\/3146682\\/chicken\u0002marengo\",\n" +
            "\"ImageSource\":null,\n" +
            "\"CreativeCommonsConfirmed\":null,\n" +
            "\"dateModified\":null" +
            "}";

            MealDatabase mealDatabase;
    MealDao mealDao;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // room database
        mealDatabase = MealDatabase.getMealDatabase(getApplicationContext());
        mealDao = mealDatabase.mealDao();


        // SQLite database for "Add Meals to DB"

        JSONObject jObj = null;
        try {
            jObj = new JSONObject(json_string2);
            mealName = jObj.getString("Meal");
            drinkAlternate = jObj.getString("DrinkAlternate");
            category = jObj.getString("Category");
            area = jObj.getString("Area");
            mealthumb = jObj.getString("MealThumb");
            tag = jObj.getString("Tags");
            youtube = jObj.getString("Youtube");

            for(int i = 1; i <= 20; i++){
                 ingredients.add(jObj.getString("Ingredient" + (i)));
             }

            for(int i = 1; i <= 20; i++){
                measures.add(jObj.getString("Measure" + (i)));
            }
            source = jObj.getString("Source");
            imageSource = jObj.getString("ImageSource");
            creativeCommonsConfirmed = jObj.getString("CreativeCommonsConfirmed");
            dateModified = jObj.getString("dateModified");



            System.out.println("Meal Name = " + mealName);
            System.out.println("drinkAlternate = " + drinkAlternate);
            System.out.println("area = " + area);
            System.out.println("mealthumb = " + mealthumb);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }



    public void storeData(View view){
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
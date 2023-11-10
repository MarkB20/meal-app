package com.example.com594_cw2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_meal")
public class MealEntity {
    @PrimaryKey(autoGenerate = true) Integer mealID;
    @ColumnInfo(name = "Meal") String meal;
    @ColumnInfo(name = "DrinkAlternate") String dAlternate;
    @ColumnInfo(name = "Category") String category;
    @ColumnInfo(name = "Area") String area;
    @ColumnInfo(name = "MealThumb") String mealThumb;

    public String getdAlternate() {
        return dAlternate;
    }

    public void setdAlternate(String dAlternate) {
        this.dAlternate = dAlternate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMealThumb() {
        return mealThumb;
    }

    public void setMealThumb(String mealThumb) {
        this.mealThumb = mealThumb;
    }

    public Integer getMealID() {
        return mealID;
    }

    public void setMealID(Integer mealID) {
        this.mealID = mealID;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }
}

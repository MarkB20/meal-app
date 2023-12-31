package com.example.com594_cw2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "table_meal")
public class MealEntity {
    @PrimaryKey(autoGenerate = true) private Integer mealID;
    @ColumnInfo(name = "Meal") private String meal;
    @ColumnInfo(name = "DrinkAlternate") private String dAlternate;
    @ColumnInfo(name = "Category") private String category;
    @ColumnInfo(name = "Area") private String area;
    @ColumnInfo(name = "MealThumb") private String mealThumb;

    @ColumnInfo(name = "Tags") private String tags ;
    @ColumnInfo(name = "Youtube") private String youtube;
    @ColumnInfo(name = "Ingredient") private List<String> ingredient;

    @ColumnInfo(name = "Measure") private List<String> measure;
    @ColumnInfo(name = "Source") private String source ;
    @ColumnInfo(name = "ImageSource") private String imageSource ;
    @ColumnInfo(name = "CreativeCommonsConfirmed") private String creativeCommonsConfirmed  ;
    @ColumnInfo(name = "DateModified") private String dateModified ;



    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public void setMeasure(List<String> measure) {
        this.measure = measure;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public void setCreativeCommonsConfirmed(String creativeCommonsConfirmed) {
        this.creativeCommonsConfirmed = creativeCommonsConfirmed;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getTags() {
        return tags;
    }

    public String getYoutube() {
        return youtube;
    }

    public List<String> getMeasure() {
        return measure;
    }

    public String getSource() {
        return source;
    }

    public String getImageSource() {
        return imageSource;
    }

    public String getCreativeCommonsConfirmed() {
        return creativeCommonsConfirmed;
    }

    public String getDateModified() {
        return dateModified;
    }

    public String getDAlternate() {
        return dAlternate;
    }

    public void setDAlternate(String dAlternate) {
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

    public List<String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<String> ingredient) {
        this.ingredient = ingredient;
    }
}

package com.example.com594_cw2;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {

    // convert class that allows the use of arrays
    @TypeConverter
    public static List<String> fromString(String value) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);

    }

    //allows the use of arrays in the database
    @TypeConverter
    public static String fromStringArray(String[] array) {
        StringBuilder result = new StringBuilder();
        for (String item : array) {
            result.append(item).append(",");
        }
        return result.toString();
    }

    @TypeConverter
    public static String[] toStringArray(String value) {
        return value.split(",");
    }

}

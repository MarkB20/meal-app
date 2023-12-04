package com.example.com594_cw2;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import android.util.Log;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {

    @Test
    public void buttonTest() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            // Simulate button click
            onView(withId(R.id.searchIngredient_btn)).perform(click());


            // Check if the expected activity is launched
            scenario.onActivity(activity -> {
                String actualClassName = activity.getClass().getName();
                assertThat("Unexpected activity launched", actualClassName, equalTo("com.example.com594_cw2.mealByIngredients"));
            });
        }
    }
}
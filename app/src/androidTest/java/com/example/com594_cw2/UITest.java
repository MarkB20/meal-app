package com.example.com594_cw2;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertThat;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {


    @Rule
    public ActivityScenarioRule<SearchAll> mActivityScenarioRule =
            new ActivityScenarioRule<>(SearchAll.class);
    @Test
    public void networkTest() {



    }
}
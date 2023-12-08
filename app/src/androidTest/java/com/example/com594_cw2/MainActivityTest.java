package com.example.com594_cw2;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        // Set a longer timeout
        IdlingPolicies.setMasterPolicyTimeout(20, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(20, TimeUnit.SECONDS);
    }

    // TODO Part test 1
    @Test
    public void mainActivityTestAddToDB() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.add_btn), withText("Add Meals to  DB"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.search_btn), withText("Search for Meals"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.mealEdt),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("chicken"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.searchBtn), withText("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton3.perform(click());


        ViewInteraction textView = onView(
                allOf(withId(R.id.mealOutput), withText("Meal: Chicken Marengo\nIngredients:\n- Olive Oil\n- Mushrooms\n- Chicken Legs\n- Passata\n- Chicken Stock Cube\n- Black Olives\n- Parsley\n"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        int count = 0;
        int check = 0;
        int maxTries = 3;
        while(true) {

            try {
                textView.check(matches(withText("Meal: Chicken Marengo\nIngredients:\n- Olive Oil\n- Mushrooms\n- Chicken Legs\n- Passata\n- Chicken Stock Cube\n- Black Olives\n- Parsley\n")));
                // break out of loop, or return, on success

            } catch (Exception e) {
                // handle exception
                System.out.println("Attempt " + count + " failed: " + e.getMessage());
                if (++count == maxTries) throw e;
            }
            if(check == count){
                check++;
            }else {
                break;
            }
        }

    }

    @Test
    public void mainActivityTestIngredients() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.searchIngredient_btn), withText("Search for Meals By Ingredient"),
                        childAtPositionSearchIngredient(
                                childAtPositionSearchIngredient(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(androidx.appcompat.R.id.action_bar_root),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPositionSearchIngredient(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void mainActivityTest_Search() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.search_btn), withText("Search for Meals"),
                        childAtPositionSearch(
                                childAtPositionSearch(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(androidx.appcompat.R.id.action_bar_root),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPositionSearch(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void mainActivityTestWebSearch() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.webSearch_btn), withText("Web Name Search"),
                        childAtPositionWebSearch(
                                childAtPositionWebSearch(
                                        withId(android.R.id.content)
                                )
                        ),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(androidx.appcompat.R.id.action_bar_root),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPositionWebSearch(
            final Matcher<View> parentMatcher) {

        return new TypeSafeMatcher<>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + 0 + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(0));
            }
        };
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }




}

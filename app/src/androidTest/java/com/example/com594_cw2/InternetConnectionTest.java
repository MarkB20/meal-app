package com.example.com594_cw2;

import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.android.volley.VolleyError;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class InternetConnectionTest  implements Helper.VolleyCallback  {
    String JSONResponse;
    boolean rightResult;
    final String EXPECTED_RESPONSE = "{\"meals\":[{\"idMeal\":\"52771\",\"strMeal\":\"Spicy Arrabiata Penne\",\"strDrinkAlternate\":null,\"strCategory\":\"Vegetarian\",\"strArea\":\"Italian\",\"strInstructions\":\"Bring a large pot of water to a boil. Add kosher salt to the boiling water, then add the pasta. Cook according to the package instructions, about 9 minutes.\\r\\nIn a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste. Bring to a boil and cook for 5 minutes. Remove from the heat and add the chopped basil.\\r\\nDrain the pasta and add it to the sauce. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm.\",\"strMealThumb\":\"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/ustsqw1468250014.jpg\",\"strTags\":\"Pasta,Curry\",\"strYoutube\":\"https:\\/\\/www.youtube.com\\/watch?v=1IszT_guI08\",\"strIngredient1\":\"penne rigate\",\"strIngredient2\":\"olive oil\",\"strIngredient3\":\"garlic\",\"strIngredient4\":\"chopped tomatoes\",\"strIngredient5\":\"red chile flakes\",\"strIngredient6\":\"italian seasoning\",\"strIngredient7\":\"basil\",\"strIngredient8\":\"Parmigiano-Reggiano\",\"strIngredient9\":\"\",\"strIngredient10\":\"\",\"strIngredient11\":\"\",\"strIngredient12\":\"\",\"strIngredient13\":\"\",\"strIngredient14\":\"\",\"strIngredient15\":\"\",\"strIngredient16\":null,\"strIngredient17\":null,\"strIngredient18\":null,\"strIngredient19\":null,\"strIngredient20\":null,\"strMeasure1\":\"1 pound\",\"strMeasure2\":\"1\\/4 cup\",\"strMeasure3\":\"3 cloves\",\"strMeasure4\":\"1 tin \",\"strMeasure5\":\"1\\/2 teaspoon\",\"strMeasure6\":\"1\\/2 teaspoon\",\"strMeasure7\":\"6 leaves\",\"strMeasure8\":\"spinkling\",\"strMeasure9\":\"\",\"strMeasure10\":\"\",\"strMeasure11\":\"\",\"strMeasure12\":\"\",\"strMeasure13\":\"\",\"strMeasure14\":\"\",\"strMeasure15\":\"\",\"strMeasure16\":null,\"strMeasure17\":null,\"strMeasure18\":null,\"strMeasure19\":null,\"strMeasure20\":null,\"strSource\":null,\"strImageSource\":null,\"strCreativeCommonsConfirmed\":null,\"dateModified\":null}]}";

    CountDownLatch latch;
    private Helper helper;

    @Before
    public void setUp() {
        helper = new Helper();
    }


    @Test
    public void testInternetConnection() {
        // Get the application context using InstrumentationRegistry
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Check if there is an active network connection
        assertTrue(isNetworkAvailable(context));
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        return false;
    }





    @Test
    public void callVolley_SuccessfulRequest_CallsOnSuccess() throws InterruptedException {
        // Arrange
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata";
        latch = new CountDownLatch(1); // Use class-level latch

        helper.callVolley(url, InstrumentationRegistry.getInstrumentation().getTargetContext(), this);

        // Wait for the callback to be invoked
        latch.await();

        // Now, you can make assertions
        assertTrue(rightResult);
    }

    @Override
    public void onSuccess(String result) {
        latch.countDown();

        JSONResponse = result;
        System.out.println(JSONResponse+"\n"+ result+ "\n");
        if(JSONResponse.equals(EXPECTED_RESPONSE)){
            rightResult = true;

        }else{
            rightResult = false;
            System.out.println("Success: Expected: "+ EXPECTED_RESPONSE + "\n"
                    + "Actual " + JSONResponse);
        }

    }

    @Override
    public void onError(VolleyError error) {
        latch.countDown();


        rightResult =false;
        System.out.println("Failed:"+error + " Expected: "+ EXPECTED_RESPONSE + "\n"
                + "Actual " + JSONResponse);
    }
}
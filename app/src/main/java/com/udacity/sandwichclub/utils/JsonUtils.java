package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        Log.i("parseSandwhichJson" ,"parseSandwhichJson Started");

        try {

            JSONObject sandwhichJSON = new JSONObject(json);
            JSONObject name = sandwhichJSON.getJSONObject("name");
            String mainname = name.getString("mainName");
            sandwich.setMainName(mainname);
            List<String> alsoKnowAs = new ArrayList<>();
            JSONArray AKA = name.getJSONArray("alsoKnownAs");
            for (int i=0; i < AKA.length(); i++) {
                String AKAName = AKA.optString(i,"");
                alsoKnowAs.add(AKAName);
            }
            sandwich.setAlsoKnownAs(alsoKnowAs);

            String placeOfOrigin = sandwhichJSON.getString("placeOfOrigin");
            sandwich.setPlaceOfOrigin(placeOfOrigin);

            String description = sandwhichJSON.optString("description");
            sandwich.setDescription(description);

            String imageUrl = sandwhichJSON.optString("image");
            sandwich.setImage(imageUrl);

            JSONArray ingredientJA = sandwhichJSON.optJSONArray("ingredients");
            List<String> Ingredients = new ArrayList<>();
            for (int i=0; i < ingredientJA.length(); i++) {
                String ing = ingredientJA.optString(i);
                Ingredients.add(ing);
            }
            sandwich.setIngredients(Ingredients);


            Log.i("parseSandwhichJson" ,"Try Ends...");
        } catch (JSONException e) {

            Log.i("parseSandwhichJson" ,"Exception");
            e.printStackTrace();
            return null;
        }

        Log.i("parseSandwhichJson" ,"Returning");
        return  sandwich;
    }
}

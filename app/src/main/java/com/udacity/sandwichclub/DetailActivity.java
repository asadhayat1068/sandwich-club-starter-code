package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent

            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {

            Log.i("sandwich is null" ,"NULL SANDWICH");
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        ImageView ingredientsIv = findViewById(R.id.image_iv);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        Log.i("IMage url",sandwich.getImage());
        setTitle(sandwich.getMainName());

        TextView TVAlsoKnownAs = (TextView) findViewById(R.id.also_known_tv);
        TVAlsoKnownAs.setText(sandwich.getAlsoKnownAs().toString());

        TextView TVPlaceOfOrigin = (TextView) findViewById(R.id.origin_tv);
        TVPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());

        TextView TVDescription = (TextView) findViewById(R.id.description_tv);
        TVDescription.setText(sandwich.getDescription());

        TextView TVIngredients = (TextView) findViewById(R.id.ingredients_tv);
        TVIngredients.setText(sandwich.getIngredients().toString());
    }
}

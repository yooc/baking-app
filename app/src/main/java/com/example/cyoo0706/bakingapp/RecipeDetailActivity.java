package com.example.cyoo0706.bakingapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.cyoo0706.bakingapp.data.Recipe;

import butterknife.BindView;

public class RecipeDetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = RecipeDetailActivity.class.getSimpleName();
    private static final String RECIPE_EXTRA = "Selected Recipe";

    Recipe mSelectedRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mSelectedRecipe = this.getIntent().getParcelableExtra(RECIPE_EXTRA);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_EXTRA, mSelectedRecipe);

        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.recipe_detail_container, recipeDetailFragment)
                .commit();
    }
}

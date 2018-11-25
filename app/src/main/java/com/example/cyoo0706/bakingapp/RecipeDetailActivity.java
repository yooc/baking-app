package com.example.cyoo0706.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cyoo0706.bakingapp.data.Recipe;
import com.example.cyoo0706.bakingapp.data.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity implements StepAdapter.StepAdapterOnClickHandler {

    private static final String LOG_TAG = RecipeDetailActivity.class.getSimpleName();
    private static final String RECIPE_EXTRA = "Selected Recipe";
    private static final String STEP_EXTRA = "Selected Step";

    @BindView(R.id.ingredients_rv)
    RecyclerView mIngredientsRecyclerView;
    @BindView(R.id.steps_overview_rv)
    RecyclerView mStepsListRecyclerView;

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

    @Override
    public void onClick(Step step) {
        Context context = this;
        Class destinationActivity = StepDetailActivity.class;
        Intent intent = new Intent(context, destinationActivity);
        intent.putExtra(RECIPE_EXTRA, mSelectedRecipe);
        intent.putExtra(STEP_EXTRA, step);
        startActivity(intent);
    }
}

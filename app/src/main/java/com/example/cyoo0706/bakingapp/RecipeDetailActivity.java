package com.example.cyoo0706.bakingapp;

import android.content.Context;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        Recipe selectedRecipe = this.getIntent().getParcelableExtra(RECIPE_EXTRA);

        IngredientAdapter ingredientsAdapter = new IngredientAdapter(selectedRecipe);
        mIngredientsRecyclerView.setAdapter(ingredientsAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this,
                2,
                GridLayoutManager.VERTICAL,
                false
        );
        mIngredientsRecyclerView.setLayoutManager(gridLayoutManager);

        StepAdapter stepAdapter = new StepAdapter(selectedRecipe, this);
        mStepsListRecyclerView.setAdapter(stepAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mStepsListRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(Step step) {
        Context context = this;
        Class destinationActivity = StepDetailActivity.class;
        Intent intent = new Intent(context, destinationActivity);
        intent.putExtra(STEP_EXTRA, step);
        startActivity(intent);
    }
}

package com.example.cyoo0706.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cyoo0706.bakingapp.data.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler{

    private static final String LOG_TAG = RecipeDetailActivity.class.getSimpleName();
    private static final String RECIPE_EXTRA = "Selected Recipe";

    @BindView(R.id.ingredients_rv)
    RecyclerView mIngredientsRecyclerView;
    @BindView(R.id.steps_list_rv)
    RecyclerView mStepsListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        IngredientAdapter ingredientsAdapter = new IngredientAdapter((Recipe) this.getIntent().getParcelableExtra(RECIPE_EXTRA));
        mIngredientsRecyclerView.setAdapter(ingredientsAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(
                this,
                2,
                GridLayoutManager.VERTICAL,
                false
        );
        mIngredientsRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(Recipe recipe) {

    }
}

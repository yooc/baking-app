package com.example.cyoo0706.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cyoo0706.bakingapp.data.Recipe;
import com.example.cyoo0706.bakingapp.data.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailFragment extends Fragment implements StepAdapter.StepAdapterOnClickHandler{

    private static final String LOG_TAG = RecipeDetailFragment.class.getSimpleName();
    private static final String RECIPE_EXTRA = "Selected Recipe";
    private static final String STEP_EXTRA = "Selected Step";

    @BindView(R.id.ingredients_rv)
    RecyclerView mIngredientsRecyclerView;
    @BindView(R.id.steps_overview_rv)
    RecyclerView mStepsListRecyclerView;

    Recipe mSelectedRecipe;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, rootView);

        mSelectedRecipe = getArguments().getParcelable(RECIPE_EXTRA);

        IngredientAdapter ingredientsAdapter = new IngredientAdapter(mSelectedRecipe);
        mIngredientsRecyclerView.setAdapter(ingredientsAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
        );
        mIngredientsRecyclerView.setLayoutManager(gridLayoutManager);

        StepAdapter stepAdapter = new StepAdapter(mSelectedRecipe, LOG_TAG, this);
        mStepsListRecyclerView.setAdapter(stepAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mStepsListRecyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

    @Override
    public void onClick(Step step) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_EXTRA, mSelectedRecipe);
        bundle.putParcelable(STEP_EXTRA, step);
    }
}

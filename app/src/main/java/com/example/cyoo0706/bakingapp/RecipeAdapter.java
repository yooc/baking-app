package com.example.cyoo0706.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cyoo0706.bakingapp.data.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    private Recipe[] mRecipeData;

    public RecipeAdapter() {

    }

    @Override
    public RecipeAdapter.RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int recipeListItem = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(recipeListItem, null);

        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mRecipeData == null) {
            return 0;
        }
        return mRecipeData.length;
    }

    public void setRecipeData(){

    }

    class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RecipeAdapterViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

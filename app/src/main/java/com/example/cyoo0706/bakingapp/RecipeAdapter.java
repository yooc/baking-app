package com.example.cyoo0706.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cyoo0706.bakingapp.data.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> mRecipeData;
    final private RecipeAdapterOnClickHandler mClickHandler;

    public interface RecipeAdapterOnClickHandler {
        void onClick(Recipe recipe);
    }

    public RecipeAdapter(RecipeAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int recipeListItem = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(recipeListItem, null);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        String name = mRecipeData.get(position).getName();
        holder.mRecipeNameTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        if (mRecipeData == null) {
            return 0;
        }
        return mRecipeData.size();
    }

    public void setRecipeData(List<Recipe> recipeData){
        mRecipeData = recipeData;
        notifyDataSetChanged();
    }

    class RecipeViewHolder extends ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipe_name_tv)
        TextView mRecipeNameTextView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Recipe selectedRecipe = mRecipeData.get(position);
            mClickHandler.onClick(selectedRecipe);
        }
    }
}

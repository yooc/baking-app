package com.example.cyoo0706.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cyoo0706.bakingapp.data.Ingredient;
import com.example.cyoo0706.bakingapp.data.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private Recipe mSelectedRecipe;
    private List<Ingredient> mIngredientData;

    public IngredientAdapter(Recipe recipe) {
        mSelectedRecipe = recipe;
        mIngredientData = mSelectedRecipe.getIngredients();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int recipeListItem = R.layout.ingredient_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(recipeListItem, null);

        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        String ingredient = mIngredientData.get(position).getIngredient();
        String quantity = mIngredientData.get(position).getQuantity();
        String measure = mIngredientData.get(position).getMeasure();

        holder.mIngredientNameTextView.setText(ingredient);
        holder.mQuantityTextView.setText(quantity);
        holder.mMeasureTextView.setText(measure);
    }

    @Override
    public int getItemCount() {
        if (mIngredientData == null) {
            return 0;
        }
        return mIngredientData.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredient_tv)
        TextView mIngredientNameTextView;
        @BindView(R.id.quantity_tv)
        TextView mQuantityTextView;
        @BindView(R.id.measure_tv)
        TextView mMeasureTextView;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

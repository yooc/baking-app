package com.example.cyoo0706.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cyoo0706.bakingapp.data.Recipe;
import com.example.cyoo0706.bakingapp.data.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
    private Recipe mSelectedRecipe;
    private List<Step> mStepData;

    public StepAdapter(Recipe recipe) {
        mSelectedRecipe = recipe;
        mStepData = mSelectedRecipe.getSteps();
    }

    @NonNull
    @Override
    public StepAdapter.StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int recipeListItem = R.layout.step_overview_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(recipeListItem, null);

        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapter.StepViewHolder holder, int position) {
        String overviewDescription = mStepData.get(position).getShortDescription();
        holder.mStepOverviewDescriptionTextView.setText(overviewDescription);
    }

    @Override
    public int getItemCount() {
        if (mStepData == null) {
            return 0;
        }
        return mStepData.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.overview_short_description_tv)
        TextView mStepOverviewDescriptionTextView;

        public StepViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

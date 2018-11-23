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

public class StepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Step> mStepData;
    private Recipe mSelectedRecipe;
    private Step mSelectedStep;
    final private String mOriginActivity;
    final private StepAdapterOnClickHandler mClickHandler;

    public interface StepAdapterOnClickHandler {
        void onClick(Step step);
    }

    public StepAdapter(Recipe recipe, String originActivity, StepAdapterOnClickHandler clickHandler) {
        mSelectedRecipe = recipe;
        mStepData = mSelectedRecipe.getSteps();
        mOriginActivity = originActivity;
        mClickHandler = clickHandler;
    }

    public StepAdapter(Recipe recipe, Step step, String originActivity) {
        mSelectedRecipe = recipe;
        mStepData = mSelectedRecipe.getSteps();
        mSelectedStep = step;
        mOriginActivity = originActivity;
        mClickHandler = null;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view;
        if (mOriginActivity.equals(RecipeDetailActivity.class.getSimpleName())) {
            int stepOverviewListItem = R.layout.step_overview_list_item;
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(stepOverviewListItem, null);
            return new StepOverviewViewHolder(view);
        } else {
            int stepDetailListItem = R.layout.step_detail_list_item;
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(stepDetailListItem, null);
            return new StepDetailViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mOriginActivity.equals(RecipeDetailActivity.class.getSimpleName())) {
            String overviewDescription = mStepData.get(position).getShortDescription();

            StepOverviewViewHolder view = (StepOverviewViewHolder) holder;
            view.mStepOverviewDescriptionTextView.setText(overviewDescription);
        } else {
            String shortDescription = mSelectedStep.getShortDescription();
            String longDescription = mSelectedStep.getLongDescription();

            StepDetailViewHolder view = (StepDetailViewHolder) holder;
            view.mStepDetailShortDescriptionTextView.setText(shortDescription);
            view.mStepDetailLongDescriptionTextView.setText(longDescription);
        }

    }

    @Override
    public int getItemCount() {
        if (mOriginActivity.equals(RecipeDetailActivity.class.getSimpleName())) {
            if (mStepData == null) {
                return 0;
            }
            return mStepData.size();
        } else {
            if (mSelectedStep == null) {
                return 0;
            }
            return 1;
        }
    }

    class StepOverviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.overview_short_description_tv)
        TextView mStepOverviewDescriptionTextView;

        public StepOverviewViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Step step = mStepData.get(position);
            mClickHandler.onClick(step);
        }
    }

    class StepDetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.detail_short_description_tv)
        TextView mStepDetailShortDescriptionTextView;
        @BindView(R.id.long_description_tv)
        TextView mStepDetailLongDescriptionTextView;

        public StepDetailViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

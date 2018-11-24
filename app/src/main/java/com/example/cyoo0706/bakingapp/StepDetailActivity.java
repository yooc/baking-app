package com.example.cyoo0706.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cyoo0706.bakingapp.data.Recipe;
import com.example.cyoo0706.bakingapp.data.Step;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailActivity extends AppCompatActivity {
    private static final String LOG_TAG = StepDetailActivity.class.getSimpleName();
    private static final String RECIPE_EXTRA = "Selected Recipe";
    private static final String STEP_EXTRA = "Selected Step";

    @BindView(R.id.steps_detail_rv)
    RecyclerView mRecyclerView;
    SimpleExoPlayer mExoPlayer;
    @BindView(R.id.playerView)
    PlayerView mPlayerView;

    Recipe mSelectedRecipe;
    Step mSelectedStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);

        mSelectedRecipe = this.getIntent().getParcelableExtra(RECIPE_EXTRA);
        mSelectedStep = this.getIntent().getParcelableExtra(STEP_EXTRA);

        StepAdapter adapter = new StepAdapter(mSelectedRecipe, mSelectedStep, LOG_TAG);
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            mPlayerView.setPlayer(mExoPlayer);
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                    this,
                    Util.getUserAgent(this, "BakingApp")
            );
            MediaSource source = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(mSelectedStep.getVideoUrl()));
            mExoPlayer.prepare(source);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Context context = this;
                Class destinationActivity = RecipeDetailActivity.class;
                Intent intent = new Intent(context, destinationActivity);
                intent.putExtra(RECIPE_EXTRA, mSelectedRecipe);
                intent.putExtra(STEP_EXTRA, mSelectedStep);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }
}

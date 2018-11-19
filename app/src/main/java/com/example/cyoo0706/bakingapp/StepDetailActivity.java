package com.example.cyoo0706.bakingapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);

        Recipe selectedRecipe = this.getIntent().getParcelableExtra(RECIPE_EXTRA);
        Step selectedStep = this.getIntent().getParcelableExtra(STEP_EXTRA);

        StepAdapter adapter = new StepAdapter(selectedRecipe, selectedStep, LOG_TAG);
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
                    .createMediaSource(Uri.parse(selectedStep.getVideoUrl()));
            mExoPlayer.prepare(source);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }
}

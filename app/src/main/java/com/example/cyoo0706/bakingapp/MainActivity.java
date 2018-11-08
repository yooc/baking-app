package com.example.cyoo0706.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recipe_rv) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        RecipeAdapter adapter = new RecipeAdapter();
        mRecyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(
                this,
                1,
                GridLayoutManager.VERTICAL,
                false
        );
        mRecyclerView.setLayoutManager(layoutManager);
    }
}

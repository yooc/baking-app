package com.example.cyoo0706.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.cyoo0706.bakingapp.data.Recipe;
import com.example.cyoo0706.bakingapp.data.RecipeApi;
import com.example.cyoo0706.bakingapp.data.RetrofitClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String RECIPE_EXTRA = "Selected Recipe";

    @BindView(R.id.recipe_rv)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final RecipeAdapter adapter = new RecipeAdapter(this);
        mRecyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(
                this,
                1,
                GridLayoutManager.VERTICAL,
                false
        );
        mRecyclerView.setLayoutManager(layoutManager);

        if (isNetworkAvailable(this)) {
            Retrofit client = RetrofitClient.getRetrofitInstance();
            RecipeApi api = client.create(RecipeApi.class);

            Call<List<Recipe>> call = api.getAllRecipes();

            call.enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    if (response.isSuccessful()) {
                        List<Recipe> recipeList = response.body();
                        adapter.setRecipeData(recipeList);
                    } else {
                        Log.d(LOG_TAG, "Empty response");
                    }
                }

                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    Log.d(LOG_TAG, "Service failure");
                }
            });
        } else {
            Toast.makeText(this, "No network available.", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    @Override
    public void onClick(Recipe recipe) {
        Context context = this;
        Class destinationActivity = RecipeDetailActivity.class;
        Intent intent = new Intent(context, destinationActivity);
        intent.putExtra(RECIPE_EXTRA, recipe);
        startActivity(intent);
    }
}

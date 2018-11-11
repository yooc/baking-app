package com.example.cyoo0706.bakingapp.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApi {
    @GET("baking.json")
    Call<List<Recipe>> getAllRecipes();
}

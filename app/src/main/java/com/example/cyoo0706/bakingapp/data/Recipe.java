package com.example.cyoo0706.bakingapp.data;

import com.google.gson.annotations.SerializedName;

public class Recipe {
    private int id;
    private String name;
    private Ingredient[] ingredients;
    private Step[] steps;
    private String servings;

    public Recipe(int id, String name, Ingredient[] ingredients, Step[] steps, String servings) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public Step[] getSteps() {
        return steps;
    }

    public void setSteps(Step[] steps) {
        this.steps = steps;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    private class Ingredient {
        private String ingredient;
        private String measure;
        private String quantity;

        public Ingredient(String ingredient, String measure, String quantity) {
            this.ingredient = ingredient;
            this.measure = measure;
            this.quantity = quantity;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }

    private class Step {
        private int id;
        private String shortDescription;
        @SerializedName("description")
        private String longDescription;
        @SerializedName("videoURL")
        private String videoUrl;

        public Step(int id, String shortDescription, String longDescription, String videoUrl) {
            this.id = id;
            this.shortDescription = shortDescription;
            this.longDescription = longDescription;
            this.videoUrl = videoUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getLongDescription() {
            return longDescription;
        }

        public void setLongDescription(String longDescription) {
            this.longDescription = longDescription;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }
    }
}

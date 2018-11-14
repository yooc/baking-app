package com.example.cyoo0706.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
    private String ingredient;
    private String measure;
    private String quantity;

    public Ingredient(String ingredient, String measure, String quantity) {
        this.ingredient = ingredient;
        this.measure = measure;
        this.quantity = quantity;
    }

    protected Ingredient(Parcel in) {
        ingredient = in.readString();
        measure = in.readString();
        quantity = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ingredient);
        parcel.writeString(measure);
        parcel.writeString(quantity);
    }
}

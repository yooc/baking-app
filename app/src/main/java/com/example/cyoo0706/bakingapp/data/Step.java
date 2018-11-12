package com.example.cyoo0706.bakingapp.data;

import com.google.gson.annotations.SerializedName;

public class Step {
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

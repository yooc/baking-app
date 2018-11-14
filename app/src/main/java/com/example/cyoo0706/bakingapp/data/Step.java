package com.example.cyoo0706.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Step implements Parcelable {
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

    protected Step(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        longDescription = in.readString();
        videoUrl = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(shortDescription);
        parcel.writeString(longDescription);
        parcel.writeString(videoUrl);
    }
}

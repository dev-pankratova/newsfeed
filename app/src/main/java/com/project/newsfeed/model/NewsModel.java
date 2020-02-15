package com.project.newsfeed.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewsModel implements Parcelable {
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;
    @SerializedName("urlToImage")
    public String urlImage;
    @SerializedName("content")
    public String content;

    public NewsModel(String title, String description, String url, String urlImage, String content) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlImage = urlImage;
        this.content = content;
    }

    protected NewsModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlImage = in.readString();
        content = in.readString();
    }

    public static final Creator<NewsModel> CREATOR = new Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel in) {
            return new NewsModel(in);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(urlImage);
        dest.writeString(content);
    }
}
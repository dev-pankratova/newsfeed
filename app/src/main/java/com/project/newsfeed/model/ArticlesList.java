package com.project.newsfeed.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesList {
    @SerializedName("articles")
    public List<NewsModel> articles;

    public ArticlesList(List<NewsModel> articles) {
        this.articles = articles;
    }

    public List<NewsModel> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsModel> articles) {
        this.articles = articles;
    }
}

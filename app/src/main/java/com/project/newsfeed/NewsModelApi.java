package com.project.newsfeed;

import com.project.newsfeed.model.ArticlesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsModelApi {

    @GET("everything/")
    Call<ArticlesList> fetchEverything(@Query("domains") String domains, @Query("apiKey") String apiKey);
}

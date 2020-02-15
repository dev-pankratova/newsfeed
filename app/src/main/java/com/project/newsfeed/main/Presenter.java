package com.project.newsfeed.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.newsfeed.NewsModelApi;
import com.project.newsfeed.model.ArticlesList;
import com.project.newsfeed.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Presenter {
    static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String TAG = "myLogs";

    public void start(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        NewsModelApi newsModelApi = retrofit.create(NewsModelApi.class);

        Call<ArticlesList> call = newsModelApi.fetchEverything("wsj.com", "71e1b8baf781402aa67e4791daf5d432");
        call.enqueue(new Callback<ArticlesList>() {
            @Override
            public void onResponse(Call<ArticlesList> call, Response<ArticlesList> response) {
                List<NewsModel> myModel = new ArrayList<>();
                if (response.isSuccessful()){
                    ArticlesList articlesList = response.body();
                    List<NewsModel> newsModelList = articlesList.articles;

                }
            }

            @Override
            public void onFailure(Call<ArticlesList> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

  /*  private void newsRecycler (NewsModel newsModel){
        Adapter mAdapter = new Adapter(this, newsModel);

    }*/
   /* private void writeRecycler (ArticlesList articlesList){
        try {
            JSONObject obj = new JSONObject(response);

            if (obj.optString("status").equals("true")){
                ArrayList<NewsModel> newsModelArrayList = new ArrayList<>();
                JSONArray dataArray = obj.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++){

                    JSONObject dataobj = dataArray.getJSONObject(i);
                    NewsModel newsModel = new NewsModel();


                    newsModelArrayList.add(newsModel);

                }
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }*/
}

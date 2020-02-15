package com.project.newsfeed.main;

import android.util.Log;

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

public class Presenter implements MainContract.Presenter {
    public Presenter(MainContract.View view) {
        this.view = view;
    }

    static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String TAG = "myLogs";
    private MainContract.View view;
    private List<NewsModel> newsList = new ArrayList<>();

    @Override
    public void start() {
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
                assert response.body() != null;
                view.newsData(response.body().articles);
            }

            @Override
            public void onFailure(Call<ArticlesList> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onNewsClicked(int position) {
        if (position > newsList.size()) return; //todo show error
        NewsModel model = newsList.get(position);
        if (model == null) return; //todo show error
        view.openPreview(model);
        Log.d(TAG, String.valueOf(position));
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

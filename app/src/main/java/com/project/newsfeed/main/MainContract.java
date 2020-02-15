package com.project.newsfeed.main;

import com.project.newsfeed.model.NewsModel;

import java.util.List;

public interface MainContract {

    interface Presenter {

        void start();

        void onNewsClicked(int position);

    }

    interface View {
        void newsData(List<NewsModel> models);

        void openPreview(NewsModel model);
    }
}

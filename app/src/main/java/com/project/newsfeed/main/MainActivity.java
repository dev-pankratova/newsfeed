package com.project.newsfeed.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.newsfeed.PreviewPage;
import com.project.newsfeed.R;
import com.project.newsfeed.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String MODEL_KEY = "model";
    private static final String TAG = "myLogs";

    public ArrayList<String> titleList = new ArrayList<>();
    public ArrayList<String> newsURL = new ArrayList<>();
    public ArrayList<String> descrList = new ArrayList<>();
    public ArrayList<String> mImageUrl = new ArrayList<>();

    private RecyclerView recyclerView;
    private Adapter mAdapter;

    private List<NewsModel> mNewsModelList = new ArrayList<>();
    private int s = mNewsModelList.size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView);

        Presenter controller = new Presenter();
        controller.start();

        mNewsModelList = new ArrayList<>();
        newsData(mNewsModelList);


        mAdapter = new Adapter(this, mNewsModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setClickListener(v -> {
            int position = recyclerView.getChildAdapterPosition(v);
            Intent intent;
            if (s > position) {
                if (mNewsModelList.get(position) != null) {
                    intent = new Intent(MainActivity.this, PreviewPage.class);
                    intent.putExtra("image", mNewsModelList.get(position).getUrlImage());
                    intent.putExtra("title", mNewsModelList.get(position).getTitle());
                    intent.putExtra("url", mNewsModelList.get(position).getUrl());
                    intent.putExtra("desc", mNewsModelList.get(position).description);
                    //intent.putExtra("url", newsURL.get(position));
                    startActivity(intent);

                    Log.d(TAG, String.valueOf(position));
                }
            }
        });
    }

    public void newsData(List<NewsModel> models) {
        mAdapter.setItem(models);

    }
/*    public void openPreview(NewsModel model) {
        Intent intent = new Intent(MainActivity.this, PreviewPage.class);
        intent.putExtra(MODEL_KEY, model);
        startActivity(intent);
    }*/

/*
    @Override
    protected void onPostExecute(String s) {
        recyclerView.setAdapter(mAdapter);
    }*/
}


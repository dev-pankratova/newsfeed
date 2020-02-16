package com.project.newsfeed.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.newsfeed.PreviewPage;
import com.project.newsfeed.R;
import com.project.newsfeed.model.NewsModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    public static final String MODEL_KEY = "model";
    private static final String TAG = "myLogs";

    private RecyclerView recyclerView;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView);

        Presenter presenter = new Presenter(this);
        presenter.start();

        mAdapter = new Adapter(this, v -> {
            int position = recyclerView.getChildAdapterPosition(v);
            presenter.onNewsClicked(position);
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void newsData(List<NewsModel> models) {
        mAdapter.setItems(models);
    }

    @Override
    public void openPreview(NewsModel model) {
        Intent intent = new Intent(this, PreviewPage.class);
        intent.putExtra(MODEL_KEY, model);
        startActivity(intent);
    }
}
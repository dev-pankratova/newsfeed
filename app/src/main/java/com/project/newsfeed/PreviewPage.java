package com.project.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.project.newsfeed.main.MainActivity;
import com.project.newsfeed.model.NewsModel;

public class PreviewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_page);
        setTitle(R.string.current_news);

        ImageView mNewsImage = findViewById(R.id.imagePre);
        TextView mNewsTitle = findViewById(R.id.tv_titlePre);
        TextView mNewsDescription = findViewById(R.id.tv_descrPre);
        Button mBtnLoad = findViewById(R.id.btn_load);

        Intent intent = getIntent();
        NewsModel model = intent.getParcelableExtra(MainActivity.MODEL_KEY);
        String image = model.getUrlImage();
        String title = model.getTitle();
        String description = model.getContent();

        Glide.with(this)
                .load(image)
                .centerCrop()
                .into(mNewsImage);
        mNewsTitle.setText(title);
        mNewsDescription.setText(description);

        mBtnLoad.setOnClickListener(v -> {
            Intent webViewIntent = new Intent(PreviewPage.this, WebViewLoad.class);
            webViewIntent.putExtra("current_url", model.getUrl());
            startActivity(webViewIntent);
        });
    }


    }


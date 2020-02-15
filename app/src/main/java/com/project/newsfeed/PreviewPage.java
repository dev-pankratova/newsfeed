package com.project.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.project.newsfeed.model.WebViewLoad;

public class PreviewPage extends AppCompatActivity {

    private ImageView mNewsImage;
    private TextView mNewsTitle;
    private TextView mNewsDescription;
    private Button mBtnLoad;



    private String mPageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_page);
        setTitle(R.string.current_news);

        mNewsImage = findViewById(R.id.imagePre);
        mNewsTitle = findViewById(R.id.tv_titlePre);
        mNewsDescription = findViewById(R.id.tv_descrPre);
        mBtnLoad = findViewById(R.id.btn_load);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String title = intent.getStringExtra("title");
        mPageUrl = intent.getStringExtra("url");

        Glide.with(this)
                .load(image)
                .centerCrop()
                .into(mNewsImage);
        mNewsTitle.setText(title);

        mBtnLoad.setOnClickListener(v -> {
            //показ через андроидовский webview
            //new Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra("url")));
            Intent webViewIntent = new Intent(PreviewPage.this, WebViewLoad.class);
            webViewIntent.putExtra("current_url", mPageUrl);
            startActivity(webViewIntent);
        });
    }


    }


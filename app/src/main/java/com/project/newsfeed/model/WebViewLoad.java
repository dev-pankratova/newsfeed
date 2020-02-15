package com.project.newsfeed.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.project.newsfeed.R;

public class WebViewLoad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_load);
        setTitle(R.string.news_in_browser);

        WebView mWebView = findViewById(R.id.myWebView);

        Intent intent = getIntent();
        String url = intent.getStringExtra("current_url");

        mWebView.loadUrl(url);
    }
}

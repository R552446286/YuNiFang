package com.baway.yunifang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.baway.yunifang.R;

public class HomeWebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_home_web_view);
        WebView home_webView = (WebView) findViewById(R.id.home_webView);
        String url = getIntent().getStringExtra("url");
        home_webView.getSettings().setJavaScriptEnabled(true);
        home_webView.loadUrl(url);
    }
}

package com.dingtao.rrmmp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dingtao.rrmmp.login.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.app.AppCompatActivity;

public class BannerWebViewActivity extends AppCompatActivity {

    private WebView banner_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_web_view);
        initView();


        Intent intent = getIntent();
        String bannerweb = intent.getStringExtra("bannerweb");
        banner_webview.loadUrl(bannerweb);
        banner_webview.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    private void initView() {
        banner_webview = (WebView) findViewById(R.id.banner_webview);
    }
}

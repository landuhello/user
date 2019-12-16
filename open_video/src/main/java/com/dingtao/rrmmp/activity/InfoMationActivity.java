package com.dingtao.rrmmp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtao.common.bean.InfoMationBean;
import com.dingtao.common.bean.LoginBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.rrmmp.help.HTMLFormat;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.presenter.AddInfoPresenter;
import com.dingtao.rrmmp.presenter.CancelInfoPresenter;
import com.dingtao.rrmmp.presenter.InfoMationPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import freemarker.template.utility.StringUtil;

public class InfoMationActivity extends AppCompatActivity {

    private String mSessionId;
    private int mId;
    private ImageView home_simple;
    private ImageView home_message_img;
    private TextView info_text_title;
    private TextView info_text_source;
    private SimpleDraweeView info_simple;
    private WebView info_content;
    private SimpleDraweeView info_shoucang;
    private SimpleDraweeView info_shoucang1;
    private SimpleDraweeView info_fenxiang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_mation);
        initView();
        EventBus.getDefault().register(this);

        home_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String infoid = intent.getStringExtra("infoid");

        if (mId != 0 && mSessionId != null) {
            InfoMationPresenter infoMationPresenter = new InfoMationPresenter(new InfoMationP());
            infoMationPresenter.reqeust(mId, mSessionId, Integer.valueOf(infoid));
        } else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                    .navigation(InfoMationActivity.this);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void dologin(LoginBean bean) {
        mSessionId = bean.sessionId;
        mId = bean.id;
    }

    private void initView() {
        home_simple = (ImageView) findViewById(R.id.home_simple);
        home_message_img = (ImageView) findViewById(R.id.home_message_img);
        info_text_title = (TextView) findViewById(R.id.info_text_title);
        info_text_source = (TextView) findViewById(R.id.info_text_source);
        info_content = (WebView) findViewById(R.id.info_content);
        info_shoucang = (SimpleDraweeView) findViewById(R.id.info_shoucang);
        info_shoucang1 = (SimpleDraweeView) findViewById(R.id.info_shoucang1);
        info_fenxiang = (SimpleDraweeView) findViewById(R.id.info_fenxiang);
    }

    private class InfoMationP implements DataCall<InfoMationBean> {
        @Override
        public void success(final InfoMationBean data, Object... args) {
            info_text_title.setText(data.title);
            Date date=new Date(data.releaseTime);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            info_text_source.setText(data.source+" "+simpleDateFormat.format(date));
            info_content.getSettings().setUseWideViewPort(false);//设置webview显示屏幕宽度 不能滑动
            info_content.getSettings().setLoadWithOverviewMode(true);
            info_content.loadDataWithBaseURL(null, HTMLFormat.getNewContent(data.content), "text/html", "utf-8", null);

            if (data.whetherCollection==0){
                info_shoucang1.setVisibility(View.GONE);
            }else {
                info_shoucang1.setVisibility(View.VISIBLE);
            }

            info_shoucang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddInfoPresenter addInfoPresenter=new AddInfoPresenter(new AddInfoP());
                    addInfoPresenter.reqeust(mId,mSessionId,data.id);
                }
            });

            info_shoucang1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CancelInfoPresenter cancelInfoPresenter=new CancelInfoPresenter(new CancelInfoP());
                    cancelInfoPresenter.reqeust(mId,mSessionId,data.id);
                }
            });

        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(InfoMationActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class AddInfoP implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {

                info_shoucang1.setVisibility(View.VISIBLE);
                Toast.makeText(InfoMationActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(InfoMationActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private class CancelInfoP implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {

                info_shoucang1.setVisibility(View.GONE);
                Toast.makeText(InfoMationActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(InfoMationActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

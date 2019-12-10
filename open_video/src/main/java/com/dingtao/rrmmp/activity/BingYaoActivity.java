package com.dingtao.rrmmp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingtao.rrmmp.fragment.BingZhengFragment;
import com.dingtao.rrmmp.fragment.YaoPinFragment;
import com.dingtao.rrmmp.login.R;
import com.facebook.drawee.view.SimpleDraweeView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/6<p>
 * <p>更改时间：2019/12/6<p>
 */
public class BingYaoActivity extends AppCompatActivity {

    private SimpleDraweeView home_simple;
    private ImageView home_message_img;
    private TextView text_bingzheng;
    private TextView text_yaopin;
    private FrameLayout fram_bing_yao;
    private BingZhengFragment bingZhengFragment;
    private YaoPinFragment yaoPinFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing_yao);
        initView();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        bingZhengFragment = new BingZhengFragment();
        yaoPinFragment = new YaoPinFragment();
        transaction.add(R.id.fram_bing_yao, bingZhengFragment);
        transaction.add(R.id.fram_bing_yao, yaoPinFragment);
        transaction.commit();

        Intent intent = getIntent();
        String bing = intent.getStringExtra("bing");
        if(Integer.valueOf(bing)==1){
            jiaZaiBingZheng();
        }else if (Integer.valueOf(bing)==2){
            jiaZaiYaoPin();
        }

        text_bingzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiaZaiBingZheng();
            }
        });

        text_yaopin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiaZaiYaoPin();
            }
        });

    }

    private void jiaZaiYaoPin() {
        FragmentManager fragmentManager2 = getSupportFragmentManager();
        FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
        transaction2.hide(bingZhengFragment);
        transaction2.show(yaoPinFragment);
        transaction2.commit();
    }

    private void jiaZaiBingZheng() {
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
        transaction1.show(bingZhengFragment);
        transaction1.hide(yaoPinFragment);
        transaction1.commit();
    }

    private void initView() {
        home_simple = (SimpleDraweeView) findViewById(R.id.home_simple);
        home_message_img = (ImageView) findViewById(R.id.home_message_img);
        text_bingzheng = (TextView) findViewById(R.id.text_bingzheng);
        text_yaopin = (TextView) findViewById(R.id.text_yaopin);
        fram_bing_yao = (FrameLayout) findViewById(R.id.fram_bing_yao);
    }
}

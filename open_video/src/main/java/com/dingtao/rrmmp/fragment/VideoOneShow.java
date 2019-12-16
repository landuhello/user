package com.dingtao.rrmmp.fragment;


import android.content.Context;
import android.view.View;

import com.dingtao.rrmmp.login.R;
import com.lxj.xpopup.impl.FullScreenPopupView;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */
public class VideoOneShow extends FullScreenPopupView {
    public VideoOneShow(Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.video_one_yanshi;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.video_one_zhidao).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //初始化
    }
}

package com.dingtao.rrmmp.activity;

import android.content.Context;

import androidx.annotation.NonNull;

import com.dingtao.rrmmp.login.R;
import com.lxj.xpopup.core.BottomPopupView;

/**
 * data:去留言
 * author:老刘（）
 * function:这是干什么的
 */
public class VideoReview_Xpop extends BottomPopupView {

    public VideoReview_Xpop(@NonNull Context context) {
        super(context);
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.video_review;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }
}

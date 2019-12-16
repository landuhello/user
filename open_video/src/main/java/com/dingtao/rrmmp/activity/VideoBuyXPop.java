package com.dingtao.rrmmp.activity;


import android.content.Context;

import androidx.annotation.NonNull;

import com.dingtao.rrmmp.login.R;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;

/*
 *@Auther:陈浩
 *@Date: 2019/8/14
 *@Time:19:31
 *@Description:${视频买的弹框}
 * */public class VideoBuyXPop extends BottomPopupView {
    public VideoBuyXPop(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.video_buy_xpop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

    }

    // 最大高度为Window的0.85
    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .85f);
    }
}

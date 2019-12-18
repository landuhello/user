package com.example.patientscircle.activity;


import android.content.Context;

import androidx.annotation.NonNull;

import com.example.patientscircle.R;
import com.lxj.xpopup.core.BottomPopupView;

/*
 *@Auther:陈浩
 *@Date: 2019/8/20
 *@Time:15:59
 *@Description:${DESCRIPTION}
 * */public class patientReviewXpop extends BottomPopupView {
    public patientReviewXpop(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.patient_review;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }
}

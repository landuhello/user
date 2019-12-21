package com.example.patientscircle.activity;


import android.content.Context;

import androidx.annotation.NonNull;

import com.example.patientscircle.R;
import com.lxj.xpopup.impl.PartShadowPopupView;

/*
 *@Auther:陈浩
 *@Date: 2019/8/21
 *@Time:11:21
 *@Description:${DESCRIPTION}
 * */public class ReleaseDepartmentXpop extends PartShadowPopupView {
    public ReleaseDepartmentXpop(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.release_department_xpop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }

}

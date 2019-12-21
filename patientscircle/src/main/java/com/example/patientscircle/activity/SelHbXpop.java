package com.example.patientscircle.activity;


import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.patientscircle.R;
import com.lxj.xpopup.core.CenterPopupView;

/*
 *@Auther:陈浩
 *@Date: 2019/8/22
 *@Time:18:55
 *@Description:${DESCRIPTION}
 * */public class SelHbXpop extends CenterPopupView {
    public SelHbXpop(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.add_hb_xpop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        findViewById(R.id.hbcancle).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });
    }
}

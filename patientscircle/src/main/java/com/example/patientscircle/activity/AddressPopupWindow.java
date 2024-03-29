package com.example.patientscircle.activity;


import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.patientscircle.R;
import com.lxj.xpopup.core.CenterPopupView;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public class AddressPopupWindow extends CenterPopupView {
    public AddressPopupWindow(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.archives_pop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.add_img).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}

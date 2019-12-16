package com.dingtao.rrmmp.videofragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dingtao.rrmmp.login.R;
import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.CenterPopupView;

/*
 *@Auther:陈浩
 *@Date: 2019/8/12
 *@Time:14:32
 *@Description:${DESCRIPTION}
 * */public class DoctordetailsRecharge extends CenterPopupView {

    private TextView titlepopcontent;
    private Button doctorpopgo;

    public DoctordetailsRecharge(@NonNull Context context) {
        super(context);
    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.dctor_details_recharge;
    }

    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.doctor_details_pop_cancle).findViewById(R.id.doctor_details_pop_cancle).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });
        titlepopcontent = findViewById(R.id.doctor_details_pop_content);
        doctorpopgo = findViewById(R.id.doctor_details_pop_go_Recharge);
        titlepopcontent.setText(data);
       //按钮设置
        doctorpopgo.setText(buttontv);
        //跳转业务页面
        doctorpopgo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               if(activity==null){
                   dismiss();
                   return;
               }
                Intent intent = new Intent(getContext(), activity);
               intent.putExtras(bundle);
                getContext().startActivity(intent);
                dismiss(); // 关闭弹窗
            }
        });
    }

    String data;
    String buttontv;
    Class<?> activity;
    Bundle bundle;
    public void setContent(String s) {
        data = s; //只记录数据，不再操作View
    }

    public void setButtontv(String bu, Class<?> activity, Bundle bundle) {
        buttontv = bu;
        this.activity=activity;
        this.bundle=bundle;
    }

    // 设置自定义动画器，看需要而定
    @Override
    protected PopupAnimator getPopupAnimator() {
        return super.getPopupAnimator();
    }

}

package com.example.patientscircle.activity;


import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.patientscircle.R;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.interfaces.XPopupCallback;

import org.greenrobot.eventbus.EventBus;

/*
 *@Auther:陈浩
 *@Date: 2019/8/19
 *@Time:10:24
 *@Description:${点击带列表的}
 * */public class PatientCircleReviewXpop extends BottomPopupView {


    public PatientCircleReviewXpop(@NonNull Context context) {
        super(context);

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.patient_comments;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        //点击外面关闭
        findViewById(R.id.patient_circle_collection_err).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //点击弹起来另一个
        findViewById(R.id.patient_circle_collection_edt).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //里面的
                final patientReviewXpop patientReviewXpop = new patientReviewXpop(getContext());
                //发送
                new XPopup.Builder(getContext())
                        .setPopupCallback(new XPopupCallback() {
                            @Override
                            public void onCreated() {
                                RelativeLayout sends = patientReviewXpop.findViewById(R.id.patient_comment_send);

                                sends.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        EditText viewById = patientReviewXpop.findViewById(R.id.patient_comment_edt);
                                        String content = viewById.getText().toString();
                                        EventBus.getDefault().post(content);
                                        patientReviewXpop.dismiss();
                                    }
                                });
                            }

                            @Override
                            public void onShow() {


                            }

                            @Override
                            public void onDismiss() {

                            }

                            @Override
                            public boolean onBackPressed() {

                                return false;
                            }
                        })
                        .autoOpenSoftInput(true) //是否弹窗显示的同时打开输入法，只在包含输入框的弹窗内才有效，默认为false
                        .asCustom(patientReviewXpop)
                        .show();

            }
        });


    }


}

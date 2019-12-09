package com.example.patientscircle.activity;


import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.bean.PatientsBean.PatientscirBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.example.patientscircle.R;
import com.example.patientscircle.R2;
import com.example.patientscircle.presenter.ParticularsPresenter;

import java.text.SimpleDateFormat;

import butterknife.BindView;
@Route(path = Constant.ACTIVITY_URL_PATIENT)
public class ConditionsActivity extends WDActivity {

    @BindView(R2.id.headtrait)
    ImageView headtrait;
    @BindView(R2.id.disease)
    TextView disease;
    @BindView(R2.id.information)
    ImageView information;
    @BindView(R2.id.name)
    TextView name;
    @BindView(R2.id.bing)
    TextView bing;
    @BindView(R2.id.illness)
    TextView illness;
    @BindView(R2.id.ke)
    TextView ke;
    @BindView(R2.id.admini)
    TextView admini;
    @BindView(R2.id.details)
    TextView details;
    @BindView(R2.id.experience)
    TextView experience;
    @BindView(R2.id.hospitalname)
    TextView hospitalname;
    @BindView(R2.id.timeoftherapy)
    TextView timeoftherapy;
    @BindView(R2.id.treatment)
    TextView treatment;
    @BindView(R2.id.relatedpictures)
    TextView relatedpictures;
    @BindView(R2.id.picture)
    ImageView picture;
    @BindView(R2.id.xing)
    ImageView xing;
    @BindView(R2.id.collect)
    TextView collect;
    @BindView(R2.id.news)
    ImageView news;
    @BindView(R2.id.suggest)
    TextView suggest;
    @BindView(R2.id.xian)
    View xian;
    @BindView(R2.id.accept)
    TextView accept;
    private TextView sickness;
    private ParticularsPresenter particularsPresenter;
    private ImageView useravatar;
    private TextView theusern;
    private TextView taketh;
    private TextView takethead;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_conditions;
    }

    @Override
    protected void initView() {
        //获取控件
        sickness = findViewById(R.id.sickness);
        useravatar = findViewById(R.id.useravatar);
        theusern = findViewById(R.id.theusernickname);
        taketh = findViewById(R.id.takethetime);
        takethead = findViewById(R.id.taketheadvice);
        //接值
        Intent intent = getIntent();
        int sick = intent.getIntExtra("sick", 0);
        String title = intent.getStringExtra("title");
        //设置值
        disease.setText(title);
        //创建p层
        particularsPresenter = new ParticularsPresenter(new PatieieCall());
        particularsPresenter.reqeust(sick);
        //点击弹出pop

    }

    @Override
    protected void destoryData() {

    }
    //創建內部类對象
    class PatieieCall implements DataCall<PatientscirBean> {
        @Override
        public void success(PatientscirBean data, Object... args) {
            illness.setText(data.disease);
            admini.setText(data.department);
            sickness.setText(data.detail);
            hospitalname.setText(data.treatmentHospital);
            //时间转码
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String format1 = format.format(data.treatmentStartTime);
            timeoftherapy.setText(format1 + "");
            treatment.setText(data.treatmentProcess);
            Uri uri = Uri.parse(data.picture);
            picture.setImageURI(uri);
            collect.setText(String.valueOf(data.collectionNum));
            suggest.setText(String.valueOf(data.commentNum));
            Uri uri1 = Uri.parse(data.adoptHeadPic);
            useravatar.setImageURI(uri1);
            theusern.setText(data.adoptNickName);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String format3 = format2.format(data.treatmentStartTime);
            taketh.setText(format3 + "");
            takethead.setText(data.adoptComment);
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}

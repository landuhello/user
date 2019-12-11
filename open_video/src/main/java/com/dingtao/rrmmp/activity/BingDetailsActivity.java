package com.dingtao.rrmmp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.DiseaseKnowBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.main.presenter.DiseaseKnowPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class BingDetailsActivity extends AppCompatActivity {

    private SimpleDraweeView home_simple;
    private ImageView home_message_img;
    private TextView details_name;
    private TextView details_pathology;
    private TextView details_malady;
    private TextView details_aaa;
    private TextView details_western;
    private TextView details_traditional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing_details);
        initView();

        Intent intent = getIntent();
        String idsa = intent.getStringExtra("idsa");
        String isname = intent.getStringExtra("idname");

        if (!TextUtils.isEmpty(idsa)&&!TextUtils.isEmpty(isname)){
            //病症详情
            DiseaseKnowPresenter diseaseKnowPresenter=new DiseaseKnowPresenter(new DiseaseKnowP());
            diseaseKnowPresenter.reqeust(Integer.valueOf(idsa));

            details_name.setText(isname);
        }else {
            Toast.makeText(this, idsa+"   "+isname, Toast.LENGTH_SHORT).show();
        }

    }

    private void initView() {
        home_simple = (SimpleDraweeView) findViewById(R.id.home_simple);
        home_message_img = (ImageView) findViewById(R.id.home_message_img);
        details_name = (TextView) findViewById(R.id.details_name);
        details_pathology = (TextView) findViewById(R.id.details_pathology);
        details_malady = (TextView) findViewById(R.id.details_malady);
        details_aaa = (TextView) findViewById(R.id.details_aaa);
        details_western = (TextView) findViewById(R.id.details_western);
        details_traditional = (TextView) findViewById(R.id.details_traditional);
    }

    private class DiseaseKnowP implements DataCall<DiseaseKnowBean> {
        @Override
        public void success(DiseaseKnowBean data, Object... args) {

                if (data. pathology==null){
                    details_western.setText("无");
                }else {
                    details_pathology.setText(data. pathology);
                }

                if (data. symptom==null){
                    details_western.setText("无");
                }else {
                    details_malady.setText(data. symptom);
                }

                if (data. benefitTaboo==null){
                    details_western.setText("无");
                }else {
                    details_aaa.setText(data. benefitTaboo);
                }

                if (data. westernMedicineTreatment==null){
                    details_western.setText("无");
                }else {
                    details_western.setText(data. westernMedicineTreatment);
                }

                if (data. chineseMedicineTreatment==null){
                    details_western.setText("无");
                }else {
                    details_traditional.setText(data. chineseMedicineTreatment);
                }
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(BingDetailsActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

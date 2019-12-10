package com.dingtao.rrmmp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.DiseaseSearchVoListBean;
import com.dingtao.common.bean.DoctorSearchVoListBean;
import com.dingtao.common.bean.DrugsSearchVoListBean;
import com.dingtao.common.bean.HomeSeachBean;
import com.dingtao.common.bean.PopularSearchBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.rrmmp.adapter.SeachDoctorAdapter;
import com.dingtao.rrmmp.adapter.SeachDrugsAdapter;
import com.dingtao.rrmmp.login.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeSeachActivity extends AppCompatActivity {

    private ImageView seach_back_img;
    private EditText seach_edit;
    private TextView seach_seach;
    private TextView seach_histry;
    private com.dingtao.rrmmp.main.myview.FlowView seach_flow;
    private TextView seach_popular;
    private LinearLayout line;
    private TextView seach_doctor;
    private RecyclerView recycler_doctor;
    private TextView seach_diease;
    private RecyclerView recycler_disease;
    private TextView seach_drugs;
    private RecyclerView recycler_drugs;
    private LinearLayout line1;
    private SeachDoctorAdapter seachDoctorAdapter;
    private com.dingtao.rrmmp.main.adapter.SeachDieaseAdapter seachDieaseAdapter;
    private SeachDrugsAdapter seachDrugsAdapter;
    private TextView seach_no_message;
    private LinearLayout line2;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_seach);
        initView();
        //点击后退图片关闭当前页面
        seach_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击搜索，进行搜索
        seach_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seachedit = seach_edit.getText().toString();
                if (TextUtils.isEmpty(seachedit)) {
                    Toast.makeText(HomeSeachActivity.this, "请输入查询需要", Toast.LENGTH_SHORT).show();
                    return;
                }

                com.dingtao.rrmmp.main.presenter.HomeSearchPresenter homeSearchPresenter = new com.dingtao.rrmmp.main.presenter.HomeSearchPresenter(new HomeSeachP());
                homeSearchPresenter.reqeust(seachedit);

                seach_popular.setVisibility(View.GONE);
                line.setVisibility(View.GONE);

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeSeachActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_disease.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(HomeSeachActivity.this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_doctor.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(HomeSeachActivity.this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_drugs.setLayoutManager(linearLayoutManager2);

        seachDoctorAdapter = new SeachDoctorAdapter();
        recycler_doctor.setAdapter(seachDoctorAdapter);
        seachDieaseAdapter = new com.dingtao.rrmmp.main.adapter.SeachDieaseAdapter();
        recycler_disease.setAdapter(seachDieaseAdapter);
        seachDrugsAdapter = new SeachDrugsAdapter();
        recycler_drugs.setAdapter(seachDrugsAdapter);

        //热门搜索
        com.dingtao.rrmmp.main.presenter.PopularSearchPresenter popularSearchPresenter = new com.dingtao.rrmmp.main.presenter.PopularSearchPresenter(new PopuLarP());
        popularSearchPresenter.reqeust();

    }

    private void initView() {
        seach_back_img = (ImageView) findViewById(R.id.seach_back_img);
        seach_edit = (EditText) findViewById(R.id.seach_edit);
        seach_seach = (TextView) findViewById(R.id.seach_seach);
        seach_histry = (TextView) findViewById(R.id.seach_histry);
        seach_flow = (com.dingtao.rrmmp.main.myview.FlowView) findViewById(R.id.seach_flow);
        seach_popular = (TextView) findViewById(R.id.seach_popular);
        line = (LinearLayout) findViewById(R.id.line);
        seach_doctor = (TextView) findViewById(R.id.seach_doctor);
        recycler_doctor = (RecyclerView) findViewById(R.id.recycler_doctor);
        seach_diease = (TextView) findViewById(R.id.seach_diease);
        recycler_disease = (RecyclerView) findViewById(R.id.recycler_disease);
        seach_drugs = (TextView) findViewById(R.id.seach_drugs);
        recycler_drugs = (RecyclerView) findViewById(R.id.recycler_drugs);
        line1 = (LinearLayout) findViewById(R.id.line1);
        seach_no_message = (TextView) findViewById(R.id.seach_no_message);
        line2 = (LinearLayout) findViewById(R.id.line2);
    }

    private class HomeSeachP implements DataCall<HomeSeachBean> {
        @Override
        public void success(HomeSeachBean data, Object... args) {
            //病症
            List<DiseaseSearchVoListBean> diseaseSearchVoList = data.diseaseSearchVoList;

            //医生
            List<DoctorSearchVoListBean> doctorSearchVoList = data.doctorSearchVoList;

            //药品
            List<DrugsSearchVoListBean> drugsSearchVoList = data.drugsSearchVoList;

            if (diseaseSearchVoList.size() == 0 && doctorSearchVoList.size() == 0 && drugsSearchVoList.size() == 0) {
                line2.setVisibility(View.VISIBLE);
                line1.setVisibility(View.GONE);
                seach_no_message.setText("抱歉！没有搜索到关于" + seach_edit.getText().toString() + "的信息");

            } else {

                line2.setVisibility(View.GONE);
                line1.setVisibility(View.VISIBLE);

                seachDieaseAdapter.addAll(diseaseSearchVoList);
                seachDieaseAdapter.notifyDataSetChanged();

                seachDoctorAdapter.addAll(doctorSearchVoList);
                seachDoctorAdapter.notifyDataSetChanged();

                seachDrugsAdapter.addAll(drugsSearchVoList);
                seachDrugsAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(HomeSeachActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private class PopuLarP implements DataCall<List<PopularSearchBean>> {
        @Override
        public void success(List<PopularSearchBean> data, Object... args) {
            for (int i = 0; i < data.size(); i++) {
                seach_flow.addTag(data.get(i).name);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(HomeSeachActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

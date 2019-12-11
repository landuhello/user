package com.dingtao.rrmmp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.dingtao.rrmmp.main.adapter.SeachDieaseAdapter;
import com.dingtao.rrmmp.main.myview.FlowView;
import com.dingtao.rrmmp.main.presenter.HomeSearchPresenter;
import com.dingtao.rrmmp.main.presenter.PopularSearchPresenter;
import com.dingtao.rrmmp.myview.HistryFlowView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeSeachActivity extends AppCompatActivity {

    private ImageView seach_back_img;
    private EditText seach_edit;
    private TextView seach_seach;
    private TextView seach_histry;
    private FlowView seach_flow;
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
    private SeachDieaseAdapter seachDieaseAdapter;
    private SeachDrugsAdapter seachDrugsAdapter;
    private TextView seach_no_message;
    private LinearLayout line2;
    private boolean isClick;
    private HistryFlowView seach_histry_flow;
    private LinearLayout seach_histry_line;

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

        //判断是否有搜索记录
        if (isClick == true) {
            seach_histry.setVisibility(View.VISIBLE);
            seach_histry_line.setVisibility(View.VISIBLE);
        }

        seach_edit.setLines(1);
        seach_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String trim = seach_edit.getText().toString().trim();
                int length = trim.length();
                if (length == 0) {
                    line.setVisibility(View.GONE);
                    line1.setVisibility(View.GONE);
                    line2.setVisibility(View.GONE);
                    seach_popular.setVisibility(View.VISIBLE);
                    //热门搜索
                    PopularSearchPresenter popularSearchPresenter = new PopularSearchPresenter(new PopuLarP());
                    popularSearchPresenter.reqeust();

                    //判断是否有搜索记录
                    if (isClick == true) {
                        seach_histry.setVisibility(View.VISIBLE);
                        seach_histry_line.setVisibility(View.VISIBLE);
                    }

                }
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

                HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(new HomeSeachP());
                homeSearchPresenter.reqeust(seachedit);

                seach_popular.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                isClick = true;

            }
        });

        //热门搜索
        PopularSearchPresenter popularSearchPresenter = new PopularSearchPresenter(new PopuLarP());
        popularSearchPresenter.reqeust();

        seach_flow.setTextClick(new FlowView.TextClick() {
            @Override
            public void onClick(String msg) {
                Toast.makeText(HomeSeachActivity.this, msg, Toast.LENGTH_SHORT).show();
                HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(new HomeSeachP());
                homeSearchPresenter.reqeust(msg);
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
        seachDieaseAdapter = new SeachDieaseAdapter();
        recycler_disease.setAdapter(seachDieaseAdapter);
        seachDrugsAdapter = new SeachDrugsAdapter();
        recycler_drugs.setAdapter(seachDrugsAdapter);

        seachDieaseAdapter.setDieaseBackId(new SeachDieaseAdapter.DieaseBackId() {
            @Override
            public void onbackid(int dieaseid, String namea) {
                Intent intent=new Intent(HomeSeachActivity.this,BingDetailsActivity.class);
                intent.putExtra("idsa",dieaseid+"");
                intent.putExtra("idname",namea);
                startActivity(intent);
            }
        });

        seachDrugsAdapter.setDrugsBackId(new SeachDrugsAdapter.DrugsBackId() {
            @Override
            public void onbackid(int drugsid, String doctorname) {
                Toast.makeText(HomeSeachActivity.this, doctorname, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(HomeSeachActivity.this,DrugsDetailsActivity.class);
                intent.putExtra("idsss",drugsid+"");
                startActivity(intent);
            }
        });

        seachDoctorAdapter.setDoctorBackId(new SeachDoctorAdapter.DoctorBackId() {
            @Override
            public void onbackid(int doctorid, String dname) {
                Toast.makeText(HomeSeachActivity.this, dname, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        seach_back_img = (ImageView) findViewById(R.id.seach_back_img);
        seach_edit = (EditText) findViewById(R.id.seach_edit);
        seach_seach = (TextView) findViewById(R.id.seach_seach);
        seach_histry = (TextView) findViewById(R.id.seach_histry);
        seach_flow = (FlowView) findViewById(R.id.seach_flow);
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
        seach_histry_flow = (HistryFlowView) findViewById(R.id.seach_histry_flow);
        seach_histry_line = (LinearLayout) findViewById(R.id.seach_histry_line);
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

            seach_histry_flow.addTag(seach_edit.getText().toString().trim());
            seach_histry.setVisibility(View.GONE);
            seach_histry_line.setVisibility(View.GONE);
            seach_popular.setVisibility(View.GONE);
            line.setVisibility(View.GONE);

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

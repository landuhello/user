package com.dingtao.rrmmp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.DepartListBean;
import com.dingtao.common.bean.FindDoctorBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.rrmmp.adapter.FindDepartAdapter;
import com.dingtao.rrmmp.adapter.FindDoctorAdapter;
import com.dingtao.rrmmp.adapter.HomeDepartAdapter;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.presenter.FindDoctorPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FindDoctorActivity extends AppCompatActivity {

    private RecyclerView inquiry_rv;
    private TextView tv_zh;
    private TextView tv_hp;
    private TextView tv_zxs;
    private TextView tv_price;
    private SimpleDraweeView sdv_pic;
    private TextView tv_name;
    private TextView tv_jobTitle;
    private TextView tv_inauguralHospital;
    private TextView tv_praiseNum;
    private TextView tv_serverNum;
    private TextView tv_servicePrice;
    private TextView tv_ask;
    private SimpleDraweeView sdv_left;
    private SimpleDraweeView sdv_right;
    private RecyclerView rv_doctor;
    private FindDepartAdapter findDepartAdapter;
    private FindDoctorAdapter findDoctorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        initView();

        //请求科室类目
        com.dingtao.rrmmp.main.presenter.DepartPresenter departPresenter = new com.dingtao.rrmmp.main.presenter.DepartPresenter(new MainDepartPresenter());
        departPresenter.reqeust();

        Intent intent = getIntent();
        final String dids = intent.getStringExtra("did");

        //科室类目
        findDepartAdapter = new FindDepartAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(FindDoctorActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        inquiry_rv.setLayoutManager(linearLayoutManager);
        inquiry_rv.setAdapter(findDepartAdapter);

        findDepartAdapter.setDepartBack(new FindDepartAdapter.DepartBack() {
            @Override
            public void backId(int did,int postionfind) {
                if (Integer.valueOf(dids)==did){
                    findDepartAdapter.setmPostion(Integer.valueOf(dids));
                    FindDoctorPresenter findDoctorPresenter=new FindDoctorPresenter(new FindDoctorP());
                    findDoctorPresenter.reqeust(Integer.valueOf(dids),1,0,1,5);
                }else {
                    FindDoctorPresenter findDoctorPresenter=new FindDoctorPresenter(new FindDoctorP());
                    findDoctorPresenter.reqeust(Integer.valueOf(dids),1,0,1,5);
                  }
            }
        });

        findDoctorAdapter = new FindDoctorAdapter();
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(FindDoctorActivity.this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_doctor.setLayoutManager(linearLayoutManager1);
        rv_doctor.setAdapter(findDoctorAdapter);

        findDoctorAdapter.setFindDoctorBack(new FindDoctorAdapter.FindDoctorBack() {
            @Override
            public void findBack(FindDoctorBean findDoctorBean,int mi) {
                if (findDoctorBean != null) {
                    findDoctorAdapter.setmPostion(mi);
                    Uri uri=Uri.parse(findDoctorBean.imagePic);
                    sdv_pic.setImageURI(uri);
                    tv_name.setText(findDoctorBean.doctorName);
                    tv_jobTitle.setText(findDoctorBean.jobTitle);
                    tv_inauguralHospital.setText(findDoctorBean.inauguralHospital);
                    tv_praiseNum.setText(findDoctorBean.praise);
                    tv_serverNum.setText("服务患者数:   "+findDoctorBean.serverNum);
                    tv_servicePrice.setText(findDoctorBean.servicePrice+"/次");
                }
            }
        });

    }

    private void initView() {
        inquiry_rv = (RecyclerView) findViewById(R.id.inquiry_rv);
        tv_zh = (TextView) findViewById(R.id.tv_zh);
        tv_hp = (TextView) findViewById(R.id.tv_hp);
        tv_zxs = (TextView) findViewById(R.id.tv_zxs);
        tv_price = (TextView) findViewById(R.id.tv_price);
        sdv_pic = (SimpleDraweeView) findViewById(R.id.sdv_pic);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_jobTitle = (TextView) findViewById(R.id.tv_jobTitle);
        tv_inauguralHospital = (TextView) findViewById(R.id.tv_inauguralHospital);
        tv_praiseNum = (TextView) findViewById(R.id.tv_praiseNum);
        tv_serverNum = (TextView) findViewById(R.id.tv_serverNum);
        tv_servicePrice = (TextView) findViewById(R.id.tv_servicePrice);
        tv_ask = (TextView) findViewById(R.id.tv_ask);
        sdv_left = (SimpleDraweeView) findViewById(R.id.sdv_left);
        sdv_right = (SimpleDraweeView) findViewById(R.id.sdv_right);
        rv_doctor = (RecyclerView) findViewById(R.id.rv_doctor);
    }
    //医生列表
    private class FindDoctorP implements DataCall<List<FindDoctorBean>> {
        @Override
        public void success(List<FindDoctorBean> data, Object... args) {
            findDoctorAdapter.OnClear();
            findDoctorAdapter.OnAddAll(data);
            findDoctorAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(FindDoctorActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //科室类目
    private class MainDepartPresenter implements DataCall<List<DepartListBean>> {
        @Override
        public void success(List<DepartListBean> data, Object... args) {
            findDepartAdapter.OnClear();
            findDepartAdapter.OnAddAll(data);
            findDepartAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(FindDoctorActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

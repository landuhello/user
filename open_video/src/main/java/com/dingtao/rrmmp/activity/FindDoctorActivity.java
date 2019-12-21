package com.dingtao.rrmmp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.DepartListBean;
import com.dingtao.common.bean.FindDoctorBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.rrmmp.adapter.FindDepartAdapter;
import com.dingtao.rrmmp.adapter.FindDoctorAdapter;
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
    private ImageView iv_xq;
    private RecyclerView rv_doctor;
    private FindDepartAdapter findDepartAdapter;
    private FindDoctorAdapter findDoctorAdapter;
    int conditionId=1;
    int findPosition;
    int doctorinfoid;
    private int doctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        initView();

        tv_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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

        findDepartAdapter.setmPostion(Integer.valueOf(dids));
        findPosition=Integer.valueOf(dids);
        final FindDoctorPresenter findDoctorPresenter=new FindDoctorPresenter(new FindDoctorP());
        findDoctorPresenter.reqeust(Integer.valueOf(dids),conditionId,0,1,5);

        findDepartAdapter.setDepartBack(new FindDepartAdapter.DepartBack() {
            @Override
            public void backId(int did,int postionfind) {
                findDoctorAdapter.setmPostion(did);
                findDepartAdapter.setMdids(postionfind);
                findPosition=did;
                FindDoctorPresenter findDoctorPresenter=new FindDoctorPresenter(new FindDoctorP());
                findDoctorPresenter.reqeust(did,conditionId,0,1,5);
            }
        });

        findDoctorAdapter = new FindDoctorAdapter();
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(FindDoctorActivity.this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_doctor.setLayoutManager(linearLayoutManager1);
        rv_doctor.setAdapter(findDoctorAdapter);

        findDoctorAdapter.setFindDoctorBack(new FindDoctorAdapter.FindDoctorBack() {
            @Override
            public void findBack(final FindDoctorBean findDoctorBean, int mi) {
                if (findDoctorBean != null) {
                    findDoctorAdapter.setmPostion(mi);
                    Uri uri=Uri.parse(findDoctorBean.imagePic);
                    sdv_pic.setImageURI(uri);
                    tv_name.setText(findDoctorBean.doctorName);
                    tv_jobTitle.setText(findDoctorBean.jobTitle);
                    tv_inauguralHospital.setText(findDoctorBean.inauguralHospital);
                    tv_praiseNum.setText("好评率:"+findDoctorBean.praise);
                     tv_servicePrice.setText(findDoctorBean.servicePrice+"H币/次");
                    doctorId = findDoctorBean.doctorId;
                }
            }
        });

        //医生详情
        iv_xq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("doctorids",doctorId+"");
                startActivity(intent);
            }
        });

        //综合
        tv_zh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionId=1;
                FindDoctorPresenter findDoctorPresenter=new FindDoctorPresenter(new FindDoctorP());
                findDoctorPresenter.reqeust(findPosition,conditionId,0,1,5);
            }
        });
        //好评
        tv_hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionId=2;
                FindDoctorPresenter findDoctorPresenter=new FindDoctorPresenter(new FindDoctorP());
                findDoctorPresenter.reqeust(findPosition,conditionId,0,1,5);
            }
        });
        //咨询数
        tv_zxs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionId=3;
                FindDoctorPresenter findDoctorPresenter=new FindDoctorPresenter(new FindDoctorP());
                findDoctorPresenter.reqeust(findPosition,conditionId,0,1,5);
            }
        });
        //价格
        tv_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionId=4;
                FindDoctorPresenter findDoctorPresenter=new FindDoctorPresenter(new FindDoctorP());
                findDoctorPresenter.reqeust(findPosition,conditionId,1,1,5);
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
        iv_xq = (ImageView) findViewById(R.id.iv_xq);
    }
    //医生列表
    private class FindDoctorP implements DataCall<List<FindDoctorBean>> {
        @Override
        public void success(final List<FindDoctorBean> data, Object... args) {
            findDoctorAdapter.OnClear();
            findDoctorAdapter.OnAddAll(data);
            findDoctorAdapter.notifyDataSetChanged();

            Uri uri=Uri.parse(data.get(0).imagePic);
            sdv_pic.setImageURI(uri);
            tv_name.setText(data.get(0).doctorName);
            tv_jobTitle.setText(data.get(0).jobTitle);
            tv_inauguralHospital.setText(data.get(0).inauguralHospital);
            tv_praiseNum.setText("好评率:"+data.get(0).praise);
            tv_serverNum.setText("服务患者数:   "+data.get(0).serverNum);
            tv_servicePrice.setText(data.get(0).servicePrice+"H币/次");

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

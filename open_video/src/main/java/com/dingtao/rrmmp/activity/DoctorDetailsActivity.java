package com.dingtao.rrmmp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtao.common.bean.DoctorComment;
import com.dingtao.common.bean.DoctorReceiveGift;
import com.dingtao.common.bean.FindDoctorInfoBean;
import com.dingtao.common.bean.LoginBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.rrmmp.adapter.DoctorInfoLwAdapter;
import com.dingtao.rrmmp.adapter.DoctorInfoPlAdapter;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.presenter.DoctorGuanZhuPresenter;
import com.dingtao.rrmmp.presenter.DoctorQuXiaoGuanZhuPresenter;
import com.dingtao.rrmmp.presenter.FindDoctorInfoPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorDetailsActivity extends AppCompatActivity{

    private ImageView d_back_img;
    private SimpleDraweeView d_simple;
    private TextView d_name;
    private TextView d_zy;
    private ImageView d_gz_n;
    private ImageView d_gz_s;
    private TextView d_dz;
    private TextView d_hp;
    private TextView d_hzs;
    private View d_view;
    private RecyclerView d_lw_recycler;
    private View d_view1;
    private TextView d_jj;
    private View d_view2;
    private TextView d_ly;
    private View d_view3;
    private TextView d_pl;
    private RecyclerView d_pl_recycler;
    private TextView d_ckgd;
    private TextView d_bi,d_plnum;
    private Button d_btn_zx;
    private String mSessionId;
    private int mId;
    private DoctorInfoLwAdapter doctorInfoLwAdapter;
    private DoctorInfoPlAdapter doctorInfoPlAdapter;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        EventBus.getDefault().register(this);
        initView();

        d_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        final String doctorids = intent.getStringExtra("doctorids");

        if (mId!=0&&mSessionId!=null){
            Toast.makeText(this, doctorids, Toast.LENGTH_SHORT).show();
            FindDoctorInfoPresenter findDoctorInfoPresenter=new FindDoctorInfoPresenter(new FindDoctorInfoP());
            findDoctorInfoPresenter.reqeust(mId,mSessionId,Integer.valueOf(doctorids));
        }else {
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                    .navigation(DoctorDetailsActivity.this);
        }

        doctorInfoLwAdapter = new DoctorInfoLwAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DoctorDetailsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        d_lw_recycler.setLayoutManager(linearLayoutManager);
        d_lw_recycler.setAdapter(doctorInfoLwAdapter);
        doctorInfoLwAdapter.notifyDataSetChanged();

        doctorInfoPlAdapter = new DoctorInfoPlAdapter();
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(DoctorDetailsActivity.this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        d_pl_recycler.setLayoutManager(linearLayoutManager1);
        d_pl_recycler.setAdapter(doctorInfoPlAdapter);
        doctorInfoPlAdapter.notifyDataSetChanged();

        d_gz_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mId!=0&&mSessionId!=null){
                    DoctorGuanZhuPresenter doctorGuanZhuPresenter = new DoctorGuanZhuPresenter(new GuanZhuP());
                    doctorGuanZhuPresenter.reqeust(mId,mSessionId,Integer.valueOf(doctorids));
                }else {
                    ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                            .navigation(DoctorDetailsActivity.this);
                }
            }
        });

        d_gz_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mId!=0&&mSessionId!=null){
                    DoctorQuXiaoGuanZhuPresenter doctorQuXiaoGuanZhuPresenter = new DoctorQuXiaoGuanZhuPresenter(new QuXiaoGuanZhuP());
                    doctorQuXiaoGuanZhuPresenter.reqeust(mId,mSessionId,Integer.valueOf(doctorids));
                }else {
                    ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                            .navigation(DoctorDetailsActivity.this);
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void dologin(LoginBean bean) {
        mSessionId = bean.sessionId;
        mId = bean.id;
    }

    private void initView() {
        d_back_img = (ImageView) findViewById(R.id.d_back_img);
        d_simple = (SimpleDraweeView) findViewById(R.id.d_simple);
        d_name = (TextView) findViewById(R.id.d_name);
        d_zy = (TextView) findViewById(R.id.d_zy);
        d_gz_n = (ImageView) findViewById(R.id.d_gz_n);
        d_gz_s = (ImageView) findViewById(R.id.d_gz_s);
        d_dz = (TextView) findViewById(R.id.d_dz);
        d_hp = (TextView) findViewById(R.id.d_hp);
        d_hzs = (TextView) findViewById(R.id.d_hzs);
        d_view = (View) findViewById(R.id.d_view);
        d_lw_recycler = (RecyclerView) findViewById(R.id.d_lw_recycler);
        d_view1 = (View) findViewById(R.id.d_view1);
        d_jj = (TextView) findViewById(R.id.d_jj);
        d_view2 = (View) findViewById(R.id.d_view2);
        d_ly = (TextView) findViewById(R.id.d_ly);
        d_view3 = (View) findViewById(R.id.d_view3);
        d_pl = (TextView) findViewById(R.id.d_pl);
        d_pl_recycler = (RecyclerView) findViewById(R.id.d_pl_recycler);
        d_ckgd = (TextView) findViewById(R.id.d_ckgd);
        d_bi = (TextView) findViewById(R.id.d_bi);
        d_plnum = (TextView) findViewById(R.id.d_plnum);
        d_btn_zx = (Button) findViewById(R.id.d_btn_zx);
    }

    private class FindDoctorInfoP implements DataCall<FindDoctorInfoBean> {
        @Override
        public void success(FindDoctorInfoBean data, Object... args) {

            if (data==null){
                return;
            }else {
                //形象照
                Uri uri=Uri.parse(data.imagePic);
                d_simple.setImageURI(uri);
                //名字
                d_name.setText(data.doctorName);
                d_zy.setText(data.jobTitle);
                d_dz.setText(data.inauguralHospital);
                d_hp.setText("好评率  "+data.praise);
                d_hzs.setText("服务患者数 "+data.serverNum);
                d_jj.setText(data.personalProfile);
                d_ly.setText(data.goodField+"");
                d_plnum.setText("("+data.commentNum+"条评论)");

                if (data.followFlag==1){
                    d_gz_s.setVisibility(View.VISIBLE);
                }else {
                    d_gz_s.setVisibility(View.GONE);
                }

                List<DoctorReceiveGift> doctorReceiveGiftList = data.doctorReceiveGiftList;
                doctorInfoLwAdapter.OnAddAll(doctorReceiveGiftList);
                doctorInfoLwAdapter.notifyDataSetChanged();

                List<DoctorComment> commentList = data.commentList;
                doctorInfoPlAdapter.OnAddAll(commentList);
                doctorInfoPlAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(DoctorDetailsActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class GuanZhuP implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {
            Toast.makeText(DoctorDetailsActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
            d_gz_s.setVisibility(View.VISIBLE);
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(DoctorDetailsActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private class QuXiaoGuanZhuP implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {
            Toast.makeText(DoctorDetailsActivity.this, "取消关注成功", Toast.LENGTH_SHORT).show();
            d_gz_s.setVisibility(View.GONE);
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(DoctorDetailsActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

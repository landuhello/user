package com.dingtao.rrmmp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dingtao.common.bean.Commentontheist;
import com.dingtao.common.bean.Particulars;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.rrmmp.adapter.FindSickAdapter;
import com.dingtao.rrmmp.login.R;

import com.dingtao.rrmmp.login.R2;
import com.dingtao.rrmmp.presenter.CommentPresenter;
import com.dingtao.rrmmp.presenter.FindSickPresenter;
import com.dingtao.rrmmp.presenter.ParticularsPresenter;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;

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
    private PopupWindow  popupWindow;
    private CommentPresenter commentPresenter;
    private int sickCircleId;
    private FindSickPresenter findSickPresenter;
    private LinearLayoutManager linearLayoutManager;
    private FindSickAdapter findSickAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_conditions;
    }

    @SuppressLint("WrongConstant")
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
        //创建发表评论p层
        commentPresenter = new CommentPresenter(new CommmitCall());
        //创建评论列表p层
        findSickPresenter = new FindSickPresenter(new CommentOntHeList());
        //请求
        particularsPresenter.reqeust(sick);
        //点击弹出pop
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //评论列表展示请求
                 findSickPresenter.reqeust(sickCircleId,2,5);
                 showPopupWindow();
            }
        });
    }

    @Override
    protected void destoryData() {

    }
    //創建內部类對象
    class PatieieCall implements DataCall<Particulars> {
        @Override
        public void success(Particulars data, Object... args) {
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
            //获取病情详情的id
            sickCircleId = data.sickCircleId;
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //创建发表评论的内部类
    class CommmitCall implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {

        }
        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //评接展示数据
    class CommentOntHeList implements DataCall<List<Commentontheist>> {
        @Override
        public void success(List<Commentontheist> data, Object... args) {
            findSickAdapter.clear();
            findSickAdapter.addAll(data);
            findSickAdapter.notifyDataSetChanged();
        }
        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //弹出pop的方法
    private void showPopupWindow() {
        //找到pop弹窗布局
        View view = LayoutInflater.from(ConditionsActivity.this).inflate(R.layout.pop_item, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(view);
        final ImageView imageView = view.findViewById(R.id.image_pop);
        RecyclerView rv = view.findViewById(R.id.rv_pop);
        final ImageView tv = view.findViewById(R.id.pop_tv);
        final TextView textView = view.findViewById(R.id.pop_liuxia);
        final RelativeLayout relativeLayout = view.findViewById(R.id.pop_rel);
        final EditText editText = view.findViewById(R.id.pop_et);
        ImageView send = view.findViewById(R.id.pop_send);
        //创建管理器
        linearLayoutManager = new LinearLayoutManager(ConditionsActivity.this);
        rv.setLayoutManager(linearLayoutManager);
        //创建适配器
        findSickAdapter = new FindSickAdapter(ConditionsActivity.this);
        rv.setAdapter(findSickAdapter);
        //展示
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //点击显示隐藏
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });
        //点击发送评论
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入控件的值
                String ss = editText.getText().toString();
                //发送评论
                commentPresenter.reqeust(418,"1575889818367418",sickCircleId,ss);
            }
        });
        //activity的布局
        View rootView = LayoutInflater.from(ConditionsActivity.this).inflate(R.layout.activity_conditions, null);
        //位置
        //设置高度
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.update();
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }
}

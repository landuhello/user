package com.example.personaldetails.activity;


import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.dingtao.common.bean.LoginBean;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.util.Constant;
import com.example.personaldetails.R;
import com.example.personaldetails.R2;
import com.example.personaldetails.activity.renwu.TaskActivity;
import com.example.personaldetails.activity.set.SettingActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.cardview.widget.CardView;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.ACTIVITY_URL_persion)
public class PersionActivity extends WDActivity {


    @BindView(R2.id.title_back)
    ImageView titleBack;
    @BindView(R2.id.title_message)
    ImageView titleMessage;
    @BindView(R2.id.rela1)
    RelativeLayout rela1;
    @BindView(R2.id.header)
    SimpleDraweeView header;
    @BindView(R2.id.name)
    TextView name;
    @BindView(R2.id.sign)
    TextView sign;
    @BindView(R2.id.rela2)
    RelativeLayout rela2;
    @BindView(R2.id.card_l1)
    LinearLayout cardL1;
    @BindView(R2.id.card_l2)
    LinearLayout cardL2;
    @BindView(R2.id.card)
    CardView card;
    @BindView(R2.id.l1)
    LinearLayout l1;
    @BindView(R2.id.l2)
    LinearLayout l2;
    @BindView(R2.id.l3)
    LinearLayout l3;
    @BindView(R2.id.l4)
    LinearLayout l4;
    @BindView(R2.id.l5)
    LinearLayout l5;
    @BindView(R2.id.l6)
    LinearLayout l6;
    @BindView(R2.id.l7)
    LinearLayout l7;
    @BindView(R2.id.l8)
    LinearLayout l8;
    @BindView(R2.id.l9)
    LinearLayout l9;
    private int mId;
    private String mNickName;
    private String mHeadPic;
    private Uri mParse;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_persion;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void dorl(LoginBean loginBean) {
        mId = loginBean.id;

        mNickName = loginBean.nickName;
        mHeadPic = loginBean.headPic;
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R2.id.title_back, R2.id.title_message, R2.id.sign, R2.id.card_l1, R2.id.card_l2, R2.id.l1, R2.id.l2, R2.id.l3, R2.id.l4, R2.id.l5, R2.id.l6, R2.id.l7, R2.id.l8, R2.id.l9})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.title_back) {
            PersionActivity.this.finish();

        } else if (id == R.id.title_message) {

        } else if (id == R.id.sign) {

        } else if (id == R.id.card_l1) {

        } else if (id == R.id.card_l2) {

        } else if (id == R.id.l1) {
            if (mId != 0) {
                startActivity(new Intent(this, ArchivesActivity.class));
            } else {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                        .navigation(this);
            }

        } else if (id == R.id.l2) {
            if (mId != 0) {
                startActivity(new Intent(this, WalletActivity.class));
            } else {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                        .navigation(this);
            }

        } else if (id == R.id.l3) {

        } else if (id == R.id.l4) {

        } else if (id == R.id.l5) {

        } else if (id == R.id.l6) {

        } else if (id == R.id.l7) {

        } else if (id == R.id.l8) {

            if (mId != 0) {
                startActivity(new Intent(this, TaskActivity.class));
            } else {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                        .navigation(this);
            }
        } else if (id == R.id.l9) {
            if (mId != 0) {
                startActivity(new Intent(this, SettingActivity.class));
            } else {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                        .navigation(this);
            }
        }
    }
}

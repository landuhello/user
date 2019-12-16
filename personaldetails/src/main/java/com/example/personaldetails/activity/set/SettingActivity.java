package com.example.personaldetails.activity.set;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.dingtao.common.util.Constant;
import com.example.personaldetails.R;
import com.example.personaldetails.R2;
import com.example.personaldetails.activity.BaseActivity;
import com.example.personaldetails.activity.BasePresenter;
import com.example.personaldetails.activity.BaseUserTitle;
import com.example.personaldetails.activity.bean.IUserPresenterImpl;
import com.example.personaldetails.activity.bean.UserEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<IUserModelImpl, IUserPresenterImpl> implements IUserContract.IUserView {

    @BindView(R2.id.user_title)
    BaseUserTitle userTitle;
    @BindView(R2.id.head)
    SimpleDraweeView head;
    @BindView(R2.id.name)
    TextView name;
    @BindView(R2.id.dec)
    TextView dec;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        if (!SPUtils.getInstance("login").getBoolean("isLogin")) {
            name.setText("未登录");
            return;
        }
        presenter.UserMessage();
        TextView viewById = findViewById(R.id.cachesize);
        try {
            viewById.setText(DataCleanManager.getTotalCacheSize(this)+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void iniData() {
        userTitle.tName.setText("设置");
        userTitle.tBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected int bindlayout() {
        return R.layout.activity_setting;
    }

    @Override
    public BasePresenter initPresenter() {
        return new IUserPresenterImpl();
    }

    /*
     * 解析数据 设置头像,名称
     * */
    @Override
    public void UserMessage(Object obj) {
        UserEntity userEntity = (UserEntity) obj;
        Uri pic = Uri.parse(userEntity.getResult().getHeadPic());
        head.setImageURI(pic);
        name.setText(userEntity.getResult().getNickName());
    }

    @Override
    public void UserWhetherSign(Object obj) {

    }

    @Override
    public void UserSign(Object obj) {

    }

    @Override
    public void UserTask(Object obj) {

    }


    @OnClick({R2.id.rela_head, R2.id.rela_update, R2.id.rela_dac, R2.id.rela_light, R2.id.rela_version, R2.id.rela_help, R2.id.rela_us, R2.id.rela_friends, R2.id.loginout})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.rela_head) {
            if (SPUtils.getInstance("login").getBoolean("isLogin")) {
                sA(UserMessageActivity.class);
                return;
            }
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                    .navigation(this);
        } else if (id == R.id.rela_update) {
            if (SPUtils.getInstance("login").getBoolean("isLogin")) {
                //跳转改密码页面
                return;
            }
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                    .navigation(this);
        } else if (id == R.id.rela_dac) {
            TextView viewById = findViewById(R.id.cachesize);
            viewById.setText(0 + "MB");
            DataCleanManager.clearAllCache(this);
        } else if (id == R.id.rela_light) {
            //跳转屏幕亮度

        } else if (id == R.id.rela_version) {
        } else if (id == R.id.rela_help) {
        } else if (id == R.id.rela_us) {
        } else if (id == R.id.rela_friends) {
        } else if (id == R.id.loginout) {
        }
    }
}

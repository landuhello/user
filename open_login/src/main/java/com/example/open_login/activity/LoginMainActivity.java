package com.example.open_login.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.bean.LoginBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.MD5Utils;
import com.dingtao.common.util.UIUtils;
import com.example.open_login.R;
import com.example.open_login.R2;
import com.example.open_login.activity.base64.RsaCoder;
import com.example.open_login.activity.presenter.LoginPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = Constant.ACTIVITY_URL_login)
public class LoginMainActivity extends WDActivity {


    @BindView(R2.id.logo)
    ImageView logo;
    @BindView(R2.id.c)
    CardView c;
    @BindView(R2.id.email)
    EditText email;
    @BindView(R2.id.lock)
    ImageView lock;
    @BindView(R2.id.password)
    EditText password;
    @BindView(R2.id.eye)
    ImageView eye;
    @BindView(R2.id.login)
    TextView login;
    @BindView(R2.id.mes)
    TextView mes;
    @BindView(R2.id.reset_password)
    TextView resetPassword;
    @BindView(R2.id.register)
    TextView register;
    @BindView(R2.id.weixin)
    ImageView weixin;
    private LoginPresenter mLoginPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_main;
    }

    @Override
    protected void initView() {
        mLoginPresenter = new LoginPresenter(new Log());


    }

    @Override
    protected void destoryData() {
        mLoginPresenter.unBind();
    }


    private boolean pasVisibile = false;

    @OnClick({R2.id.eye, R2.id.login, R2.id.register})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.eye) {
            if (pasVisibile) {//密码显示，则隐藏
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                pasVisibile = false;
            } else {//密码隐藏则显示
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                pasVisibile = true;
            }
        } else if (id == R.id.login) {
            String trim = email.getText().toString().trim();
            String trim1 = password.getText().toString().trim();
            String encryptone = RsaCoder.encryptByPublicKey(trim1);


            if (TextUtils.isEmpty(trim)) {
                UIUtils.showToastSafe("请输入正确的手机号");
                return;
            }
            if (TextUtils.isEmpty(trim1)) {
                UIUtils.showToastSafe("请输入密码");
                return;
            }
            mLoadDialog.show();
            mLoginPresenter.reqeust(trim, encryptone);

            android.util.Log.d("sessid",encryptone);

        } else if (id == R.id.register) {
            Intent intent = new Intent(LoginMainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    private class Log implements DataCall<LoginBean> {


        @Override
        public void success(LoginBean data, Object... args) {
            Toast.makeText(LoginMainActivity.this, "欢迎" + data.userName + "使用", Toast.LENGTH_SHORT).show();
            if (data != null) {
                intentByRouter(Constant.ACTIVITY_URL_LOGIN);

                EventBus.getDefault().postSticky(data);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}

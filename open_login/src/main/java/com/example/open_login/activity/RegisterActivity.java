package com.example.open_login.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.MD5Utils;
import com.dingtao.common.util.UIUtils;
import com.example.open_login.R;
import com.example.open_login.R2;
import com.example.open_login.activity.base64.RsaCoder;
import com.example.open_login.activity.presenter.CodePresenter;
import com.example.open_login.activity.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends WDActivity {


    @BindView(R2.id.logo)
    ImageView logo;
    @BindView(R2.id.c)
    CardView c;
    @BindView(R2.id.email_icon)
    ImageView emailIcon;
    @BindView(R2.id.email)
    EditText email;
    @BindView(R2.id.email_bt)
    TextView emailBt;
    @BindView(R2.id.code_icon)
    ImageView codeIcon;
    @BindView(R2.id.code)
    EditText code;
    @BindView(R2.id.lock)
    ImageView lock;
    @BindView(R2.id.password)
    EditText password;
    @BindView(R2.id.eye)
    ImageView eye;
    @BindView(R2.id.lock2)
    ImageView lock2;
    @BindView(R2.id.password2)
    EditText password2;
    @BindView(R2.id.eye2)
    ImageView eye2;
    @BindView(R2.id.register)
    TextView register;
    private RegisterPresenter mRegisterPresenter;
    private CodePresenter mCodePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mRegisterPresenter = new RegisterPresenter(new Reg());
        mCodePresenter = new CodePresenter(new Code());

    }

    @Override
    protected void destoryData() {
        mRegisterPresenter.unBind();
        mCodePresenter.unBind();
    }


    private boolean pasVisibile = false;


    @OnClick({R2.id.email_bt, R2.id.eye, R2.id.eye2, R2.id.register})
    public void onViewClicked(View view) {

        int id = view.getId();
        if (id == R.id.email_bt) {
            Toast.makeText(this, "点击事件", Toast.LENGTH_SHORT).show();
            String trim2 = email.getText().toString().trim();
            mCodePresenter.reqeust(trim2);
        } else if (id == R.id.eye) {
            if (pasVisibile) {//密码显示，则隐藏
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                pasVisibile = false;
            } else {//密码隐藏则显示
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                pasVisibile = true;
            }
        } else if (id == R.id.eye2) {
            if (pasVisibile) {//密码显示，则隐藏
                password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                pasVisibile = false;
            } else {//密码隐藏则显示
                password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                pasVisibile = true;
            }
        } else if (id == R.id.register) {
            String trim = email.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String password = password2.getText().toString().trim();
            String trim1 = code.getText().toString().trim();
            String encryptone = RsaCoder.encryptByPublicKey(pass);
            String encrypttwo = RsaCoder.encryptByPublicKey(password);


            if (TextUtils.isEmpty(trim)) {
                UIUtils.showToastSafe("请输入正确的手机号");
                return;
            }
            if (TextUtils.isEmpty(pass)) {
                UIUtils.showToastSafe("请输入密码");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                UIUtils.showToastSafe("请输入密码");
                return;
            }
            mLoadDialog.show();
            mRegisterPresenter.reqeust(trim, trim1, encryptone, encrypttwo);
        }
    }
//注册账号
    private class Reg implements DataCall {


        @Override
        public void success(Object data, Object... args) {
            mLoadDialog.cancel();
            UIUtils.showToastSafe("注册成功，请登录");
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            mLoadDialog.cancel();
            UIUtils.showToastSafe(data.getCode() + " " + data.getDisplayMessage());
        }
    }
//发送验证码
    private class Code implements DataCall<Result> {





    @Override
    public void success(Result data, Object... args) {
        if ("0000".equals(data.getStatus())&&data!=null){
            startActivity(new Intent(RegisterActivity.this,LoginMainActivity.class));
        }
    }

    @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(RegisterActivity.this, data.getMessage() + "", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.personaldetails.activity.set;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.personaldetails.R;
import com.example.personaldetails.R2;
import com.example.personaldetails.activity.BaseActivity;
import com.example.personaldetails.activity.BasePresenter;
import com.example.personaldetails.activity.BaseUserTitle;


import butterknife.BindView;
import butterknife.OnClick;

/*
*
* 改密码
* */
public class PasswordActivity extends BaseActivity<SettingModelImpl, SettingPresenterImpl> implements SettingContract.SettingView {


    @BindView(R2.id.user_title)
    BaseUserTitle userTitle;
    @BindView(R2.id.old_password)
    EditText oldPassword;
    @BindView(R2.id.new_password)
    EditText newPassword;
    @BindView(R2.id.enter_password)
    EditText enterPassword;

    @Override
    protected void iniData() {
        userTitle.tBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userTitle.tName.setText("修改密码");
    }

    @Override
    protected int bindlayout() {
        return R.layout.activity_password;
    }

    @Override
    public BasePresenter initPresenter() {
        return new SettingPresenterImpl();
    }

    @Override
    public void updateP(Object obj) {
        MessageEntity messageEntity = (MessageEntity) obj;
        Log.e("222", "update: " + messageEntity.toString());
        if (messageEntity.getStatus().equals("0000")) {
            ToastUtils.showShort(messageEntity.getMessage());
            oldPassword.setText("");
            newPassword.setText("");
            enterPassword.setText("");
        } else {
            ToastUtils.showShort(messageEntity.getMessage());
        }
    }

    @OnClick(R2.id.update)
    public void onViewClicked() {
        try {
            presenter.updateP(SPUtils.getInstance("login").getString("pwd"), RsaCoder.encryptByPublicKey("12345"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

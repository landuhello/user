package com.example.patientscircle.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.dingtao.common.core.hello.BasePresenter;
import com.dingtao.common.core.hello.IBaseModel;
import com.dingtao.common.core.hello.IBaseView;
import com.example.patientscircle.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *@Auther:陈浩
 *@Date: 2019/8/2
 *@Time:16:50
 *@Description:${DESCRIPTION}
 * */
public abstract class BaseActivity<M extends IBaseModel, P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    public P presenter;
    public M model;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindlayout());
        setBar(0);
        unbinder = ButterKnife.bind(this);
        presenter = (P) initPresenter();
        if (presenter != null) {
            model = (M) presenter.getModel();
            if (model != null) {
                presenter.attach( this);
            }
        }
        //数据处理
        iniData();
    }







    /**
     * 带参数的颜色值
     *
     * @param b
     */
    public void setBar(int b) {
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(this, b);
    }

    /**
     * 数据处理
     */
    protected abstract void iniData();

    /**
     * 绑定布局
     *
     * @return
     */
    protected abstract int bindlayout();

    /**
     * 判断网络状态
     *
     * @return
     */
    public Boolean isNet() {
        boolean connected = NetworkUtils.isConnected();
        return connected;
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettach();
            presenter=null;
        }

        if (unbinder != null) {
            unbinder.unbind();
            unbinder=null;
        }
    }



}

package com.example.personaldetails.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.dingtao.common.core.hello.IBaseModel;
import com.example.personaldetails.R;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *@Auther:老刘
 *@Date: 2019/12/12
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
     * 跳转页面
     *
     * @param clz
     */
    public void sA(Class<? extends Activity> clz) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        startActivity(intent);

    }

    /**
     * [携带数据的页面跳转]
     */
    public void sA(Class<? extends Activity> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);

    }

    /**
     * 设置沉浸式
     *
     * @param
     */
    public void setBar() {

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

    /***
     * 按返回键动画
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}

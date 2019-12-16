package com.dingtao.rrmmp.ibase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.dingtao.common.core.hello.IBaseModel;
import com.dingtao.rrmmp.ibase.IBaseView;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * data:基类
 * author:老刘（）
 * function:这是干什么的
 */
public abstract class BaseFragment<M extends IBaseModel, P extends BasePresenter> extends Fragment implements IBaseView {
    public P presenter;
    public M model;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(bindlayout(), container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = (P) initPresenter();
        if (presenter != null) {
            model = (M) presenter.getModel();
            if (model != null) {
                presenter.attach(this);
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
        intent.setClass(getActivity(), clz);
        startActivity(intent);
        getActivity().overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);

    }

    /**
     * [携带数据的页面跳转]
     */
    public void sA(Class<? extends Activity> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getActivity().overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);

    }



    /**
     * 带参数的颜色值
     *
     * @param b
     */
    public void setBar(int b) {
        BarUtils.setStatusBarLightMode(getActivity(), true);
        BarUtils.setStatusBarColor(getActivity(), b);
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
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettach();
        }

        if (unbinder != null) {
            unbinder.unbind();
        }
    }

}

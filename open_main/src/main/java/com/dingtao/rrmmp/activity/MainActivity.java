package com.dingtao.rrmmp.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.util.Constant;
import com.dingtao.rrmmp.main.R;

import com.dingtao.common.core.WDActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

@Route(path = Constant.ACTIVITY_URL_MAIN)
public class MainActivity extends WDActivity  {




    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        BGABanner bgaBanner = findViewById(R.id.bgabannerview);
        List<View> views = new ArrayList<>();
        views.add(View.inflate(this, R.layout.guideone, null));
        views.add(View.inflate(this, R.layout.guidetwo, null));
        views.add(View.inflate(this, R.layout.guidethree, null));
        views.add(View.inflate(this, R.layout.guidefour, null));
        View inflate = View.inflate(this, R.layout.guidefive, null);
        views.add(inflate);
        //点击事件跳转
        inflate.findViewById(R.id.five_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentByRouter(Constant.ACTIVITY_URL_LOGIN);

            }
        });
        bgaBanner.setData(views);
    }

    @Override
    protected void destoryData() {

    }



    Fragment currentFragment;

    /**
     * 展示Fragment
     */
    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.container, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }
}

package com.dingtao.rrmmp.activity;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.util.Constant;
import com.dingtao.rrmmp.fragment.HomeFrag;
import com.dingtao.rrmmp.fragment.VideoFrag;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.login.R2;

import java.util.ArrayList;

import butterknife.BindView;

@Route(path = Constant.ACTIVITY_URL_LOGIN)
public class LoginActivity extends WDActivity implements View.OnClickListener {


    @BindView(R2.id.home_pager)
    ViewPager home_pager;
    @BindView(R2.id.rb1)
    RadioButton home;
    @BindView(R2.id.rb3)
    RadioButton video;
    @BindView(R2.id.rg)
    RadioGroup rg;
    @BindView(R2.id.advisory)
    ImageView advisory;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //点击视频隐藏
        home.setOnClickListener(this);
        video.setOnClickListener(this);
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFrag());
        fragments.add(new VideoFrag());
        //内部适配器
        home_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        //滑动监听
        home_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        advisory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_patient);
                Toast.makeText(LoginActivity.this, "病友圈模块！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void destoryData() {


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rb1) {
            home_pager.setCurrentItem(0);

        } else if (i == R.id.rb3) {
            home_pager.setCurrentItem(1);

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int tag = intent.getIntExtra("tag", 0);
        home_pager.setCurrentItem(tag );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}

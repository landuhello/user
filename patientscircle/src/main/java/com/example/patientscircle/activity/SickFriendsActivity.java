package com.example.patientscircle.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.PatientsBean.Administrative;
import com.dingtao.common.bean.PatientsBean.Stateofanillness;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.core.hello.BasePresenter;
import com.dingtao.common.util.Constant;
import com.example.patientscircle.R;
import com.example.patientscircle.R2;
import com.example.patientscircle.adapter.AdministrativeAdapter;
import com.example.patientscircle.adapter.StateofanillnessAdapter;
import com.example.patientscircle.bean.CircleListsBean;
import com.example.patientscircle.bean.HomeWzzxEntity;
import com.example.patientscircle.presenter.AdministrativePresenter;
import com.example.patientscircle.presenter.StateofanillnessPresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.dingtao.common.core.WDApplication.getContext;
@Route(path = Constant.ACTIVITY_URL_patient)
public class SickFriendsActivity extends BaseActivity<SickFriendsModelImpl, SickFriendsPresenterImpl> implements ISickFriendsContract.SickFriendsView {


    @BindView(R2.id.patient_circle_tab)
    TabLayout patientCircleTab;
    @BindView(R2.id.patient_circle_recy)
    XRecyclerView patientCircleRecy;
    @BindView(R2.id.friends_home_search)
    ImageView searchimg;
    @BindView(R2.id.friends_home_head)
    ImageView friendsHomeHead;
    @BindView(R2.id.patient_home_search)
    EditText patientHomeSearch;
    @BindView(R2.id.friends_home_rbbell)
    ImageView friendsHomeRbbell;
    @BindView(R2.id.friends_home_tv)
    TextView friendsHomeTv;
    @BindView(R2.id.friends_home_titile)
    TextView friendsHomeTitile;
    @BindView(R2.id.friends_home_appbar)
    AppBarLayout friendsHomeAppbar;
    @BindView(R2.id.patientadvisory)
    ImageView patientadvisory;
    @BindView(R2.id.patienthome)
    RadioButton patienthome;
    @BindView(R2.id.patientvideo)
    RadioButton patientvideo;
    @BindView(R2.id.patientvg)
    RadioGroup patientvg;
    private ArrayList<CircleListsBean.ResultBean> resultBeans = new ArrayList<>();
    private PatientHomeAdpter patientAdpter;
    private int page = 1;
    private int mdepartmentId = 7;

    @Override
    protected void iniData() {
        iniUi();
        patientadvisory.setOnClickListener(new NoDoubleClickListener() {

            @Override
            protected void onNoDoubleClick(View v) {

                startActivity(new Intent(getContext(),PublishedcircleActivity.class));
            }
        });


        if (SPUtils.getInstance("user") != null) {
            String head = SPUtils.getInstance("user").getString("headPic");

        }
        setBar(0);
        iniPC();
        //点击搜索显示搜索隐藏tab
        searchimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏头像
                friendsHomeHead.setVisibility(View.GONE);
                //显示搜索
                patientHomeSearch.setVisibility(View.VISIBLE);
                friendsHomeTv.setVisibility(View.VISIBLE);
                friendsHomeAppbar.setExpanded(false, true);
            }
        });
        //滑动显示隐藏appbar
        friendsHomeAppbar.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (i < -80) {
                    //显示搜索
                    //隐藏头像
                    friendsHomeHead.setVisibility(View.GONE);
                    patientHomeSearch.setVisibility(View.VISIBLE);
                    friendsHomeTv.setVisibility(View.VISIBLE);
                }
                if (i == 0) {
                    //头像
                    friendsHomeHead.setVisibility(View.VISIBLE);
                    //搜索
                    patientHomeSearch.setVisibility(View.GONE);
                    //文字
                    friendsHomeTv.setVisibility(View.GONE);
                }
                // L.e("222", i + "");
            }
        });
        //搜素更多
        searchMore();
    }

    private void iniUi() {

        patientvideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Bundle bundle=new Bundle();
                    bundle.putInt("tag",1);
                    //首页
                }
            }
        });
        patienthome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ARouter.getInstance().build(Constant.ACTIVITY_URL_MAIN);

//                    overridePendingTransition(com.wd.base_core.R.animator.slide_in_left, com.wd.base_core.R.animator.slide_out_right);
                    finish();
                }
            }
        });

    }

    //搜素更多
    private void searchMore() {
        patientHomeSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“搜索”键*/
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String key = patientHomeSearch.getText().toString().trim();
                    String s = patientHomeSearch.getText().toString();
                    presenter.requestSearchPatient(s);
                    resultBeans.clear();
                    KeyboardUtils.hideSoftInput(SickFriendsActivity.this);
                    return true;
                }
                return false;
            }
        });


    }


    //初始化病友圈
    private void iniPC() {
        presenter.requestDepartmentList();//请求科室列表
        presenter.requestSickList(mdepartmentId + "", page + "", "8");
        //tab选中
        initabClick();
        //下拉加载
        iniRecyLoading();
    }

    private void iniRecyLoading() {
        patientCircleRecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                resultBeans.clear();
                page = 1;
                presenter.requestSickList(mdepartmentId + "", page + "", "8");
                patientAdpter.notifyDataSetChanged();
                patientCircleRecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.requestSickList(mdepartmentId + "", page + "", "5");
                patientCircleRecy.loadMoreComplete();
                patientAdpter.notifyDataSetChanged();
            }
        });

    }

    //tab数据改变
    private void initabClick() {
        patientCircleTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                page = 1;
                friendsHomeTv.setText(tab.getText().toString());
                int tagId = (int) tab.getTag();
                mdepartmentId = tagId;
                resultBeans.clear();
                presenter.requestSickList(mdepartmentId + "", page + "", "8");
                //初始化病友圈
                LinearLayoutManager linearLayoutManager = new MyLinnerLayoutManger(SickFriendsActivity.this);
                patientCircleRecy.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int bindlayout() {
        return R.layout.friends_home;
    }

    @Override
    public BasePresenter initPresenter() {
        return new SickFriendsPresenterImpl();
    }

    //病友圈列表
    @Override
    public void showSickList(Object o) {
        CircleListsBean circleListsBean = (CircleListsBean) o;

        if (circleListsBean.getResult().size() != 0) {
            for (int i = 0; i < circleListsBean.getResult().size(); i++) {
                resultBeans.add(circleListsBean.getResult().get(i));
            }
        } else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
        //设置适配器
        if (patientAdpter == null) {
            patientAdpter = new PatientHomeAdpter(this, resultBeans);
            patientCircleRecy.setAdapter(patientAdpter);
        }
        patientAdpter.notifyDataSetChanged();
    }

    //科室列表
    @Override
    public void showDepartmentList(Object obj) {
        HomeWzzxEntity wzzxEntity = (HomeWzzxEntity) obj;
        if (wzzxEntity.getResult().size() != 0) {
            for (int i = 0; i < wzzxEntity.getResult().size(); i++) {
                patientCircleTab.addTab(patientCircleTab.newTab().setText(wzzxEntity.getResult().get(i).getDepartmentName()).setTag(wzzxEntity.getResult().get(i).getId()));
            }
        }
    }

    //搜素结果
    @Override
    public void showSearchPatient(Object obj) {
        CircleListsBean circleListsBean = (CircleListsBean) obj;

        if (circleListsBean.getResult().size() != 0) {
            for (int i = 0; i < circleListsBean.getResult().size(); i++) {
                resultBeans.add(circleListsBean.getResult().get(i));
            }
        } else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
        //设置适配器
        if (patientAdpter == null) {
            patientAdpter = new PatientHomeAdpter(this, resultBeans);
            patientCircleRecy.setAdapter(patientAdpter);
        }
        patientAdpter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (patientAdpter != null) {
            patientAdpter = null;
        }
    }
}

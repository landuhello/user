package com.example.patientscircle.activity;



import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.core.hello.BasePresenter;
import com.example.patientscircle.R;
import com.example.patientscircle.R2;
import com.example.patientscircle.bean.CircleListsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

public class UserpageActivity extends BaseActivity<UserPageModelImpl, UserPagePresenterImpl> implements IUserPageContract.IuserPageView {


    @BindView(R2.id.user_page_bg)
    ImageView userPageBg;
    @BindView(R2.id.user_page_headpic)
    ImageView userPageHeadpic;
    @BindView(R2.id.user_page_name)
    TextView userPageName;
    @BindView(R2.id.user_page_recy)
    XRecyclerView userPageRecy;
    private int page = 1;
    private PatientHomeAdpter patientAdpter;

    private ArrayList<CircleListsBean.ResultBean> resultBeans = new ArrayList<>();


    @Override
    protected void iniData() {

        presenter.drequestPatientreleaseList(getIntent().getIntExtra("patientUserId", 0) + "", page + "", "8");
        iniRecyLoading();
    }

    private void iniRecyLoading() {
        userPageRecy.setPullRefreshEnabled(false);
        Glide.with(this).load(getIntent().getStringExtra("headPic"))
                .apply(new RequestOptions().circleCrop())
                .into(userPageHeadpic);
        userPageName.setText(getIntent().getStringExtra("hickName") + "");
        userPageRecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                resultBeans.clear();
                page = 1;
                presenter.drequestPatientreleaseList(getIntent().getIntExtra("patientUserId", 0) + "", page + "", "8");
                patientAdpter.notifyDataSetChanged();
                userPageRecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.drequestPatientreleaseList(getIntent().getIntExtra("patientUserId", 0) + "", page + "", "5");
                userPageRecy.loadMoreComplete();
                patientAdpter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected int bindlayout() {
        return R.layout.activity_userpage;
    }

    @Override
    public BasePresenter initPresenter() {
        return new UserPagePresenterImpl();
    }

    /**
     * 列表
     *
     * @param obj
     */
    @Override
    public void showPatientreleaseList(Object obj) {
        CircleListsBean patientRelease = (CircleListsBean) obj;
        if (patientRelease.getResult().size() != 0) {
            for (int i = 0; i < patientRelease.getResult().size(); i++) {
                resultBeans.add(patientRelease.getResult().get(i));
            }
        } else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
        //设置适配器
        if (patientAdpter == null) {
            userPageRecy.setLayoutManager(new LinearLayoutManager(this));
            patientAdpter = new PatientHomeAdpter(this, resultBeans);
            userPageRecy.setAdapter(patientAdpter);
        }
        patientAdpter.notifyDataSetChanged();
        //Toast.makeText(this, patientRelease.getMessage(), Toast.LENGTH_SHORT).show();
    }


}

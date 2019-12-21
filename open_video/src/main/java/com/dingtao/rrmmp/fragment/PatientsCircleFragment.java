package com.dingtao.rrmmp.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.dingtao.common.bean.PatientsBean.Administrative;
import com.dingtao.common.bean.PatientsBean.Stateofanillness;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.rrmmp.activity.ConditionsActivity;
import com.dingtao.rrmmp.activity.SeekActivity;
import com.dingtao.rrmmp.adapter.AdministrativeAdapter;
import com.dingtao.rrmmp.adapter.StateofanillnessAdapter;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.login.R2;
import com.dingtao.rrmmp.presenter.AdministrativePresenter;
import com.dingtao.rrmmp.presenter.StateofanillnessPresenter;

import java.util.List;

import butterknife.BindView;

public class PatientsCircleFragment extends WDFragment {

    @BindView(R2.id.recycler1)
    RecyclerView recycler1;
    @BindView(R2.id.recycler2)
    RecyclerView recycler2;
    @BindView(R2.id.ming)
    TextView ming;
    @BindView(R2.id.information)
    ImageView information;
    @BindView(R2.id.seek)
    TextView seek;
    @BindView(R2.id.imagesearch)
    ImageView imagesearch;
    @BindView(R2.id.headportrait)
    ImageView headportrait;
    @BindView(R2.id.message)
    ImageView message;
    private AdministrativePresenter administrativePresenter;
    private AdministrativeAdapter administrativeAdapter;
    private StateofanillnessAdapter stateofanillnessAdapter;
    private StateofanillnessPresenter stateofanill;
    private int sickCircleId;
    private String title;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_patients_circle;
    }

    @Override
    protected void initView() {
        //创建p层
        administrativePresenter = new AdministrativePresenter(new Perss());
        //创建p层
        stateofanill = new StateofanillnessPresenter(new StateofanillnessCall());
        //创建管理类
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler1.setLayoutManager(linearLayoutManager);
        //创建管理器
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        recycler2.setLayoutManager(linearLayoutManager1);
        //设置适配器
        administrativeAdapter = new AdministrativeAdapter(getContext());
        recycler1.setAdapter(administrativeAdapter);
        //创建适配器
        stateofanillnessAdapter = new StateofanillnessAdapter(getActivity());
        recycler2.setAdapter(stateofanillnessAdapter);
        //请求
        administrativePresenter.reqeust();
        stateofanill.reqeust(1, 2, 5);

        //点击跳转搜索
        seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeekActivity.class);
                startActivity(intent);
            }
        });
        //跳转
        imagesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeekActivity.class);
                startActivity(intent);
            }
        });
    }

    //创建展示tablayout内部类
    class Perss implements DataCall<List<Administrative>> {
        @Override
        public void success(final List<Administrative> data, Object... args) {
            administrativeAdapter.addAll(data);
            administrativeAdapter.notifyDataSetChanged();
            administrativeAdapter.setOnItemCilckListener(new AdministrativeAdapter.OnItemCilckListener() {
                @Override
                public void onItemCilck(int position) {
                    stateofanill.reqeust(position, 2, 5);
                    administrativeAdapter.setmPosition(position);
                    administrativeAdapter.notifyDataSetChanged();
                }
            });

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //展示病情的内部类
    class StateofanillnessCall implements DataCall<List<Stateofanillness>> {
        @Override
        public void success(final List<Stateofanillness> data, Object... args) {
            stateofanillnessAdapter.clear();
            stateofanillnessAdapter.addAll(data);
            stateofanillnessAdapter.notifyDataSetChanged();
            //点击条目
            stateofanillnessAdapter.setOnItemCilckListener(new StateofanillnessAdapter.OnItemCilckListener() {
                @Override
                public void OnItemClick(View view, int position) {
                    for (int i = 0; i < data.size(); i++) {
                        title = data.get(position).title;
                        sickCircleId = data.get(position).sickCircleId;
                    }
                    //跳转
                    Intent intent = new Intent(getActivity(), ConditionsActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("sick", sickCircleId);
                    startActivity(intent);
                }
            });
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

}

package com.example.patientscircle.activity;


import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.bean.PatientsBean.Administrative;
import com.dingtao.common.bean.PatientsBean.Stateofanillness;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.example.patientscircle.R;
import com.example.patientscircle.R2;
import com.example.patientscircle.adapter.AdministrativeAdapter;
import com.example.patientscircle.adapter.StateofanillnessAdapter;
import com.example.patientscircle.presenter.AdministrativePresenter;
import com.example.patientscircle.presenter.StateofanillnessPresenter;

import java.util.List;

import butterknife.BindView;

import static com.dingtao.common.core.WDApplication.getContext;
@Route(path = Constant.ACTIVITY_URL_patient)
public class SickFriendsActivity extends WDActivity {
    private AdministrativePresenter administrativePresenter;
    private StateofanillnessPresenter stateofanill;
    private AdministrativeAdapter administrativeAdapter;
    private StateofanillnessAdapter stateofanillnessAdapter;
    private int sickCircleId;
    private String title;
    @BindView(R2.id.recycler1)
    RecyclerView recycler1;
    @BindView(R2.id.recycler2)
    RecyclerView recycler2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sick_friends;
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
        stateofanillnessAdapter = new StateofanillnessAdapter(getContext());
        recycler2.setAdapter(stateofanillnessAdapter);
        //请求
        administrativePresenter.reqeust();
        stateofanill.reqeust(1, 2, 5);
    }

    @Override
    protected void destoryData() {

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
                    stateofanill.reqeust(position,2,5);
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
                    for (int i = 0; i <data.size() ; i++) {
                        title = data.get(position).title;
                        sickCircleId = data.get(position).sickCircleId;
                    }
                    //跳转
                    Intent intent = new Intent(getContext(), ConditionsActivity.class);
                    intent.putExtra("title",title);
                    intent.putExtra("sick",sickCircleId);
                    startActivity(intent);
                }
            });
        }
        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}

package com.dingtao.rrmmp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dingtao.common.bean.DepartListBean;
import com.dingtao.common.bean.DiseaseListBean;
import com.dingtao.common.bean.LoginBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.rrmmp.adapter.BingZhengAdapter;
import com.dingtao.rrmmp.adapter.DiseaseAdapter;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.main.presenter.DepartPresenter;
import com.dingtao.rrmmp.main.presenter.DiseasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/6<p>
 * <p>更改时间：2019/12/6<p>
 */
public class BingZhengFragment extends Fragment {

    private RecyclerView leftrecycler,rightrecycler;
    private BingZhengAdapter bingZhengAdapter;
    private DiseaseAdapter diseaseAdapter;
    private String mSessionId;
    private int mId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_bing_zheng, container, false);
        leftrecycler = inflate.findViewById(R.id.leftrecycler);
        rightrecycler = inflate.findViewById(R.id.rightrecycler);
        EventBus.getDefault().register(this);
        return inflate;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //常见病症类目
        DepartPresenter departPresenter=new DepartPresenter(new DepartP());
        departPresenter.reqeust();

        //常见病症类目列表
        bingZhengAdapter = new BingZhengAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftrecycler.setLayoutManager(linearLayoutManager);
        leftrecycler.setAdapter(bingZhengAdapter);

        //根据常见病症类目id查询常见病症列表
        DiseasePresenter diseasePresenter=new DiseasePresenter(new DiseaseP());
        diseasePresenter.reqeust(7);

        bingZhengAdapter.setGetListener(new BingZhengAdapter.getListener() {
            @Override
            public void mlistener(int mposition) {
                bingZhengAdapter.setmPosition(mposition);
                bingZhengAdapter.notifyDataSetChanged();
            }
        });

        bingZhengAdapter.setBingZhengBack(new BingZhengAdapter.BingZhengBack() {
            @Override
            public void bingBack(int aid) {
                DiseasePresenter diseasePresenter1=new DiseasePresenter(new DiseaseP());
                diseasePresenter1.reqeust(aid);
            }
        });

        diseaseAdapter = new DiseaseAdapter();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rightrecycler.setLayoutManager(gridLayoutManager);
        rightrecycler.setAdapter(diseaseAdapter);
        diseaseAdapter.notifyDataSetChanged();

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void dologin(LoginBean bean) {
        mSessionId = bean.sessionId;
        mId = bean.id;
    }

    private class DepartP implements DataCall<List<DepartListBean>> {
        @Override
        public void success(List<DepartListBean> data, Object... args) {
            bingZhengAdapter.OnAddAll(data);
            bingZhengAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(), data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private class DiseaseP implements DataCall<List<DiseaseListBean>> {
        @Override
        public void success(List<DiseaseListBean> data, Object... args) {
            diseaseAdapter.onClear();
            diseaseAdapter.OnAddAll(data);
            diseaseAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(), data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

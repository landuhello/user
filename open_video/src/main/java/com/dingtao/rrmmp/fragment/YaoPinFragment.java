package com.dingtao.rrmmp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dingtao.common.bean.DrugsBean;
import com.dingtao.common.bean.DrugsListBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.rrmmp.adapter.DrugsAdapter;
import com.dingtao.rrmmp.adapter.DrugsListAdapter;
import com.dingtao.rrmmp.login.R;

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
public class YaoPinFragment extends Fragment {

    private RecyclerView leftrecycler,rightrecycler;
    private DrugsAdapter drugsAdapter;
    private DrugsListAdapter drugsListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_bing_zheng, container, false);
        leftrecycler = inflate.findViewById(R.id.leftrecycler);
        rightrecycler = inflate.findViewById(R.id.rightrecycler);
        return inflate;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //药品类目
        com.dingtao.rrmmp.main.presenter.DrugsPresenter drugsPresenter=new com.dingtao.rrmmp.main.presenter.DrugsPresenter(new DrugsP());
        drugsPresenter.reqeust();

        drugsAdapter = new DrugsAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftrecycler.setLayoutManager(linearLayoutManager);
        leftrecycler.setAdapter(drugsAdapter);

        drugsAdapter.setYaoDrugsBack(new DrugsAdapter.YaoDrugsBack() {
            @Override
            public void yaoBack(int aid) {
                //根据药品类目查询药品列表
                com.dingtao.rrmmp.main.presenter.DrugsListPresenter drugsListPresenter=new com.dingtao.rrmmp.main.presenter.DrugsListPresenter(new DrugsListP());
                drugsListPresenter.reqeust(aid,1,30);
            }
        });

        drugsAdapter.setGetListener(new DrugsAdapter.getListener() {
            @Override
            public void mlistener(int mposition) {
                drugsAdapter.setmPosition(mposition);
                drugsAdapter.notifyDataSetChanged();
            }
        });

        drugsListAdapter = new DrugsListAdapter();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rightrecycler.setLayoutManager(gridLayoutManager);
        rightrecycler.setAdapter(drugsListAdapter);

    }

    private class DrugsP implements DataCall<List<DrugsBean>> {
        @Override
        public void success(List<DrugsBean> data, Object... args) {
            drugsAdapter.OnAddAll(data);
            drugsAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(), data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private class DrugsListP implements DataCall<List<DrugsListBean>> {
        @Override
        public void success(List<DrugsListBean> data, Object... args) {
            drugsListAdapter.onClear();
            drugsListAdapter.OnAddAll(data);
            drugsListAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(), data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

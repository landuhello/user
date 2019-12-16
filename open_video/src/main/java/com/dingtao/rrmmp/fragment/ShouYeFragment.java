package com.dingtao.rrmmp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtao.common.bean.DepartListBean;
import com.dingtao.common.bean.HomeBannerBean;
import com.dingtao.common.bean.PlateList;
import com.dingtao.common.bean.PlateListBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.rrmmp.activity.BannerWebViewActivity;
import com.dingtao.rrmmp.activity.BingYaoActivity;
import com.dingtao.rrmmp.activity.HomeSeachActivity;
import com.dingtao.rrmmp.adapter.HomeDepartAdapter;
import com.dingtao.rrmmp.adapter.HomeListOneAdapter;
import com.dingtao.rrmmp.adapter.HomeTypeAdapter;
import com.dingtao.rrmmp.login.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

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
 * <p>创建时间：2019/12/4<p>
 * <p>更改时间：2019/12/4<p>
 */
public class ShouYeFragment extends Fragment {
    private MZBannerView home_banner;
    private SimpleDraweeView home_simple;
    private TextView home_seach_view;
    private ImageView home_message_img;
    private LinearLayout home_bing_zheng;
    private LinearLayout home_yao_pin;
    private RecyclerView home_recycelr_type;
    private ListView home_recycler_list;
    private RecyclerView depart_recycler;
    private HomeTypeAdapter homeTypeAdapter;
    private HomeDepartAdapter homeDepartAdapter;
    private HomeListOneAdapter homeListOneAdapter;
    public FrameLayout fram_shouye;
    private FrameLayout frag_shouye;
    private ImageView home_vertu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_shouye_layout, null, false);
        fram_shouye = inflate.findViewById(R.id.frag_shouye);
        home_banner = inflate.findViewById(R.id.home_banner);
        home_bing_zheng = inflate.findViewById(R.id.home_bing_zheng);
        home_simple = inflate.findViewById(R.id.home_simple);
        home_seach_view = inflate.findViewById(R.id.home_seach_view);
        home_message_img = inflate.findViewById(R.id.home_message_img);
        home_recycelr_type = inflate.findViewById(R.id.home_recycelr_type);
        home_recycler_list = inflate.findViewById(R.id.home_recycler_list);
        depart_recycler = inflate.findViewById(R.id.depart_recycler);
        home_yao_pin = inflate.findViewById(R.id.home_yao_pin);
        home_vertu = inflate.findViewById(R.id.home_vertu);
        return inflate;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //头像点击进入个人详情页面
        home_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_persion)
                        .navigation(getContext());
            }
        });
        //健康评测
        home_vertu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.dingtao.rrmmp.main.presenter.VertuPresenter vertuPresenter=new com.dingtao.rrmmp.main.presenter.VertuPresenter(new VertuP());
                vertuPresenter.reqeust();
            }
        });

        //搜索页面
        home_seach_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomeSeachActivity.class));
            }
        });

        //常见病症
        home_bing_zheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), BingYaoActivity.class);
                intent.putExtra("bing", "1");
                startActivity(intent);

                /*FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.frag_shouye,);
                fragmentTransaction.commit();

                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.show(bingYaoFragment);
                fragmentTransaction1.commit();*/
            }
        });

        //常见药品
        home_yao_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BingYaoActivity.class);
                intent.putExtra("bing", "2");
                startActivity(intent);
            }
        });

        //请求健康咨询类目
        com.dingtao.rrmmp.main.presenter.ShouYePresenter shouYePresenter = new com.dingtao.rrmmp.main.presenter.ShouYePresenter(new MainPresenter());
        shouYePresenter.reqeust();

        //请求科室类目
        com.dingtao.rrmmp.main.presenter.DepartPresenter departPresenter = new com.dingtao.rrmmp.main.presenter.DepartPresenter(new MainDepartPresenter());
        departPresenter.reqeust();

        //banner轮播图
        com.dingtao.rrmmp.main.presenter.HomeBannerPresenter homeBannerPresenter = new com.dingtao.rrmmp.main.presenter.HomeBannerPresenter(new HomeBannerP());
        homeBannerPresenter.reqeust();
        //咨询类目
        homeTypeAdapter = new HomeTypeAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        home_recycelr_type.setLayoutManager(linearLayoutManager);
        home_recycelr_type.setAdapter(homeTypeAdapter);

        homeTypeAdapter.setOnBackId(new HomeTypeAdapter.OnBackId() {
            @Override
            public void backid(int ids) {
                if (ids != 0) {
                    //根据咨询类目查询咨询列表
                    com.dingtao.rrmmp.main.presenter.PlateListPresenter plateListPresenter = new com.dingtao.rrmmp.main.presenter.PlateListPresenter(new MainPlatePresenter());
                    plateListPresenter.reqeust(ids, 1, 5);
                    //咨询列表展示
                    homeListOneAdapter = new HomeListOneAdapter();
                    homeListOneAdapter.onClearData();
                    home_recycler_list.setAdapter(homeListOneAdapter);
                    homeListOneAdapter.notifyDataSetChanged();
                }
            }
        });

        //科室类目recyclerview的适配器
        homeDepartAdapter = new HomeDepartAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        depart_recycler.setLayoutManager(gridLayoutManager);
        depart_recycler.setAdapter(homeDepartAdapter);
    }

    //咨询类目
    private class MainPresenter implements DataCall<List<PlateListBean>> {
        @Override
        public void success(List<PlateListBean> data, Object... args) {
            homeTypeAdapter.OnAddAll(data);
            homeTypeAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(), data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //科室类目
    private class MainDepartPresenter implements DataCall<List<DepartListBean>> {
        @Override
        public void success(List<DepartListBean> data, Object... args) {
            homeDepartAdapter.OnAddAll(data);
            homeDepartAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(), data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //咨询列表
    private class MainPlatePresenter implements DataCall<List<PlateList>> {
        @Override
        public void success(List<PlateList> data, Object... args) {
            homeListOneAdapter.OnAddAll(data);
            homeListOneAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(), data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //banner
    private class HomeBannerP implements DataCall<List<HomeBannerBean>> {
        @Override
        public void success(final List<HomeBannerBean> data, Object... args) {

            home_banner.setIndicatorVisible(false);
            home_banner.setPages(data, new MZHolderCreator<BannerViewHolder>() {
                @Override
                public BannerViewHolder createViewHolder() {
                    return new BannerViewHolder();
                }
            });
            home_banner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
                @Override
                public void onPageClick(View view, int i) {
                    Toast.makeText(getContext(), "点击了page", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), BannerWebViewActivity.class);
                    intent.putExtra("bannerweb", data.get(i).jumpUrl);
                    getActivity().startActivity(intent);
                }
            });
            home_banner.start();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    class BannerViewHolder implements MZViewHolder<HomeBannerBean> {

        private SimpleDraweeView banner_simple;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_layout, null);
            banner_simple = view.findViewById(R.id.banner_simple);
            return view;

        }

        @Override
        public void onBind(Context context, int i, HomeBannerBean o) {
            banner_simple.setImageURI(Uri.parse(o.imageUrl));
        }
    }

    private class VertuP implements DataCall {
        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}

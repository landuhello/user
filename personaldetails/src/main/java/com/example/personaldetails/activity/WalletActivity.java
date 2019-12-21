package com.example.personaldetails.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.personaldetails.R;
import com.example.personaldetails.R2;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//H币
public class WalletActivity extends BaseActivity<WalletModelImpl, WalletPresenterImpl> implements WalletContract.WalletView {


    @BindView(R2.id.wallet_back)
    ImageView walletBack;
    @BindView(R2.id.user_title)
    BaseUserTitle userTitle;
    @BindView(R2.id.wallet_card)
    ImageView walletCard;
    @BindView(R2.id.num)
    TextView num;
    @BindView(R2.id.bt1)
    TextView bt1;
    @BindView(R2.id.bt2)
    TextView bt2;
    @BindView(R2.id.wallet_view)
    View walletView;
    @BindView(R2.id.wallet_rec)
    RecyclerView walletRec;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    private ArrayList<WalletRecEntity.ResultBean> resultBeans;
    private int page = 1;
    private int count = 10;
    private WalletAdapter walletAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.RecordList(page, count);
    }

    @Override
    protected void iniData() {
        userTitle.back.setBackgroundColor(Color.TRANSPARENT);
        userTitle.tBack.setImageResource(R.mipmap.common_icon_back_white_n);
        userTitle.tBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                finish();
            }
        });
        userTitle.tName.setText("钱包");
        userTitle.tName.setTextColor(Color.WHITE);
        userTitle.view.setVisibility(View.GONE);
        resultBeans = new ArrayList<>();
        presenter.findWallet();


        if (resultBeans != null) {
            walletRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            walletAdapter = new WalletAdapter(this, resultBeans);
            walletRec.setAdapter(walletAdapter);
        }

        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.RecordList(page, count);
                walletAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }

    @Override
    protected int bindlayout() {
        return R.layout.activity_wallet;
    }

    @Override
    public BasePresenter initPresenter() {
        return new WalletPresenterImpl();
    }

    /**
     * 钱包金额
     *
     * @param obj
     */
    @Override
    public void findWallet(Object obj) {
        WalletEntity walletEntity = (WalletEntity) obj;
        if (walletEntity.getStatus().equals("0000")) {
            String result = walletEntity.getResult() + "";
            if (result.length() >= 5) {
                num.setTextSize(26);
                num.setText(result);
            } else {
                num.setText(result);
            }
        } else {
            num.setText("0");
            ToastUtils.showShort(walletEntity.getMessage());
        }
    }

    /**
     * 钱包记录
     *
     * @param obj
     */
    @Override
    public void recordList(Object obj) {
        WalletRecEntity walletRecEntity = (WalletRecEntity) obj;
        if (walletRecEntity.getStatus().equals("0000")) {
            if (walletRecEntity.getResult().size() != 0) {
                for (int i = 0; i < walletRecEntity.getResult().size(); i++) {
                    if (!resultBeans.contains(walletRecEntity.getResult().get(i))) {
                        resultBeans.add(walletRecEntity.getResult().get(i));
                    } else {

                    }
                }
            } else {
                refresh.finishLoadMoreWithNoMoreData();
            }
        } else {
            ToastUtils.showShort(walletRecEntity.getMessage());
        }
    }

    @OnClick({R2.id.bt1, R2.id.bt2})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.bt1) {
        } else if (id == R.id.bt2) {
            startActivity(new Intent(this,RechargeActivity.class));

        }
    }


}

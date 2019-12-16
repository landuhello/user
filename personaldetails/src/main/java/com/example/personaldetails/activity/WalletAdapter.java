package com.example.personaldetails.activity;

import android.content.Context;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.example.personaldetails.R;


import java.util.ArrayList;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletHolder> {
    private Context context;
    private ArrayList<WalletRecEntity.ResultBean> resultBeans;

    public WalletAdapter(Context context, ArrayList<WalletRecEntity.ResultBean> resultBeans) {
        this.context = context;
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public WalletHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new WalletHolder(from.inflate(R.layout.wallet_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WalletHolder walletHolder, int i) {
        int direction = resultBeans.get(i).getDirection();
        int type = resultBeans.get(i).getType();
        String title = getTitle(type);

        if (direction == 1) {
            walletHolder.num.setTextColor(Color.RED);
            walletHolder.num.setText("+" + resultBeans.get(i).getChangeNum() + "H币");
        } else {
            walletHolder.num.setTextColor(Color.BLUE);
            walletHolder.num.setText("-" + resultBeans.get(i).getChangeNum() + "H币");
        }
        walletHolder.time.setText(TimeUtils.millis2String(resultBeans.get(i).getCreateTime()));
        walletHolder.name.setText(title);
    }

    @Override
    public int getItemCount() {
        return resultBeans.size();
    }

    private String getTitle(int type) {
        switch (type) {
            case 1:
                return "签到";

            case 2:
                return "病友圈首评";

            case 3:
                return "收发病友圈";

            case 4:
                return "完善档案";

            case 5:
                return "健康评测";

            case 6:
                return "悬赏消费";

            case 7:
                return "悬赏奖励";

            case 8:
                return "邀请奖励";

            case 9:
                return "问诊消费";

            case 10:
                return "问诊收入";

            case 11:
                return "观看资讯";

            case 12:
                return "送礼物";

            case 13:
                return "绑定身份证";

            case 14:
                return "绑定银行卡";

            case 15:
                return "充值";

            case 16:
                return "提现";

            case 17:
                return "购买健康视频";
        }
        return "未知";
    }


    static class WalletHolder extends RecyclerView.ViewHolder {

        public TextView num;
        public TextView time;
        public TextView name;

        public WalletHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            num = itemView.findViewById(R.id.num);
        }
    }
}

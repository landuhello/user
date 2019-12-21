package com.example.patientscircle.activity;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.TimeUtils;
import com.example.patientscircle.R;
import com.example.patientscircle.R2;
import com.example.patientscircle.bean.CircleListsBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@Auther:陈浩
 *@Date: 2019/8/17
 *@Time:17:09
 *@Description:${首页适配器朋友圈}
 * */public class PatientHomeAdpter extends RecyclerView.Adapter<PatientHomeAdpter.PatientViewHolde> {

    private Activity context;
    private ArrayList<CircleListsBean.ResultBean> resultBeans;

    public PatientHomeAdpter(Activity context, ArrayList<CircleListsBean.ResultBean> resultBeans) {
        this.context = context;
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public PatientViewHolde onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new PatientViewHolde(from.inflate(R.layout.friends_home_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolde patientViewHolde, final int i) {
        //标题
        patientViewHolde.homeItemTv1.setText(resultBeans.get(i).getTitle());
        //病描述
        patientViewHolde.homeItemTv3.setText(resultBeans.get(i).getDetail());
        //时间
        patientViewHolde.homeItemTv2.setText("" + TimeUtils.getFriendlyTimeSpanByNow(resultBeans.get(i).getReleaseTime()));
        //收藏数
        patientViewHolde.homeItemTv5.setText(resultBeans.get(i).getCollectionNum() + "");
        //建议
        patientViewHolde.homeItemTv7.setText(resultBeans.get(i).getCommentNum() + "");
        //增加的悬赏额度,等于0则没有额外悬赏
        if (resultBeans.get(i).getAmount() == 0) {
            patientViewHolde.homeItemIv.setVisibility(View.GONE);
            patientViewHolde.homeItemTv9.setVisibility(View.GONE);
        } else {
            patientViewHolde.homeItemIv.setVisibility(View.VISIBLE);
            patientViewHolde.homeItemTv9.setVisibility(View.VISIBLE);
            patientViewHolde.homeItemTv9.setText(resultBeans.get(i).getAmount()+"");

        }
        //点击跳转到病友圈详细
        patientViewHolde.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PatienthomeDetailsActivity.class);
                intent.putExtra("sickCircleId", resultBeans.get(i).getSickCircleId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return resultBeans.size();
    }

    class PatientViewHolde extends RecyclerView.ViewHolder {
        @BindView(R2.id.home_item_tv1)
        TextView homeItemTv1;
        @BindView(R2.id.home_item_iv)
        ImageView homeItemIv;
        @BindView(R2.id.home_item_tv9)
        TextView homeItemTv9;
        @BindView(R2.id.home_item_tv2)
        TextView homeItemTv2;
        @BindView(R2.id.home_item_tv3)
        TextView homeItemTv3;
        @BindView(R2.id.home_item_tv4)
        TextView homeItemTv4;
        @BindView(R2.id.home_item_tv5)
        TextView homeItemTv5;
        @BindView(R2.id.home_item_tv6)
        TextView homeItemTv6;
        @BindView(R2.id.home_item_tv7)
        TextView homeItemTv7;

        public PatientViewHolde(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

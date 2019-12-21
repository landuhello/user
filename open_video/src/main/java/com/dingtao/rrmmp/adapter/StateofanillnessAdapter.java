package com.dingtao.rrmmp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.dingtao.common.bean.PatientsBean.Stateofanillness;
import com.dingtao.common.util.DateUtils;
import com.dingtao.rrmmp.login.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: 韩聪聪
 * data: 2019/12/5 21:21:35
 * function:
 */
public class StateofanillnessAdapter extends RecyclerView.Adapter<StateofanillnessAdapter.MyViewHolder> {
    private Context context;
    private List<Stateofanillness> list;
    private View inflate;

    public StateofanillnessAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public void addAll(List<Stateofanillness> data){
        if (data!=null){
            list.addAll(data);
        }
    }
    @NonNull
    @Override
    public StateofanillnessAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.stateofanillness_layout, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull StateofanillnessAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(list.get(position).title);
        try {
            holder.data.setText(DateUtils.dateFormat(new Date(list.get(position).releaseTime), DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.jieshao.setText(list.get(position).detail);
        holder.coller.setText(String.valueOf(list.get(position).collectionNum));
        holder.suggest.setText(String.valueOf(list.get(position).commentNum));
        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCilckListener.OnItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView data;
        private final TextView jieshao;
        private final TextView coller;
        private final TextView suggest;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            data = itemView.findViewById(R.id.data);
            jieshao = itemView.findViewById(R.id.jieshao);
            coller = itemView.findViewById(R.id.collect);
            suggest = itemView.findViewById(R.id.suggest);
        }
    }
    //声明接口
    public OnItemCilckListener onItemCilckListener;
    //实现接口
    public void setOnItemCilckListener(OnItemCilckListener onItemCilckListener) {
        this.onItemCilckListener = onItemCilckListener;
    }
    //接口回调
    public interface OnItemCilckListener{
        void OnItemClick(View view, int position);
    }

}

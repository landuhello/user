package com.dingtao.rrmmp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.dingtao.common.bean.PatientsBean.Administrative;
import com.dingtao.rrmmp.login.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 韩聪聪
 * data: 2019/12/5 19:19:30
 * function:
 */
public class AdministrativeAdapter extends RecyclerView.Adapter<AdministrativeAdapter.MyViewHolder> {
    //创建类
    private Context context;
    private List<Administrative> list;
    private View inflate;

    //创建构造方法
    public AdministrativeAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    //创建方法
    public void addAll(List<Administrative> data){
        if (data!=null){
            list.addAll(data);
        }
    }
    @NonNull
    @Override
    public AdministrativeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.administrative_layout, parent, false);
        return new MyViewHolder(inflate);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final AdministrativeAdapter.MyViewHolder holder, final int position) {
        holder.administrative.setText(list.get(position).departmentName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCilckListener.onItemCilck(list.get(position).id);
                onItemCilckListener.onItemCilck(position);
                notifyDataSetChanged();
            }
        });
        if (position == getmPosition()) {
            holder.administrative.setTextColor(holder.itemView.getResources().getColor(Color.GREEN));
        }else{
//            否则的话就全白色初始化背景
           holder.administrative.setTextColor(Color.GRAY);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView administrative;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            administrative = itemView.findViewById(R.id.administrative);
        }
    }
    //定义接口
    public OnItemCilckListener onItemCilckListener;
    //实现接口
    public void setOnItemCilckListener(OnItemCilckListener onItemCilckListener) {
        this.onItemCilckListener = onItemCilckListener;
    }
    //创建接口
    public interface OnItemCilckListener{
        void onItemCilck(int position);
    }
    //
    private int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }
}

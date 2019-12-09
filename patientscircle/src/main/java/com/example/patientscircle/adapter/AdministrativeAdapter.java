package com.example.patientscircle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.PatientsBean.Administrative;
import com.example.patientscircle.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */
public class AdministrativeAdapter extends RecyclerView.Adapter<AdministrativeAdapter.MyViewHolder>{
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

    @Override
    public void onBindViewHolder(@NonNull final AdministrativeAdapter.MyViewHolder holder, final int position) {
        holder.administrative.setText(list.get(position).departmentName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCilckListener.onItemCilck(list.get(position).id);
            }
        });
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
}

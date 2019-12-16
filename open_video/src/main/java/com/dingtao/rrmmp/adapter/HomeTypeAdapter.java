package com.dingtao.rrmmp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.PlateListBean;
import com.dingtao.rrmmp.login.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/5<p>
 * <p>更改时间：2019/12/5<p>
 */
public class HomeTypeAdapter extends RecyclerView.Adapter<HomeTypeAdapter.TypeViewHolder> {

    List<PlateListBean> list=new ArrayList<>();

    public void OnAddAll(List<PlateListBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_type, parent, false);
        return new TypeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, final int position) {
        holder.homerecyclername.setText(list.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getListener!=null){
                    getListener.mlistener(position);
                }

                onBackId.backid(list.get(position).id);
                notifyDataSetChanged();
            }
        });

        if (position==getmPosition()){
            holder.homerecyclername.setTextColor(Color.BLUE);
        }else {
            holder.homerecyclername.setTextColor(Color.BLACK);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {

        private final TextView homerecyclername;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            homerecyclername = itemView.findViewById(R.id.home_recycler_name);
        }
    }

    public OnBackId onBackId;

    public void setOnBackId(OnBackId onBackId) {
        this.onBackId = onBackId;
    }

    public interface OnBackId{
        void backid(int ids);
    }

    //写一个接口回调，把条目id传到页面上
    public getListener getListener;

    //通过暴露的set方法把获取到的下标再赋值给一个自定义的int型常量
    public void setGetListener(getListener getListener) {
        this.getListener = getListener;
    }

    public interface getListener{
        void mlistener(int mposition);
    }

    //自己定义的常量，进行一个set，get，get用于获取值，set用来设置值
    private int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

}

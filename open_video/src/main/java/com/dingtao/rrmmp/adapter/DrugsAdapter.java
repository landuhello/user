package com.dingtao.rrmmp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.DrugsBean;
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
public class DrugsAdapter extends RecyclerView.Adapter<DrugsAdapter.TypeViewHolder> {

    List<DrugsBean> list=new ArrayList<>();

    public void OnAddAll(List<DrugsBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_yao_pin_type, parent, false);
        return new TypeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final TypeViewHolder holder, final int position) {
        holder.yao_pin_name.setText(list.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getListener!=null){
                    getListener.mlistener(position);
                }
                yaoDrugsBack.yaoBack(list.get(position).id);
            }
        });

        if (position==getmPosition()){
            holder.yao_pin_view.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //写完之后可能会造成回调position的错误，重写下面这个方法，返回值返回自定义int常量，亲调，no question
    @Override
    public int getItemViewType(int position) {
        return getmPosition();
    }

    public void onClear() {
        list.clear();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {

        private final TextView yao_pin_name;
        private final  View yao_pin_view;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            yao_pin_name = itemView.findViewById(R.id.yao_pin_name);
            yao_pin_view = itemView.findViewById(R.id.yao_pin_view);
        }
    }

    public YaoDrugsBack yaoDrugsBack;

    public void setYaoDrugsBack(YaoDrugsBack yaoDrugsBack) {
        this.yaoDrugsBack = yaoDrugsBack;
    }

    public interface YaoDrugsBack{
        void yaoBack(int aid);
    }

    //写一个接口回调，把条目id传到页面上
    public getListener getListener;

    //通过暴露的set方法把获取到的下标再赋值给一个自定义的int型常量
    public void setGetListener(DrugsAdapter.getListener getListener) {
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

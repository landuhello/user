package com.dingtao.rrmmp.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.DepartListBean;
import com.dingtao.common.bean.DoctorComment;
import com.dingtao.common.bean.DoctorReceiveGift;
import com.dingtao.rrmmp.login.R;
import com.facebook.drawee.view.SimpleDraweeView;

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
public class DoctorInfoLwAdapter extends RecyclerView.Adapter<DoctorInfoLwAdapter.TypeViewHolder> {

    List<DoctorReceiveGift> list=new ArrayList<>();

    public void OnAddAll(List<DoctorReceiveGift> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lw_list, parent, false);
        return new TypeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final TypeViewHolder holder, final int position) {

        holder.lw_name.setText(list.get(position).receiveNum+"");
        Uri uri=Uri.parse(list.get(position).giftPic);
        holder.lw_simple.setImageURI(uri);

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

    class TypeViewHolder extends RecyclerView.ViewHolder {

        private final TextView lw_name;
        private final SimpleDraweeView lw_simple;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            lw_name = itemView.findViewById(R.id.lw_name);
            lw_simple = itemView.findViewById(R.id.lw_simple);
        }
    }

    public BingZhengBack bingZhengBack;

    public void setBingZhengBack(BingZhengBack bingZhengBack) {
        this.bingZhengBack = bingZhengBack;
    }

    public interface BingZhengBack{
        void bingBack(int aid);
    }


    //写一个接口回调，把条目id传到页面上
    public getListener getListener;

    //通过暴露的set方法把获取到的下标再赋值给一个自定义的int型常量
    public void setGetListener(DoctorInfoLwAdapter.getListener getListener) {
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

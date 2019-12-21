package com.dingtao.rrmmp.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.DoctorComment;
import com.dingtao.common.bean.DoctorReceiveGift;
import com.dingtao.rrmmp.login.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/5<p>
 * <p>更改时间：2019/12/5<p>
 */
public class DoctorInfoPlAdapter extends RecyclerView.Adapter<DoctorInfoPlAdapter.TypeViewHolder> {

    List<DoctorComment> list=new ArrayList<>();

    public void OnAddAll(List<DoctorComment> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pl_list, parent, false);
        return new TypeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final TypeViewHolder holder, final int position) {

        holder.pl_name.setText(list.get(position).nickName);
        Date date=new Date(list.get(position).commentTime);
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        holder.pl_time.setText(simpleDateFormat.format(date));
        holder.pl_connect.setText(list.get(position).content);
        Uri uri=Uri.parse(list.get(position).headPic);
        holder.pl_simple.setImageURI(uri);

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

        private final TextView pl_name,pl_time,pl_connect;
        private final SimpleDraweeView pl_simple;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            pl_simple = itemView.findViewById(R.id.pl_simple);
            pl_name = itemView.findViewById(R.id.pl_name);
            pl_time = itemView.findViewById(R.id.pl_time);
            pl_connect = itemView.findViewById(R.id.pl_connect);
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
    public void setGetListener(DoctorInfoPlAdapter.getListener getListener) {
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

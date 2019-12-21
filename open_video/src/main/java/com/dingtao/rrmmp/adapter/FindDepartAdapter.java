package com.dingtao.rrmmp.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.DepartListBean;
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
public class FindDepartAdapter extends RecyclerView.Adapter<FindDepartAdapter.TypeViewHolder> {

    List<DepartListBean> list=new ArrayList<>();

    public void OnAddAll(List<DepartListBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    public void OnClear(){
        list.clear();
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_find_depart_type, parent, false);
        return new TypeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, final int position) {
        holder.depart_text_name.setText(list.get(position).departmentName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (departBack != null) {
                    departBack.backId(list.get(position).id,position);
                    notifyDataSetChanged();
                }
            }
        });

        if (list.get(position).id==getmPostion()){
            holder.depart_text_name.setTextColor(Color.BLUE);
        }else{
            holder.depart_text_name.setTextColor(Color.BLACK);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {

        private final TextView depart_text_name;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            depart_text_name = itemView.findViewById(R.id.depart_text_name);
        }
    }

    public DepartBack departBack;

    public void setDepartBack(DepartBack departBack) {
        this.departBack = departBack;
    }

    public interface DepartBack{
        void backId(int did,int postionfind);
    }

    public int mPostion;

    public int getmPostion() {
        return mPostion;
    }

    public void setmPostion(int mPostion) {
        this.mPostion = mPostion;
    }

    public int mdids;

    public int getMdids() {
        return mdids;
    }

    public void setMdids(int mdids) {
        this.mdids = mdids;
    }
}

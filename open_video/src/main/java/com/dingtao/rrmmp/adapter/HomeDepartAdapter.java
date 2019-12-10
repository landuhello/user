package com.dingtao.rrmmp.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.DepartListBean;
import com.dingtao.common.bean.PlateListBean;
import com.dingtao.rrmmp.login.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
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
public class HomeDepartAdapter extends RecyclerView.Adapter<HomeDepartAdapter.TypeViewHolder> {

    List<DepartListBean> list=new ArrayList<>();

    public void OnAddAll(List<DepartListBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_depart_type, parent, false);
        return new TypeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        holder.depart_text_name.setText(list.get(position).departmentName);
        Uri uri=Uri.parse(list.get(position).pic);
        holder.departsimple.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {

        private final TextView depart_text_name;
        private final SimpleDraweeView departsimple;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            departsimple = itemView.findViewById(R.id.depart_simple);
            depart_text_name = itemView.findViewById(R.id.depart_text_name);
        }
    }
}

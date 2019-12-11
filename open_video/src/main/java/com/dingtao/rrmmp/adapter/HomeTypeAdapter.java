package com.dingtao.rrmmp.adapter;

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
                onBackId.backid(list.get(position).id);
            }
        });

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

}

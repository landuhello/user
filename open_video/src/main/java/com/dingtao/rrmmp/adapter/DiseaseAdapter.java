package com.dingtao.rrmmp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.DepartListBean;
import com.dingtao.common.bean.DiseaseListBean;
import com.dingtao.rrmmp.activity.BingDetailsActivity;
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
public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.TypeViewHolder> {

    List<DiseaseListBean> list=new ArrayList<>();

    public void OnAddAll(List<DiseaseListBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_bing_zheng_list, parent, false);
        return new TypeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final TypeViewHolder holder, final int position) {
        holder.bing_list_name.setText(list.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), BingDetailsActivity.class);
                intent.putExtra("idsa",list.get(position).id+"");
                intent.putExtra("idname",list.get(position).name);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void onClear() {
        list.clear();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {

        private final TextView bing_list_name;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            bing_list_name = itemView.findViewById(R.id.bing_list_name);
        }
    }

    public BingListBack bingListBack;

    public void setBingListBack(BingListBack bingListBack) {
        this.bingListBack = bingListBack;
    }

    public interface BingListBack{
        void bingBack(int aid);
    }

}

package com.dingtao.rrmmp.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.DiseaseListBean;
import com.dingtao.common.bean.DrugsListBean;
import com.dingtao.rrmmp.activity.DrugsDetailsActivity;
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
public class DrugsListAdapter extends RecyclerView.Adapter<DrugsListAdapter.TypeViewHolder> {

    List<DrugsListBean> list=new ArrayList<>();

    public void OnAddAll(List<DrugsListBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_yao_pin_list, parent, false);
        return new TypeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final TypeViewHolder holder, final int position) {
        holder.bing_list_name.setText(list.get(position).name);
        Uri uri=Uri.parse(list.get(position).picture);
        holder.yao_list_simple.setImageURI(uri);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), DrugsDetailsActivity.class);
                intent.putExtra("idsss",list.get(position).id+"");
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
        private final SimpleDraweeView yao_list_simple;

        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            bing_list_name = itemView.findViewById(R.id.yao_list_name);
            yao_list_simple = itemView.findViewById(R.id.yao_list_simple);
        }
    }

    public YaoListaBack yaoListaBack;

    public void setYaoListaBack(YaoListaBack yaoListaBack) {
        this.yaoListaBack = yaoListaBack;
    }

    public interface YaoListaBack{
        void yaolistBack(int aid);
    }

}

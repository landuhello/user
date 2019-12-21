package com.dingtao.rrmmp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dingtao.common.bean.Commentontheist;
import com.dingtao.rrmmp.login.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author: 韩聪聪
 * data: 2019/12/9 15:15:25
 * function:
 */
public class FindSickAdapter extends RecyclerView.Adapter<FindSickAdapter.MyViewHolder> {
    private Context context;
    private List<Commentontheist> list;
    private View inflate;

    public FindSickAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    //创建方法
    public void addAll(List<Commentontheist> data){
         if (data!=null){
             list.addAll(data);
         }
    }
    @NonNull
    @Override
    public FindSickAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.pop_re_layout, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FindSickAdapter.MyViewHolder holder, int position) {
        holder.iv.setImageURI(list.get(position).headPic);
        holder.name.setText(list.get(position).nickName);
        holder.xiang.setText(list.get(position).content);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format1 = format.format(list.get(position).commentTime);
        holder.shi.setText(String.valueOf(format1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView iv;
        private final TextView name;
        private final TextView xiang;
        private final TextView shi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.pop_iv);
            name = itemView.findViewById(R.id.pop_name);
            xiang = itemView.findViewById(R.id.pop_xiang);
            shi = itemView.findViewById(R.id.pop_shi);
        }
    }
}

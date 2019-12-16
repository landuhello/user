package com.dingtao.rrmmp.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.FindDoctorBean;
import com.dingtao.rrmmp.login.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/16<p>
 * <p>更改时间：2019/12/16<p>
 */
public class FindDoctorAdapter extends RecyclerView.Adapter<FindDoctorAdapter.FindDoctorViewHolder> {

    List<FindDoctorBean> list=new ArrayList<>();

    public void OnAddAll(List<FindDoctorBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    public void OnClear(){
        if (list != null) {
            list.clear();
        }
    }

    @NonNull
    @Override
    public FindDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_find_doctor, parent, false);
        return new FindDoctorViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FindDoctorViewHolder holder, final int position) {
        holder.findname.setText(list.get(position).doctorName);
        Uri uri=Uri.parse(list.get(position).imagePic);
        holder.findsimple.setImageURI(uri);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindDoctorBean findDoctorBean = list.get(position);
                if (findDoctorBack != null) {
                    findDoctorBack.findBack(findDoctorBean,position);
                    notifyDataSetChanged();
                }
            }
        });

        if (position==getmPostion()){
            holder.findname.setBackgroundColor(Color.BLUE);
        }else{
            holder.findname.setBackgroundColor(Color.LTGRAY);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FindDoctorViewHolder extends RecyclerView.ViewHolder {

        private final TextView findname;
        private final SimpleDraweeView findsimple;

        public FindDoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            findsimple = itemView.findViewById(R.id.find_simple);
            findname = itemView.findViewById(R.id.find_name);
        }
    }

    public FindDoctorBack findDoctorBack;

    public void setFindDoctorBack(FindDoctorBack findDoctorBack) {
        this.findDoctorBack = findDoctorBack;
    }

    public interface FindDoctorBack{
        void findBack(FindDoctorBean findDoctorBean,int mi);
    }

    public int mPostion;

    public int getmPostion() {
        return mPostion;
    }

    public void setmPostion(int mPostion) {
        this.mPostion = mPostion;
    }
}

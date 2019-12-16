package com.dingtao.rrmmp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.DiseaseSearchVoListBean;
import com.dingtao.common.bean.DoctorSearchVoListBean;
import com.dingtao.rrmmp.login.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/9<p>
 * <p>更改时间：2019/12/9<p>
 */
public class SeachDoctorAdapter extends RecyclerView.Adapter<SeachDoctorAdapter.DieaseViewHolder> {

    List<DoctorSearchVoListBean> list=new ArrayList<>();

    public void addAll(List<DoctorSearchVoListBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public DieaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_seach_diease, parent, false);
        return new DieaseViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull DieaseViewHolder holder, final int position) {
        holder.seach_diease_name.setText(list.get(position).doctorName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doctorBackId != null) {
                    doctorBackId.onbackid(list.get(position).doctorId,list.get(position).doctorName);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DieaseViewHolder extends RecyclerView.ViewHolder {

        private final TextView seach_diease_name;

        public DieaseViewHolder(@NonNull View itemView) {
            super(itemView);
            seach_diease_name = itemView.findViewById(R.id.seach_diease_name);
        }
    }

    public DoctorBackId doctorBackId;

    public void setDoctorBackId(DoctorBackId doctorBackId) {
        this.doctorBackId = doctorBackId;
    }

    public interface DoctorBackId{
        void onbackid(int doctorid,String dname);
    }

}

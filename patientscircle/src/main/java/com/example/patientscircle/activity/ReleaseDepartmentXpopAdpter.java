package com.example.patientscircle.activity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientscircle.R;
import com.example.patientscircle.bean.HomeWzzxEntity;

import java.util.List;

/*
 *@Auther:陈浩
 *@Date: 2019/8/21
 *@Time:13:27
 *@Description:${DESCRIPTION}
 * */public class ReleaseDepartmentXpopAdpter extends RecyclerView.Adapter<ReleaseDepartmentXpopAdpter.DepartmentViewHolder> {
    private Context context;
    private List<HomeWzzxEntity.ResultBean> result;

    public ReleaseDepartmentXpopAdpter(Context context, List<HomeWzzxEntity.ResultBean> result) {
        this.context = context;
        this.result = result;
    }


    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new DepartmentViewHolder(from.inflate(R.layout.release_department_xpop_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder departmentViewHolder, final int i) {
        departmentViewHolder.departmentrecy_item.setText(result.get(i).getDepartmentName());
        departmentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.click(result.get(i).getDepartmentName(),result.get(i).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    private DepartmentClick click;

    public void setClick(DepartmentClick click) {
        this.click = click;
    }

    public interface DepartmentClick {
        void click(String departmentName, int id);
    }

    class DepartmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView departmentrecy_item;

        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            departmentrecy_item = itemView.findViewById(R.id.release_department_xpop_recy_item);
        }
    }
}

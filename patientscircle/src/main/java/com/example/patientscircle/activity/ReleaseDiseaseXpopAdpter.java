package com.example.patientscircle.activity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientscircle.R;
import com.example.patientscircle.bean.IllnessListEntity;

import java.util.List;

/*
 *@Auther:陈浩
 *@Date: 2019/8/21
 *@Time:17:10
 *@Description:${DESCRIPTION}
 * */public class ReleaseDiseaseXpopAdpter extends RecyclerView.Adapter<ReleaseDiseaseXpopAdpter.DiseaseViewHolder> {

    private Context context;
    private List<IllnessListEntity.ResultBean> result;

    public ReleaseDiseaseXpopAdpter(Context context, List<IllnessListEntity.ResultBean> result) {
        this.context = context;
        this.result = result;
    }


    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new DiseaseViewHolder(from.inflate(R.layout.release_department_xpop_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseViewHolder diseaseViewHolder, final int i) {
        diseaseViewHolder.departmentrecy_item.setText(result.get(i).getName());
        diseaseViewHolder.departmentrecy_item.setTag(result.get(i).getId());

        diseaseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.click(result.get(i).getName(), result.get(i).getId(), result.get(i).getDepartmentId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    private DiseasementClick click;

    public interface DiseasementClick {
        void click(String departmentName, int id, int DepartmentId);
    }

    public void setClick(DiseasementClick click) {
        this.click = click;
    }

    class DiseaseViewHolder extends RecyclerView.ViewHolder {

        private final TextView departmentrecy_item;

        public DiseaseViewHolder(@NonNull View itemView) {
            super(itemView);
            departmentrecy_item = itemView.findViewById(R.id.release_department_xpop_recy_item);
        }
    }
}

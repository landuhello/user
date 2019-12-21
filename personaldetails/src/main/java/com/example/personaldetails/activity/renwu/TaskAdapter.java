package com.example.personaldetails.activity.renwu;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ColorUtils;
import com.example.personaldetails.R;
import com.example.personaldetails.activity.NoDoubleClickListener;
import com.example.personaldetails.activity.set.TaskEntity;

import java.util.ArrayList;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private Context context;
    private ArrayList<TaskEntity.ResultBean> resultBeans;

    public TaskAdapter(Context context, ArrayList<TaskEntity.ResultBean> resultBeans) {
        this.context = context;
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new TaskHolder(from.inflate(R.layout.task_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder taskHolder, final int i) {
        taskHolder.name.setText(resultBeans.get(i).getTaskName());
        taskHolder.hb.setText(resultBeans.get(i).getReward() + "HB");

        int whetherFinish = resultBeans.get(i).getWhetherFinish();
        int whetherReceive = resultBeans.get(i).getWhetherReceive();
        if (whetherFinish == 1) {
            if (whetherReceive == 1) {
                taskHolder.bt.setText("未领取");
                taskHolder.bt.setTextColor(Color.WHITE);
                taskHolder.bt.setBackgroundResource(R.drawable.task_bt);
                taskHolder.bt.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        callInTaskId.getTaskId(resultBeans.get(i).getId());
                        notifyItemChanged(i);
                    }
                });

            } else if (whetherReceive == 2) {
                taskHolder.bt.setBackgroundResource(R.drawable.task_bt2);
                taskHolder.bt.setTextColor(Color.BLACK);
                taskHolder.bt.setText("已做完");
                taskHolder.bt.setClickable(false);
            }
        } else {
            taskHolder.bt.setBackgroundResource(R.drawable.login_shape);
            taskHolder.bt.setTextColor(Color.WHITE);
            taskHolder.bt.setText("去完成");
        }
    }

    @Override
    public int getItemCount() {
        if (resultBeans.size() != 0) {
            return resultBeans.size();
        }
        return 0;
    }

    private CallInTaskId callInTaskId;

    public void setCallInTaskId(CallInTaskId callInTaskId) {
        this.callInTaskId = callInTaskId;
    }

    public interface CallInTaskId {
        void getTaskId(int taskId);
    }

    static class TaskHolder extends RecyclerView.ViewHolder {

        public TextView bt;
        public TextView name;
        public TextView hb;


        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            bt = itemView.findViewById(R.id.task_item_bt);
            name = itemView.findViewById(R.id.task_item_name);
            hb = itemView.findViewById(R.id.task_item_hb);
        }
    }
}


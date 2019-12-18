package com.example.personaldetails.activity.renwu;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.personaldetails.R;
import com.example.personaldetails.R2;
import com.example.personaldetails.activity.BaseActivity;
import com.example.personaldetails.activity.BasePresenter;
import com.example.personaldetails.activity.BaseUserTitle;
import com.example.personaldetails.activity.NoDoubleClickListener;
import com.example.personaldetails.activity.bean.MessageEntity;
import com.example.personaldetails.activity.bean.SignEntity;
import com.example.personaldetails.activity.set.TaskEntity;

import java.util.ArrayList;

import butterknife.BindView;

public class TaskActivity extends BaseActivity<TaskModelImpl, TaskPresenterImpl> implements TaskContract.TaskView {


    private static final String TAG = "TaskList";
    @BindView(R2.id.user_title)
    BaseUserTitle userTitle;
    @BindView(R2.id.mybar)
    SignBar mybar;
    @BindView(R2.id.task_rec1)
    RecyclerView taskRec1;
    @BindView(R2.id.task_rec2)
    RecyclerView taskRec2;
    private ArrayList<TaskEntity.ResultBean> resultBeans;
    private ArrayList<TaskEntity.ResultBean> resultBeans2;
    private TaskAdapter taskAdapter;


    @Override
    protected void onResume() {
        super.onResume();
        presenter.showList();
        presenter.getSignDay();
    }

    @Override
    protected void iniData() {
        taskRec2.setNestedScrollingEnabled(false);
        taskRec1.setNestedScrollingEnabled(false);
        userTitle.tName.setText("我的任务");
        userTitle.tBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int bindlayout() {
        return R.layout.activity_task;
    }

    @Override
    public BasePresenter initPresenter() {
        return new TaskPresenterImpl();
    }

    /**
     * 签到天数
     * @param o
     */
    @Override
    public void signDay(Object o) {
        SignEntity signEntity= (SignEntity) o;
        if (signEntity.getStatus().equals("0000")){
            ToastUtils.showShort(signEntity.getResult()+"");
            int day = signEntity.getResult();
            mybar.setDay(day);
        }
    }

    /**
     * 任务列表
     * @param o
     */
    @Override
    public void taskList(Object o) {
        TaskEntity taskEntity = (TaskEntity) o;
        resultBeans = new ArrayList<>();
        resultBeans2 = new ArrayList<>();
        if (taskEntity.getStatus().equals("0000")){

            for (int i = 0; i < taskEntity.getResult().size(); i++) {
                if (taskEntity.getResult().get(i).getTaskType()==1){
                    resultBeans.add(taskEntity.getResult().get(i));
                }else {
                    resultBeans2.add(taskEntity.getResult().get(i));
                }
            }
            taskRec1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            taskRec2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            setAda(resultBeans,taskRec1);
            setAda(resultBeans2,taskRec2);
        }else {
            ToastUtils.showShort(taskEntity.getMessage());
        }
    }

    /**
     * 做任务
     * @param obj
     */
    @Override
    public void doTask(Object obj) {
        MessageEntity messageEntity= (MessageEntity) obj;
        ToastUtils.showShort(messageEntity.getMessage());
    }

    /**
     * 领取奖励
     * @param obj
     */
    @Override
    public void receiveReword(Object obj) {
        MessageEntity messageEntity= (MessageEntity) obj;
        if (messageEntity.getStatus().equals("0000")){
            presenter.showList();
        }else {
            ToastUtils.showShort(messageEntity.getMessage());
        }
    }

    private void setAda(ArrayList<TaskEntity.ResultBean> arrayList,RecyclerView recyclerView){
        taskAdapter = new TaskAdapter(this,arrayList);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.setCallInTaskId(new TaskAdapter.CallInTaskId() {
            @Override
            public void getTaskId(int taskId) {

                    presenter.receiveReword(taskId);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        resultBeans2.clear();
        resultBeans.clear();
        if (taskAdapter!=null){
            taskAdapter=null;
        }
    }
}

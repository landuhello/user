package com.example.personaldetails.activity.renwu;

import com.example.personaldetails.activity.BasePresenter;
import com.example.personaldetails.activity.CallBackObj;
import com.example.personaldetails.activity.set.IBaseModel;
import com.example.personaldetails.activity.set.IBaseView;


/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:任务
 * */
public interface TaskContract {
    interface TaskView extends IBaseView {
        void signDay(Object o);
        void taskList(Object o);
        void doTask(Object obj);
        void receiveReword(Object obj);
    }
    interface TaskModel extends IBaseModel {
        void dogetDay(CallBackObj callBackObj);
        void dogetList(CallBackObj callBackObj);
        void dopostTask(int taskId, CallBackObj callBackObj);
        void dopostReword(int taskId, CallBackObj callBackObj);
    }
    abstract class TaskPresenter extends BasePresenter<TaskModel,TaskView> {
        public abstract void getSignDay();
        /**
         * 列表
         */
        public abstract  void  showList();

        public abstract void doTask(int taskId);

        public abstract void receiveReword(int taskId);

        @Override
        public TaskModel getModel() {
            return new TaskModelImpl();
        }
    }
}

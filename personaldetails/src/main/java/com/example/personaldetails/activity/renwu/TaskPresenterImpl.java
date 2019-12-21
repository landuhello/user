package com.example.personaldetails.activity.renwu;


import com.example.personaldetails.activity.CallBackObj;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class TaskPresenterImpl extends TaskContract.TaskPresenter {
    /**
     * 连续签到天数
     */
    @Override
    public void getSignDay() {
        model.dogetDay(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.signDay(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    /**
     * 任务
     *列表
     */
    @Override
    public void showList() {
        model.dogetList(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.taskList(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    /**
     * 做任务
     * @param taskId
     */
    @Override
    public void doTask(int taskId) {
        model.dopostTask(taskId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.doTask(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void receiveReword(int taskId) {
        model.dopostReword(taskId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.receiveReword(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }
}

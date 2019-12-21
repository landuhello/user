package com.example.personaldetails.activity.renwu;


import com.dingtao.common.core.hello.IBaseModel;
import com.example.personaldetails.activity.CallBackObj;
import com.example.personaldetails.activity.HttpUntils;
import com.example.personaldetails.activity.set.MessageEntity;
import com.example.personaldetails.activity.set.SignEntity;
import com.example.personaldetails.activity.set.TaskApi;
import com.example.personaldetails.activity.set.TaskEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:任务
 * */
public class TaskModelImpl implements TaskContract.TaskModel, IBaseModel {
    /**
     * 连续签到天数
     * @param callBackObj
     */
    @Override
    public void dogetDay(final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(TaskApi.class).signDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SignEntity>() {
                    @Override
                    public void accept(SignEntity signEntity) throws Exception {
                        callBackObj.success(signEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * 列表
     * @param callBackObj
     */
    @Override
    public void dogetList(final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(TaskApi.class).taskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TaskEntity>() {
                    @Override
                    public void accept(TaskEntity taskEntity) throws Exception {
                        callBackObj.success(taskEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * 做任务
     * @param taskId
     * @param callBackObj
     */
    @Override
    public void dopostTask(int taskId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(TaskApi.class).doTask(taskId)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MessageEntity>() {
                    @Override
                    public void accept(MessageEntity messageEntity) throws Exception {
                        callBackObj.success(messageEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                    }
                });
    }

    /**
     * 领奖励
     * @param taskId
     * @param callBackObj
     */
    @Override
    public void dopostReword(int taskId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(TaskApi.class).receiveTask(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MessageEntity>() {
                    @Override
                    public void accept(MessageEntity messageEntity) throws Exception {
                        callBackObj.success(messageEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                      throwable.printStackTrace();
                    }
                });
    }
}

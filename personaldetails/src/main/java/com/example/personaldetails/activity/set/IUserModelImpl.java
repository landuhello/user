package com.example.personaldetails.activity.set;



import com.dingtao.common.core.hello.IBaseModel;
import com.example.personaldetails.activity.CallBackObj;
import com.example.personaldetails.activity.HttpUntils;
import com.example.personaldetails.activity.UserApi;
import com.example.personaldetails.activity.bean.SignEntity;
import com.example.personaldetails.activity.bean.UserEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public class IUserModelImpl implements IUserContract.IUserModel, IBaseModel {
    @Override
    public void dogetUser(final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(UserApi.class)
                .findUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserEntity>() {
                    @Override
                    public void accept(UserEntity userEntity) throws Exception {
                        callBackObj.success(userEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void dogetWhetherSign(final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(UserApi.class).whetherSign()
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

    @Override
    public void dopostSign(final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(UserApi.class).sign()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<com.example.personaldetails.activity.bean.MessageEntity>() {
                    @Override
                    public void accept(com.example.personaldetails.activity.bean.MessageEntity messageEntity) throws Exception {
                        callBackObj.success(messageEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void dopostTask(int taskId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(TaskApi.class).doTask(taskId)
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

    @Override
    public void dopostPic(MultipartBody.Part image, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(UserApi.class).headerPic(image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<com.example.personaldetails.activity.bean.MessageEntity>() {
                    @Override
                    public void accept(com.example.personaldetails.activity.bean.MessageEntity messageEntity) throws Exception {
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

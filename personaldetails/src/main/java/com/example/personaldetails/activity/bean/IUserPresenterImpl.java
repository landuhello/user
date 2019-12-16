package com.example.personaldetails.activity.bean;

import com.example.personaldetails.activity.CallBackObj;
import com.example.personaldetails.activity.set.IUserContract;


import okhttp3.MultipartBody;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class IUserPresenterImpl extends IUserContract.IUserPresenter {
    /*
     * 用户信息
     * */
    @Override
    public void UserMessage() {
        model.dogetUser(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.UserMessage(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    /*
     * 用户是否签到
     * */
    @Override
    public void UserWhetherSign() {
        model.dogetWhetherSign(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.UserWhetherSign(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    /*
     *签到
     * */
    @Override
    public void UserSign() {
        model.dopostSign(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.UserSign(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void UserTask(int taskId) {
        model.dopostTask(taskId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.UserTask(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void UserPic(MultipartBody.Part image) {
        model.dopostPic(image, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.UserWhetherSign(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }
}

package com.example.personaldetails.activity.set;



import com.dingtao.common.core.hello.IBaseModel;
import com.example.personaldetails.activity.CallBackObj;
import com.example.personaldetails.activity.HttpUntils;
import com.example.personaldetails.activity.UserApi;
import com.example.personaldetails.activity.bean.MessageEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public class SettingModelImpl implements SettingContract.SettingModel, IBaseModel {
    @Override
    public void doputUpdate(String oldPwd, String newPwd, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(UserApi.class).update(oldPwd,newPwd)
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

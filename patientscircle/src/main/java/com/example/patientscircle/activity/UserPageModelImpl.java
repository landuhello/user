package com.example.patientscircle.activity;


import com.example.patientscircle.bean.CircleListsBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:陈浩
 *@Date: 2019/8/23
 *@Time:14:24
 *@Description:${DESCRIPTION}
 * */public class UserPageModelImpl implements IUserPageContract.IuserPageModel {
    @Override
    public void doGetPatientreleaseList(String patientUserId, String page, String count, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).findPatientSickCircleList(patientUserId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CircleListsBean>() {
                    @Override
                    public void accept(CircleListsBean circleListsBean) throws Exception {
                        callBackObj.success(circleListsBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
}

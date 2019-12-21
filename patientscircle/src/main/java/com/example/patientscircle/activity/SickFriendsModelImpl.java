package com.example.patientscircle.activity;



import com.example.patientscircle.bean.CircleListsBean;
import com.example.patientscircle.bean.HomeWzzxEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by ysn
 * TIME: 2019/8/14
 * Doing:
 */
public class SickFriendsModelImpl implements ISickFriendsContract.SickFriendsModel {
    //病友圈列表
    @Override
    public void doGetSickList(String departmentId, String page, String count, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).Sickfriend(departmentId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CircleListsBean>() {
                    @Override
                    public void accept(CircleListsBean homeBanner) throws Exception {
                        callBackObj.success(homeBanner);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void doGetDepartmentList(final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class)
                .getHomeWzzx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeWzzxEntity>() {
                    @Override
                    public void accept(HomeWzzxEntity homeWzzxEntity) throws Exception {
                        callBackObj.success(homeWzzxEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void doGetSearchPatient(String keyWord, final CallBackObj callBackObj) {

        HttpUntils.getInstance().getcreate(ApiService.class)
                .SearchCirclePatients(keyWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CircleListsBean>() {
                    @Override
                    public void accept(CircleListsBean homeWzzxEntity) throws Exception {
                        callBackObj.success(homeWzzxEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
}

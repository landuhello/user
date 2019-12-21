package com.example.patientscircle.activity;



import com.example.patientscircle.bean.HomeWzzxEntity;
import com.example.patientscircle.bean.IllnessListEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

/*
 *@Auther:陈浩
 *@Date: 2019/8/21
 *@Time:11:05
 *@Description:${DESCRIPTION}
 * */public class ReleaseCrcleModelImpl implements IReleaseCrcleContract.IReleaseCrcleModel {
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

    /**
     * 查询主要病症
     * @param departmentId
     * @param callBackObj
     */
    @Override
    public void doGetCorrespondingIllness(String departmentId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class)
                .DiseaseList(departmentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<IllnessListEntity>() {
                    @Override
                    public void accept(IllnessListEntity homeWzzxEntity) throws Exception {
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
    public void doGetPublishSickCircle(Map<String, Object> map, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class)
                .publishSickCircle(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody homeWzzxEntity) throws Exception {
                        callBackObj.success(homeWzzxEntity.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void doGetUploadSickCirclePicture(String sickCircleId, List<MultipartBody.Part> files, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class)
                .uploadSickCirclePicture(sickCircleId,files)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody homeWzzxEntity) throws Exception {
                        callBackObj.success(homeWzzxEntity.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
}

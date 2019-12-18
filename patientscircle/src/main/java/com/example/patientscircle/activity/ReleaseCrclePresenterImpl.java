package com.example.patientscircle.activity;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;

/*
 *@Auther:陈浩
 *@Date: 2019/8/21
 *@Time:11:10
 *@Description:${DESCRIPTION}
 * */public class ReleaseCrclePresenterImpl extends IReleaseCrcleContract.IReleaseCrclePresenter {
    @Override
    public void requestDepartmentList() {
        model.doGetDepartmentList(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showDepartment(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestCorrespondingIllness(String departmentId) {
        model.doGetCorrespondingIllness(departmentId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showCorrespondingIllness(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestPublishSickCircle(Map<String, Object> map) {
        model.doGetPublishSickCircle(map, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showPublishSickCircle(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestUploadSickCirclePicture(String sickCircleId,  List<MultipartBody.Part> files) {
        model.doGetUploadSickCirclePicture(sickCircleId, files, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showUploadSickCirclePicture(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }
}

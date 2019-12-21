package com.example.patientscircle.activity;



import com.dingtao.common.core.hello.BasePresenter;
import com.dingtao.common.core.hello.IBaseModel;
import com.dingtao.common.core.hello.IBaseView;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;

/*
 *@Auther:陈浩
 *@Date: 2019/8/21
 *@Time:11:02
 *@Description:${DESCRIPTION}
 * */public interface IReleaseCrcleContract {
    interface IReleaseCrcleView extends IBaseView {
        //科室列表
        void showDepartment(Object obj);

        //4. 根据科室查询对应病症
        void showCorrespondingIllness(Object obj);

        //发布
        void showPublishSickCircle(Object obj);

        void showUploadSickCirclePicture(Object obj);
    }

    interface IReleaseCrcleModel extends IBaseModel {
        //科室列表
        void doGetDepartmentList(CallBackObj callBackObj);

        //4. 根据科室查询对应病症departmentId
        void doGetCorrespondingIllness(String departmentId, CallBackObj callBackObj);

        void doGetPublishSickCircle(Map<String, Object> map, CallBackObj callBackObj);

        void doGetUploadSickCirclePicture(String sickCircleId, List<MultipartBody.Part> files, CallBackObj callBackObj);
    }

    abstract static class IReleaseCrclePresenter extends BasePresenter<IReleaseCrcleModel, IReleaseCrcleView> {
        //科室列表
        public abstract void requestDepartmentList();

        //4. 根据科室查询对应病症departmentId
        public abstract void requestCorrespondingIllness(String departmentId);

        //请求发布
        public abstract void requestPublishSickCircle(Map<String, Object> map);

        public abstract void requestUploadSickCirclePicture(String sickCircleId,  List<MultipartBody.Part> files);

        @Override
        public IReleaseCrcleModel getModel() {
            return new ReleaseCrcleModelImpl();
        }
    }
}

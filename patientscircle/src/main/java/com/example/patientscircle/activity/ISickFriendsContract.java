package com.example.patientscircle.activity;


import com.dingtao.common.core.hello.BasePresenter;
import com.dingtao.common.core.hello.IBaseModel;
import com.dingtao.common.core.hello.IBaseView;

/**
 * Create by ysn
 * TIME: 2019/8/14
 * Doing:
 */
public interface ISickFriendsContract {
    //病友圈首页列表
    interface SickFriendsView extends IBaseView {
        //病友圈首页列表
        void showSickList(Object o);

        //科室列表
        void showDepartmentList(Object obj);

        //搜素
        void showSearchPatient(Object obj);
    }

    interface SickFriendsModel extends IBaseModel {
        //病友圈首页列表
        void doGetSickList(String departmentId, String page, String count, CallBackObj callBackObj);

        //科室列表
        void doGetDepartmentList(CallBackObj callBackObj);

        void doGetSearchPatient(String keyWord, CallBackObj callBackObj);
    }

    abstract static class SickFriendsPresenter extends BasePresenter<SickFriendsModel, SickFriendsView> {
        //病友圈首页列表
        public abstract void requestSickList(String departmentId, String page, String count);

        //科室列表
        public abstract void requestDepartmentList();

        public abstract void requestSearchPatient(String keyWord);

        @Override
        public SickFriendsModel getModel() {
            return new SickFriendsModelImpl();
        }
    }
}

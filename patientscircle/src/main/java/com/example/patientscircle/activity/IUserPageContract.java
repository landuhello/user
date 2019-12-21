package com.example.patientscircle.activity;


import com.dingtao.common.core.hello.BasePresenter;
import com.dingtao.common.core.hello.IBaseModel;
import com.dingtao.common.core.hello.IBaseView;

/*
 *@Auther:陈浩
 *@Date: 2019/8/23
 *@Time:14:21
 *@Description:${病友圈用户信息}
 * */public interface IUserPageContract {
    interface IuserPageView extends IBaseView {
        //病人发布的列表
        void showPatientreleaseList(Object obj);
    }

    interface IuserPageModel extends IBaseModel {
        //病人发布的列表
        void doGetPatientreleaseList(String patientUserId, String page, String count, CallBackObj callBackObj);
    }

    abstract  class IuserPagePresenter extends BasePresenter<IuserPageModel,IuserPageView> {
        //病人发布的列表
        public abstract void drequestPatientreleaseList(String patientUserId, String page, String count);

        @Override
        public IuserPageModel getModel() {
            return new UserPageModelImpl();
        }
    }
}

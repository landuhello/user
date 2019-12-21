package com.example.patientscircle.activity;

/*
 *@Auther:陈浩
 *@Date: 2019/8/23
 *@Time:14:25
 *@Description:${DESCRIPTION}
 * */public class UserPagePresenterImpl extends IUserPageContract.IuserPagePresenter {
    @Override
    public void drequestPatientreleaseList(String patientUserId, String page, String count) {
        model.doGetPatientreleaseList(patientUserId, page, count, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showPatientreleaseList(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

}

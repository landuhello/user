package com.example.patientscircle.activity;

/**
 * Create by ysn
 * TIME: 2019/8/14
 * Doing:
 */
public class SickFriendsPresenterImpl extends ISickFriendsContract.SickFriendsPresenter {
    @Override
   public void requestSickList(String departmentId, String page, String count) {
        model.doGetSickList(departmentId, page, count, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showSickList(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestDepartmentList() {
        model.doGetDepartmentList(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showDepartmentList(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestSearchPatient(String keyWord) {
        model.doGetSearchPatient(keyWord, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showSearchPatient(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }
}

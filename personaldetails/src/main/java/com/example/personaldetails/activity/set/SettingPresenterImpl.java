package com.example.personaldetails.activity.set;


import com.example.personaldetails.activity.CallBackObj;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public class SettingPresenterImpl extends SettingContract.SettingPresenter {
    @Override
    public void updateP(String oldPwd, String newPwd) {
        model.doputUpdate(oldPwd, newPwd, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.updateP(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }
}

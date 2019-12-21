package com.example.personaldetails.activity.set;


import com.example.personaldetails.activity.BasePresenter;
import com.example.personaldetails.activity.CallBackObj;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public interface SettingContract {
    interface SettingView extends IBaseView{
        void updateP(Object obj);
    }
    interface SettingModel extends IBaseModel{
        void doputUpdate(String oldPwd, String newPwd, CallBackObj callBackObj);
    }
    abstract class SettingPresenter extends BasePresenter<SettingModel,SettingView> {
        public abstract void updateP(String oldPwd,String newPwd);
        @Override
        public SettingModel getModel() {
            return new SettingModelImpl();
        }
    }
}

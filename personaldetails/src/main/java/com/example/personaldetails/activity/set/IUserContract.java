package com.example.personaldetails.activity.set;



import com.example.personaldetails.activity.BasePresenter;
import com.example.personaldetails.activity.CallBackObj;

import okhttp3.MultipartBody;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public interface IUserContract {
    interface IUserView extends IBaseView{
        void UserMessage(Object obj);
        void UserWhetherSign(Object obj);
        void UserSign(Object obj);
        void UserTask(Object obj);
    }
    interface IUserModel extends IBaseModel{
        void dogetUser(CallBackObj callBackObj);
        void dogetWhetherSign(CallBackObj callBackObj);
        void dopostSign(CallBackObj callBackObj);
        void dopostTask(int taskId, CallBackObj callBackObj);
        void dopostPic(MultipartBody.Part image, CallBackObj callBackObj);
    }
    abstract class IUserPresenter extends BasePresenter<IUserModel,IUserView> {
        public abstract void UserMessage();
        public abstract void UserWhetherSign();
        public abstract void UserSign();
        public abstract void UserTask(int taskId);
        public abstract void UserPic(MultipartBody.Part image);

        @Override
        public IUserModel getModel() {
            return new IUserModelImpl();
        }
    }

}

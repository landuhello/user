package com.example.personaldetails.activity;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public interface RechargeContract {
    interface RechargeView extends IBaseView{
        void message(Object obj);
    }
    interface RechargeModel extends IBaseModel{
        void dopostRech(int money, int payType, CallBackObj callBackObj);
    }
    abstract class RechargePresenter extends BasePresenter<RechargeModel,RechargeView>{
        public abstract  void getMessage(int money,int payType);

        @Override
        public RechargeModel getModel() {
            return new RechargeModelImpl();
        }
    }
}

package com.example.personaldetails.activity;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class RechargePresenterImpl extends RechargeContract.RechargePresenter {
    @Override
    public void getMessage(int money, int payType) {
        model.dopostRech(money, payType, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.message(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }
}

package com.example.personaldetails.activity;


import com.dingtao.common.core.hello.IBaseModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RechargeModelImpl implements RechargeContract.RechargeModel, IBaseModel {
    @Override
    public void dopostRech(int money, int payType, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(RechargeApi.class).recharge(money,payType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RechargeEntity>() {
                    @Override
                    public void accept(RechargeEntity rechargeEntity) throws Exception {
                        callBackObj.success(rechargeEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
}

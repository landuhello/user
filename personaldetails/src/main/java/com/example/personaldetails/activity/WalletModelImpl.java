package com.example.personaldetails.activity;



import com.dingtao.common.core.hello.IBaseModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class WalletModelImpl implements WalletContract.WalletModel, IBaseModel {
    @Override
    public void dogetWallet(final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(WalletApi.class).findWallet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WalletEntity>() {
                    @Override
                    public void accept(WalletEntity walletEntity) throws Exception {
                        callBackObj.success(walletEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void dogetRecord(int page, int count, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(WalletApi.class).walletRec(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WalletRecEntity>() {
                    @Override
                    public void accept(WalletRecEntity walletRecEntity) throws Exception {
                        callBackObj.success(walletRecEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
}

package com.example.personaldetails.activity;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public class WalletPresenterImpl extends WalletContract.WalletPresenter {
    @Override
    public void findWallet() {
        model.dogetWallet(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.findWallet(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void RecordList(int page, int count) {
        model.dogetRecord(page, count, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.recordList(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }
}

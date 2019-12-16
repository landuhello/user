package com.example.personaldetails.activity;



/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public interface WalletContract {
    interface WalletView extends IBaseView{
            void findWallet(Object obj);
            void recordList(Object obj);
    }
    interface WalletModel extends IBaseModel{
        void dogetWallet(CallBackObj callBackObj);
        void dogetRecord(int page, int count, CallBackObj callBackObj);
    }
    abstract class WalletPresenter extends BasePresenter<WalletModel,WalletView>{
        public abstract void findWallet();
        public abstract void RecordList(int page,int count);

        @Override
        public WalletModel getModel() {
            return new WalletModelImpl();
        }
    }
}

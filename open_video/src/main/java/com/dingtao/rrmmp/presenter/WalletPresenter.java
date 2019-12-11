package com.dingtao.rrmmp.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * data:查看H币钱包有多少钱
 * author:老刘（）
 * function:这是干什么的
 */
public class WalletPresenter extends WDPresenter<IAppRequest> {
    public WalletPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.dowallet((int)args[0],(String) args[1]);
    }
}

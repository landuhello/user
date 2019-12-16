package com.dingtao.rrmmp.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */
public class VideobulletPresenter extends WDPresenter<IAppRequest> {

    public VideobulletPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.bullet((int)args[0]);
    }
}

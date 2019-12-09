package com.dingtao.rrmmp.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;
import com.dingtao.common.core.http.IBaiduRequest;

import io.reactivex.Observable;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */
public class VideodisplayPresenter extends WDPresenter<IAppRequest> {
    public VideodisplayPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.dovideovol((int)args[0],(int)args[1],(int)args[2]);
    }
}

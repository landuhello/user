package com.dingtao.rrmmp.presenter;

import android.support.v4.os.IResultReceiver;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function 视频类目id
 */
public class VideocatPresenter extends WDPresenter<IAppRequest> {
    public VideocatPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.dovideo();
    }
}

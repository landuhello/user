package com.dingtao.rrmmp.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * data:购买视频p层
 * author:老刘（）
 * function:这是干什么的
 */
public class VideoBuyPresenter extends WDPresenter<IAppRequest> {
    public VideoBuyPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.dovideobuy((int)args[0],(String) args[1],(int)args[2],(int)args[3]);
    }
}

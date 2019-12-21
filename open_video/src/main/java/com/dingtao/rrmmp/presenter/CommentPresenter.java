package com.dingtao.rrmmp.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * author: 韩聪聪
 * data: 2019/12/9 14:14:28
 * function:
 */
public class CommentPresenter extends WDPresenter<IAppRequest> {
    public CommentPresenter(DataCall dataCall) {
        super(dataCall);
    }
    @Override
    protected Observable getModel(Object... args) {
        return iRequest.publishComment((int)args[0],(String)args[1],(int)args[2],(String)args[3]);
    }
}


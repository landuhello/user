package com.dingtao.rrmmp.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * author: 韩聪聪
 * data: 2019/12/9 15:15:07
 * function:
 */
public class FindSickPresenter extends WDPresenter<IAppRequest> {
    public FindSickPresenter(DataCall dataCall) {
        super(dataCall);
    }
    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findSickCircleCommentList((int)args[0],(int)args[1],(int)args[2]);
    }
}

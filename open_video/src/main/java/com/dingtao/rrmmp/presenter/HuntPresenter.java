package com.dingtao.rrmmp.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * author: 韩聪聪
 * data: 2019/12/10 18:18:47
 * function:
 */
public class HuntPresenter extends WDPresenter<IAppRequest> {
    public HuntPresenter(DataCall dataCall) {
        super(dataCall);
    }
    @Override
    protected Observable getModel(Object... args) {
        return iRequest.searchSickCircle((String)args[0]);
    }
}

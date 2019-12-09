package com.example.patientscircle.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * author: 韩聪聪
 * data: 2019/12/5 21:21:33
 * function:
 */
public class StateofanillnessPresenter extends WDPresenter<IAppRequest> {
    public StateofanillnessPresenter(DataCall dataCall) {
        super(dataCall);
    }
    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findSickCircleList((int)args[0],(int)args[1],(int)args[2]);
    }
}

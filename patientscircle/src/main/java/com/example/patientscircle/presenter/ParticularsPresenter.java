package com.example.patientscircle.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * author: 韩聪聪
 * data: 2019/12/6 20:20:02
 * function:
 */
public class ParticularsPresenter extends WDPresenter<IAppRequest> {
    public ParticularsPresenter(DataCall dataCall) {
        super(dataCall);
    }
    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findSickCircleInfo((int)args[0]);
    }
}

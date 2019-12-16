package com.example.patientscircle.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * author: 韩聪聪
 * data: 2019/12/5 16:16:28
 * function:
 */
public class AdministrativePresenter extends WDPresenter<IAppRequest> {
    public AdministrativePresenter(DataCall dataCall) {
        super(dataCall);
    }
    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDepartment();
    }
}

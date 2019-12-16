package com.example.personaldetails.activity.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * data:查看档案p层
 * author:老刘（）
 * function:这是干什么的
 */
public class ArchivesPresenter extends WDPresenter<IAppRequest> {

    public ArchivesPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.doarchives((int)args[0],(String)args[1]);
    }
}

package com.dingtao.rrmmp.main.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/5<p>
 * <p>更改时间：2019/12/5<p>
 */
public class DrugsListPresenter extends WDPresenter<IAppRequest> {

    public DrugsListPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.FINDDRUGSKNOWLEDGELIST((int)args[0],(int)args[1],(int)args[2]);
    }
}

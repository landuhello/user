package com.example.open_login.activity.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * data:注册的p层
 * author:老刘（）
 * function:这是干什么的
 */
public class RegisterPresenter extends WDPresenter<IAppRequest> {

    public RegisterPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.doreg((String) args[0],(String)args[1],(String)args[2],(String)args[3]);
    }
}

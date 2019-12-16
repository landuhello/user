package com.example.open_login.activity.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * data:登录的p层
 * author:老刘（）
 * function:这是干什么的
 */
public class LoginPresenter extends WDPresenter<IAppRequest> {

    public LoginPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.dologin((String) args[0],(String) args[1]);
    }
}

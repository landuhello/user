package com.example.open_login.activity.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * data:发送邮箱验证码
 * author:老刘（）
 * function:这是干什么的
 */
public class CodePresenter extends WDPresenter<IAppRequest> {

    public CodePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.docode((String) args[0]);
    }
}

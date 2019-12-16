package com.dingtao.rrmmp.activity;

import android.app.Application;
import android.content.Context;

import com.dingtao.common.core.db.DaoSession;


/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */
public class App extends Application {
    private static App instances;

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    public static final String APP_ID = "wxe3fcbe8a55cd33ff";

    // IWXAPI 是第三方app和微信通信的openApi接口

    public static Context context;
    private static DaoSession daoSession;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

}

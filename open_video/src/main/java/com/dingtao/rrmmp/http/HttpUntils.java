package com.dingtao.rrmmp.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.dingtao.rrmmp.api.Api;
import com.dingtao.rrmmp.videofragment.MyInterceptor;
import com.dingtao.rrmmp.videofragment.NetworkCacheInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */public class HttpUntils {

    private static HttpUntils untils;
    private final Retrofit retrofit;

    private HttpUntils() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new NetworkCacheInterceptor())
                .addInterceptor(NetworkCacheInterceptor.OfflineCacheInterceptor)
                .addInterceptor(new MyInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();




        retrofit = new Retrofit.Builder().baseUrl(Api.BASEURL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 双层检验所
     * @return
     */
    public static HttpUntils getInstance() {
        if (untils == null) {
            synchronized (HttpUntils.class) {
                if (untils == null) {
                    untils = new HttpUntils();
                }
            }
        }
        return untils;
    }

    public <T> T getcreate(final Class<T> service) {
        return retrofit.create(service);
    }

    /**
     * 判断网络
     * @param context
     * @return
     */
    public boolean isnet(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null){
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }

}

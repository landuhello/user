package com.example.patientscircle.activity;

import com.blankj.utilcode.util.NetworkUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 *@Auther:陈浩
 *@Date: 2019/8/21
 *@Time:10:20
 *@Description:${DESCRIPTION}
 * */public class NetworkCacheInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        int onlineCacheTime = 0;//在线的时候的缓存过期时间，如果想要不缓存，直接时间设置为0
        return response.newBuilder()
                .header("Cache-Control", "public, max-age=" + onlineCacheTime)
                .removeHeader("Pragma")
                .build();
    }

    /**
     * /**
     * 没有网时候的缓存
     */
    public static Interceptor OfflineCacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isAvailable()) {
                int offlineCacheTime = 999999;//离线的时候的缓存的过期时间
                request = request.newBuilder()
//                        .cacheControl(new CacheControl
//                                .Builder()
//                                .maxStale(60,TimeUnit.SECONDS)
//                                .onlyIfCached()
//                                .build()
//                        ) 两种方式结果是一样的，写法不同
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime)
                        .build();
            }
            return chain.proceed(request);
        }
    };



}


package com.dingtao.rrmmp.videofragment;


import com.dingtao.rrmmp.bean.VideoCommentList;
import com.dingtao.rrmmp.bean.VideoListEntity;
import com.dingtao.rrmmp.bean.VideoTypeEntity;
import com.dingtao.rrmmp.http.HttpUntils;
import com.dingtao.rrmmp.ibase.ApiService;
import com.dingtao.rrmmp.ibase.CallBackObj;
import com.dingtao.rrmmp.ibase.IvideoContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/*
 *@Auther:陈浩
 *@Date: 2019/8/13
 *@Time:8:57
 *@Description:${DESCRIPTION}ApiService.java
 * */public class VideoMmodelImpl implements IvideoContract.VideoModel {


    @Override
    public void dogetVideoType(final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).getVideoType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VideoTypeEntity>() {
                    @Override
                    public void accept(VideoTypeEntity videoTypeEntity) throws Exception {
                        callBackObj.success(videoTypeEntity);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                      throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void showVideoList(String categoryId, String page, String count, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).getVideoList(categoryId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VideoListEntity>() {
                    @Override
                    public void accept(VideoListEntity videoListEntity) throws Exception {
                        callBackObj.success(videoListEntity);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void dogetcollectionVideo(String videoId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).collectionVideo(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody videoListEntity) throws Exception {
                        callBackObj.success(videoListEntity.string());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBackObj.err("失败");
                       throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void dogetCanclecollectionVideo(String videoId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).CancelVideoCollection(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody videoListEntity) throws Exception {
                        callBackObj.success(videoListEntity.string());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBackObj.err("失败");
                        throwable.printStackTrace();

                    }
                });
    }

    @Override
    public void dogetReleaseBarrage(String videoId, String content, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).Videobarrage(videoId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody videoListEntity) throws Exception {
                        callBackObj.success(videoListEntity.string());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void dogetBarrageList(String videoId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).videoCommentList(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VideoCommentList>() {
                    @Override
                    public void accept(VideoCommentList videoListEntity) throws Exception {
                        callBackObj.success(videoListEntity);
                        callBackObj.success(videoListEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void dogetBuyVideo(String videoId, String price, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).buyVideo(videoId, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        callBackObj.success(responseBody.string());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBackObj.err("失败");
                       throwable.printStackTrace();

                    }
                });
    }

    @Override
    public void doGetUserwallet(final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).Querywallet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        callBackObj.success(responseBody.string());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       throwable.printStackTrace();
                        callBackObj.err("失败");
                    }
                });
    }

}

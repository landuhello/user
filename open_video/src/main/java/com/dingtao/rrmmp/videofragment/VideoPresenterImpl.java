package com.dingtao.rrmmp.videofragment;


import com.dingtao.rrmmp.ibase.CallBackObj;
import com.dingtao.rrmmp.ibase.IvideoContract;

/*
 *@Auther:陈浩
 *@Date: 2019/8/13
 *@Time:9:00
 *@Description:${DESCRIPTION}
 * */public class VideoPresenterImpl extends IvideoContract.VideoPresenter {
    @Override
    public void requestVideoType() {
        model.dogetVideoType(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showVideoType(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestVideoList(String categoryId, String page, String count) {
        model.showVideoList(categoryId, page, count, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showVideoList(obj);
            }

            @Override
            public void err(String msg) {

            }
        });

    }

    @Override
    public void requestcollectionVideo(String videoId) {
        model.dogetcollectionVideo(videoId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showcollection(obj);
            }

            @Override
            public void err(String msg) {
                view.showcollection(msg);
            }
        });
    }

    @Override
    public void requestCanclecollectionVideo(String videoIdc) {
        model.dogetCanclecollectionVideo(videoIdc, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showcanclecollection(obj);
            }

            @Override
            public void err(String msg) {
                view.showcanclecollection(msg);
            }
        });
    }

    @Override
    public void requestReleaseBarrage(String videoId, String content) {
        model.dogetReleaseBarrage(videoId, content, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showReleaseBarrage(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestBarrageList(String videoId) {
        model.dogetBarrageList(videoId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showBarrageList(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestBuyVideo(String videoId, String price) {
        model.dogetBuyVideo(videoId, price, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showBuyVideo(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestUserwallet() {
        model.doGetUserwallet(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showUserwallet(obj);
            }

            @Override
            public void err(String msg) {
                view.showUserwallet(msg);
            }
        });
    }
}

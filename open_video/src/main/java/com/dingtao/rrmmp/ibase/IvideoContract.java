package com.dingtao.rrmmp.ibase;


import com.dingtao.common.core.hello.IBaseModel;
import com.dingtao.rrmmp.ibase.CallBackObj;
import com.dingtao.rrmmp.ibase.IBaseView;
import com.dingtao.rrmmp.presenter.BasePresenter;
import com.dingtao.rrmmp.videofragment.VideoMmodelImpl;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */public interface IvideoContract {
    interface VideoView extends IBaseView {
        //视频类目
        void showVideoType(Object obj);

        //视频列表
        void showVideoList(Object obj);

        //收藏
        void showcollection(Object obj);

        //取消收藏
        void showcanclecollection(Object obj);

        //发布弹幕
        void showReleaseBarrage(Object obj);

        //弹幕列表
        void showBarrageList(Object obj);

        //购买视频
        void showBuyVideo(Object obj);

        //用户钱包
        void showUserwallet(Object obj);
    }

    interface VideoModel extends IBaseModel {
        //视频类目
        void dogetVideoType(CallBackObj callBackObj);

        //视频列表
        void showVideoList(String categoryId, String page, String count, CallBackObj callBackObj);

        //收藏视频
        void dogetcollectionVideo(String videoId, CallBackObj callBackObj);

        ///取消收藏视频
        void dogetCanclecollectionVideo(String videoId, CallBackObj callBackObj);

        //发弹幕
        void dogetReleaseBarrage(String videoId, String content, CallBackObj callBackObj);

        //评论列表
        void dogetBarrageList(String videoId, CallBackObj callBackObj);

        //购买视频
        void dogetBuyVideo(String videoId, String price, CallBackObj callBackObj);

        //用户钱包
        void doGetUserwallet(CallBackObj callBackObj);


    }

    public abstract static class VideoPresenter extends BasePresenter<VideoModel, VideoView> {
        //视频类目
        public abstract void requestVideoType();

        //视频列表
        public abstract void requestVideoList(String categoryId, String page, String count);

        //收藏视频
        public abstract void requestcollectionVideo(String videoId);

        ///取消收藏视频
        public abstract void requestCanclecollectionVideo(String videoIdc);

        //发弹幕
        public abstract void requestReleaseBarrage(String videoId, String content);

        //评论列表
        public abstract void requestBarrageList(String videoId);

        //购买视频
        public abstract void requestBuyVideo(String videoId, String price);

        //钱包
        public abstract void requestUserwallet();

        @Override
        public VideoModel getModel() {
            return new VideoMmodelImpl();
        }
    }

}

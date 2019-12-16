package com.dingtao.rrmmp.ibase;



import com.dingtao.rrmmp.api.Api;
import com.dingtao.rrmmp.bean.FavoritesList;
import com.dingtao.rrmmp.bean.MessageEntity;
import com.dingtao.rrmmp.bean.MyVideoEntity;
import com.dingtao.rrmmp.bean.VideoCommentList;
import com.dingtao.rrmmp.bean.VideoListEntity;
import com.dingtao.rrmmp.bean.VideoTypeEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */public interface ApiService {










    /**
     * 收藏
     *
     * @param infoId
     * @return
     */
    @POST(Api.ADDCOLLECTION)
    @FormUrlEncoded
    Observable<ResponseBody> getHomeConsultationCollection(@Field("infoId") String infoId);

    /**
     * 观看咨询奖励
     *
     * @param infoId
     * @return
     */
    @POST(Api.homewatchInfoRewards)
    @FormUrlEncoded
    Observable<ResponseBody> watchInfoRewards(@Field("infoId") String infoId);

    /***
     * 收藏列表
     * @param page
     * @param count
     * @return
     */
    @GET(Api.COLLECTIONLIST)
    Observable<FavoritesList> getHomeConsultationCollectionList(@Query("page") String page, @Query("count") String count);

    /**
     * 取消收藏咨询
     *
     * @param sickCircleId
     * @return
     */
    @DELETE(Api.DELCOLLECTION)
    Observable<ResponseBody> delectCollectionList(@Query("infoId") String sickCircleId);




    /**
     * 查询用户其那边
     *
     * @return
     */
    @GET(Api.Querywallet)
    Observable<ResponseBody> Querywallet();

    // 咨询医生
    @PUT(Api.home_consultDoctor)
    Observable<ResponseBody> Consultdoctor(@Query("doctorId") String doctorId);

    /**
     * 发送消息
     *
     * @param inquiryId
     * @param msgContent
     * @param content
     * @param type
     * @param doctorId
     * @return
     */
    @POST(Api.pushMessage)
    @Multipart
    Observable<ResponseBody> pushMessage(@Query("inquiryId") int inquiryId,
                                         @Query("msgContent") String msgContent,
                                         @Part MultipartBody.Part content,
                                         @Query("type") int type,
                                         @Query("doctorId") int doctorId);

    @POST(Api.pushMessage)
    Observable<ResponseBody> pushMessage(@Query("inquiryId") int inquiryId,
                                         @Query("msgContent") String msgContent,
                                         @Query("type") int type,
                                         @Query("doctorId") int doctorId);


    //结束问诊
    @PUT(Api.home_endInquiry)
    Observable<ResponseBody> EndTheConsultation(@Query("recordId") String doctorId);

    /**
     * 关注医生
     *
     * @param doctorId
     * @return
     */
    @POST(Api.home_followDoctor)
    @FormUrlEncoded
    Observable<ResponseBody> followDoctor(@Field("doctorId") String doctorId);

    // 取消关注医生
    @DELETE(Api.home_cancelFollow)
    Observable<ResponseBody> cancelFollow(@Query("doctorId") String doctorId);

    /**
     * 视频类目
     *
     * @return
     */
    @GET(Api.videotype)
    Observable<VideoTypeEntity> getVideoType();

    /**
     * 视频列表
     *
     * @param categoryId
     * @param page
     * @param count
     * @return
     */
    @GET(Api.VideoList)
    Observable<VideoListEntity> getVideoList(@Query("categoryId") String categoryId, @Query("page") String page, @Query("count") String count);

    /***
     * 收藏视频
     * @param videoId
     * @return
     */
    @POST(Api.Collectionvideo)
    Observable<ResponseBody> collectionVideo(@Query("videoId") String videoId);

    /**
     * 帆布弹幕
     *
     * @param videoId
     * @param content
     * @return
     */
    @POST(Api.Videobarrage)
    Observable<ResponseBody> Videobarrage(@Query("videoId") String videoId, @Query("content") String content);

    /**
     * 取消收藏视频
     *
     * @param videoId
     * @return
     */
    @DELETE(Api.cancelVideoCollection)
    Observable<ResponseBody> CancelVideoCollection(@Query("videoId") String videoId);

    /**
     * 视频评论列表
     *
     * @param videoId
     * @return
     */
    @GET(Api.VideoCommentList)
    Observable<VideoCommentList> videoCommentList(@Query("videoId") String videoId);

    /***
     * 购买视频
     * @param videoId
     * @param price
     * @return
     */
    @POST(Api.Buy_video)
    Observable<ResponseBody> buyVideo(@Query("videoId") String videoId, @Query("price") String price);


















    /**
     * 患者发表评论
     *
     * @param sickCircleIdD
     * @param content
     * @return
     */
    @POST(Api.PatientPublishComment)
    Observable<ResponseBody> PatientPublishComment(@Query("sickCircleId") String sickCircleIdD,
                                                   @Query("content") String content);


    /**
     * 发布病友圈
     *
     * @param map
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(Api.publishSickCircle)
    Observable<ResponseBody> publishSickCircle(@Body Map<String, Object> map);

    /**
     * 上传图片
     *
     * @param sickCircleId
     * @param files
     * @return
     */
    @POST(Api.uploadSickCirclePicture)
    @Multipart
    Observable<ResponseBody> uploadSickCirclePicture(@Query("sickCircleId") String sickCircleId,
                                                     @Part List<MultipartBody.Part> files);





    /**
     * 购买的视频
     *
     * @param page
     * @param count
     * @return
     */
    @GET(Api.findVideo)
    Observable<MyVideoEntity> findVideoCollectionList(@Query("page") String page, @Query("count") String count);

    @DELETE(Api.DELBUYVIDEO)
    Observable<MessageEntity> delVedio(@Query("videoId") int videoId);





}




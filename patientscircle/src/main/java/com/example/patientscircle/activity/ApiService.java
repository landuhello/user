package com.example.patientscircle.activity;



import com.example.patientscircle.bean.CircleListsBean;
import com.example.patientscircle.bean.CommonMedicineListEntity;
import com.example.patientscircle.bean.DetailedDiseaseEntity;
import com.example.patientscircle.bean.DoctorDetails;
import com.example.patientscircle.bean.DoctorList;
import com.example.patientscircle.bean.DrugCategoryEntity;
import com.example.patientscircle.bean.DrugDetailsEntity;
import com.example.patientscircle.bean.FavoritesList;
import com.example.patientscircle.bean.HomeBanner;
import com.example.patientscircle.bean.HomeConsultationdetailsEntity;
import com.example.patientscircle.bean.HomeHealthConsultationContent;
import com.example.patientscircle.bean.HomeHealthConsultationTitle;
import com.example.patientscircle.bean.HomeSearchEntity;
import com.example.patientscircle.bean.HomeWzzxEntity;
import com.example.patientscircle.bean.IllnessListEntity;
import com.example.patientscircle.bean.InquiryEntity;
import com.example.patientscircle.bean.MessageEntity;
import com.example.patientscircle.bean.MyVideoEntity;
import com.example.patientscircle.bean.PatientCircleCommentListEntity;
import com.example.patientscircle.bean.PatientDetailsEntity;
import com.example.patientscircle.bean.PopularSearchEntity;
import com.example.patientscircle.bean.VideoCommentList;
import com.example.patientscircle.bean.VideoListEntity;
import com.example.patientscircle.bean.VideoTypeEntity;

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

/*
 *@Auther:陈浩
 *@Date: 2019/8/3
 *@Time:16:54
 *@Description:${首页}
 * */public interface ApiService {
    /**
     * 首页伦比
     *
     * @return
     */
    @GET(Api.HOME_BANNER)
    Observable<HomeBanner> getHomeBanner();

    /**
     * 问诊咨询
     *
     * @return
     */
    @GET(Api.HOME_WZZX_LIST)
    Observable<HomeWzzxEntity> getHomeWzzx();

    /**
     * 获得健康咨询标题
     *
     * @return
     */
    @GET(Api.HOME_Health_Consultation_Title)
    Observable<HomeHealthConsultationTitle> getHomeHealth_Consultation_Title();

    /**
     * 获取健康咨询内荣
     *
     * @param plateId
     * @param page
     * @param count
     * @return
     */
    @GET(Api.HOME_Health_Consultation_Content)
    Observable<HomeHealthConsultationContent> getgetHomeHealth_Consultation_Content(@Query("plateId") String plateId,
                                                                                    @Query("page") String page,
                                                                                    @Query("count") String count);

    /**
     * 查询咨询详情
     *
     * @param infoId
     * @return
     */
    @GET(Api.Home_Consultation_details)
    Observable<HomeConsultationdetailsEntity> getHomeConsultationdetails(@Query("infoId") String infoId);

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


    //. 查询问诊医生列表
    @GET(Api.home_doctor_list)
    Observable<DoctorList> getDoctorList(
            @Query("deptId") String deptId,
            @Query("condition") String condition,
            @Query("sortBy")
                    String sortBy,
            @Query("page")
                    String page,
            @Query("count")
                    String count);

    /**
     * 医生详情
     *
     * @param doctorId
     * @return
     */
    @GET(Api.home_doctor_detail)
    Observable<DoctorDetails> getDoctorDetails(@Query("doctorId") String doctorId);

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
     * 病友圈列表
     *
     * @param departmentId
     * @param page
     * @param count
     * @return
     */
    @GET(Api.Listofpatients)
    Observable<CircleListsBean> Sickfriend(@Query("departmentId") String departmentId,
                                           @Query("page") String page,
                                           @Query("count") String count);

    //根据科室查询对应病症
    @GET(Api.DiseaseList)
    Observable<IllnessListEntity> DiseaseList(@Query("departmentId") String departmentId);

    //药品分类
    @GET(Api.DrugsCategoryType)
    Observable<DrugCategoryEntity> DrugsCategoryType();

    /**
     * 7.根据药品类目查询常见药品
     * * @param drugsCategoryId
     * * @param page
     * * @param count
     * * @return
     */
    @GET(Api.DrugsKnowledgeList)
    Observable<CommonMedicineListEntity> DrugsKnowledgeList(@Query("drugsCategoryId") String drugsCategoryId,
                                                            @Query("page") String page,
                                                            @Query("count") String count);

    /**
     * //病友圈详细
     *
     * @param sickCircleId
     * @return
     */
    @GET(Api.PatientCircleDetails)
    Observable<PatientDetailsEntity> PatientCircleDetails(@Query("sickCircleId") String sickCircleId);

    /*-*
    搜索病友圈
     */
    @GET(Api.SearchCirclePatients)
    Observable<CircleListsBean> SearchCirclePatients(@Query("keyWord") String keyWord);

    //收藏病友圈
    @POST(Api.ADDSICK)
    Observable<ResponseBody> addSickCollection(@Query("sickCircleId") String sickCircleId);

    //取消收藏病友圈
    @DELETE(Api.DELSICK)
    Observable<ResponseBody> cancelSickCollection(@Query("sickCircleId") String sickCircleId); //取消收藏病友圈   //取消收藏病友圈

    /*
     * //患者圈评论列表
     *
     * @param sickCircleId
     * @param page
     * @param count
     * @return
     */
    @GET(Api.PatientCircleCommentList)
    Observable<PatientCircleCommentListEntity> PatientCircleCommentList(@Query("sickCircleId") String sickCircleId, @Query("page") String page, @Query("count") String count);

    /**
     * 采纳病友圈优秀的评论
     *
     * @param sickCircleId
     * @return
     */
    @PUT(Api.adoptionProposal)
    Observable<ResponseBody> adoptionProposal(@Query("sickCircleId") String sickCircleId, @Query("commentId") String commentId); //取消收藏病友圈

    @PUT(Api.expressOpinion)
    Observable<ResponseBody> expressOpinion(@Query("opinion") String opinion, @Query("commentId") String commentId); //取消收藏病友圈

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
     * 病友发布的病友圈
     *
     * @param patientUserId
     * @param page
     * @param count
     * @return
     */
    @GET(Api.findPatientSickCircleList)
    Observable<CircleListsBean> findPatientSickCircleList(@Query("patientUserId") String patientUserId, @Query("page") String page, @Query("count") String count);

    /**
     * 病症详细
     *
     * @param id
     * @return
     */
    @GET(Api.findDrugsKnowledge)
    Observable<DrugDetailsEntity> findDrugsKnowledge(@Query("id") String id);

    /**
     * 药品信息
     *
     * @param id
     * @return
     */
    @GET(Api.findDiseaseKnowledge)
    Observable<DetailedDiseaseEntity> findDiseaseKnowledge(@Query("id") String id);

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

    /**
     * 当前问诊
     *
     * @return
     */
    @GET(Api.findCurrentInquiryRecord)
    Observable<InquiryEntity> inquiry();

    /**
     * 热门搜素
     * @return
     */
    @GET(Api.popularSearch)
    Observable<PopularSearchEntity> popularSearch();

    /**
     * 搜索
     * @param keyWord
     * @return
     */
    @GET(Api.homePageSearch)
    Observable<HomeSearchEntity> homesearch(@Query("keyWord") String keyWord);
}




package com.dingtao.common.core.http;

import com.dingtao.common.bean.Commentontheist;
import com.dingtao.common.bean.FindDoctorBean;
import com.dingtao.common.bean.Hunt;
import com.dingtao.common.bean.FindDoctorInfoBean;
import com.dingtao.common.bean.InfoMationBean;
import com.dingtao.common.bean.FinduserarchivesBean;
import com.dingtao.common.bean.LoginBean;

import com.dingtao.common.bean.DepartListBean;
import com.dingtao.common.bean.DiseaseKnowBean;
import com.dingtao.common.bean.DiseaseListBean;
import com.dingtao.common.bean.DrugsBean;
import com.dingtao.common.bean.DrugsKnowBean;
import com.dingtao.common.bean.DrugsListBean;
import com.dingtao.common.bean.HomeBannerBean;
import com.dingtao.common.bean.HomeSeachBean;

import com.dingtao.common.bean.PatientsBean.Administrative;
import com.dingtao.common.bean.PatientsBean.PatientscirBean;
import com.dingtao.common.bean.PatientsBean.Stateofanillness;
import com.dingtao.common.bean.PlateList;
import com.dingtao.common.bean.PlateListBean;
import com.dingtao.common.bean.PopularSearchBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.video.BulletBean;
import com.dingtao.common.bean.video.ResultBean;
import com.dingtao.common.bean.video.VideocatBean;
import com.dingtao.common.bean.video.VideovolBean;

import com.dingtao.common.bean.videobean.CategorylistBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;

import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {

//查找档案
    @GET("health/user/verify/v1/findUserArchives")
    Observable<FinduserarchivesBean> doarchives(@Header("userId")int userId,@Header("sessionId")String sessionId);
    //视频类目id
    @GET("health/user/video/v1/findVideoCategoryList")
    Observable<CategorylistBean> dovideo();

    //根据视频id查找相关视频
    @GET("health/user/video/v1/findVideoVoList")
    Observable<Result<List<VideovolBean>>> dovideovol(
            @Query("categoryId") int categoryId,
            @Query("page") int page,
            @Query("count") int count);

    //查询视频弹幕
    @GET("health/user/video/v1/findVideoCommentList")
    Observable<Result<List<BulletBean>>> bullet(@Query("videoId") int videoId);

    //展示病症详情
    @GET("health/user/sickCircle/v1/findSickCircleInfo")
    Observable<Result<PatientscirBean>> findSickCircleInfo(@Query("sickCircleId") int sickCircleId);

    //展示科室数据
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<Administrative>>> findDepartment();

    //科室病症
    @GET("health/user/sickCircle/v1/findSickCircleList")
    Observable<Result<List<Stateofanillness>>> findSickCircleList(@Query("departmentId") int departmentId,
                                                                  @Query("page") int page,
                                                                  @Query("count") int count);

    //注册
    @FormUrlEncoded
    @POST("health/user/v1/register")
    Observable<Result> doreg(@Field("email") String email, @Field("code") String code, @Field("pwd1") String pwd1, @Field("pwd2") String pwd2

    );

    //发送邮箱验证码
    @FormUrlEncoded
    @POST("health/user/v1/sendOutEmailCode")
    Observable<Result> docode(@Field("email") String email);

    //登录
    @FormUrlEncoded
    @POST("health/user/v1/login")
    Observable<Result<LoginBean>> dologin(@Field("email") String email, @Field("pwd") String pwd);

    //购买视频
    @FormUrlEncoded
    @POST("health/user/video/verify/v1/videoBuy")
    Observable<Result> dovideobuy(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                  @Field("videoId") int videoId, @Field("price") int price
    );

    //我的钱包
    @GET("health/user/verify/v1/findUserWallet")
    Observable<Result<ResultBean>> dowallet(@Header("userId") int userId, @Header("sessionId") String sessionId);

//    //发表视频评论（弹幕）
//    @GET("health/user/video/verify/v1/addVideoComment")
//    Observable<> doaddvideocomment(@Header("userId") int userId, @Header("sessionId") String sessionId,
//                                   @Field("videoId") int videoId, @Field("content") String content
//    );


    //查询咨询类目
    @GET("health/share/information/v1/findInformationPlateList")
    Observable<Result<List<PlateListBean>>> FINDINFORMATIONPLATELIST();

    //查询科室类目
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<DepartListBean>>> FINDDEPARTMENT();

    //根据咨询类目返回的ID进行查询咨询列表
    @GET("health/share/information/v1/findInformationList")
    Observable<Result<List<PlateList>>> FINDINFORMATIONLIST(@Query("plateId") int plateId,
                                                            @Query("page") int page,
                                                            @Query("count") int count);

    //banner轮播图
    @GET("health/share/v1/bannersShow")
    Observable<Result<List<HomeBannerBean>>> BANNERSSHOW();

    //根据常见病症类目id查询常见病症列表
    @GET("health/share/knowledgeBase/v1/findDiseaseCategory")
    Observable<Result<List<DiseaseListBean>>> FINDDISEASECATEGORY(@Query("departmentId") int departmentId);

    //药品类目
    @GET("health/share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<Result<List<DrugsBean>>> FINDDRUGSCATEGORYLIST();

    //根据药品类目查询药品列表
    @GET("health/share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<Result<List<DrugsListBean>>> FINDDRUGSKNOWLEDGELIST(@Query("drugsCategoryId") int drugsCategoryId,
                                                                   @Query("page") int page,
                                                                   @Query("count") int count);

    //病症详情
    @GET("health/share/knowledgeBase/v1/findDiseaseKnowledge")
    Observable<Result<DiseaseKnowBean>> FINDDISEASEKNOWLEDGE(@Query("id") int id);

    //药品详情
    @GET("health/share/knowledgeBase/v1/findDrugsKnowledge")
    Observable<Result<DrugsKnowBean>> FINDDRUGSKNOWLEDGE(@Query("id") int id);

    //搜索
    @GET("health/share/v1/homePageSearch")
    Observable<Result<HomeSeachBean>> HOMEPAGESEARCH(@Query("keyWord") String keyWord);

    //热门搜索
    @GET("health/share/v1/popularSearch")
    Observable<Result<List<PopularSearchBean>>> POPULARSEARCH();

    //健康评测
    @GET("health/user/verify/v1/userHealthTest")
    Observable<Result> userHealthTest(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //咨询详情
    @GET("health/share/information/v1/findInformation")
    Observable<Result<InfoMationBean>> FINDINFOMATION(@Header("userId")int userId, @Header("sessionId")String sessionId, @Query("infoId")int infoId);

    //咨询收藏
    @POST("health/user/verify/v1/addInfoCollection")
    @FormUrlEncoded
    Observable<Result> ADDINFOCOLLECTION(@Header("userId")int userId,@Header("sessionId")String sessionId,@Field("infoId") int infoId);

    //咨询取消收藏
    @DELETE("health/user/verify/v1/cancelInfoCollection")
    Observable<Result> CANCELINFOCOLLECTION(@Header("userId")int userId,@Header("sessionId")String sessionId,@Query("infoId")int infoId);

    //查询医生列表
    @GET("health/user/inquiry/v1/findDoctorList")
    Observable<Result<List<FindDoctorBean>>> FINDDOCTORLIST(@Query("deptId")int deptId,
                                                            @Query("condition")int condition,
                                                            @Query("sortBy")int sortBy,
                                                            @Query("page")int page,
                                                            @Query("count")int count);
    //展示搜索
    @GET("user/sickCircle/v1/searchSickCircle")
    Observable<Result<List<Hunt>>> searchSickCircle(@Query("keyWord")String keyWord);
    //发送评论
    @POST("user/sickCircle/verify/v1/publishComment")
    Observable<Result> publishComment(@Header("userId")int userId,
                                      @Header("sessionId")String sessionId,
                                      @Query("sickCircleId")int sickCircleId,
                                      @Query("content")String content);
    //展示pop查看评论列表
    @GET("user/sickCircle/v1/findSickCircleCommentList")
    Observable<Result<List<Commentontheist>>> findSickCircleCommentList(@Query("sickCircleId")int sickCircleId,
                                                                        @Query("page")int page,
                                                                        @Query("count")int count);

    //yi医生详情
    @GET("health/user/inquiry/v1/findDoctorInfo")
    Observable<Result<FindDoctorInfoBean>> FINDDOCTORINFO(@Header("userId")int userId,
                                                          @Header("sessionId")String sessionId,
                                                          @Query("doctorId")int doctorId);

    //关注医生
    @POST("health/user/inquiry/verify/v1/followDoctor")
    @FormUrlEncoded
    Observable<Result> FOLLOWDOCTOR(@Header("userId")int userId,
                                    @Header("sessionId")String sessionId,
                                    @Field("doctorId")int doctorId);

    //取消关注医生
    @DELETE("health/user/inquiry/verify/v1/cancelFollow")
    Observable<Result> CANCELFOLLOW(@Header("userId")int userId,
                                    @Header("sessionId")String sessionId,
                                    @Query("doctorId") int doctorId);

    //咨询医生/。
    @PUT("health/user/inquiry/verify/v1/consultDoctor")
    Observable<Result> CONSULTDOCTOR(@Header("userId")int userId,
                               @Header("sessionId")String sessionI从d,
                               @Query("doctorId")int doctorId);
}

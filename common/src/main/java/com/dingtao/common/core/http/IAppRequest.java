package com.dingtao.common.core.http;

import com.dingtao.common.bean.LoginBean;
import com.dingtao.common.bean.PatientsBean.Administrative;
import com.dingtao.common.bean.PatientsBean.PatientscirBean;
import com.dingtao.common.bean.PatientsBean.Stateofanillness;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.video.BulletBean;
import com.dingtao.common.bean.video.ResultBean;
import com.dingtao.common.bean.video.VideocatBean;
import com.dingtao.common.bean.video.VideovolBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {
    //视频类目id
    @GET("health/user/video/v1/findVideoCategoryList")
    Observable<Result<List<VideocatBean>>> dovideo();

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
    Observable<Result<ResultBean>> dowallet(@Header("userId")int userId,@Header("sessionId") String sessionId);
    //发送弹幕
    @
}

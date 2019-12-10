package com.dingtao.common.core.http;

import com.dingtao.common.bean.PatientsBean.Administrative;
import com.dingtao.common.bean.PatientsBean.PatientscirBean;
import com.dingtao.common.bean.PatientsBean.Stateofanillness;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.video.BulletBean;
import com.dingtao.common.bean.video.VideocatBean;
import com.dingtao.common.bean.video.VideovolBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
    Observable<Result<List<BulletBean>>> bullet(@Query("videoId")int videoId);
    //展示病症详情
    @GET("health/user/sickCircle/v1/findSickCircleInfo")
    Observable<Result<PatientscirBean>> findSickCircleInfo(@Query("sickCircleId")int sickCircleId);
    //展示科室数据
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<Administrative>>> findDepartment();
    //科室病症
    @GET("health/user/sickCircle/v1/findSickCircleList")
    Observable<Result<List<Stateofanillness>>> findSickCircleList(@Query("departmentId")int departmentId,
                                                                  @Query("page")int page,
                                                                  @Query("count")int count);
}

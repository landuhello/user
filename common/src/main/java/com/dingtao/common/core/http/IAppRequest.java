package com.dingtao.common.core.http;

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
import com.dingtao.common.bean.video.VideocatBean;
import com.dingtao.common.bean.video.VideovolBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

//查询咨询类目
    @GET("health/share/information/v1/findInformationPlateList")
    Observable<Result<List<PlateListBean>>> FINDINFORMATIONPLATELIST();

    //查询科室类目
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<DepartListBean>>> FINDDEPARTMENT();

    //根据咨询类目返回的ID进行查询咨询列表
    @GET("health/share/information/v1/findInformationList")
    Observable<Result<List<PlateList>>> FINDINFORMATIONLIST(@Query("plateId")int plateId,
                                                            @Query("page")int page,
                                                            @Query("count")int count);

    //banner轮播图
    @GET("health/share/v1/bannersShow")
    Observable<Result<List<HomeBannerBean>>> BANNERSSHOW();

    //根据常见病症类目id查询常见病症列表
    @GET("health/share/knowledgeBase/v1/findDiseaseCategory")
    Observable<Result<List<DiseaseListBean>>> FINDDISEASECATEGORY(@Query("departmentId")int departmentId);

    //药品类目
    @GET("health/share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<Result<List<DrugsBean>>> FINDDRUGSCATEGORYLIST();

    //根据药品类目查询药品列表
    @GET("health/share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<Result<List<DrugsListBean>>> FINDDRUGSKNOWLEDGELIST(@Query("drugsCategoryId")int drugsCategoryId,
                                                                   @Query("page")int page,
                                                                   @Query("count")int count);

    //病症详情
    @GET("health/share/knowledgeBase/v1/findDiseaseKnowledge")
    Observable<Result<DiseaseKnowBean>> FINDDISEASEKNOWLEDGE(@Query("id")int id);

    //药品详情
    @GET("health/share/knowledgeBase/v1/findDrugsKnowledge")
    Observable<Result<DrugsKnowBean>> FINDDRUGSKNOWLEDGE(@Query("id")int id);

    //搜索
    @GET("health/share/v1/homePageSearch")
    Observable<Result<HomeSeachBean>> HOMEPAGESEARCH(@Query("keyWord")String keyWord);

    //热门搜索
    @GET("health/share/v1/popularSearch")
    Observable<Result<List<PopularSearchBean>>> POPULARSEARCH();

    //健康评测
    @GET("health/user/verify/v1/userHealthTest")
    Observable<Result> userHealthTest(@Header("userId")int userId, @Header("sessionId")String sessionId);
}

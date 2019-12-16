package com.example.personaldetails.activity;



import com.example.personaldetails.activity.bean.AddArchives;
import com.example.personaldetails.activity.bean.ArchivesEntity;
import com.example.personaldetails.activity.bean.MessageEntity;
import com.example.personaldetails.activity.bean.SignEntity;
import com.example.personaldetails.activity.bean.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public interface UserApi {
    /*
     * 根据id查询
     * */
    @GET(Api.USERINFOBYID)
    Observable<UserEntity> findUser();

    /*
     * 是否签到
     * */
    @GET(Api.WHETHERSIGN)
    Observable<SignEntity> whetherSign();

    /*
     * 签到
     * */
    @POST(Api.SIGN)
    Observable<MessageEntity> sign();

    /*
    * 修该密码
    * */
    @PUT(Api.UPDATEPASSWODE)
    @FormUrlEncoded
    Observable<MessageEntity> update(@Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd);

    /**
     * 查看用户档案
     * @return
     */
    @GET(Api.USERARCHIVES)
    Observable<ArchivesEntity> userArchives();


    /**
     * 添加档案
     * @param
     * @return
     */
    @POST(Api.ARCHIVES)
    Observable<MessageEntity> archives(@Body AddArchives addArchives);

    /**
     * 删除档案
     * @param archivesId
     * @return
     */
    @DELETE(Api.DELARCHIVES)
    Observable<MessageEntity> delArchives(@Query("archivesId") int archivesId);

    /**
     * 上传图片
     * @param picture
     * @return
     */
    @Multipart
    @POST(Api.ARCHIVESPICTURE)
    Observable<MessageEntity> uploadArchivesPicture(@Part List<MultipartBody.Part> picture);

    @POST(Api.HEADPIC)
    @Multipart
    Observable<MessageEntity> headerPic(@Part MultipartBody.Part image);


}

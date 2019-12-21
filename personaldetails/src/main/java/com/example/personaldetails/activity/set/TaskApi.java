package com.example.personaldetails.activity.set;

import com.example.personaldetails.activity.Api;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public interface TaskApi {
    @GET(Api.SIGNDAY)
    Observable<SignEntity> signDay();
    /**
     * 任务列表
     * @return
     */
    @GET(Api.TASKLIST)
    Observable<TaskEntity> taskList();

    @POST(Api.DOTASK)
    @FormUrlEncoded
    Observable<MessageEntity> doTask(@Field("taskId") int taskId);

    @POST(Api.RECEIVEREWARD)
    @FormUrlEncoded
    Observable<MessageEntity> receiveTask(@Field("taskId") int taskId);
}

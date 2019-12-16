package com.example.personaldetails.activity;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public interface RechargeApi {
    @POST(Api.RECHARGE)
    @FormUrlEncoded
    Observable<RechargeEntity> recharge(@Field("money") int money, @Field("payType") int payType);

}

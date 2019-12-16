package com.example.personaldetails.activity;



import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public interface WalletApi {
    /**
     * 查找钱包
     * @return
     */
    @GET(Api.WALLET)
    Observable<WalletEntity> findWallet();

    @GET(Api.RecordList)
    Observable<WalletRecEntity> walletRec(@Query("page") int page, @Query("count") int count);
}

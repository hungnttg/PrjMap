package com.duanmau.prjmap.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceRequestGetData {
    @GET("get_product_detail.php?")
    //@GET("getall.json?")
    Call<ResponseServer> GetJSON(@Query("pid") String pid);
}

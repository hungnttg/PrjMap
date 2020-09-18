package com.duanmau.prjmap.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceInsert {
    @FormUrlEncoded
    @POST("create_product.php")
    Call<ServerResponse> thucThiInsert(@Field("name") String name,
                                       @Field("price") String price,
                                       @Field("description") String description);

//    @GET("add_user")
//    Call<ResponseBody> add_user(
//
//            @Query("username") String username,
//            @Query("password") String password,
//            @Query("name") String name,
//            @Query("age") int age,
//            @Query("gender") String gender,
//            @Query("cnumber") String cnumber,
//            @Query("address") String address,
//            @Query("city_id") int city_id,
//            @Query("email") String email
//    );
}

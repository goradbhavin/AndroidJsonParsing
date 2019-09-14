package com.b.om.androidjsonparsingdemo.thirddemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    /* @POST("https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php")
        @POST("/Demonuts/JsonTest/Tennis/json_parsing.php") https://demonuts.com/
        @POST("/json_parsing.php") https://demonuts.com/Demonuts/JsonTest/Tennis */

    @FormUrlEncoded
    @POST("json_parsing.php")
    Call<UserResponse> getUserList(@Field("name") String name);
}

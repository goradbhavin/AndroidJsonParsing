package com.b.om.androidjsonparsingdemo.seconddemo;


import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiContacts {

    //@FormUrlEncoded
    @GET("/contacts/")
    Call<ContactsResponse> getContacts();
}

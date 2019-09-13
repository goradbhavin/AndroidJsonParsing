package com.b.om.androidjsonparsingdemo.controller;

import com.b.om.androidjsonparsingdemo.firstdemo.MovieApi;
import com.b.om.androidjsonparsingdemo.seconddemo.ApiContacts;
import com.b.om.androidjsonparsingdemo.thirddemo.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static HttpLoggingInterceptor logging;
    private static OkHttpClient.Builder httpClient;
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;
    private Gson gson;
    //private static String url = "https://api.androidhive.info/";
    private RetrofitClient (String url) {
        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
    }

    public static synchronized RetrofitClient getInstance(String url){
        retrofitClient = new RetrofitClient(url);
       return retrofitClient;
    }

    public ApiContacts apiContacts(){
        return retrofit.create(ApiContacts.class);
    }

    public MovieApi apiMovies(){
        return retrofit.create(MovieApi.class);
    }

    public ApiInterface apiInterface(){
        return retrofit.create(ApiInterface.class);
    }
}

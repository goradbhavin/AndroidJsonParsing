package com.b.om.androidjsonparsingdemo.firstdemo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("jsonData/moviesData.txt")
    Call<MovieResponse> getMovies();
}

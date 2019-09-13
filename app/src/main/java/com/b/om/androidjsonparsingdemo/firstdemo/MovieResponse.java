package com.b.om.androidjsonparsingdemo.firstdemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("movies")
    private List<Movie> movies;

    public List<Movie> getMovieList() {
        return movies;
    }
}

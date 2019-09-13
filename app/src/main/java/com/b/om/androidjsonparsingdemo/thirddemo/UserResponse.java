package com.b.om.androidjsonparsingdemo.thirddemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    private boolean status;
    private String message;

    @SerializedName("data")
    private List<UserModel> models;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<UserModel> getModels() {
        return models;
    }
}

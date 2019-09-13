package com.b.om.androidjsonparsingdemo.thirddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.b.om.androidjsonparsingdemo.R;
import com.b.om.androidjsonparsingdemo.controller.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdJsonDemo extends AppCompatActivity {


    String JSONURL = "https://demonuts.com/Demonuts/JsonTest/Tennis/";
    private RecyclerView rvUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_json_demo);

        rvUser = (RecyclerView) findViewById(R.id.rvUser);
        rvUser.setLayoutManager(new LinearLayoutManager(this));

        getUserList();
    }

    private void getUserList() {

        Call<UserResponse> call = RetrofitClient.getInstance(JSONURL).apiInterface().getUserList("bhavin");
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.isSuccessful()){

                   UserListAdapter listAdapter = new UserListAdapter(ThirdJsonDemo.this,response.body().getModels());
                   rvUser.setAdapter(listAdapter);
                }else {
                    Toast.makeText(ThirdJsonDemo.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(ThirdJsonDemo.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

package com.sunmeng.mvvmsimple.app;

import com.sunmeng.mvvmsimple.roomdemo.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */


    public interface UserApi {
        @GET("/users/{username}")
        Call<User> queryUserByUsername(@Path("username") String username);
    }



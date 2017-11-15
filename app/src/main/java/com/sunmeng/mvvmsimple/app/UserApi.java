package com.sunmeng.mvvmsimple.app;


import com.sunmeng.mvvmsimple.db.User;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */


public interface UserApi {

    @GET("/users/{username}")
    Call<User> queryUserByUsername(@Path("username") String username);

    @GET("/search/repositories?q=tetris+language:assembly&sort=stars&order=desc")
    Call<Projects> queryProjects(@Query("page") int page);
}



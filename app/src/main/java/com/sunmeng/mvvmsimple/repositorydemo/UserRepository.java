package com.sunmeng.mvvmsimple.repositorydemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.sunmeng.mvvmsimple.app.UserApi;
import com.sunmeng.mvvmsimple.roomdemo.User;
import com.sunmeng.mvvmsimple.utils.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class UserRepository {

    private static final UserRepository instance = new UserRepository();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return instance;
    }

    private UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

    public LiveData<User> getUser(String username) {
        final MutableLiveData<User> user = new MutableLiveData<>();
        userApi.queryUserByUsername(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        user.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
        return user;
    }

}

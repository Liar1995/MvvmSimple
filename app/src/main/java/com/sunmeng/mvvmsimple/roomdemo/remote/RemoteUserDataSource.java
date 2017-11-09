package com.sunmeng.mvvmsimple.roomdemo.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.sunmeng.mvvmsimple.app.UserApi;
import com.sunmeng.mvvmsimple.roomdemo.User;
import com.sunmeng.mvvmsimple.roomdemo.local.LocalUserDataSource;
import com.sunmeng.mvvmsimple.utils.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class RemoteUserDataSource implements UserDataSource {

    private static final RemoteUserDataSource instance = new RemoteUserDataSource();

    private RemoteUserDataSource() {
    }

    public static RemoteUserDataSource getInstance() {
        return instance;
    }

    private UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

    @Override
    public LiveData<User> queryUserByUsername(String username) {
        final MutableLiveData<User> data = new MutableLiveData<>();
        userApi.queryUserByUsername(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (null == user)
                            return;
                        data.setValue(user);
                        // update cache
                        LocalUserDataSource.getInstance().addUser(user);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.i("summer","本地数据更新失败"+t.getMessage());
                        t.printStackTrace();
                    }
                });
        return data;
    }
}

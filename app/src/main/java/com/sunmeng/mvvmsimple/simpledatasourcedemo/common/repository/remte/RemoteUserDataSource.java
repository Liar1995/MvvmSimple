package com.sunmeng.mvvmsimple.simpledatasourcedemo.common.repository.remte;

import com.sunmeng.mvvmsimple.app.UserApi;
import com.sunmeng.mvvmsimple.db.User;
import com.sunmeng.mvvmsimple.simpledatasourcedemo.common.repository.local.LocalUserDataSource;
import com.sunmeng.mvvmsimple.simpledatasourcedemo.common.repository.UserDataSource;
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
    public void queryUserByUsername(String username, final Result<User> result) {
        userApi.queryUserByUsername(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        result.onSuccess(user);
                        // update cache
                        LocalUserDataSource.getInstance().addUser(user);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                        result.onFailed(t);
                    }
                });
    }
}

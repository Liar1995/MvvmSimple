package com.sunmeng.mvvmsimple.statusdemo.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.sunmeng.mvvmsimple.app.UserApi;
import com.sunmeng.mvvmsimple.statusdemo.Lcee;
import com.sunmeng.mvvmsimple.statusdemo.UserDataSource;
import com.sunmeng.mvvmsimple.roomdemo.User;
import com.sunmeng.mvvmsimple.statusdemo.local.LocalUserDataSource;
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
    public LiveData<Lcee<User>> queryUserByUsername(String username) {
        final MutableLiveData<Lcee<User>> data = new MutableLiveData<>();
        data.setValue(Lcee.loading());

        userApi.queryUserByUsername(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (null == user) {
                            data.setValue(Lcee.empty());
                            return;
                        }
                        data.setValue(Lcee.content(user));
                        // update cache
                        LocalUserDataSource.getInstance().addUser(user);
                        Log.i("summer","queryUserByUsername success ");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                        Log.i("summer","queryUserByUsername error ");
                        data.setValue(Lcee.error(t));
                    }
                });
        return data;
    }
}

package com.sunmeng.mvvmsimple.roomdemo;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.sunmeng.mvvmsimple.roomdemo.local.LocalUserDataSource;
import com.sunmeng.mvvmsimple.roomdemo.remote.RemoteUserDataSource;
import com.sunmeng.mvvmsimple.utils.NetworkUtils;

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

    private Context context;
    private UserDataSource remoteUserDataSource = RemoteUserDataSource.getInstance();
    private UserDataSource localUserDataSource = LocalUserDataSource.getInstance();

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public LiveData<User> getUser(String username) {
        if (NetworkUtils.isConnected(context)) {
            Log.i("summer"," -- isConnected");
            return remoteUserDataSource.queryUserByUsername(username);
        } else {
            Log.i("summer"," -- not Connected");
            return localUserDataSource.queryUserByUsername(username);
        }
    }


}

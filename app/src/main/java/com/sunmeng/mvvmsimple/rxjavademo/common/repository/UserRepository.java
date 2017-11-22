package com.sunmeng.mvvmsimple.rxjavademo.common.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.sunmeng.mvvmsimple.db.User;
import com.sunmeng.mvvmsimple.rxjavademo.common.bean.Lcee;
import com.sunmeng.mvvmsimple.rxjavademo.common.repository.local.LocalUserDataSource;
import com.sunmeng.mvvmsimple.rxjavademo.common.repository.remote.RemoteUserDataSource;
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

    public LiveData<Lcee<User>> getUser(String username) {
        if (NetworkUtils.isConnected(context)) {
            Log.i("summer"," -- isConnected");
            return remoteUserDataSource.queryUserByUsername(username);
        } else {
            Log.i("summer"," -- not Connected");
            return localUserDataSource.queryUserByUsername(username);
        }
    }


}

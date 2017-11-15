package com.sunmeng.mvvmsimple.simpledatasourcedemo.common.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.sunmeng.mvvmsimple.db.User;
import com.sunmeng.mvvmsimple.simpledatasourcedemo.common.bean.Lcee;
import com.sunmeng.mvvmsimple.simpledatasourcedemo.common.repository.local.LocalUserDataSource;
import com.sunmeng.mvvmsimple.simpledatasourcedemo.common.repository.remte.RemoteUserDataSource;
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
            return getUserFromRemote(username);
        } else {
            return getUserFromLocal(username);
        }
    }

    private LiveData<Lcee<User>> getUserFromRemote(String username) {
        return getUserFromDataSource(remoteUserDataSource, username);
    }

    private LiveData<Lcee<User>> getUserFromLocal(String username) {
        return getUserFromDataSource(localUserDataSource, username);
    }

    private LiveData<Lcee<User>> getUserFromDataSource(UserDataSource dataSource, String username) {
        final MutableLiveData<Lcee<User>> data = new MutableLiveData<>();
        data.setValue(Lcee.loading());
        dataSource.queryUserByUsername(username, new UserDataSource.Result<User>() {
            @Override
            public void onSuccess(User user) {
                data.setValue(Lcee.content(user));
            }

            @Override
            public void onFailed(Throwable throwable) {
                data.setValue(Lcee.error(throwable));
            }
        });
        return data;
    }

}

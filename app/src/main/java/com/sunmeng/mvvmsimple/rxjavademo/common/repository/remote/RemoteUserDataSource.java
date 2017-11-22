package com.sunmeng.mvvmsimple.rxjavademo.common.repository.remote;

import android.arch.lifecycle.LiveData;

import com.sunmeng.mvvmsimple.rxjavademo.common.bean.Lcee;
import com.sunmeng.mvvmsimple.rxjavademo.common.repository.UserDataSource;
import com.sunmeng.mvvmsimple.rxjavademo.common.repository.convert.LiveDataObservableAdapter;
import com.sunmeng.mvvmsimple.db.User;
import com.sunmeng.mvvmsimple.utils.RetrofitFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        return LiveDataObservableAdapter.fromObservableLcee(userApi.queryUserByUsername(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()));
    }
}

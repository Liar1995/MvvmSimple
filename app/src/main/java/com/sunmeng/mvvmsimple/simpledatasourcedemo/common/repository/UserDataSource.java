package com.sunmeng.mvvmsimple.simpledatasourcedemo.common.repository;

import com.sunmeng.mvvmsimple.db.User;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public interface UserDataSource {

    interface Result<T> {
        void onSuccess(T data);
        void onFailed(Throwable throwable);
    }

    void queryUserByUsername(String username, Result<User> result);
}

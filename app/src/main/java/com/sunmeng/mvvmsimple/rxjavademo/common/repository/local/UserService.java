package com.sunmeng.mvvmsimple.rxjavademo.common.repository.local;

import android.arch.lifecycle.LiveData;

import com.sunmeng.mvvmsimple.db.User;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public interface UserService {
    LiveData<Long> add(User user);
    LiveData<User> queryByUsername(String userName);
}

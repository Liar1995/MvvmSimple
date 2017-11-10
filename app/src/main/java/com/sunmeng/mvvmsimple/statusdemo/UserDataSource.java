package com.sunmeng.mvvmsimple.statusdemo;

import android.arch.lifecycle.LiveData;

import com.sunmeng.mvvmsimple.roomdemo.User;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public interface UserDataSource {
    LiveData<Lcee<User>> queryUserByUsername(String username);
}

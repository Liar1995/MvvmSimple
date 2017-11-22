package com.sunmeng.mvvmsimple.rxjavademo.common.repository;

import android.arch.lifecycle.LiveData;

import com.sunmeng.mvvmsimple.db.User;
import com.sunmeng.mvvmsimple.rxjavademo.common.bean.Lcee;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public interface UserDataSource {
    LiveData<Lcee<User>> queryUserByUsername(String username);
}

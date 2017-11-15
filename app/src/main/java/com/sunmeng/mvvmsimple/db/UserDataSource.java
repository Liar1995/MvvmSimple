package com.sunmeng.mvvmsimple.db;

import android.arch.lifecycle.LiveData;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public interface UserDataSource {
    LiveData<User> queryUserByUsername(String username);
}

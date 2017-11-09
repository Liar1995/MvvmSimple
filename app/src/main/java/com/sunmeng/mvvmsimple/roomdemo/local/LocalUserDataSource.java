package com.sunmeng.mvvmsimple.roomdemo.local;

import android.arch.lifecycle.LiveData;

import com.sunmeng.mvvmsimple.roomdemo.User;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class LocalUserDataSource implements UserDataSource {

    private static final LocalUserDataSource instance = new LocalUserDataSource();
    private LocalUserDataSource() {
    }
    public static LocalUserDataSource getInstance() {
        return instance;
    }

    private UserService userService = UserServiceImpl.getInstance();


    @Override
    public LiveData<User> queryUserByUsername(String username) {
        return userService.queryByUsername(username);
    }


    /**
     * 远程数据访问成功后，更新本地
     * */
    public LiveData<Long> addUser(User user) {
        return userService.add(user);
    }

}

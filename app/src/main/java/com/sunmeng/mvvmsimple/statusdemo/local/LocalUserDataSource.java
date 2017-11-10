package com.sunmeng.mvvmsimple.statusdemo.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.sunmeng.mvvmsimple.statusdemo.Lcee;
import com.sunmeng.mvvmsimple.statusdemo.UserDataSource;
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
    public LiveData<Lcee<User>> queryUserByUsername(String username) {
        final MediatorLiveData<Lcee<User>> data = new MediatorLiveData<>();
        data.setValue(Lcee.<User>loading());
        data.addSource(userService.queryByUsername(username), user -> {
            if (null == user) {
                data.setValue(Lcee.empty());
            } else {
                data.setValue(Lcee.content(user));
            }
        });
        return data;
    }


    /**
     * 远程数据访问成功后，更新本地
     * */
    public LiveData<Long> addUser(User user) {
        return userService.add(user);
    }

}

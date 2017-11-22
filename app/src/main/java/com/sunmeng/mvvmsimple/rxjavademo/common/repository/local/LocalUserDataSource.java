package com.sunmeng.mvvmsimple.rxjavademo.common.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.sunmeng.mvvmsimple.db.User;
import com.sunmeng.mvvmsimple.rxjavademo.common.bean.Lcee;
import com.sunmeng.mvvmsimple.rxjavademo.common.repository.UserDataSource;

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
        data.setValue(Lcee.loading());
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

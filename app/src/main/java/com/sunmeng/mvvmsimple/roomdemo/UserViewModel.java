package com.sunmeng.mvvmsimple.roomdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class UserViewModel extends ViewModel {

    private UserRepository userRepository = UserRepository.getInstance();

    private LiveData<User> user;

    public LiveData<User> getUser(String username) {
        if (null == user)
            user = userRepository.getUser(username);
        return user;
    }


}
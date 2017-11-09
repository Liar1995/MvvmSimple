package com.sunmeng.mvvmsimple.hellomvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by sunmeng on 2017/11/7.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class UserViewModel extends ViewModel {
    private MutableLiveData<User> user;

    public LiveData<User> getUser() {
        if (user == null)
            user = new MutableLiveData<>();
        return user;
    }

    public void setUsername(String username) {
        user.setValue(new User(1995, username));
    }
}

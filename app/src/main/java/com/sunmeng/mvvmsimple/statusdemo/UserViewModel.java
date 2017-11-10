package com.sunmeng.mvvmsimple.statusdemo;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.sunmeng.mvvmsimple.roomdemo.User;


/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class UserViewModel extends ViewModel {

    private UserRepository userRepository = UserRepository.getInstance();
    private MutableLiveData<String> ldUsername;
    private LiveData<Lcee<User>> user;

    public LiveData<Lcee<User>> getUser() {
        if (null == user) {
            user = Transformations.switchMap(ldUsername, input -> userRepository.getUser(input));
        }
        return user;
    }

    public void reload(String username) {
        if (ldUsername == null)
            ldUsername = new MutableLiveData<>();
        ldUsername.setValue(username);
    }


}

package com.sunmeng.mvvmsimple.simpledatasourcedemo.common.repository.local;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.sunmeng.mvvmsimple.db.User;
import com.sunmeng.mvvmsimple.simpledatasourcedemo.common.repository.UserDataSource;

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


    @SuppressLint("StaticFieldLeak")
    @Override
    public void queryUserByUsername(String username, Result<User> result) {
        new AsyncTask<Void, Void, Object>() {
            @Override
            protected Object doInBackground(Void... voids) {
                try {
                    User user = userService.queryByUsername(username);
                    return user;
                } catch (Exception e) {
                    e.printStackTrace();
                    return e;
                }
            }

            @Override
            protected void onPostExecute(Object obj) {
                if (obj instanceof User)
                    result.onSuccess((User) obj);
                else if (obj instanceof Exception)
                    result.onFailed((Throwable) obj);
                else
                    result.onFailed(null);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void addUser(final User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userService.add(user);
                return null;
            }
        }.execute();
    }
}

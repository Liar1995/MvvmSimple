package com.sunmeng.mvvmsimple.roomdemo.local;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.sunmeng.mvvmsimple.db.DBHelper;
import com.sunmeng.mvvmsimple.db.UserDao;
import com.sunmeng.mvvmsimple.roomdemo.User;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    private UserDao userDao = DBHelper.getInstance().getDb().getUserDao();


    @SuppressLint("StaticFieldLeak")
    @Override
    public LiveData<Long> add(final User user) {
        final MutableLiveData<Long> data = new MutableLiveData<>();

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                Log.i("summer","本地数据更新成功");
                return userDao.add(user);
            }

            @Override
            protected void onPostExecute(Long rowId) {
                data.setValue(rowId);
            }
        }.execute();

        return data;
    }

    @Override
    public LiveData<User> queryByUsername(String userName) {
        return userDao.queryByUsername(userName);
    }
}

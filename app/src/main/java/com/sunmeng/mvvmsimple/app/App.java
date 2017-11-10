package com.sunmeng.mvvmsimple.app;

import android.app.Application;

import com.sunmeng.mvvmsimple.db.DBHelper;
import com.sunmeng.mvvmsimple.statusdemo.UserRepository;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.getInstance().init(this);
        UserRepository.getInstance().init(this);
    }
}

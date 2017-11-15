package com.sunmeng.mvvmsimple.simpledatasourcedemo.common;

import android.arch.persistence.room.Room;
import android.content.Context;


/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class DBHelper {

    private static final DBHelper instance = new DBHelper();
    private static final String DATABASE_NAME = "mvvm_cache";

    private DBHelper() {

    }

    public static DBHelper getInstance() {
        return instance;
    }

    private DB db;

    public void init(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(), DB.class, DATABASE_NAME).build();
    }

    public DB getDb() {
        return db;
    }

}

package com.sunmeng.mvvmsimple.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sunmeng.mvvmsimple.roomdemo.User;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {
    public abstract UserDao getUserDao();
}

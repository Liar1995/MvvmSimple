package com.sunmeng.mvvmsimple.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sunmeng.mvvmsimple.roomdemo.User;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //指发生冲突的解决方式
    Long add(User user);

    @Query("select * from user where login =:userName")
    LiveData<User> queryByUsername(String userName);
}

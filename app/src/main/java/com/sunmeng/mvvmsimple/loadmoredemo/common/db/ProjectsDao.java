package com.sunmeng.mvvmsimple.loadmoredemo.common.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Dao
public interface ProjectsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)// cache need update
    Long add(Projects projects);

    @Query("select * from projects where page=:page")
    LiveData<Projects> queryProjects(int page);
}

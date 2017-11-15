package com.sunmeng.mvvmsimple.loadmoredemo.common.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

@Database(entities = {Projects.class}, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {
    public abstract ProjectsDao getProjectsDao();

}

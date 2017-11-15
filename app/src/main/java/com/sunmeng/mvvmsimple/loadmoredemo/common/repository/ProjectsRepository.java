package com.sunmeng.mvvmsimple.loadmoredemo.common.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Lcee;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;
import com.sunmeng.mvvmsimple.loadmoredemo.common.repository.local.LocalProjectDataSource;
import com.sunmeng.mvvmsimple.loadmoredemo.common.repository.remote.RemoteProjectDataSource;
import com.sunmeng.mvvmsimple.utils.NetworkUtils;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class ProjectsRepository {
    private static final ProjectsRepository instance = new ProjectsRepository();
    private ProjectsRepository() {
    }
    public static ProjectsRepository getInstance() {
        return instance;
    }


    private Context context;
    private ProjectDataSource remoteProjectDataSource = RemoteProjectDataSource.getInstance();
    private ProjectDataSource localProjectDataSource = LocalProjectDataSource.getInstance();

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public LiveData<Lcee<Projects>> getProjects(int page) {
        Log.i("summer","---ProjectsRepository getProjects");
        if (NetworkUtils.isConnected(context)) {
            return remoteProjectDataSource.queryProjects(page);
        } else {
            return localProjectDataSource.queryProjects(page);
        }
    }
}

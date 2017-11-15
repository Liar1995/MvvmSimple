package com.sunmeng.mvvmsimple.loadmoredemo.common.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.util.Log;

import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Lcee;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;
import com.sunmeng.mvvmsimple.loadmoredemo.common.repository.ProjectDataSource;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class LocalProjectDataSource implements ProjectDataSource {

    private static final LocalProjectDataSource instance = new LocalProjectDataSource();

    private LocalProjectDataSource() {
    }

    public static LocalProjectDataSource getInstance() {
        return instance;
    }


    private ProjectsService projectsService = ProjectsServiceImpl.getInstance();

    /**
     * 为remote提供储存服务
     * */
    public LiveData<Long> addProjects(Projects projects) {
        projects.itemsToJson();
        Log.i("summer","---LocalProjectDataSource addProjects");
        return projectsService.add(projects);
    }

    @Override
    public LiveData<Lcee<Projects>> queryProjects(int page) {
        final MediatorLiveData<Lcee<Projects>> data = new MediatorLiveData<>();
        data.setValue(Lcee.loading());//先展示loading
        data.addSource(projectsService.queryProjects(page), projects -> {
            if (null == projects) {
                data.setValue(Lcee.empty());
            } else {
                projects.itemsFromJson();
                Log.i("summer","---LocalProjectDataSource queryProjects");
                data.setValue(Lcee.content(projects));
            }
        });
        return data;
    }
}

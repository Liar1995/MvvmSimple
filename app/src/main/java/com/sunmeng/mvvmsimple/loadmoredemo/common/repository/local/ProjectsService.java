package com.sunmeng.mvvmsimple.loadmoredemo.common.repository.local;

import android.arch.lifecycle.LiveData;

import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public interface ProjectsService {
    LiveData<Long> add(Projects projects);

    /**
     *
     * @param page start from 1
     * @return
     */
    LiveData<Projects> queryProjects(int page);
}
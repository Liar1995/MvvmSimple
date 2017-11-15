package com.sunmeng.mvvmsimple.loadmoredemo.common.repository;

import android.arch.lifecycle.LiveData;

import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Lcee;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public interface ProjectDataSource {
    LiveData<Lcee<Projects>> queryProjects(int page);
}

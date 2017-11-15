package com.sunmeng.mvvmsimple.loadmoredemo.common.repository.local;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;
import com.sunmeng.mvvmsimple.loadmoredemo.common.db.DBHelper;
import com.sunmeng.mvvmsimple.loadmoredemo.common.db.ProjectsDao;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class ProjectsServiceImpl implements ProjectsService {

    private static final ProjectsServiceImpl instance = new ProjectsServiceImpl();

    private ProjectsServiceImpl() {
    }

    public static ProjectsServiceImpl getInstance() {
        return instance;
    }


    private ProjectsDao projectsDao = DBHelper.getInstance().getDb().getProjectsDao();


    @SuppressLint("StaticFieldLeak")
    @Override
    public LiveData<Long> add(Projects projects) {
        // transfer long to LiveData<Long>
        final MutableLiveData<Long> data = new MutableLiveData<>();
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return projectsDao.add(projects);
            }

            @Override
            protected void onPostExecute(Long rowId) {
                data.setValue(rowId);
                Log.i("summer","---ProjectsServiceImpl add");
            }
        }.execute();
        return data;
    }

    @Override
    public LiveData<Projects> queryProjects(int page) {
        Log.i("summer","---ProjectsServiceImpl queryProjects");
        return projectsDao.queryProjects(page);
    }
}

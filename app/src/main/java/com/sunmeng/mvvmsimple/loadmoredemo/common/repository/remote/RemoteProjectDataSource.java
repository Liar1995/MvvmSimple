package com.sunmeng.mvvmsimple.loadmoredemo.common.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.sunmeng.mvvmsimple.app.UserApi;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Lcee;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;
import com.sunmeng.mvvmsimple.loadmoredemo.common.repository.ProjectDataSource;
import com.sunmeng.mvvmsimple.loadmoredemo.common.repository.local.LocalProjectDataSource;
import com.sunmeng.mvvmsimple.utils.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class RemoteProjectDataSource implements ProjectDataSource {

    private static final RemoteProjectDataSource instance = new RemoteProjectDataSource();

    private RemoteProjectDataSource() {
    }

    public static RemoteProjectDataSource getInstance() {
        return instance;
    }


    private UserApi projectApi = RetrofitFactory.getInstance().create(UserApi.class);

    @Override
    public LiveData<Lcee<Projects>> queryProjects(int page) {
        final MutableLiveData<Lcee<Projects>> data = new MutableLiveData<>();
        data.setValue(Lcee.loading());
        projectApi.queryProjects(page)
                .enqueue(new Callback<Projects>() {
                    @Override
                    public void onResponse(Call<Projects> call, Response<Projects> response) {
                        Projects projects = response.body();
                        if (null == projects) {
                            data.setValue(Lcee.empty());
                            return;
                        }
                        Log.i("summer","---RemoteProjectDataSource queryProjects");
                        data.setValue(Lcee.content(projects));
                        projects.setPage(page);
                        LocalProjectDataSource.getInstance().addProjects(projects);
                    }

                    @Override
                    public void onFailure(Call<Projects> call, Throwable t) {
                        t.printStackTrace();
                        data.setValue(Lcee.error(t));
                    }
                });
        return data;
    }
}

package com.sunmeng.mvvmsimple.loadmoredemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Lcee;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;
import com.sunmeng.mvvmsimple.loadmoredemo.common.repository.ProjectsRepository;
import com.sunmeng.mvvmsimple.utils.PageUtils;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class ProjectsViewModel extends ViewModel {

    private ProjectsRepository projectsRepository = ProjectsRepository.getInstance();
    private MutableLiveData<Integer> ldPage;
    private LiveData<Lcee<Projects>> ldProjects;

    public LiveData<Lcee<Projects>> getProjects() {
        if (null == ldProjects) {
            ldPage = new MutableLiveData<>();
            ldProjects = Transformations.switchMap(ldPage, page -> projectsRepository.getProjects(page));
        }
        Log.i("summer","---ProjectsViewModel getProjects");
        return ldProjects;
    }

    public void reload() {
        Log.i("summer","---ProjectsViewModel reload");
        ldPage.setValue(1);
    }

    public void loadMore(int currentCount) {
        ldPage.setValue(PageUtils.getPage(currentCount));
    }
}

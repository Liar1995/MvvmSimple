package com.sunmeng.mvvmsimple.loadmoredemo;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sunmeng.mvvmsimple.R;
import com.sunmeng.mvvmsimple.loadmoredemo.adapter.ProjectsAdapter;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Lcee;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.ListStatus;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Projects;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.Status;
import com.sunmeng.mvvmsimple.loadmoredemo.common.db.DBHelper;
import com.sunmeng.mvvmsimple.loadmoredemo.common.repository.ProjectsRepository;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class LoadMoreActivity extends AppCompatActivity {

    private FrameLayout vContent;
    private FrameLayout vError;
    private FrameLayout vEmpty;
    private FrameLayout vLoading;
    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    private ProjectsAdapter projectsAdapter;
    private ProjectsViewModel projectsViewModel;
    private Status status;
    private ListStatus listStatus = ListStatus.Content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadmore);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        vContent = findViewById(R.id.v_content);
        vError = findViewById(R.id.v_error);
        vLoading = findViewById(R.id.v_loading);
        vEmpty = findViewById(R.id.v_empty);

        srl = findViewById(R.id.srl);
        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        projectsAdapter = new ProjectsAdapter();
        rv.setAdapter(projectsAdapter);
    }

    private void initData() {
        Log.i("summer","---initData");
        DBHelper.getInstance().init(this);
        ProjectsRepository.getInstance().init(this);
        projectsViewModel = ViewModelProviders.of(this).get(ProjectsViewModel.class);
        projectsViewModel.getProjects().observe(this, this::updateView);
        reload();
    }


    private void initEvent() {
        View.OnClickListener reloadClickListener = view -> reload();
        vError.setOnClickListener(reloadClickListener);
        vEmpty.setOnClickListener(reloadClickListener);

        // refresh
        srl.setOnRefreshListener(() -> {
            if (isLoading()) {
                srl.setRefreshing(false);
                return;
            }
            listStatus = ListStatus.Refreshing;
            Log.i("summer","---LoadMoreActivity reload");
            reload();
        });

        // load more
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mLastVisibleItemPosition;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                }
                if (projectsAdapter == null)
                    return;

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == projectsAdapter.getItemCount()) {
                    if (isLoading())
                        return;
                    listStatus = ListStatus.LoadingMore;
                    loadMore();
                }
            }
        });
    }


    private void reload() {
        projectsViewModel.reload();
    }

    private void loadMore() {
        Log.i("summer","---LoadMoreActivity loadMore");
        projectsViewModel.loadMore(projectsAdapter.getItemCount());
    }


    private void updateView(Lcee<Projects> lcee) {
        Log.i("summer","---LoadMoreActivity updateView");
        status = lcee.status;
        switch (lcee.status) {
            case Content: {
                updateContentView(lcee.data);
                break;
            }
            case Empty: {
                updateEmptyView();
                break;
            }
            case Error: {
                updateErrorView();
                break;
            }
            case Loading: {
                updateLoadingView();
                break;
            }
        }
    }

    private boolean isLoading() {
        return status == Status.Loading;
    }

    private void updateContentView(Projects projects) {
        Log.i("summer","---LoadMoreActivity updateContentView");
        switch (listStatus) {
            case LoadingMore: {
                projectsAdapter.addData(projects.getItems());
                break;
            }
            case Refreshing: {
                srl.setRefreshing(false);
                break;
            }
            default: {
                showContent();
                projectsAdapter.setData(projects.getItems());
                break;
            }
        }
    }

    private void updateEmptyView() {
        Log.i("summer","---LoadMoreActivity updateEmptyView");
        switch (listStatus) {
            case LoadingMore: {
                break;
            }
            case Refreshing: {
                srl.setRefreshing(false);
                showEmpty();
                break;
            }
            default: {
                showEmpty();
                break;
            }
        }
    }

    private void updateErrorView() {
        Log.i("summer","---LoadMoreActivity updateErrorView");
        switch (listStatus) {
            case LoadingMore: {
                break;
            }
            case Refreshing: {
                srl.setRefreshing(false);
                Toast.makeText(this, "Refresh failed", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                showError();
                break;
            }
        }
    }

    private void updateLoadingView() {
        Log.i("summer","---LoadMoreActivity updateLoadingView");
        switch (listStatus) {
            case LoadingMore: {
                // todo show loading more view in list footer

                break;
            }
            case Refreshing: {

                break;
            }
            default: {
                showLoading();
                break;
            }
        }
    }

    private void showContent() {
        vContent.setVisibility(View.VISIBLE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.GONE);
    }

    private void showEmpty() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.VISIBLE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.GONE);
    }

    private void showError() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.VISIBLE);
        vLoading.setVisibility(View.GONE);
    }

    private void showLoading() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.VISIBLE);
    }


}

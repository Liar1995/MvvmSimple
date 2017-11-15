package com.sunmeng.mvvmsimple.loadmoredemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunmeng.mvvmsimple.R;
import com.sunmeng.mvvmsimple.loadmoredemo.common.bean.ProjectItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder> {

    private List<ProjectItem> data = new ArrayList<>();

    public ProjectsAdapter() {
    }

    public ProjectsAdapter(List<ProjectItem> data) {
        this.data = data;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        ProjectItem item = getData().get(position);
        holder.tvName.setText(item.getFull_name());
        holder.tvStar.setText(item.getStargazers_count() + " star");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ProjectItem> data) {
        if (null != data)
            this.data = data;
        else
            this.data.clear();

        notifyDataSetChanged();
    }

    public void addData(List<ProjectItem> data) {
        if (null == data || data.size() == 0)
            return;

        int index = getItemCount();
        this.data.addAll(data);
        notifyItemRangeInserted(index, data.size());
    }


    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvStar;

        public ProjectViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvStar = itemView.findViewById(R.id.tv_star);
        }
    }

    public List<ProjectItem> getData() {
        return data;
    }

}

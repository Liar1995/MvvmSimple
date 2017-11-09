package com.sunmeng.mvvmsimple.hellomvvm;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sunmeng.mvvmsimple.R;
import com.sunmeng.mvvmsimple.hellomvvm.User;
import com.sunmeng.mvvmsimple.hellomvvm.UserViewModel;

/**
 * Created by sunmeng on 2017/11/7.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class HelloMvvmActivity extends AppCompatActivity {

    private TextView tvId;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        tvId = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
    }

    private void initData() {
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                updateView(user);
            }
        });
        userViewModel.setUsername("ittianyu");
    }

    private void updateView(User user) {
        tvId.setText(user.getId() + "");
        tvName.setText(user.getName());
    }

}

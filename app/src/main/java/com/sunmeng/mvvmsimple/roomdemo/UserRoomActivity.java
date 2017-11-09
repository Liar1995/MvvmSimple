package com.sunmeng.mvvmsimple.roomdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sunmeng.mvvmsimple.R;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class UserRoomActivity extends AppCompatActivity {

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

        userViewModel.getUser("Liar1995").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                updateView(user);
            }
        });
    }

    private void updateView(User user) {
        tvId.setText(user.getAvatar_url());
        tvName.setText(user.getName());
    }
}

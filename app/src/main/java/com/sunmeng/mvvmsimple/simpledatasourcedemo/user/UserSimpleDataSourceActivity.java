package com.sunmeng.mvvmsimple.simpledatasourcedemo.user;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunmeng.mvvmsimple.R;
import com.sunmeng.mvvmsimple.db.User;
import com.sunmeng.mvvmsimple.simpledatasourcedemo.common.bean.Lcee;

/**
 * Created by sunmeng on 2017/11/9.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class UserSimpleDataSourceActivity extends AppCompatActivity {

    EditText et_username;
    private TextView tvId;
    private TextView tvName;
    private Button btn_search;
    private LinearLayout vContent;
    private FrameLayout vError;
    private FrameLayout vEmpty;
    private FrameLayout vLoading;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        vContent = findViewById(R.id.v_content);
        vError = findViewById(R.id.v_error);
        vLoading = findViewById(R.id.v_loading);
        vEmpty = findViewById(R.id.v_empty);
        et_username = findViewById(R.id.et_username);
        btn_search = findViewById(R.id.btn_search);
        tvId = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
        btn_search.setOnClickListener(view -> initData());
    }

    private void reload() {
        userViewModel.reload(getUsername());
    }

    private String getUsername() {
        return et_username.getText().toString();
    }

    private void initData() {
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.reload(getUsername());
        userViewModel.getUser().observe(this, this::updateView);
    }


    private void initEvent() {
        View.OnClickListener reloadClickListener = view -> {
            hideKeyboard();
            reload();
        };
        vError.setOnClickListener(reloadClickListener);
        vEmpty.setOnClickListener(reloadClickListener);

        findViewById(R.id.btn_search).setOnClickListener(reloadClickListener);

        et_username.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                hideKeyboard();
                reload();
                return true;
            }
            return false;
        });
    }

    private void hideKeyboard() {
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void updateView(Lcee<User> lcee) {
        switch (lcee.status) {
            case Content: {
                showContent();
                tvId.setText(lcee.data.getId() + "");
                tvName.setText(lcee.data.getName());
                break;
            }
            case Empty: {
                showEmpty();
                break;
            }
            case Error: {
                showError();
                break;
            }
            case Loading: {
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

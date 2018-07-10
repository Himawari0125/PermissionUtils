package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.LogUtils;

public class StackTaceActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_stacktrace);
        super.onCreate(savedInstanceState);
        One();
    }

    private void One(){
        Two();
    }

    private void Two(){
        LogUtils.i(3,"function two");

    }
}

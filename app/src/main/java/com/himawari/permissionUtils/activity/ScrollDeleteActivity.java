package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;

/**
 * Created by S.Lee on 2017/12/29.
 */

public class ScrollDeleteActivity extends BaseActivity {
    private ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_scrolldelete);
        super.onCreate(savedInstanceState);
        constraintLayout.setMinWidth((int) MyApplication.width);
    }

    @Override
    protected void initView() {
        constraintLayout = findViewById(R.id.imageView14);

    }
}

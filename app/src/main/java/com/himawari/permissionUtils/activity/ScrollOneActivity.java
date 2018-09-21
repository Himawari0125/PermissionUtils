package com.himawari.permissionUtils.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.view.DragEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.LogUtils;

public class ScrollOneActivity extends BaseActivity {
    private ConstraintLayout constraintLayout;
    private HorizontalScrollView hscrollview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.item_scrolldelete);
        super.onCreate(savedInstanceState);
        constraintLayout.setMinWidth((int) MyApplication.width);

    }

    @Override
    protected void initView() {
        constraintLayout = findViewById(R.id.imageView14);
        hscrollview = findViewById(R.id.hscrollview);
    }
}
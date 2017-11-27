package com.himawari.permissionUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by S.Lee on 2017/9/15 0015.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected static String TAG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        TAG = this.getClass().getSimpleName()+"_";
    }

    protected abstract void initView();
}

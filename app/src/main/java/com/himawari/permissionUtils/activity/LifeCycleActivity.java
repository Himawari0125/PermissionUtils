package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.LogUtils;

import static com.himawari.permissionUtils.MyApplication.finishTillActivity;

public class LifeCycleActivity extends BaseActivity {

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_lifecycle);
        super.onCreate(savedInstanceState);

    }

    public void onFinishTillClick(View view){


        finishTillActivity(MainViewActivity.class.getSimpleName());




    }
}

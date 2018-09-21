package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.MyActivityManager;


public class LifeCycleActivity extends BaseActivity {

    private MyActivityManager manager;
    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_lifecycle);
        super.onCreate(savedInstanceState);
        manager = MyActivityManager.getManager();

    }

    public void onFinishTillClick(View view){


        manager.finishTillActivity(MainViewActivity.class.getSimpleName());




    }
}

package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.ProgressUtils;

/**
 * Created by S.Lee on 2018/3/22.
 */

public class ProgressActivity extends BaseActivity {
    @Override
    protected void initView() {
        ProgressUtils.showProgressWait(this,"正在加载...");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_progress);
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    ProgressUtils.dismissProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}

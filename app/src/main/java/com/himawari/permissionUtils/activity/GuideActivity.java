package com.himawari.permissionUtils.activity;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.views.GifView;
import com.himawari.permissionUtils.views.PointView;

/**
 * Created by S.Lee on 2018/1/5.
 */

public class GuideActivity extends BaseActivity {

    private GifView gifView;
    private PointView pointView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

        gifView = findViewById(R.id.gifView);
        pointView = findViewById(R.id.pointView);
        pointView.setIsCurrent(3);

    }

}

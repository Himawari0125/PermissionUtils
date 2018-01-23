package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.views.ScaleplateView;

/**
 * Created by S.Lee on 2017/12/27.
 */

public class ScalePlateActivity extends BaseActivity {
    private ScaleplateView scaleplateView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_scaleplate);
        super.onCreate(savedInstanceState);
        scaleplateView.setBodyTypeAndWeight(new String[]{"偏瘦","正常","偏胖","肥胖","重度","极度"},
                new String[]{"18.5","24.9","29.9","34.6","36.9"},2,1.5f);//少个0.2 why
    }

    @Override
    protected void initView() {
        scaleplateView = findViewById(R.id.scaleplate);

    }
}

package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.views.MoireView;


/**
 * Created by S.Lee on 2017/12/26.
 */

public class MoireActivity extends BaseActivity {
    private MoireView moireView;
    @Override
    protected void initView() {
        moireView = findViewById(R.id.moireview);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_moire);
        super.onCreate(savedInstanceState);

    }
}

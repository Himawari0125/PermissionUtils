package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.views.ScrollPickerView;

import java.util.Arrays;


/**
 * Created by S.Lee on 2018/1/3.
 */

public class BirthdayPickerActivity extends BaseActivity {
    private ScrollPickerView scrollPickerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_birthdaypicker);
        super.onCreate(savedInstanceState);
        scrollPickerView = findViewById(R.id.scrollview);
        scrollPickerView.setDatas(2002,2032,false);
    }

    @Override
    protected void initView() {

    }
}

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
        scrollPickerView.setDatas(Arrays.asList(new String[]{"2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017"
        ,"2018","2019","2020"}),false);
    }

    @Override
    protected void initView() {

    }
}

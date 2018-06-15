package com.himawari.permissionUtils.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.AddScaleDateBeans;
import com.himawari.permissionUtils.parcels.ParcelTest;

/**
 * Created by S.Lee on 2018/3/2.
 */

public class ParcelReceiverActivity extends BaseActivity {
    private TextView tv5,tv6,tv7,tv8;
    @Override
    protected void initView() {
        tv5 = findViewById(R.id.textView5);
        tv6 = findViewById(R.id.textView6);
        tv7 = findViewById(R.id.textView7);
        tv8 = findViewById(R.id.textView8);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_parcelreceiver);
        super.onCreate(savedInstanceState);

        Intent intent1 = getIntent();
        ParcelTest parcelTest = intent1.getParcelableExtra("parcelTest");
        AddScaleDateBeans beans = parcelTest.getmData();
        tv5.setText(beans.getCheckTime().toString());
        tv6.setText(beans.getMuscleControl()+"");
        tv7.setText(beans.getBone()+"");
        tv8.setText(beans.getFatControl()+"");

    }
}

package com.himawari.permissionUtils.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.AddScaleDateBeans;
import com.himawari.permissionUtils.parcels.ParcelTest;

/**
 * Created by S.Lee on 2018/3/2.
 */

public class ParcelActivity extends BaseActivity{
    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel);
    }

    public void onPutParcelClick(View view){
        Intent it = new Intent(this,ParcelReceiverActivity.class);
        AddScaleDateBeans beans = new AddScaleDateBeans();
        beans.setCheckTime("2018.03.02");
        beans.setFatControl(17.5f);
        beans.setMuscleControl(0.0f);
        beans.setBone(22.5f);

        ParcelTest parcelTest = new ParcelTest(beans);
        it.putExtra("parcelTest",parcelTest);
        startActivity(it);
    }
}

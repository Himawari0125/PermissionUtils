package com.himawari.permissionUtils.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.AddScaleDateBeans;
import com.himawari.permissionUtils.parcels.ParcelTest;
import com.himawari.permissionUtils.utils.LogUtils;
import com.himawari.permissionUtils.utils.TimeUtils;

import java.util.Random;

/**
 * Created by S.Lee on 2018/3/2.
 */

public class ParcelActivity extends BaseActivity{

    private String checkTime = "";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            inCrease();


        }
    };
    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_parcel);
        super.onCreate(savedInstanceState);


        long startMills = System.currentTimeMillis();
        Random r = new Random(47);
        mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(r.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        long endMills = System.currentTimeMillis();

        LogUtils.i(LogUtils.originalIndex,"StartMills:"+startMills+" endMills:"+endMills);


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

    public void inCrease(){

        AddScaleDateBeans beans = new AddScaleDateBeans();
        beans.setCheckTime("2018.03.02");
        beans.setFatControl(17.5f);
        beans.setMuscleControl(0.0f);
        beans.setBone(22.5f);
        beans.setCheckTime(TimeUtils.getFormatTimeStr(TimeUtils.FormatExpression.YMDHMS));

        if(checkTime.equals(beans.getCheckTime())){
           // LogUtils.i(LogUtils.originalIndex," =============== ");
            return;
        }
        LogUtils.i(LogUtils.originalIndex,"before temp:"+beans.getCheckTime()+" checkTime:"+checkTime);
        checkTime = beans.getCheckTime();
        LogUtils.i(LogUtils.originalIndex,"after  temp:"+beans.getCheckTime()+" checkTime:"+checkTime);
        LogUtils.i(LogUtils.originalIndex," ----------------------------------------- ");
    }
}

package com.himawari.permissionUtils.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.himawari.permissionUtils.utils.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by S.Lee on 2017/9/29.
 */

public class BaseService extends Service {

    private TimerTask timerTask;
    private Timer timer;


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("===onCreate===Service===");
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("===BaseService===");
            }
        };
        timer.schedule(timerTask,0,300);

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //@IntDef(value = {Service.START_FLAG_REDELIVERY, Service.START_FLAG_RETRY} => int flags
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("===onStartCommand===Service===");
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

}

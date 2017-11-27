package com.himawari.permissionUtils.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by S.Lee on 2017/9/29.
 */

public class BaseService extends Service {
    private boolean isRunning;

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //@IntDef(value = {Service.START_FLAG_REDELIVERY, Service.START_FLAG_RETRY} => int flags
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!isRunning){
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(3000);
                    isRunning = false;
                    startService(new Intent(BaseService.this, ProtectorService.class));
                    System.out.println("BaseService");
                }
            }).start();
        }

        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

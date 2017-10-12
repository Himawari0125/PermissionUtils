package com.himawari.a24hoursrecord.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by S.Lee on 2017/10/11.
 */

public class ProtectorService extends Service {
    private boolean isRunning;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        if(!isRunning){
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(3000);
                    isRunning = false;
                    startService(new Intent(ProtectorService.this, BaseService.class));
                    System.out.println("ProtectorService");
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

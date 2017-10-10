package com.himawari.a24hoursrecord.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import com.himawari.a24hoursrecord.Contant;
import com.himawari.a24hoursrecord.utils.AlarmUtils;

/**
 * Created by S.Lee on 2017/9/29.
 */

public class BaseService extends Service {

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
        String settingAlarm = intent.getStringExtra(Contant.SETALARM);
        Log.i("MyService",settingAlarm==null?"null":settingAlarm);
        AlarmUtils.SetAlarm(this,settingAlarm,"com.himawari.requestpermission.mybroadcast",AlarmUtils.SUBSCRIPT_TWENTYHOUR);
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

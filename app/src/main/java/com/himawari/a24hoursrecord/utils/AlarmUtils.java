package com.himawari.a24hoursrecord.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;

import com.himawari.a24hoursrecord.activity.AlarmActivity;

import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by S.Lee on 2017/9/8 0008.
 */

public class AlarmUtils {
    private static AlarmManager am;
    public static void SetAlarm(Context mContext, String firstStr){
        am = (AlarmManager)mContext.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent();
        intent.setAction("com.himawari.requestpermission.mybroadcast");
        PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        if(firstStr == null || "".equals(firstStr)){
            calendar.setTimeInMillis(System.currentTimeMillis());
        }else{
            String[] time = firstStr.split(":");
            calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time[0]));
            calendar.set(Calendar.MINUTE,Integer.parseInt(time[1]));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pi);
        }else{
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),24 * 60 * 60 * 1000, pi);
        }
        Log.i("Calendar:",new Date(calendar.getTimeInMillis()).toString());

    }
}

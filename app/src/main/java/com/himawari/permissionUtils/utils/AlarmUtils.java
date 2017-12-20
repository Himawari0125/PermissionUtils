package com.himawari.permissionUtils.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by S.Lee on 2017/9/8 0008.
 */

public class AlarmUtils {
    public static AlarmManager am;
    public static final int SUBSCRIPT_TWENTYHOUR = 0;
    public static final int SUBSCRIPT_ONEDAY = 1;
    public static final int SUBSCRIPT_THREEDAY= 2;
    public static final int SUBSCRIPT_SEVENDAY= 3;
    public static final int SUBSCRIPT_ONEMONTH= 4;
    public static final int SUBSCRIPT = 5;
    public static void SetAlarm(Context mContext, String firstStr,String actionStr,int subscript){
        am = (AlarmManager)mContext.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent();
        intent.setAction(actionStr);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        if(firstStr == null || "".equals(firstStr)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                calendar.setTimeInMillis(calendar.getTimeInMillis()+getIntervalMillis(subscript));
                //calendar.set(Calendar.SECOND,0);
                am.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pi);
            }else{

            }
        }else{//第一次设置
            String[] time = firstStr.split(":");
            calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time[0]));
            calendar.set(Calendar.MINUTE,Integer.parseInt(time[1]));
            calendar.set(Calendar.SECOND,0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                am.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pi);
            }else{
                am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),getIntervalMillis(subscript), pi);
            }
        }
        Log.i("Calendar:",new Date(calendar.getTimeInMillis()).toString());

    }

    /**
     *
     * @param alarmCalendar
     * @return alarmTime>currentTime?true:false
     */
    private static boolean alarmTimeIsLessthanCurrent(Calendar alarmCalendar){
        return alarmCalendar.getTimeInMillis() > System.currentTimeMillis();
    }

    private static long getIntervalMillis(int subscript) {
        if (SUBSCRIPT_TWENTYHOUR == subscript) {
            // 12个小时的毫秒数
          //  return 12 * 60 * 60 * 1000;
            return 5* 60 * 1000;
        } else if (SUBSCRIPT_ONEDAY == subscript) {
            // 一天的毫秒数
            return 24 * 60 * 60 * 1000;
        } else if (SUBSCRIPT_THREEDAY == subscript) {
            // 三天的的毫秒数
            return ((24 * 3) * 60 * 60 * 1000);
        } else if (SUBSCRIPT_SEVENDAY == subscript) {
            // 七天的毫秒数
            return ((24 * 7) * 60 * 60 * 1000);
        } else if (SUBSCRIPT_ONEMONTH == subscript) {
            // 一个月的毫秒数
            return getCurrentToNextMonth() * 60 * 60 * 1000;
        }
        return 0;
    }


    /*
     * 获取当前月的日期到下个月的日期的天数
     */
    public static int getCurrentToNextMonth() {
        // 得到Calendar的实例，获取当前是哪一天，获取当前月份的最后一天
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int currentMaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 计算本月剩余天数
        int currentMonthResidueDay = currentMaxDay - currentDay;

        // 获取下个月的最后一天
        cal.add(Calendar.MONTH, 1);
        int nextMaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 返回当前这个月的日期到下个月的这个日期的总天数
        if (nextMaxDay > currentDay) {
            return currentMonthResidueDay + currentDay;
        } else if (nextMaxDay == currentDay || nextMaxDay < currentDay) {
            return nextMaxDay + currentMonthResidueDay;
        }
        return 0;
    }
}

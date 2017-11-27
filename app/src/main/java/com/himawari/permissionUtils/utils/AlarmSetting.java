package com.himawari.permissionUtils.utils;

/**
 * Created by S.Lee on 2017/9/8 0008.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

public class AlarmSetting {
    private static final int SUBSCRIPT_TWENTYHOUR = 0;
    private static final int SUBSCRIPT_ONEDAY = 1;
    private static final int SUBSCRIPT_THREEDAY= 2;
    private static final int SUBSCRIPT_SEVENDAY= 3;
    private static final int SUBSCRIPT_ONEMONTH= 4;
    private static final int SUBSCRIPT = 5;

    public static void setAlarmNeed(Context context, int month, int day, int type) {
        // 设置闹铃的时间
        Calendar setCalendar = Calendar.getInstance();
        setCalendar.setTimeInMillis(System.currentTimeMillis());
        setCalendar.set(Calendar.MONTH, month);
        setCalendar.set(Calendar.DATE, day);
        setCalendar.set(Calendar.HOUR_OF_DAY, 10);
        setCalendar.set(Calendar.MINUTE, 0);
        setCalendar.set(Calendar.SECOND, 0);
        setCalendar.set(Calendar.MILLISECOND, 0);

        setAlarmPeriod(context, setCalendar, type);
    }

    /**
     * 一天两次
     * @param context
     */
    public static void setAlarmTwiceADay(Context context) {
        setAlarmCancel(context);
        // 设置闹铃的时间
        Calendar setCalendar = Calendar.getInstance();
        setCalendar.setTimeInMillis(System.currentTimeMillis());
        setCalendar.set(Calendar.HOUR_OF_DAY, 10);
        setCalendar.set(Calendar.MINUTE, 0);
        setCalendar.set(Calendar.SECOND, 0);
        setCalendar.set(Calendar.MILLISECOND, 0);
        setAlarmPeriod(context, setCalendar, SUBSCRIPT_TWENTYHOUR);
    }

    /**
     * 自定义时间
     * @param context
     */
    public static void setConvertAlarm(Context context, String time) {
        Calendar setCalendar = Calendar.getInstance();
        if(time == null || "".equals(time)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                setCalendar.setTimeInMillis(setCalendar.getTimeInMillis()+getIntervalMillis(SUBSCRIPT_ONEDAY));
                setCalendar.set(Calendar.SECOND, 0);
                setCalendar.set(Calendar.MILLISECOND, 0);
            }else{
            }
        }else{
            String[] strs = time.split(":");
            setAlarmCancel(context);
            // 设置闹铃的时间
            setCalendar.setTimeInMillis(System.currentTimeMillis());
            setCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strs[0]));
            setCalendar.set(Calendar.MINUTE, Integer.parseInt(strs[1]));
            setCalendar.set(Calendar.SECOND, 0);
            setCalendar.set(Calendar.MILLISECOND, 0);
        }
        setAlarmPeriod(context, setCalendar, SUBSCRIPT_ONEDAY);
    }

    /**
     * 一天一次
     * @param context
     */
    public static void setAlarmOnceADay(Context context) {
        setAlarmCancel(context);
        // 设置闹铃的时间
        Calendar setCalendar = Calendar.getInstance();
        setCalendar.setTimeInMillis(System.currentTimeMillis());
        setCalendar.set(Calendar.HOUR_OF_DAY, 10);
        setCalendar.set(Calendar.MINUTE, 0);
        setCalendar.set(Calendar.SECOND, 0);
        setCalendar.set(Calendar.MILLISECOND, 0);
        setAlarmPeriod(context, setCalendar, SUBSCRIPT_ONEDAY);
    }

    /**
     * 三天一次
     * @param context
     */
    public static void setAlarmThreeADay(Context context) {
        setAlarmCancel(context);
        // 设置闹铃的时间
        Calendar setCalendar = Calendar.getInstance();
        setCalendar.setTimeInMillis(System.currentTimeMillis());
        setCalendar.set(Calendar.HOUR_OF_DAY, 10);
        setCalendar.set(Calendar.MINUTE, 0);
        setCalendar.set(Calendar.SECOND, 0);
        setCalendar.set(Calendar.MILLISECOND, 0);
        setAlarmPeriod(context, setCalendar, SUBSCRIPT_THREEDAY);
    }

    /**
     * 一周一次
     * @param context
     */
    public static void setAlarmWeekly(Context context) {
        setAlarmCancel(context);
        // 设置闹铃的时间
        Calendar setCalendar = Calendar.getInstance();
        setCalendar.setTimeInMillis(System.currentTimeMillis());
        setCalendar.set(Calendar.HOUR_OF_DAY, 10);
        setCalendar.set(Calendar.MINUTE, 0);
        setCalendar.set(Calendar.SECOND, 0);
        setCalendar.set(Calendar.MILLISECOND, 0);
        setAlarmPeriod(context, setCalendar, SUBSCRIPT_SEVENDAY);
    }

    /**
     * 一月一次
     * @param context
     */
    public static void setAlarmOnceAMonth(Context context) {
        setAlarmCancel(context);
        // 设置闹铃的时间
        Calendar setCalendar = Calendar.getInstance();
        setCalendar.setTimeInMillis(System.currentTimeMillis());
        setCalendar.set(Calendar.HOUR_OF_DAY, 10);
        setCalendar.set(Calendar.MINUTE, 0);
        setCalendar.set(Calendar.SECOND, 0);
        setCalendar.set(Calendar.MILLISECOND, 0);
        setAlarmPeriod(context, setCalendar, SUBSCRIPT_ONEMONTH);
    }

    /**
     * 关闭闹钟
     * @param context
     */
    public static void setAlarmCancel(Context context) {
        // 设置闹铃的时间
        Calendar setCalendar = Calendar.getInstance();
        setCalendar.setTimeInMillis(System.currentTimeMillis());
        setCalendar.set(Calendar.HOUR_OF_DAY, 10);
        setCalendar.set(Calendar.MINUTE, 0);
        setCalendar.set(Calendar.SECOND, 0);
        setCalendar.set(Calendar.MILLISECOND, 0);
        setAlarmPeriod(context, setCalendar, SUBSCRIPT);
    }

    private static void setAlarmPeriod(Context context, Calendar setCalendar, int subscript) {
//        SaveAlarmDateModel model = new SaveAlarmDateModel();
//        model.setMonth(setCalendar.get(Calendar.MONTH) + "");
//        model.setDay(setCalendar.get(Calendar.DATE) + "");
//        model.setType(subscript + "");
//        SaveAlarmDateService service = new SaveAlarmDateService(context);
//        service.saveOrUpdateAlarm(model);

        // 系统当前时间
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(System.currentTimeMillis());

        // 设置时间加上12小时的一个毫秒数
        long countLong = setCalendar.getTimeInMillis() + getIntervalMillis(subscript);
        // 当天最大的时间
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTimeInMillis(System.currentTimeMillis());
        todayCalendar.set(Calendar.HOUR_OF_DAY, 23);
        todayCalendar.set(Calendar.MINUTE, 59);
        todayCalendar.set(Calendar.SECOND, 59);

        // 建立Intent和PendingIntent，来调用目标组件
        Intent intent = new Intent();
        intent.setAction("com.himawari.requestpermission.mybroadcast");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // 获取闹钟管理的实例
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (subscript == SUBSCRIPT) {
            alarmManager.cancel(pendingIntent);
        } else {
            // 设置周期闹钟
            // 判断设置闹铃时间是否小于当前时间
            if (currentCalendar.getTimeInMillis() > setCalendar.getTimeInMillis()) {
                // 判断是否闹铃是一天响两次  subscript==0?true:false
                if (subscript == SUBSCRIPT_TWENTYHOUR) {
                    // 判断设置时间是前面12点还是后面12点
                    if (currentCalendar.getTimeInMillis() > countLong) {
                        setCalendar.set(Calendar.DAY_OF_YEAR, setCalendar.get(Calendar.DAY_OF_YEAR) + 1);
                        setAlarmManager(alarmManager,
                                setCalendar.getTimeInMillis(),
                                getIntervalMillis(subscript),
                                pendingIntent);

                    } else {
                        setCalendar.setTimeInMillis(countLong);
                        setAlarmManager(alarmManager,
                                setCalendar.getTimeInMillis(),
                                getIntervalMillis(subscript),
                                pendingIntent);
                    }
                } else {
                    if (subscript == SUBSCRIPT_ONEDAY) {
                        setCalendar.set(Calendar.DAY_OF_YEAR, setCalendar.get(Calendar.DAY_OF_YEAR) + 1);
                    } else if (subscript == SUBSCRIPT_THREEDAY) {
                        setCalendar.set(Calendar.DAY_OF_YEAR, setCalendar.get(Calendar.DAY_OF_YEAR) + 3);
                    } else if (subscript == SUBSCRIPT_SEVENDAY) {
                        setCalendar.set(Calendar.DAY_OF_YEAR, setCalendar.get(Calendar.DAY_OF_YEAR) + 7);
                    } else if (subscript == SUBSCRIPT_ONEMONTH) {
                        setCalendar.set(Calendar.DAY_OF_YEAR, setCalendar.get(Calendar.DAY_OF_YEAR) + getCurrentToNextMonth());
                    }
                    setAlarmManager(alarmManager,
                            setCalendar.getTimeInMillis(),
                            getIntervalMillis(subscript),
                            pendingIntent);
                }

            } else {
                setAlarmManager(alarmManager,
                        setCalendar.getTimeInMillis(),
                        getIntervalMillis(subscript),
                        pendingIntent);
            }
        }

    }

    private static void setAlarmManager(AlarmManager alarmManager,long calendarMillis,long subscriptMilli,PendingIntent pendingIntent){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendarMillis,pendingIntent);
        }else{
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendarMillis,
                    subscriptMilli,
                    pendingIntent);
        }
    }

    private static long getIntervalMillis(int subscript) {
        if (SUBSCRIPT_TWENTYHOUR == subscript) {
            // 12个小时的毫秒数
            return 12 * 60 * 60 * 1000;
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

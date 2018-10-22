package com.himawari.permissionUtils.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;

import com.himawari.permissionUtils.bean.AddScheduleBean;

import java.text.ParseException;
import java.util.TimeZone;

/**
 * Created by Kokosnuss on 2018/10/15
 */
public class AddScheduleUtils {

    public static final int AddCalendarAccountFailed = 10001, AddEventFailed = 10002, AddAlarmFailed = 10003, AddScheduleSuccess = 10004;


    private static AddScheduleUtils addScheduleUtils;

    private AddScheduleUtils() {
    }

    public static AddScheduleUtils getInstance() {
        if (addScheduleUtils == null) {
            synchronized (AddScheduleUtils.class) {
                if (addScheduleUtils == null)
                    addScheduleUtils = new AddScheduleUtils();
            }
        }
        return addScheduleUtils;
    }

    /**
     * 添加日程
     * @param context
     * @return
     */

    public int addCalendarEvent(Context context, AddScheduleBean bean) {
        // 获取日历账户的id
        int calId = checkCalendarAccount(context, bean.getCALENDARS_ACCOUNT_NAME());

        if (calId < 0) // 获取账户id失败，创建新账户
            if (addCalendarAccount(context, bean) < 0)//创建账户id失败，返回
                return AddCalendarAccountFailed;

        ContentValues event = new ContentValues();
        event.put("title", bean.getScheduleTitle());
        event.put("description", bean.getScheduleDetail());
        // 插入账户的id
        event.put("calendar_id", calId);
        long start = 0;
        try {
            start = TimeUtils.getFormatTime(TimeUtils.FormatExpression.YMDHMS).parse(bean.getScheduleTime()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        event.put(CalendarContract.Events.DTSTART , start);//Events.DTSTART  EXTRA_EVENT_BEGIN_TIME
        event.put(CalendarContract.Events.DTEND , start + 60*1000*60*2);
        event.put(CalendarContract.Events.HAS_ALARM, 1);//设置有闹钟提醒
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());  //这个是时区，必须有



        @SuppressLint("MissingPermission") Uri newEvent = context.getContentResolver().insert(CalendarContract.Events.CONTENT_URI, event);

        if (newEvent == null) {
            // 添加日历事件失败直接返回
            return AddEventFailed;
        }
        //事件提醒的设定
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
        // 提前10分钟有提醒
        values.put(CalendarContract.Reminders.MINUTES, 5);
        values.put(CalendarContract.Reminders.METHOD, 1);


        @SuppressLint("MissingPermission") Uri uri = context.getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, values);
        if(uri == null) {
            // 添加闹钟提醒失败直接返回
            return AddAlarmFailed;
        }
        return AddScheduleSuccess;
    }


    /**
     * 确认日历账户是否已存在
     * @return -1不存在 存在则返回accountId
     */
    private int checkCalendarAccount(Context context,String CALENDARS_ACCOUNT_NAME){
        @SuppressLint("MissingPermission") Cursor userCursor = context.
                getContentResolver().query(CalendarContract.Calendars.CONTENT_URI,
                null, null, null, null);
        try{
            if (userCursor == null)return -1;
            int count = userCursor.getCount();

            if (count <= 0) return -1;

            for (userCursor.moveToFirst();userCursor.getPosition() < count;userCursor.moveToNext()){
                String accountType = userCursor.getString(userCursor.getColumnIndex(CalendarContract.Calendars.ACCOUNT_TYPE));
                LogUtils.i(LogUtils.originalIndex,accountType);//calendarid在每个cursor的第八个

            }
            for (userCursor.moveToFirst();userCursor.getPosition() < count;userCursor.moveToNext()){
                String accountName = userCursor.getString(userCursor.getColumnIndex(CalendarContract.Calendars.ACCOUNT_NAME));
                LogUtils.i(LogUtils.originalIndex,accountName);//calendarid在每个cursor的第八个
                if (CALENDARS_ACCOUNT_NAME.equals(accountName)) {//存在现有账户，取第一个账户的id返回
                    return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
                }
            }
        }finally {
            userCursor.close();
        }

        return -1;
    }

    /**
     * 新增日历账户
     * @param context
     * @return
     */
    private long addCalendarAccount(Context context,AddScheduleBean bean) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME, bean.getCALENDAR_NAME());

        value.put(CalendarContract.Calendars.ACCOUNT_NAME, bean.getCALENDARS_ACCOUNT_NAME());
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, bean.getCALENDARS_ACCOUNT_TYPE());
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, bean.getCALENDARS_DISPLAY_NAME());
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, bean.getCALENDARS_ACCOUNT_NAME());
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);




        Uri calendarUri = CalendarContract.Calendars.CONTENT_URI.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, bean.getCALENDARS_ACCOUNT_NAME())
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, bean.getCALENDARS_ACCOUNT_TYPE())
                .build();
        Uri result = context.getContentResolver().insert(calendarUri, value);
        long id = result == null ? -1 : ContentUris.parseId(result);
        return id;


    }

}

package com.himawari.permissionUtils.activity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.AddScheduleBean;
import com.himawari.permissionUtils.utils.AddScheduleUtils;
import com.himawari.permissionUtils.utils.LogUtils;
import com.himawari.permissionUtils.utils.PermissionRequestUtils;
import com.himawari.permissionUtils.utils.TimeUtils;


import java.text.ParseException;
import java.util.TimeZone;

import butterknife.BindView;

/**
 * Created by Kokosnuss on 2018/10/11
 */
public class AddScheduleActivity extends BaseActivity {

    @BindView(R.id.editText5)
    EditText scheduleEdit;
    @BindView(R.id.editText6)
    EditText remindEdit;

    private final String TOAST_REMINDSTRFORMATERROR = "日期格式错误";


    private static final String CALENDAR_NAME = "AddScheduleCalendar";
    private static final String CALENDARS_ACCOUNT_NAME = "AddScheduleAccount";
    private static final String CALENDARS_ACCOUNT_TYPE = "com.android.table";
    private static final String CALENDARS_DISPLAY_NAME = "添加日程";




    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_addschedule);
        super.onCreate(savedInstanceState);




    }

    public void onAddScheduleClick(View view){
        if(PermissionRequestUtils.requestPermission(AddScheduleActivity.this,
                PermissionRequestUtils.calander_RequestCode,"需要访问日历权限"))
            addSchedule();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(PermissionRequestUtils.IsAllGranted(permissions,grantResults))
            addSchedule();
    }

    private void addSchedule(){
        String schduleTitle = scheduleEdit.getText().toString();
        String remindTime = remindEdit.getText().toString();

        if(!TimeUtils.isFormatMatch(remindTime,TimeUtils.FormatExpression.YMDHMS.details())){
            showToast(TOAST_REMINDSTRFORMATERROR);
            return;
        }
        AddScheduleBean bean = new AddScheduleBean();
        bean.setScheduleDetail("detail");
        bean.setScheduleTime(remindTime);
        bean.setScheduleTitle(schduleTitle);
        bean.setCALENDAR_NAME(CALENDAR_NAME);
        bean.setCALENDARS_ACCOUNT_NAME(CALENDARS_ACCOUNT_NAME);
        bean.setCALENDARS_ACCOUNT_TYPE(CALENDARS_ACCOUNT_TYPE);
        bean.setCALENDARS_DISPLAY_NAME(CALENDARS_DISPLAY_NAME);
        try{
            switch (AddScheduleUtils.getInstance().addCalendarEvent(AddScheduleActivity.this,bean)){
                case AddScheduleUtils.AddCalendarAccountFailed:
                    showToast("AddCalendarAccountFailed");
                    break;
                case AddScheduleUtils.AddAlarmFailed:
                    showToast("AddAlarmFailed");
                    break;
                case AddScheduleUtils.AddEventFailed:
                    showToast("AddEventFailed");
                    break;
                case AddScheduleUtils.AddScheduleSuccess:
                    showToast("AddScheduleSuccess");
                    break;
            }

        }catch (Exception e){
            showToast("AddScheduleFailed:"+e.getMessage());
        }


    }


    private void showToast(String toastStr){
        Toast.makeText(AddScheduleActivity.this,toastStr,Toast.LENGTH_SHORT).show();
    }





}

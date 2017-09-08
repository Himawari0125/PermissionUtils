package com.himawari.a24hoursrecord.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.utils.AlarmSetting;
import com.himawari.a24hoursrecord.utils.AlarmUtils;

/**
 * Created by S.Lee on 2017/9/7 0007.
 */

public class AlarmActivity extends Activity implements View.OnClickListener{
    private Button setalarm_btn;
    private EditText input_edt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        setalarm_btn = findViewById(R.id.setAlarm);
        input_edt = findViewById(R.id.inputtime);
        setalarm_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setAlarm:
                 AlarmUtils.SetAlarm(this,input_edt.getText().toString(),"com.himawari.requestpermission.mybroadcast",AlarmUtils.SUBSCRIPT_TWENTYHOUR);
                //AlarmSetting.setConvertAlarm(this,input_edt.getText().toString());
                break;
        }
    }
}

package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.AlarmUtils;

/**
 * Created by S.Lee on 2017/9/7 0007.
 */

public class AlarmActivity extends BaseActivity implements View.OnClickListener{
    private Button setalarm_btn;
    private EditText input_edt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.activity_alarm);
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void initView() {
        setalarm_btn = (Button)findViewById(R.id.setAlarm);
        input_edt = (EditText)findViewById(R.id.inputtime);
        setalarm_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setAlarm:
                 AlarmUtils.SetAlarm(this,input_edt.getText().toString(),"com.himawari.requestpermission.mybroadcast", AlarmUtils.SUBSCRIPT_TWENTYHOUR);
                //AlarmSetting.setConvertAlarm(this,input_edt.getText().toString());
                break;
        }
    }
}

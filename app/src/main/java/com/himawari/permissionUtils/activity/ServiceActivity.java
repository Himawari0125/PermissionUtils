package com.himawari.permissionUtils.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.himawari.permissionUtils.BaseActvity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.services.BaseService;

/**
 * Created by S.Lee on 2017/9/29.
 */

public class ServiceActivity extends BaseActvity implements View.OnClickListener{
    private Button startService_btn,stopService_btn,setalarm_btn;
    private Intent it;
    private EditText input_edt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_service);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        startService_btn = (Button)findViewById(R.id.button2);
        startService_btn.setOnClickListener(this);
        it = new Intent(this, BaseService.class);
        stopService_btn = (Button)findViewById(R.id.button3);
        stopService_btn.setOnClickListener(this);
        input_edt = (EditText)findViewById(R.id.editText);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
//                String settingStr = input_edt.getText().toString();
//                if(settingStr == null || "".equals(settingStr)) break;
//                AlarmUtils.SetAlarm(this,settingStr,"com.himawari.requestpermission.mybroadcast",AlarmUtils.SUBSCRIPT_TWENTYHOUR);
                startService(it);
                break;
            case R.id.button3:
                stopService(it);
                break;
        }
    }
}

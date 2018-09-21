package com.himawari.permissionUtils.activity;


import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.services.BaseService;
import com.himawari.permissionUtils.services.ProtectorService;
import com.himawari.permissionUtils.utils.MyActivityManager;
import com.himawari.permissionUtils.utils.ServiceUtils;

/**
 * Created by S.Lee on 2017/9/29.
 */

public class ServiceActivity extends BaseActivity implements View.OnClickListener{
    private Button startService_btn,stopService_btn,setalarm_btn,
            printSer_btn,killback_btn,exit_btn,finishac_btn;

    private EditText input_edt;
    private ServiceUtils serviceUtils;
    private Intent it;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_service);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        startService_btn = findViewById(R.id.button2);
        startService_btn.setOnClickListener(this);

        stopService_btn = findViewById(R.id.button3);
        stopService_btn.setOnClickListener(this);
        input_edt = findViewById(R.id.editText);
        printSer_btn = findViewById(R.id.button37);
        killback_btn = findViewById(R.id.button38);
        exit_btn = findViewById(R.id.button39);
        finishac_btn = findViewById(R.id.button47);
        printSer_btn.setOnClickListener(this);
        killback_btn.setOnClickListener(this);
        exit_btn.setOnClickListener(this);
        finishac_btn.setOnClickListener(this);

        serviceUtils = new ServiceUtils();





        it = new Intent(this, ProtectorService.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
//                String settingStr = input_edt.getText().toString();
//                if(settingStr == null || "".equals(settingStr)) break;
//                AlarmUtils.SetAlarm(this,settingStr,"com.himawari.requestpermission.mybroadcast",AlarmUtils.SUBSCRIPT_TWENTYHOUR);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(it);
                }else{
                    startService(it);
                }
                break;
            case R.id.button3:
                stopService(it);
                break;
            case R.id.button37:
                serviceUtils.printAllService(ServiceActivity.this);
                break;

            case R.id.button38:
                ActivityManager activityMgr= (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
                activityMgr.killBackgroundProcesses(this.getPackageName());//service依然存在


                break;
            case R.id.button39:
                System.exit(0);//service消失
                break;

            case R.id.button47:
                MyActivityManager.getManager().finishAllActivity();//service 依然存在
                break;

        }
    }
}

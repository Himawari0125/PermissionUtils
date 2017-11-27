package com.himawari.permissionUtils.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.himawari.permissionUtils.resolution.ResolutionActivity;
import com.himawari.permissionUtils.services.BaseService;
import com.himawari.permissionUtils.utils.AlarmUtils;

/**
 * Created by S.Lee on 2017/9/7 0007.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent aIntent = new Intent();
        aIntent.setClassName("com.himawari.permissionUtils.resolution", "ResolutionActivity");
        if (context.getPackageManager().resolveActivity(aIntent, 0) == null) {//判断AlarmPeriodActivity是否不存在
            Intent alarmIntent = new Intent(context, ResolutionActivity.class);
            alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(alarmIntent);
            context.startService(new Intent(context, BaseService.class));
            AlarmUtils.SetAlarm(context,"",intent.getAction(), AlarmUtils.SUBSCRIPT_TWENTYHOUR);

            //AlarmSetting.setConvertAlarm(context,"",0);
        }
    }
}

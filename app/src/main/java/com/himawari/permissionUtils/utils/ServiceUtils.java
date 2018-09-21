package com.himawari.permissionUtils.utils;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * Created by S.Lee on 2018/2/9.
 */

public class ServiceUtils {

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * @param context
     * @return true 表示开启
     */
    public static final boolean isOPen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }


    /**
     * 强制帮用户打开GPS
     * @param context
     */
    public static final void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断某个后台Service是否运行
     * @param context
     * @param serviceName
     * @return
     */
    public boolean isServiceRunning(Context context,String serviceName){
        if(TextUtils.isEmpty(serviceName))
            return false;
        List<ActivityManager.RunningServiceInfo> runningService = getAllService(context);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString()
                    .equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 打印所有运行Service
     * @param context
     */
    public void printAllService(Context context){
        List<ActivityManager.RunningServiceInfo> runningService = getAllService(context);
        for (int i = 0; i < runningService.size(); i++)
            LogUtils.i(LogUtils.originalIndex,"running service:"+runningService.get(i).service.getClassName().toString());
    }

    private List<ActivityManager.RunningServiceInfo> getAllService(Context context){
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningService
                = activityManager.getRunningServices(30);
        return runningService;

    }


}

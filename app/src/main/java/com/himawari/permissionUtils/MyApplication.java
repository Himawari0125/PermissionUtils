package com.himawari.permissionUtils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.himawari.permissionUtils.utils.BleStateListenerUtils;
import com.himawari.permissionUtils.utils.DensityUtils;
import com.himawari.permissionUtils.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by S.Lee on 2017/9/5 0005.
 */

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks  {
    private static Set<Activity> activities = new HashSet<>();
    public static List<Activity> activityList = new ArrayList<>();
    public static float width;
    public static float height;
    public static float width_dp;
    public static float height_dp;
    private PackageInfo packageInfo;
    public static String[] requestPermission;
    private static Activity currentActivity;
    @Override
    public void onCreate() {
        super.onCreate();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        width_dp = DensityUtils.dip2px(getApplicationContext(),width_dp);
        height_dp = DensityUtils.dip2px(getApplicationContext(),height_dp);
        Log.i("Display","width = "+ DensityUtils.px2dip(getApplicationContext(),width)+" height = "+ DensityUtils.px2dip(getApplicationContext(),height));

        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
            requestPermission = packageInfo.requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        BleStateListenerUtils.registerBleStateListener(this);
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        BleStateListenerUtils.unregisterBleStateListener(this);
        super.onTerminate();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activities.add(activity);
        activityList.add(activity);
        currentActivity = activity;
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;

    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtils.i(3,activity.getComponentName().toShortString());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtils.i(3,activity.getComponentName().toShortString());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.i(3,activity.getComponentName().toShortString());
        if (activities.contains(activity)) {
            activities.remove(activity);
            activityList.remove(activity);
        }

    }

    public static void finishAllActivity() {
        for (Activity activity : activities) {
            LogUtils.i(3,activity.getComponentName()+"");
            activity.finish();
        }
        activityList.clear();
        activities.removeAll(activities);

    }

    public static void finishTillActivity(String componentName){
        int length = activities.size();
        int targetIdx = -1;
        for(int i = length-1;i >= 0;i--){
            String localName = activityList.get(i).getLocalClassName();
            String simpleName = localName.substring(localName.lastIndexOf(".")+1);
            if(componentName.equals(simpleName)){
                targetIdx = i;
                break;
            };
        }
        if(targetIdx<0)return;
        for (int i = length - 1; i > targetIdx; i--){
            Activity activity = activityList.get(i);

            activities.remove(activityList.get(i));
            activityList.remove(i);

            activity.finish();
        }

    }

    public static void finishOneActivity(Activity activity) {
        if (activities.contains(activity)) {
            activityList.remove(activity);
            activities.remove(activity);
        }
        activity.finish();
    }

    public static void finishTopActivity() {
        if (activityList.size() > 0 && activities.size() > 0) {
            Activity activity = activityList.get(activityList.size() - 1);
            activity.finish();
            if (activities.contains(activity)) activities.remove(activity);
            activityList.remove(activity);
        }
    }

}

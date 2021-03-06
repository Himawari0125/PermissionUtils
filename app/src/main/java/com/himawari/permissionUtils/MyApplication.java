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
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import com.himawari.permissionUtils.handler.CrashHandler;
import com.himawari.permissionUtils.utils.BleStateListenerUtils;
import com.himawari.permissionUtils.utils.DensityUtils;
import com.himawari.permissionUtils.utils.LogUtils;
import com.himawari.permissionUtils.utils.MyActivityManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by S.Lee on 2017/9/5 0005.
 */

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks  {

    public static float width;
    public static float height;
    public static float width_dp;
    public static float height_dp;
    private PackageInfo packageInfo;
    public static int statusBarHeight = 0;
    public static int navigationBarHeight = 0;
    public static String[] requestPermission;
    private static Activity currentActivity;

    private MyActivityManager manager;
    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        getBarsHeight();
        manager = MyActivityManager.getManager();
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
        manager.addActivity(activity);

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
        manager.removeActivity(activity);
    }


    /**
     * 获取stateBar navigationBar高度
     */
    private void getBarsHeight() {
        //获取status_bar_height，navigation_bar_height资源的ID
        int statusBarId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (statusBarId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(statusBarId);
        }

        boolean hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        LogUtils.i(LogUtils.originalIndex,hasBackKey + "" + hasMenuKey);
        if (!hasBackKey && !hasMenuKey) {
            int navigationBarId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            if (navigationBarId > 0) {
                navigationBarHeight = getResources().getDimensionPixelSize(navigationBarId);
            }
        }

        LogUtils.i(LogUtils.originalIndex, "statusBarHeight:" + statusBarHeight + " navigationBarHeight:" + navigationBarHeight);
        LogUtils.i( LogUtils.originalIndex,"width = " + DensityUtils.px2dip(getApplicationContext(), width) + " height = " + DensityUtils.px2dip(getApplicationContext(), height));
    }
}

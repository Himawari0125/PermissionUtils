package com.himawari.permissionUtils;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.himawari.permissionUtils.utils.DensityUtils;

/**
 * Created by S.Lee on 2017/9/5 0005.
 */

public class MyApplication extends Application {
    public static float width;
    public static float height;
    public static float width_dp;
    public static float height_dp;
    private PackageInfo packageInfo;
    public static String[] requestPermission;

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

    }
}

package com.himawari.a24hoursrecord.utils;

import android.app.Activity;
import android.content.Context;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by S.Lee on 2017/9/14 0014.
 */

public class ScreenControlUtils {
    private static PowerManager.WakeLock mWakelock;
    private static String TAG = "ScreenUtils_";

    public static void acquireCpuWakeLock(Context context) {
        if (mWakelock != null) {
            return;
        }
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        mWakelock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, TAG);
        mWakelock.acquire();
    }
    // 唤醒屏幕
    public static void wakeScreenUp(Activity context) {
        final Window win = context.getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }


    public static void releaseCpuLock() {
        if (mWakelock != null) {
            mWakelock.release();
            mWakelock = null;
        }
    }
}

package com.himawari.a24hoursrecord;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by S.Lee on 2017/9/5 0005.
 */

public class MyApplication extends Application {
    public static float width;
    public static float height;

    @Override
    public void onCreate() {
        super.onCreate();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }
}

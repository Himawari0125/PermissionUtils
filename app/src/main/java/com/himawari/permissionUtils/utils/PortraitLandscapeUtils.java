package com.himawari.permissionUtils.utils;

import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by 25646 on 2017/9/17.
 */

public class PortraitLandscapeUtils {

    public static void requestFullScreen(FragmentActivity mContext){
        // 将activity设置为全屏显示
        mContext.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}

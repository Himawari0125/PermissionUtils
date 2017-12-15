package com.himawari.permissionUtils.utils;

import android.util.Log;

/**
 * Created by S.Lee on 2017-12-04.
 */
public class LogUtils {
    private static final boolean mdebug = true;
    public static void e(String Tag,String detail){
        if(mdebug){
            Log.e(Tag,detail);
        }
    }

    public static void i(String Tag,String detail){
        if(mdebug){
            Log.i(Tag,detail);
        }
    }

    public static void d(String Tag,String detail){
        if(mdebug){
            Log.d(Tag,detail);
        }
    }
}

package com.himawari.permissionUtils.utils;

import android.util.Log;

/**
 * Created by DELL on 2016-07-21.
 */
public class LogUtils {
    private static final boolean mdebug = true;
    private static int originalIndex = 3;

    public static void e(String detail){
        if(mdebug){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[originalIndex];
            Log.e(stackTraceElement.getFileName()+"."+stackTraceElement.getMethodName()+" line:"+stackTraceElement.getLineNumber(),detail);
        }
    }
    public static void i(String detail){
        if(mdebug){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[originalIndex];
            Log.i(stackTraceElement.getFileName()+"."+stackTraceElement.getMethodName()+" line:"+stackTraceElement.getLineNumber(),detail);
        }
    }

    public static void d(String detail){
        if(mdebug){
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[originalIndex];
            Log.d(stackTraceElement.getFileName()+"."+stackTraceElement.getMethodName()+" line:"+stackTraceElement.getLineNumber(),detail);
        }
    }
}

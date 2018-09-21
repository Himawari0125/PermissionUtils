package com.himawari.permissionUtils.utils;

import android.util.Log;

/**
 * Created by DELL on 2016-07-21.
 */
public class LogUtils {
    private static final boolean mdebug = true;
    public static int originalIndex = 4;
    public static int superIndex = 5;

    public static void e(int traceIndex,Object detail){
        if(mdebug){
            StackTraceElement stackTraceElement = getStackTrace(traceIndex);
            if(stackTraceElement !=null)
                Log.e(stackTraceElement.getFileName()+"."+stackTraceElement.getMethodName()+" line:"+stackTraceElement.getLineNumber(),""+detail);
            else
                Log.e("no stack trace",""+detail);
        }
    }
    public static void i(int traceIndex,Object detail){
        if(mdebug){
            StackTraceElement stackTraceElement = getStackTrace(traceIndex);
            if(stackTraceElement !=null)
                Log.i(stackTraceElement.getFileName()+"."+stackTraceElement.getMethodName()+" line:"+stackTraceElement.getLineNumber(),""+detail);
            else
                Log.i("no stack trace",""+detail);
        }
    }

    public static void d(int traceIndex,Object detail){
        if(mdebug){
            StackTraceElement stackTraceElement = getStackTrace(traceIndex);
            if(stackTraceElement !=null)
                Log.d(stackTraceElement.getFileName()+"."+stackTraceElement.getMethodName()+" line:"+stackTraceElement.getLineNumber(),""+detail);
            else
                Log.d("no stack trace",""+detail);
        }
    }

    private static StackTraceElement getStackTrace(int index){
        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
        if(stackTraceElement!=null && stackTraceElement.length > index)
            return stackTraceElement[index];
        else
            return null;
    }


    public static void iAlltrace(String detail){
        if(mdebug){
            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
            for (StackTraceElement element:stackTraceElement)
                Log.i(element.getFileName()+"."+element.getMethodName()+" line:"+element.getLineNumber(),detail);
        }
    }
}

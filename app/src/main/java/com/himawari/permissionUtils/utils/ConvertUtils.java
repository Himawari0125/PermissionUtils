package com.himawari.permissionUtils.utils;

import android.text.TextUtils;

import com.himawari.permissionUtils.mineException.InputException;

import java.util.regex.Pattern;

/**
 * Created by S.Lee on 2017/11/14.
 */

public class ConvertUtils {
    public static int StringParseInteger(String str,int RequestCode)throws InputException {
        Pattern pattern = Pattern.compile("[0-9]*");
        if(TextUtils.isEmpty(str))throw new InputException(" must not input null",RequestCode);
        if(!pattern.matcher(str).matches())throw new InputException(" must input Numbers",RequestCode);
        return Integer.parseInt(str);
    }

    public static float StringParseFloat(String str,int RequestCode)throws InputException {
        Pattern pattern = Pattern.compile("([0-9]*)\\.([0-9]*)");
        if(TextUtils.isEmpty(str))throw new InputException(" must not input null",RequestCode);
        if(!pattern.matcher(str).matches())throw new InputException(" must input Numbers",RequestCode);
        return Float.parseFloat(str);
    }


    public static short StringParseShort(String str,int RequestCode)throws InputException {
        Pattern pattern = Pattern.compile("[0-9]*");
        if(TextUtils.isEmpty(str))throw new InputException(" must not input null",RequestCode);
        if(!pattern.matcher(str).matches())throw new InputException(" must input Numbers",RequestCode);
        return Short.parseShort(str);
    }
    public static String StringParseString(String str,int RequestCode)throws InputException {
        if(TextUtils.isEmpty(str))throw new InputException("You must not input null",RequestCode);
        return str;
    }
}

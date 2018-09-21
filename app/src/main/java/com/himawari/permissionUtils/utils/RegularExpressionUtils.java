package com.himawari.permissionUtils.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by Kokosnuss on 2018/9/18
 */
public class RegularExpressionUtils {
    public static final String FILTER_ISINTEGER = "(\\-|\\+)[0-9]*";//正负整数
    public static final String FILTER_ISDECIMAL = "(\\-|\\+)[0-9]*\\.[0-9]*";//正负小数

    public static boolean isAccord(String regularStr,String checkStr){
        Pattern pattern = Pattern.compile(regularStr);
        if(TextUtils.isEmpty(checkStr))return false;
        if(!pattern.matcher(checkStr).matches())return false;
        return true;
    }
}

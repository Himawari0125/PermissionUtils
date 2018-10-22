package com.himawari.permissionUtils.utils;

/**
 * Created by Kokosnuss on 2018/9/29
 */
public class TimeDifUtils {

    /**
     * 单位毫秒 (1000毫秒 = 1秒)
     * @param limitMills
     * @param startMills
     * @param endMills
     * @return
     */
    public static boolean isMorethan(long limitMills,long startMills,long endMills){
            return endMills - startMills <limitMills ? false:true;
    }
}

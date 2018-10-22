package com.himawari.permissionUtils.utils;



import com.himawari.permissionUtils.commons.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by S.Lee on 2018/1/12.
 */

public class FileNameUtils {

    public static String getPicName(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentDate = new Date();
        return Constant.USERID+simpleDateFormat.format(currentDate);
    }
}

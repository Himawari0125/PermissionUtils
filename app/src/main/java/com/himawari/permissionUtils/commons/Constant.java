package com.himawari.permissionUtils.commons;

import android.os.Environment;

public class Constant {
    public static final String appFolderPath = Environment.getExternalStorageDirectory()+"/permissionUtils";//应用相关数据存储总文件夹
    public static final String crashPath = "/crash";//奔溃日志本地文件夹
}

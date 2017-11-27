package com.himawari.permissionUtils.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.himawari.permissionUtils.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by S.Lee on 2017/8/26.
 */

public class PermissionRequestUtils {
    /**
     * use eg:       if(!PermissionRequestUtils.RequestPermission(this,PermissionRequestUtils.location_RequestCode,"为了确保APP正常工作,请允许云悦健康使用您的位置信息"))
                      Log.i("Permission_","权限不需要授予");
     */
    public static final int contact_RequestCode = 1001;
    public static final int phone_RequestCode = 1002;
    public static final int camera_RequestCode = 1003;
    public static final int sensor_RequestCode = 1004;
    public static final int calander_RequestCode = 1005;
    public static final int location_RequestCode = 1006;
    public static final int storage_RequestCode = 1007;
    public static final int microphone_RequestCode = 1008;
    public static final int sms_RequestCode = 1009;

    //只要其中一个申请了就全部打开了
    /**
     * 联系人权限
     */
    private String[] contact_Permission = {//Manifest.permission_group.CONTACTS,
    Manifest.permission.WRITE_CONTACTS,
    Manifest.permission.GET_ACCOUNTS,
    Manifest.permission.READ_CONTACTS
    };

    private String[] phone_Perission = {//Manifest.permission_group.PHONE,
            Manifest.permission.READ_CALL_LOG,
    Manifest.permission.READ_PHONE_STATE,
    Manifest.permission.CALL_PHONE,
    Manifest.permission.WRITE_CALL_LOG,
    Manifest.permission.USE_SIP,
    Manifest.permission.PROCESS_OUTGOING_CALLS,
    Manifest.permission.ADD_VOICEMAIL,//com.android.voicemail.permission.ADD_VOICEMAIL
    };

    private String[] camera_Permission = {//Manifest.permission_group.CAMERA,
            Manifest.permission.CAMERA};

    private String[] sensor_Permission = {//Manifest.permission_group.SENSORS,
    Manifest.permission.BODY_SENSORS};

    private String[] calander_Permission = {//Manifest.permission_group.CALENDAR,
    Manifest.permission.READ_CALENDAR,
    Manifest.permission.WRITE_CALENDAR};


    private String[] location_Permission = {   //Manifest.permission_group.LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    private String[] storage_Permission = {//Manifest.permission_group.STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private String[] microphone_Permission = {// Manifest.permission_group.MICROPHONE,
            Manifest.permission.RECORD_AUDIO};



    private String[] sms_Permission = {//Manifest.permission_group.SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.SEND_SMS
            //Manifest.permission.READ_CELL_BROADCASTS
    };


    public static boolean RequestPermission(final Activity mActivity, final int RequestCode, String requestRationaleStr){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false;
        final String[] Requests = new PermissionRequestUtils().getPermissions(RequestCode);
        for(String request:Requests){
            //判断是否有权限
            if (ContextCompat.checkSelfPermission(mActivity,
                    request) != PackageManager.PERMISSION_GRANTED) {
                Log.i("Permission_",request+" 没有权限");

                //判断是否需要 向用户解释，为什么要申请该权限
                if(ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                        request)) {
                    new AlertDialog.Builder(mActivity)
                            .setMessage(requestRationaleStr)
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //请求权限
                                    ActivityCompat.requestPermissions(mActivity, Requests, RequestCode);
                                }
                            }).create().show();
                    return true;
                }
                //请求权限
                ActivityCompat.requestPermissions(mActivity, Requests, RequestCode);
                return true;//申请一个权限则整组权限可使用。
            }
        }
        return false;

    }

    public static boolean RequestPermission(final Fragment mFragment, Context mContext, final int RequestCode, String requestRationaleStr){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false;
        String[] Requests = new PermissionRequestUtils().getPermissions(RequestCode);
        for(String request:Requests){
            //判断是否有权限
            if (mContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.i("Permission_",request+" 没有权限");
                //判断是否需要 向用户解释，为什么要申请该权限
                if(mFragment.shouldShowRequestPermissionRationale(request)) {
                    new AlertDialog.Builder(mContext)
                            .setMessage(requestRationaleStr)
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //请求权限
                                    mFragment.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, RequestCode);
                                }
                            }).create().show();
                    return true;

                }
                //请求权限
                mFragment.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, RequestCode);
                return true;//申请一个权限则整组权限可使用。
            }
        }
        return false;
    }

    private String[] getAllRequestPermission(){

        return null;
    }
    private String[] getPermissions(int requestCode){
        switch (requestCode){
            case contact_RequestCode:
                return getLastPermission(calander_Permission);
            case phone_RequestCode :
                return getLastPermission(phone_Perission);
            case camera_RequestCode :
                return getLastPermission(camera_Permission);
            case sensor_RequestCode :
                return getLastPermission(sensor_Permission);
            case calander_RequestCode :
                return getLastPermission(calander_Permission);
            case location_RequestCode :
                return getLastPermission(location_Permission);
            case storage_RequestCode :
                return getLastPermission(storage_Permission);
            case microphone_RequestCode :
                return getLastPermission(microphone_Permission);
            case sms_RequestCode :
                return getLastPermission(sms_Permission);
        }
        return null;
    }

    public String[] getLastPermission(String[] orginalPermission){
        List<String>  lastPermission = new ArrayList<>();
        if(MyApplication.requestPermission==null)return null;
        for(String permission: MyApplication.requestPermission){
            for(int i = 0 ; i < orginalPermission.length ; i++){
                if(orginalPermission[i].equals(permission))
                    lastPermission.add(permission);
            }
        }
        String[] lastStrs = lastPermission.toArray(new String[lastPermission.size()]);
        return lastStrs;

    }

    public static boolean IsAllGranted(@NonNull String[] permissions, @NonNull int[] grantResults){
        boolean isAllGranted = true;
        // 判断是否所有的权限都已经授予了
        int i = 0;
        for (int grant : grantResults) {
            if (grant != PackageManager.PERMISSION_GRANTED) {
                Log.i("Permission_",permissions[i]);
                isAllGranted = false;
                break;
            }
            i++;
        }
        return isAllGranted;
    }



}

package com.himawari.permissionUtils.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by S.Lee on 2017/8/31 0031.
 */

public class BlutoothVersionUtils {
    private static final String TAG = "BlutoothVersionUtils_";
    public static final int STOPSCAN = 0;
    public static final int STARTSCAN = 1;

    public static final int TURNONBLUETOOTH = 1001;

    public static final int SCAN_TIME = 10000;

    /**
     * stopLeScan(BluetoothAdapter.LeScanCallback callback)
     This method was deprecated in API level 21. Use stopScan(ScanCallback) instead.
     */
    public static void stopOrStartScanningBle(final BluetoothAdapter mBluetoothAdapter,
                                              final LeScanCallback callback,
                                              final ScanCallback scanCallback,
                                              int stopOrstartScan){
        switch(stopOrstartScan){
            case STARTSCAN:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Log.i(TAG,"调用的是android5.0以上的启动方法");
                    final BluetoothLeScanner scanner = mBluetoothAdapter.getBluetoothLeScanner();
                    scanner.startScan(scanCallback);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(SCAN_TIME);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    scanner.stopScan(scanCallback);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }else {
                    Log.i(TAG,"调用的是android5.0以下的启动方法");
                    mBluetoothAdapter.startLeScan(callback);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(SCAN_TIME);
                                mBluetoothAdapter.stopLeScan(callback);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                }
                break;
            case STOPSCAN:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Log.i(TAG,"调用的是android5.0以上的停止方法");
                    mBluetoothAdapter.getBluetoothLeScanner().stopScan(scanCallback);
                }else {
                    Log.i(TAG,"调用的是android5.0以下的停止方法");
                    mBluetoothAdapter.stopLeScan(callback);
                }
                break;
        }
    }

    /**
     * 蓝牙是否开启
     * @return
     */
    public static boolean IsTurnonBluetooth(FragmentActivity mfragmentActivity,BluetoothAdapter mbluetoothAdapter){
        if(!mbluetoothAdapter.isEnabled()){
            Intent intent = new Intent();
            intent.setAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            mfragmentActivity.startActivityForResult(intent,TURNONBLUETOOTH);
            return false;
        }else{
            return true;
        }
    }


}

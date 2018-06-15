package com.himawari.permissionUtils.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by S.Lee on 2018/1/17.
 */

public class BleStateListenerUtils {

    public static void registerBleStateListener(Context context){
        context.registerReceiver(mReceiver, makeFilter());
    }

    public static void unregisterBleStateListener(Context context){
        context.unregisterReceiver(mReceiver);
    }

    private static IntentFilter makeFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        return filter;
    }

    private static BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch(intent.getAction()) {
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    switch (blueState) {
                        case BluetoothAdapter.STATE_TURNING_ON:
                            LogUtils.e( "onReceive---------STATE_TURNING_ON");
                            break;
                        case BluetoothAdapter.STATE_ON:
                            LogUtils.e( "onReceive---------STATE_ON");
                            break;
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            LogUtils.e("onReceive---------STATE_TURNING_OFF");

                            break;
                        case BluetoothAdapter.STATE_OFF:
                            LogUtils.e( "onReceive---------STATE_OFF");

                            break;
                    }
                    break;
            }
        }
    };
}

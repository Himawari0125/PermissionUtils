package com.himawari.a24hoursrecord.activity;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.adapter.BTDeviceAdapter;
import com.himawari.a24hoursrecord.resolution.ResolutionActivity;
import com.himawari.a24hoursrecord.utils.BlutoothVersionUtils;
import com.himawari.a24hoursrecord.utils.CameraAlbumUtils;
import com.himawari.a24hoursrecord.utils.PermissionRequestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity_5.0以上";
    private static final String TAG_2 = "MainActivity_5.0以下";



    private Button camera_btn,scanbluetooth_btn,resolution_btn,sendBroadcast_btn;
    private ListView list;
    private ImageView imageView;
    private BTDeviceAdapter adapter;

    private BluetoothAdapter mbluetoothAdapter;
    private LeScanCallback callback;
    private ScanCallback scanCallback;
    private Map<String,String> bluetoothData;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.setDeviceData(bluetoothData);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothData = new HashMap<>();


        list = (ListView)findViewById(R.id.listview);
        adapter = new BTDeviceAdapter(this);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new BluetoothDeviceOnItemclick());
        imageView = (ImageView)findViewById(R.id.imageview);

        camera_btn = (Button)findViewById(R.id.intent_camera);
        camera_btn.setOnClickListener(this);
        scanbluetooth_btn = (Button)findViewById(R.id.scanbluetooth);
        scanbluetooth_btn.setOnClickListener(this);
        resolution_btn = (Button)findViewById(R.id.screenResolution);
        resolution_btn.setOnClickListener(this);
        sendBroadcast_btn = (Button)findViewById(R.id.sendBroadcast);
        sendBroadcast_btn.setOnClickListener(this);

        mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        callback = new LeScanCallback(){
            @Override
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
                Log.i(TAG_2,bluetoothDevice.getName()+" "+bluetoothDevice.getAddress());
                String key = bluetoothDevice.getAddress();
                String value = bluetoothDevice.getName().toString();
                if(bluetoothData.containsKey(key))return;
                bluetoothData.put(key,value == null?"null":value);
                mHandler.sendEmptyMessage(1);
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            scanCallback = new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Log.i("onScanResult",result == null?"true":"false");
                        Log.i(TAG,result.getDevice().getName()+" "+result.getDevice().getAddress());
                        BluetoothDevice device = result.getDevice();
                        Log.i(TAG,device.getName()+" "+device.getAddress());
                        String key = device.getAddress();
                        String value = device.getName();
                        if(bluetoothData.containsKey(key))return;
                        bluetoothData.put(key,value == null?"null":value);
                        mHandler.sendEmptyMessage(1);
                    }
                }

                @Override
                public void onBatchScanResults(List<ScanResult> results) {
                    super.onBatchScanResults(results);
                    Log.i("onScanResult",results == null?"true":"false");
                    for(ScanResult result:results){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            BluetoothDevice device = result.getDevice();
                            Log.i(TAG,device.getName()+" "+device.getAddress());
                            String key = device.getAddress().toString();
                            String value = device.getName().toString();
                            if(bluetoothData.containsKey(key))return;
                            bluetoothData.put(key,value == null?"null":value);
                        }
                    }
                    mHandler.sendEmptyMessage(1);
                }

                @Override
                public void onScanFailed(int errorCode) {
                    super.onScanFailed(errorCode);
                    Log.i(TAG,""+errorCode);
                    switch(errorCode){
                        case SCAN_FAILED_ALREADY_STARTED :
                            Log.i(TAG,"SCAN_FAILED_ALREADY_STARTED");
                            break;
                        case SCAN_FAILED_APPLICATION_REGISTRATION_FAILED :
                            Log.i(TAG,"SCAN_FAILED_APPLICATION_REGISTRATION_FAILED");
                            break;
                        case SCAN_FAILED_FEATURE_UNSUPPORTED :
                            Log.i(TAG,"SCAN_FAILED_FEATURE_UNSUPPORTED");
                            break;
                        case SCAN_FAILED_INTERNAL_ERROR :
                            Log.i(TAG,"SCAN_FAILED_INTERNAL_ERROR");
                            break;
                    }
                }
            };
        }
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.intent_camera:
                if(!PermissionRequestUtils.RequestPermission(MainActivity.this,PermissionRequestUtils.storage_RequestCode,"  "))
                    if(!PermissionRequestUtils.RequestPermission(MainActivity.this,PermissionRequestUtils.camera_RequestCode,"  "))
                        CameraAlbumUtils.intentSystemCamera(MainActivity.this);
                break;
            case R.id.scanbluetooth:
                if(BlutoothVersionUtils.IsTurnonBluetooth(MainActivity.this,mbluetoothAdapter))
                if(!PermissionRequestUtils.RequestPermission(MainActivity.this,PermissionRequestUtils.location_RequestCode,"  "))
                   ScanBluetoothDevice();
                break;
            case R.id.screenResolution:
                startActivity(new Intent(this,ResolutionActivity.class));
                break;
            case R.id.sendBroadcast:
                Intent intent = new Intent(MainActivity.this,AlarmActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void ScanBluetoothDevice(){
        BlutoothVersionUtils.stopOrStartScanningBle(mbluetoothAdapter,callback,scanCallback,BlutoothVersionUtils.STARTSCAN);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case PermissionRequestUtils.storage_RequestCode:
                if(PermissionRequestUtils.IsAllGranted(permissions,grantResults)) {
                    Log.i("Permission_Yes","权限已授予");
                    if (!PermissionRequestUtils.RequestPermission(MainActivity.this, PermissionRequestUtils.camera_RequestCode, "   "))
                        CameraAlbumUtils.intentSystemCamera(MainActivity.this);
                }else{
                    Log.i("Permission_No","权限未授予");
                }
                break;
            case PermissionRequestUtils.camera_RequestCode:
                if(PermissionRequestUtils.IsAllGranted(permissions,grantResults)){
                    Log.i("Permission_Yes","权限已授予");
                    CameraAlbumUtils.intentSystemCamera(MainActivity.this);
                }else{
                    Log.i("Permission_No","权限未授予");
                }
                break;
            case PermissionRequestUtils.location_RequestCode:
                if(PermissionRequestUtils.IsAllGranted(permissions,grantResults)){
                    Log.i("Permission_Yes","权限已授予");
                    ScanBluetoothDevice();
                }else{
                    Log.i("Permission_No","权限未授予");
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case BlutoothVersionUtils.TURNONBLUETOOTH:
                if(resultCode == RESULT_OK)
                    if(!PermissionRequestUtils.RequestPermission(MainActivity.this,PermissionRequestUtils.location_RequestCode,"  "))
                        ScanBluetoothDevice();
                break;
		    case CameraAlbumUtils.PHOTOHRAPH:
                if(resultCode == RESULT_OK)CameraAlbumUtils.startPhotoZoom(MainActivity.this);
                break;
            case CameraAlbumUtils.PHOTOCROP:
                CameraAlbumUtils.dealWithResult(requestCode,data,imageView);
                break;
        }


    }

   class BluetoothDeviceOnItemclick implements AdapterView.OnItemClickListener{

       @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           TextView tv = adapterView.findViewById(R.id.textView2);
           Log.i("----Mac-----",tv.getText().toString()+"-----------");
           BluetoothDevice device = mbluetoothAdapter.getRemoteDevice("A0:E4:53:21:2D:0A");//tv.getText().toString());
           BluetoothGatt bluetoothGatt = device.connectGatt(MainActivity.this, false, new BluetoothGattCallback() {
               @Override
               public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                   super.onPhyUpdate(gatt, txPhy, rxPhy, status);
                   Log.i("onPhyUpdate","");
               }

               @Override
               public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                   super.onPhyRead(gatt, txPhy, rxPhy, status);
                   Log.i("onPhyRead","");
               }

               @Override
               public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                   super.onConnectionStateChange(gatt, status, newState);
                   Log.i("onConnectionStateChange"," "+status+" "+newState);
               }

               @Override
               public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                   super.onServicesDiscovered(gatt, status);
                   Log.i("onServicesDiscovered","");
               }

               @Override
               public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                   super.onCharacteristicRead(gatt, characteristic, status);
                   Log.i("onCharacteristicRead","");
               }

               @Override
               public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                   super.onCharacteristicWrite(gatt, characteristic, status);
                   Log.i("onCharacteristicWrite","");
               }

               @Override
               public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                   super.onCharacteristicChanged(gatt, characteristic);
                   Log.i("onCharacteristicChanged","");
               }

               @Override
               public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                   super.onDescriptorRead(gatt, descriptor, status);
                   Log.i("onDescriptorRead","");
               }

               @Override
               public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                   super.onDescriptorWrite(gatt, descriptor, status);
                   Log.i("onDescriptorWrite","");
               }

               @Override
               public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
                   super.onReliableWriteCompleted(gatt, status);
                   Log.i("onReliableWriteComp","");
               }

               @Override
               public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                   super.onReadRemoteRssi(gatt, rssi, status);
                   Log.i("onReadRemoteRssi","");
               }

               @Override
               public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                   super.onMtuChanged(gatt, mtu, status);
                   Log.i("onMtuChanged","");
               }
           });

       }
   }
}

package com.himawari.permissionUtils.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.BleStateListenerUtils;
import com.himawari.permissionUtils.utils.LogUtils;
import com.himawari.permissionUtils.utils.ServiceUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by S.Lee on 2017/12/27.
 */

public class MainViewActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private List<String> items;
    private String[] itemStr = new String[]{"Main","Scaleplate","ScrollDelete","BirthdayPicker","Guide","WebViewLoadGif"
    ,"Test","GPS AGPS"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_mainview);
        super.onCreate(savedInstanceState);

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initView() {
        items = Arrays.asList(itemStr);

        listView = findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,ScalePlateActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,ScrollDeleteActivity.class));
                break;
            case 3:
                startActivity(new Intent(this,BirthdayPickerActivity.class));
                break;
            case 4:
                startActivity(new Intent(this,GuideActivity.class));
                break;
            case 5:
                startActivity(new Intent(this,WebViewloadGifActivity.class));
                break;
            case 6:
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,23);
                calendar.set(Calendar.MINUTE,59);
                calendar.set(Calendar.SECOND,50);
                Log.i("Calendar:",new Date(calendar.getTimeInMillis()).toString());
                for(int i = 0 ; i < 5;i++){
                    calendar.setTimeInMillis(calendar.getTimeInMillis()+5000);
                    Log.i("Calendar:",new Date(calendar.getTimeInMillis()).toString());
                }
                break;
            case 7:
                if(ServiceUtils.isOPen(this)){
                    Toast.makeText(this,"定位服务已打开",Toast.LENGTH_SHORT).show();
                }else{
                    ServiceUtils.openGPS(this);
                }
                break;


        }

    }


}

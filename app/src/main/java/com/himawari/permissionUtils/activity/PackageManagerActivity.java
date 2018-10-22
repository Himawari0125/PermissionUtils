package com.himawari.permissionUtils.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.LogUtils;

/**
 * Created by Kokosnuss on 2018/9/25
 */
public class PackageManagerActivity extends BaseActivity {
    private EditText editText;
    private Button button;
    private TextView textView;
    private PackageManager packageManager;
    @Override
    protected void initView() {
        editText = findViewById(R.id.editText4);
        button = findViewById(R.id.button48);
        textView = findViewById(R.id.textView21);

        packageManager = this.getPackageManager();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText((String)msg.obj);
        }
    };

    private Handler textHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        LogUtils.i(LogUtils.originalIndex,"PackageManager onCreate");

        setContentView(R.layout.activity_packagemanager);
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                textHandler = new Handler() {
                    public void handleMessage(Message msg) {
                        Toast.makeText(PackageManagerActivity.this, (String)msg.obj, Toast.LENGTH_SHORT).show();
                    }
                };


                Looper.loop();
            }
        }).start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message msg = new Message();
                Message testMsg = new Message();
                try {
                    PackageInfo packageInfo =
                            packageManager.getPackageInfo(editText.getText().toString(),
                                    PackageManager.GET_ACTIVITIES);
                    if (packageInfo!=null){
                       msg.obj = "versionName:" + packageInfo.versionName == null?"null"
                               :packageInfo.versionName+
                               " versionCode:"+packageInfo.versionCode;
                    }else{
                        msg.obj = "PackageInfo == null";
                    }
                    testMsg.obj = msg.obj;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    msg.obj = e.toString();
                    testMsg.obj = msg.obj;
                }
                mHandler.sendMessage(msg);
                testMsg = null;
                textHandler.sendMessage(testMsg);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(LogUtils.originalIndex,"PackageManager onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i(LogUtils.originalIndex,"PackageManager onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(LogUtils.originalIndex,"PackageManager onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i(LogUtils.originalIndex,"PackageManager onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i(LogUtils.originalIndex,"PackageManager onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i(LogUtils.originalIndex,"PackageManager onPause");
    }
}

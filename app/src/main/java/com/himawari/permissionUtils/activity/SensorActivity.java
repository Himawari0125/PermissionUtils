package com.himawari.permissionUtils.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.LogUtils;

import java.util.List;

/**
 * Created by Kokosnuss on 2018/10/9
 */
public class SensorActivity extends BaseActivity {

    private TextView tv;
    private ImageView img;


    @Override
    protected void initView() {
        tv = findViewById(R.id.textView23);
        img = findViewById(R.id.img_animation);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);  //获取系统的传感器服务并创建实例

     //   List<Sensor> list = sm.getSensorList(Sensor.TYPE_ALL);  //获取传感器的集合
//        for (Sensor sensor:list){
//            tv.append(sensor.getStringType() + "\n");  //把传感器种类显示在TextView中
//
//        }

        Sensorlistener sensorlistener = new Sensorlistener();
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(sensorlistener,sensor,SensorManager.SENSOR_DELAY_NORMAL);




    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_sensor);
        super.onCreate(savedInstanceState);

    }

    private class Sensorlistener implements SensorEventListener {
        private float predegree = 0;
        @Override
        public void onSensorChanged(SensorEvent event) {
            /**
             * values[0]: x-axis 方向加速度
             　　 values[1]: y-axis 方向加速度
             　　 values[2]: z-axis 方向加速度
             */
            float degree = event.values[0];// 存放了方向值
            /**动画效果*/
            RotateAnimation animation = new RotateAnimation(predegree, degree,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,
                    0.5f);
            animation.setDuration(200);
            img.startAnimation(animation);
            predegree=-degree;



            float x=event.values[SensorManager.DATA_X];
            float y=event.values[SensorManager.DATA_Y];
            float z=event.values[SensorManager.DATA_Z];

             LogUtils.i(LogUtils.originalIndex,"x="+(int)x+",y="+(int)y+",z="+(int)z);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}

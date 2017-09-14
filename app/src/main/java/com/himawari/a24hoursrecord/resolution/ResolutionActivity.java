package com.himawari.a24hoursrecord.resolution;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.utils.ScreenControlUtils;


/**
 * Created by S.Lee on 2017/9/5 0005.
 */

public class ResolutionActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenControlUtils.wakeScreenUp(this);
        setContentView(R.layout.activity_resolution);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ScreenControlUtils.acquireCpuWakeLock(this);
    }
}

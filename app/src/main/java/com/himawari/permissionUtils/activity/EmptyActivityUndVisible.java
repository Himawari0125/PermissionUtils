package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.mineException.EmptyException;
import com.himawari.permissionUtils.utils.LogUtils;
import com.himawari.permissionUtils.utils.MyActivityManager;

public class EmptyActivityUndVisible extends BaseActivity implements View.OnClickListener{
    private MyActivityManager manager;
    private ImageView imageView;
    private Button visibleBtn,goneBtn;



    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        manager = MyActivityManager.getManager();

        visibleBtn = findViewById(R.id.button35);
        goneBtn = findViewById(R.id.button36);
        imageView = findViewById(R.id.imageView6);

        visibleBtn.setOnClickListener(this);
        goneBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button34:
                manager.finishAllActivity();
                try {
                    manager.getRunningActivity();
                } catch (EmptyException e) {
                    LogUtils.e(LogUtils.originalIndex,e.getMessage());

                }
                break;
            case R.id.button35:
                imageView.setVisibility(View.VISIBLE);

                LogUtils.i(LogUtils.originalIndex,"Visibleheight:"+imageView.getHeight());
                break;
            case R.id.button36:
                imageView.setVisibility(View.GONE);
                LogUtils.i(LogUtils.originalIndex,"Goneheight:"+imageView.getHeight());
                break;
        }
    }
}

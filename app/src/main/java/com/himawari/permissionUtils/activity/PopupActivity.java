package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.PopupWindowUtils;

public class PopupActivity extends BaseActivity implements View.OnClickListener{
    private ImageView imageView;
    private Button top_btn,bottom_btn,left_btn,right_btn;
    @Override
    protected void initView() {
        imageView = findViewById(R.id.imageButton);
        top_btn = findViewById(R.id.button22);
        bottom_btn = findViewById(R.id.button23);
        left_btn = findViewById(R.id.button24);
        right_btn = findViewById(R.id.button25);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_popup);
        super.onCreate(savedInstanceState);

        top_btn.setOnClickListener(this);
        bottom_btn.setOnClickListener(this);
        left_btn.setOnClickListener(this);
        right_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button22:
                PopupWindowUtils.showPopupLocation(
                        PopupActivity.this,
                        imageView,
                        Gravity.TOP|Gravity.CENTER);
                break;
            case R.id.button23:
                PopupWindowUtils.showPopupLocation(
                        PopupActivity.this,
                        imageView,
                        Gravity.BOTTOM|Gravity.CENTER);
                break;
            case R.id.button24:
                PopupWindowUtils.showPopupLocation(
                        PopupActivity.this,
                        imageView,
                        Gravity.LEFT|Gravity.CENTER);
                break;
            case R.id.button25:
                PopupWindowUtils.showPopupLocation(
                        PopupActivity.this,
                        imageView,
                        Gravity.RIGHT|Gravity.CENTER);
                break;

        }
    }
}

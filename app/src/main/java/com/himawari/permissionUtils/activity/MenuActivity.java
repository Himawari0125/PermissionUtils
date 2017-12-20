package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.views.CustomMenu;

/**
 * Created by S.Lee on 2017/11/29.
 */

public class MenuActivity extends BaseActivity {
    private CustomMenu menu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        super.onCreate(savedInstanceState);
        menu.setImageClick(new CustomMenu.imgOnclick() {
            @Override
            public void imgOnClick(int position) {

            }
        });
    }

    @Override
    protected void initView() {
        menu = findViewById(R.id.setting_menu);

    }
}

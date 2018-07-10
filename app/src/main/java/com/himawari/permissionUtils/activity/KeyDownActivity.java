package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.Toast;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.fragment.KeyDownFragment;
import com.himawari.permissionUtils.utils.LogUtils;

public class KeyDownActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_keydown);
        super.onCreate(savedInstanceState);

        KeyDownFragment fragment = new KeyDownFragment();
        getFragmentManager().beginTransaction().add(R.id.main_fragment,fragment).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtils.i(3,"Activity keycode:"+keyCode+" action:"+event.getAction());
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "返回键无效", Toast.LENGTH_SHORT).show();
            return true;//return true;拦截事件传递,从而屏蔽back键。
        }
        if (KeyEvent.KEYCODE_HOME == keyCode) {
            Toast.makeText(getApplicationContext(), "HOME 键已被禁用...", Toast.LENGTH_SHORT).show();
            return true;//同理
        }

        if (429 == keyCode) {
            Toast.makeText(getApplicationContext(), "HOME 键已被禁用...429", Toast.LENGTH_SHORT).show();
            return true;//同理
        }
        return super.onKeyDown(keyCode, event);
    }
}

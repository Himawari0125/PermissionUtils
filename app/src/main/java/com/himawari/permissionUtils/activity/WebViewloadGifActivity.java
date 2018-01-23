package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.LogUtils;

/**
 * Created by S.Lee on 2018/1/17.
 */

public class WebViewloadGifActivity extends BaseActivity{
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_webviewload);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.webView);

        webView.loadUrl("file:///android_asset/guide_accmanage.gif");
    }
}

package com.himawari.a24hoursrecord.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.himawari.a24hoursrecord.BaseActvity;
import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.utils.WebViewLoadUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by S.Lee on 2017/9/15 0015.
 */

public class WebActivity extends BaseActvity {
    private WebView webView;
    private LinearLayout layout;
    private static final String urlPath = "<html><body><iframe height=498 width=510 src='http://player.youku.com/embed/XNzQzMTc4Njg0' frameborder=0 'allowfullscreen'></iframe></body></html>";//"http://v.youku.com/v_show/id_XNzQzMTc4Njg0.html";
    private static final String testPath = "http://oq2qpeuia.bkt.clouddn.com/kuwo_vf_1.mp4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_web);
        super.onCreate(savedInstanceState);
        //WebViewLoadUtils.LoadWebView(webView,testPath,WebViewLoadUtils.LOADURL);
        WebViewLoadUtils.LoadWebView(webView,urlPath,WebViewLoadUtils.LOADDATA);
    }

    @Override
    protected void initView() {
        webView = (WebView)findViewById(R.id.web_view);
        layout = (LinearLayout)findViewById(R.id.layout);
    }

    // goBack()表示返回webView的上一页面
    public boolean onKeyDown(int keyCoder, KeyEvent event) {
       if(WebViewLoadUtils.closeWebView(webView,keyCoder,layout)){
           this.finish();
           return true;
       }else{
           return false;
       }
    }
}

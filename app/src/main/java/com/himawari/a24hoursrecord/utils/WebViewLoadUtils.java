package com.himawari.a24hoursrecord.utils;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

/**
 * Created by S.Lee on 2017/9/15 0015.
 */

public class WebViewLoadUtils {

    public static final int LOADDATA = 1001;
    public static final int LOADURL = 1002;

    public static void LoadWebView(WebView webView,String urlPath,int type){
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }
        });
        switch (type){
            case LOADDATA:
                webView.loadData(urlPath, "text/html", "utf-8");
                break;
            case LOADURL:
                webView.loadUrl(urlPath);
                break;
        }

    }

    public static boolean closeWebView(WebView webView, int keyCoder, ViewGroup parentLayout){
        if (!webView.canGoBack() && keyCoder == KeyEvent.KEYCODE_BACK) {
            parentLayout.removeAllViews();
            webView.clearCache(true);
            webView.clearHistory();
            webView.destroy();
            return true;
        }
        return false;
    }
}

package com.himawari.permissionUtils.utils;

import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by S.Lee on 2017/9/15 0015.
 */

public class WebViewLoadUtils {

    public static final int LOADDATA = 1001;
    public static final int LOADURL = 1002;

    public static void LoadWebView(WebView webView,String urlPath,int type){
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
 //       webView.getSettings().setSupportZoom(false);
//        webView.getSettings().setDisplayZoomControls(false);
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

package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.PortraitLandscapeUtils;
import com.himawari.permissionUtils.utils.WebViewLoadUtils;

/**
 * Created by S.Lee on 2017/9/15 0015.
 */

public class WebActivity extends BaseActivity {
    private WebView webView;
    private LinearLayout layout;
    private static final String urlPath = "<html><body><iframe height=360 width=604 src='http://player.youku.com/embed/XNzQzMTc4Njg0' frameborder=0 'allowfullscreen'></iframe></body></html>";//"http://v.youku.com/v_show/id_XNzQzMTc4Njg0.html";
    private static final String testPath = "http://oq2qpeuia.bkt.clouddn.com/kuwo_vf_1.mp4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        PortraitLandscapeUtils.requestFullScreen(this);
        setContentView(R.layout.activity_web);
        super.onCreate(savedInstanceState);

        //WebViewLoadUtils.LoadWebView(webView,testPath,WebViewLoadUtils.LOADURL);
        WebViewLoadUtils.LoadWebView(webView,urlPath, WebViewLoadUtils.LOADDATA);
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

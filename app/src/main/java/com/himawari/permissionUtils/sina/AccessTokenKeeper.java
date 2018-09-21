package com.himawari.permissionUtils.sina;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.himawari.permissionUtils.utils.SharePerferenceUtils;


public class AccessTokenKeeper {
    private static final String PREFERENCES_NAME = "com_weibo_sdk_android";

    private static final String KEY_UID           = "uid";
    private static final String KEY_ACCESS_TOKEN  = "access_token";
    private static final String KEY_EXPIRES_IN    = "expires_in";

    /**
     * 清空 SharedPreferences 中 Token信息。
     * 
     * @param context 应用程序上下文环境
     */
    public static void clear(Context context) {
        if (null == context) {
            return;
        }
        SharePerferenceUtils.clearAll(context,PREFERENCES_NAME,Context.MODE_APPEND);

    }
}

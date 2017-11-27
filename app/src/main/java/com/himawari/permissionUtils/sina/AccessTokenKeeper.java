package com.himawari.permissionUtils.sina;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**  
 * @Title: AccessTokenKeeper.java
 * @Package com.chronocloud.bodyscale.util.sina
 * @Description: TODO(该类定义了微博授权时所需要的参数。)
 * @author Mars HouJianChao1204@163.com 
 * @date Aug 7, 2014 2:30:18 PM
 * @version V1.0  
 */
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
        
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}

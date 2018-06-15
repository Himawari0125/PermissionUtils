package com.himawari.permissionUtils.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

/**
 * Created by S.Lee on 2017/11/21.
 */

public class TextViewUtils {

    public static void setSpecifiedTvColor(String str , int ch1 , int ch2 , int color , TextView tv){
        if(ch1 >= 0 && ch2 > 0 && ch2 <= str.length()){
            SpannableStringBuilder builder = new SpannableStringBuilder(str);
            builder.setSpan(new ForegroundColorSpan(color), ch1, ch2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(builder);
        }

    }

    public static SpannableStringBuilder setSpecifiedTvButtonSize(Context context, String str , int ch1 , int ch2 ,int buttonSize, int color ){
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        if(ch1 >= 0 && ch2 > 0 && ch2 <= str.length()){
            builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), ch1, ch2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(color), 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new AbsoluteSizeSpan(DensityUtils.dip2px(context,buttonSize)), ch1, ch2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }

}

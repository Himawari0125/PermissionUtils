package com.himawari.permissionUtils.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.himawari.permissionUtils.R;

/**
 * Created by S.Lee on 2018/3/22.
 */

public class ProgressUtils {

    private static Dialog waitDialog;
    public static void showProgressWait(Context context,String toast){

        //创建ContextView
        int padding = DensityUtils.dip2px(context,10);
        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundResource(R.drawable.historyitemback);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setPaddingRelative(padding*2,padding*2,padding*4,padding*2);

        //添加ProgressBar
        ProgressBar progressBar = new ProgressBar(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        layout.addView(progressBar,params);

        //添加TextView
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams text_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        text_params.gravity = Gravity.CENTER_VERTICAL;
        textView.setPaddingRelative(padding,0,0,0);
        textView.setText(toast);
        layout.addView(textView,params);

        //创建Dialog
        waitDialog =  new Dialog(context);
        waitDialog.getWindow().setBackgroundDrawableResource(R.color.tranfer);
        waitDialog.setCanceledOnTouchOutside(false);
        waitDialog.setContentView(layout);
        waitDialog.show();


    }

    public static void dismissProgress(){
        if(waitDialog!=null && waitDialog.isShowing())waitDialog.dismiss();
    }
}

package com.himawari.permissionUtils.utils;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.CalenderPickerBean;
import com.himawari.permissionUtils.views.CalenderPickerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by S.Lee on 2017/11/30.
 */

public class PopupWindowUtils {
    private static PopupWindow deletPopupWindow;
    private static PopupWindow calendarPopupWindow;
    public static void showDeleteDialog(Context mcontext, View rootView, int gravityLocation, boolean isShow, final DeletePopWindowListener listener){
        View contextView = LayoutInflater.from(mcontext).inflate(R.layout.dialog_delete,null);
        if(deletPopupWindow == null){
            deletPopupWindow = new PopupWindow(contextView, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            deletPopupWindow.setContentView(contextView);
            deletPopupWindow.setAnimationStyle(R.style.ActionSheetDialogAnimation);
            deletPopupWindow.setOutsideTouchable(false);
        }
        if(isShow)
            deletPopupWindow.showAtLocation(rootView,gravityLocation,0,0);
        else
            deletPopupWindow.dismiss();
        LinearLayout deleteClick = contextView.findViewById(R.id.delete_layout);
        deleteClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteClick();
            }
        });
    }

    public interface DeletePopWindowListener{
        void deleteClick();
    }

    public static void releaseDeletePopupWindow(){
        if(deletPopupWindow!=null)deletPopupWindow = null;
    }

    public static void showCalenderView(Context mcontext,View rootView, int gravityLocation,boolean isShow,Calendar time){

        View view = LayoutInflater.from(mcontext).inflate(R.layout.dialog_datechoose,null);
        CalenderPickerView calendarView = view.findViewById(R.id.calenderPickerView);
        calendarView.setCurrentTime(time);
        calendarPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        calendarPopupWindow.setContentView(view);
        if(isShow)
            calendarPopupWindow.showAtLocation(rootView,gravityLocation,0,0);
        else
            calendarPopupWindow.dismiss();
    }

    public static void releaseCalendarPopupWindow(){
        if(calendarPopupWindow!=null)calendarPopupWindow = null;
    }
}

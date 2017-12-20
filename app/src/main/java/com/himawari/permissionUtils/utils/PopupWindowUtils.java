package com.himawari.permissionUtils.utils;

import android.content.Context;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
    private static CalenderPickerView calendarView;
    private static TextView monthYear;
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

    public static void showCalenderView(Context mcontext,View rootView, int gravityLocation,Calendar time){
        View view = LayoutInflater.from(mcontext).inflate(R.layout.dialog_datechoose, null);
        if(calendarPopupWindow == null) {
            calendarView = view.findViewById(R.id.calenderPickerView);
            calendarView.setCurrentTime(time);
            monthYear = view.findViewById(R.id.textView3);
            monthYear.setText(time.get(Calendar.YEAR)+mcontext.getResources().getString(R.string.year)+
                    (time.get(Calendar.MONTH)+1)+mcontext.getResources().getString(R.string.month));
            calendarView.setSelectedListener(new CalenderPickerView.selectListener() {
                @Override
                public void onSelectedListener(CalenderPickerBean bean) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            calendarPopupWindow.dismiss();
                        }
                    },200);

                }
            });
            ImageView proMonth_btn = view.findViewById(R.id.imageView2);
            proMonth_btn.setOnClickListener(new monthBtnClick());
            ImageView nextMonth_btn = view.findViewById(R.id.imageView3);
            nextMonth_btn.setOnClickListener(new monthBtnClick());
            calendarPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            calendarPopupWindow.setContentView(view);
            calendarPopupWindow.setAnimationStyle(R.style.CalendarActionSheetDialogAnimation);
            calendarPopupWindow.setOutsideTouchable(true);
            calendarPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_OUTSIDE)
                        calendarPopupWindow.dismiss();
                    return false;
                }
            });
        }
        calendarPopupWindow.showAtLocation(rootView,gravityLocation,0,0);

    }

    public static void releaseCalendarPopupWindow(){
        if(calendarPopupWindow!=null)calendarPopupWindow = null;
    }

    private static class monthBtnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.imageView2:
                    monthYear.setText(calendarView.proMonth());
                    break;
                case R.id.imageView3:
                    monthYear.setText(calendarView.nextMonth());
                    break;
            }
        }
    }
}

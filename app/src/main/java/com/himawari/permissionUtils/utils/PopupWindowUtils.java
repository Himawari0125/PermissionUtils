package com.himawari.permissionUtils.utils;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.adapter.AccChoosePopAdapter;
import com.himawari.permissionUtils.bean.AccManageBean;
import com.himawari.permissionUtils.bean.CalenderPickerBean;
import com.himawari.permissionUtils.views.CalenderPickerView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by S.Lee on 2017/11/30.
 */

public class PopupWindowUtils {
    private static PopupWindow deletPopupWindow;
    private static PopupWindow calendarPopupWindow;
    private static CalenderPickerView calendarView;
    private static TextView monthYear;

    private static List<AccManageBean> acc_datas;
    private static AccChoosePopAdapter acc_adapter;
    private static PopupWindow accountPopupWindow;

    /**
     *删除按钮
     */
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

    /**
     *日历控件
     */
    public static void showCalenderView(Context mcontext,View rootView,Calendar time){
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
        calendarPopupWindow.showAsDropDown(rootView);

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

    public static void showPopupLocation(Context context,View rootView,int gravity){
        View view = LayoutInflater.from(context).inflate(R.layout.item_popup,null);
        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.CalendarActionSheetDialogAnimation);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(rootView,gravity,0,0);
        //popupWindow.showAsDropDown(rootView);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_OUTSIDE)
                    popupWindow.dismiss();
                return false;
            }
        });
    }
    /**
     * 账号选择
     */

    public static AccChoosePopAdapter showAccountView(Context context, final List<AccManageBean> datas, View rootView, final AccSelectListener listener){
        if (datas == null||datas.size() <= 0)return null;


        View view = LayoutInflater.from(context).inflate(R.layout.pop_accchoose, null);
        ListView listView = view.findViewById(R.id.listview);
        if(acc_datas == null || acc_datas != datas){
            acc_datas = datas;

            acc_adapter = new AccChoosePopAdapter(context,datas);
            listView.setAdapter(acc_adapter);
            accountPopupWindow = new PopupWindow(view,WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            accountPopupWindow.setAnimationStyle(R.style.CalendarActionSheetDialogAnimation);
            accountPopupWindow.setOutsideTouchable(false);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0){

                    }else if(position == datas.size()-1){
                        listener.onAddAcc();
                    }else{
                        if(datas.get(position).IsChild())
                            listener.onAccSelect(datas.get(position).getData().getBindUserId());
                        else
                            listener.onAccSelect(datas.get(position).getData().getParentUserId());
                    }

                    if(accountPopupWindow.isShowing())accountPopupWindow.dismiss();
                }
            });
        }




        if(!accountPopupWindow.isShowing())accountPopupWindow.showAsDropDown(rootView);

        LogUtils.i(LogUtils.originalIndex,"PopupWindow is showing.");
        return acc_adapter;



    }
    public interface AccSelectListener{
        void onAccSelect(int userId);
        void onAddAcc();
    }


}

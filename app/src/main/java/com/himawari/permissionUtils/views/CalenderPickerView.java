package com.himawari.permissionUtils.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.CalenderPickerBean;
import com.himawari.permissionUtils.utils.DensityUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by S.Lee on 2017/12/14.
 */

public class CalenderPickerView extends View implements View.OnTouchListener{
    private Paint normalDate_paint;
    private Paint todayDate_paint;
    private Paint selectedDate_paint;
    private Paint dataDate_paint;
    private Paint selectedDate_bg_paint;
    private float averageWidth;
    private float selectedAadius;
    private List<CalenderPickerBean> beans;
    private float paintSize;
    private float startX;
    private Context context;
    private selectListener listener;

    private int today;
    private int month;
    private int year;

    public void setSelectedListener(selectListener mlistener){
        this.listener = mlistener;
    }

    public CalenderPickerView(Context context) {
        super(context);
        this.context = context;
        init();
    }
    public CalenderPickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        float resultWidth;
        if (wSpecMode == MeasureSpec.EXACTLY){
                resultWidth = wSpecSize;
        }else{
            resultWidth = MyApplication.width - (getPaddingLeft()+getPaddingRight());
        }
        averageWidth = resultWidth/7;
        selectedAadius = (averageWidth-10)/2;
        startX = averageWidth/2;
        setMeasuredDimension((int)resultWidth,(int)resultWidth);
    }

    public void setCurrentTime(Calendar currentTime){
        beans = getDaysOfMonth(currentTime);
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(beans==null)return;
        for(int i = 0; i < beans.size(); i++){
            CalenderPickerBean bean = beans.get(i);
            float x = startX+averageWidth*(bean.getDayofWeek() - 1);
            float y = startX+averageWidth*(bean.getWeekofMonth() -1);
            if(bean.isNormalDay()){
                beans.get(i).setSetXAndY(x,y);
                drawNormalDate(canvas,x,y,bean.getDay()+"");
            }
            if(bean.isScaledDay()){
                beans.get(i).setSetXAndY(x,y);
                drawScaledDate(canvas,x,y,bean.getDay()+"");
            }
//            if(bean.isToday()){
//                beans.get(i).setSetXAndY(x,y);
//                drawTodayDate(canvas,x,y,bean.getDay()+"");
//            }
            if(bean.isSelectedDay()){
                beans.get(i).setSetXAndY(x,y);
                drawSelectedDate(canvas,x,y,bean.getDay()+"");
            }
        }

    }

    /**
     * 被选择的日期
     */
    private void drawSelectedDate(Canvas canvas,float x,float y,String dayOfMonth){
        canvas.drawCircle(x,y - paintSize/2,selectedAadius,selectedDate_bg_paint);
        canvas.drawText(dayOfMonth,x,y,selectedDate_paint);


    }

    /**
     * 含有测量数据的日期
     */
    private void drawScaledDate(Canvas canvas,float x,float y,String dayOfMonth){
        canvas.drawText(dayOfMonth,x,y,dataDate_paint);
    }

    /**
     * 普通日期
     */
    private void drawNormalDate(Canvas canvas,float x,float y,String dayOfMonth){
        canvas.drawText(dayOfMonth,x,y,normalDate_paint);
    }

    /**
     * 普通日期
     */
    private void drawTodayDate(Canvas canvas,float x,float y,String dayOfMonth){
        canvas.drawText(dayOfMonth,x,y,todayDate_paint);
    }
    /**
     * 初始化画笔
     */
    private void init(){
        setOnTouchListener(this);

        int normalDate_color = Color.parseColor("#ffffff");
        int selectedDate_color = Color.parseColor("#56d9ff");
        int scaledDate_color = Color.parseColor("#89d1ff");
        int selectedDate_bg_color = Color.parseColor("#ffffff");
        int todayDate_color = Color.parseColor("#FF4081");

        normalDate_paint = new Paint();
        selectedDate_bg_paint = new Paint();
        dataDate_paint = new Paint();
        selectedDate_paint = new Paint();
        todayDate_paint = new Paint();

        selectedDate_bg_paint.setAntiAlias(true);

        normalDate_paint.setTextAlign(Paint.Align.CENTER);
        dataDate_paint.setTextAlign(Paint.Align.CENTER);
        selectedDate_paint.setTextAlign(Paint.Align.CENTER);
        todayDate_paint.setTextAlign(Paint.Align.CENTER);

        paintSize = DensityUtils.dip2px(context,15);

        normalDate_paint.setColor(normalDate_color);
        normalDate_paint.setTextSize(paintSize);
        selectedDate_bg_paint.setColor(selectedDate_bg_color);
        dataDate_paint.setColor(scaledDate_color);
        dataDate_paint.setTextSize(paintSize);
        selectedDate_paint.setColor(selectedDate_color);
        selectedDate_paint.setTextSize(paintSize);
        todayDate_paint.setColor(todayDate_color);
        todayDate_paint.setTextSize(paintSize);
    }

    private List<CalenderPickerBean> getDaysOfMonth(Calendar currentCalendar ){
        //获取当前时间相关信息

        today = currentCalendar.get(Calendar.DAY_OF_MONTH);
        month = currentCalendar.get(Calendar.MONTH)+1;
        year = currentCalendar.get(Calendar.YEAR);

        //获取当前月份天数
        List<Integer> bigMonth = Arrays.asList(1,3,5,7,8,10,12);
        List<Integer> littleMonth = Arrays.asList(4,6,9,11);
        int totalDays;
        if(bigMonth.contains(month)){
            totalDays = 31;
        }else if(littleMonth.contains(month)){
            totalDays = 30;
        }else{
            if(year%100 ==0 && year%400 == 0){//世纪年闰年
                totalDays = 29;
            }else if(year%100 !=0 && year%4 == 0){//普通年闰年
                totalDays = 29;
            }else{
                totalDays = 28;
            }
        }
        //获取当前月份每日相关信息
        List<CalenderPickerBean> datas = new ArrayList<>();
        for(int i = 1; i <= totalDays ; i++){
            currentCalendar.set(Calendar.DAY_OF_MONTH,i);
            CalenderPickerBean bean = new CalenderPickerBean();
            bean.setDay(i);
            bean.setMonth(month);
            bean.setYear(year);
            bean.setDayofWeek(currentCalendar.get(Calendar.DAY_OF_WEEK));
            bean.setWeekofMonth(currentCalendar.get(Calendar.WEEK_OF_MONTH));
            bean.setSelectedDay(today == i);//setToday(today == i ? true:false);
            bean.setNormalDay(true);

            datas.add(bean);
        }
        return datas;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("-------"," x:"+event.getX()+" y:"+event.getY());
                setSelectedDate(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_UP:

                break;

        }
        return false;
    }

    private void setSelectedDate(float x,float y){
        int returnBean = -1;
        for(int i = 0 ; i < beans.size() ;i++ ){
            CalenderPickerBean bean = beans.get(i);
            if(Math.abs(x - bean.getSetX()) < selectedAadius && Math.abs(y - bean.getSetY()) < selectedAadius){
                returnBean = i;
                beans.get(i).setSelectedDay(true);
            }else{
                beans.get(i).setSelectedDay(false);
            }
        }
        if(returnBean >= 0)listener.onSelectedListener(beans.get(returnBean));
        invalidate();
    }

    public interface selectListener{
        void onSelectedListener(CalenderPickerBean bean);
    }

    public String proMonth(){
        String monthYear = "";
        if(month > 0 && month <= 12){
            month--;
            if(month == 0){
                year--;
                month = 12;
            }

            monthYear = year + getResources().getString(R.string.year)+month+getResources().getString(R.string.month);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month-1);
            calendar.set(Calendar.DAY_OF_MONTH,today);
            setCurrentTime(calendar);

        }
        return monthYear;
    }
    public String nextMonth(){
        String monthYear = "";
        if(month > 0 && month <= 12){
            month++;
            if(month == 13){
                year++;
                month = 1;
            }
            monthYear = year + getResources().getString(R.string.year)+month+getResources().getString(R.string.month);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month-1);
            calendar.set(Calendar.DAY_OF_MONTH,today);
            setCurrentTime(calendar);
        }
        return monthYear;
    }
}

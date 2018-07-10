package com.himawari.permissionUtils.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.bean.ScrollItemBean;
import com.himawari.permissionUtils.utils.DensityUtils;
import com.himawari.permissionUtils.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by S.Lee on 2018/1/3.
 */

public class ScrollPickerView extends LinearLayout implements View.OnTouchListener{

    private List<String> datas;
    private int dataSize;
    private boolean isRecycle;

    private Context context;

    private List<View> viewList;

    private int totalItem = 7;

    private int itemHeight ;

    public ScrollPickerView(Context context) {
        super(context);
        this.context = context;
    }

    public ScrollPickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public ScrollPickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        LogUtils.i(3,MeasureSpec.AT_MOST+" "+MeasureSpec.EXACTLY+" "+MeasureSpec.UNSPECIFIED);

        LogUtils.i(3,widthMode+" "+widthSize+" "+heightMode+" "+heightSize);

    }

    /**
     *设置填充数据
     */
    public void setDatas(int startDate,int endDate,boolean isRecycle){
        datas = new ArrayList<>();
        for(int i = startDate; i<= endDate;i++){
            datas.add(i+"");
        }
        this.isRecycle = isRecycle;
        totalItem = datas.size();
        setOrientation(VERTICAL);
        setOnTouchListener(this);
        initializing();
    }

    private void initializing(){
        dataSize = datas.size();
        itemHeight = DensityUtils.dip2px(context,30);

        if(datas == null)return;
        viewList = new ArrayList<>();

        if(isRecycle){
            for(int i = 0;i <=totalItem/2;i++){
                viewList.add(getItem(i));
            }
            for (int i = 1 ; i <= totalItem/2;i++){
                viewList.add(0,getItem(dataSize-i));
            }
        }else{
            for(int i = 0 ; i < totalItem ; i++){
                viewList.add(getItem(i));
            }
        }
        for(View view:viewList)addView(view);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
               // addBottomView((Integer)getChildAt(getChildCount()-1).getTag());
                addTopView((Integer)getChildAt(0).getTag());
                //removeBottomView();
                break;
        }
        return true;
    }

    /**
     * 添加顶部View
     */
    private void addTopView(int currentTopIndex){
        if(currentTopIndex == 0)
            currentTopIndex = dataSize - 1;
        else
            currentTopIndex = currentTopIndex -1;
        addView(getItem(currentTopIndex),0);
    }

    /**
     * 添加底部View
     */
    private void addBottomView(int currentBottomIndex){
        if(currentBottomIndex == dataSize - 1)
            currentBottomIndex = 0;
        else
            currentBottomIndex = currentBottomIndex + 1;
        addView(getItem(currentBottomIndex));
    }

    /**
     * 移除顶部View
     */
    private void removeTopView(){
        removeView(getChildAt(0));
    }

    /**
     * 移除底部View
     */
    private void removeBottomView(){
        removeView(getChildAt(getChildCount() - 1));
    }


    /**
     *创建TextView
     */
    private TextView getItem(int index){
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,itemHeight);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(params);
        textView.setText(datas.get(index).toString());
        textView.setTag(index);
        return textView;
    }

}

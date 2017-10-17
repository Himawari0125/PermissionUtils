package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.himawari.a24hoursrecord.MyApplication;
import com.himawari.a24hoursrecord.utils.DensityUtils;

/**
 * Created by S.Lee on 2017/10/13.
 */

public class MyCircleLayout extends ViewGroup {

    private float averageAngle;
    private float radius;
    private float center_X,center_Y;


    public MyCircleLayout(Context context) {
        this(context,null);
    }
    public MyCircleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MyCircleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //完成对childview位置的指定  onLayout要与onMeasure配合使用 子视图需要在onMeasure中测量一下，才能在显示出内容
        final int countChild = getChildCount();
        averageAngle = 360/(countChild*1.0f);
        for(int i = 0 ; i < countChild;i++){
            CircleImageViews child = (CircleImageViews) getChildAt(i );
            int cWidth = DensityUtils.px2dip(getContext(),child.getMeasuredWidth());
            int cHeight = DensityUtils.px2dip(getContext(),child.getMeasuredHeight());

            float childAngle = (i+1)*averageAngle;
            radius = MyApplication.width/2;
            center_X = radius;
            center_Y = radius;
            int child_X = 0,child_Y = 0;
            if(childAngle >= 0 && childAngle < 90){
                child_X = (int) (center_X+radius*Math.cos(childAngle));
                child_Y = (int) (center_Y+radius*Math.sin(childAngle));
            }else if(childAngle >= 90 && childAngle < 180){
                childAngle = 180 - childAngle;
                child_X = (int) (center_X-radius*Math.cos(childAngle));
                child_Y = (int) (center_Y+radius*Math.sin(childAngle));
            }else if(childAngle >= 180 && childAngle < 270){
                childAngle = childAngle - 180;
                child_X = (int) (center_X-radius*Math.cos(childAngle));
                child_Y = (int) (center_Y-radius*Math.sin(childAngle));
            }else if(childAngle >= 270 && childAngle <= 360){
                childAngle =360 - childAngle;
                child_X = (int) (center_X+radius*Math.cos(childAngle));
                child_Y = (int) (center_Y-radius*Math.sin(childAngle));
            }
            child.layout(child_X-cWidth/2,child_Y-cHeight/2,child_X+cWidth/2,child_Y+cHeight/2);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        Log.i("--widthMeasureSpec---"+widthMeasureSpec,"---heightMeasureSpec---"+heightMeasureSpec);
        for (int i = 0; i < count; i++){
            // 先测量子布局的宽高，然后在layout中在获取宽高。
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);//measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //完成对自己宽高的指定
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("Touchs_","dispatchTouchEvent:"+ev.toString());
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("Touchs_","onInterceptTouchEvent_"+ev.toString());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("Touchs_","onTouchEvent_"+event.toString());
        return super.onTouchEvent(event);
    }

}

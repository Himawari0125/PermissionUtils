package com.himawari.permissionUtils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.DensityUtils;
import com.himawari.permissionUtils.utils.LogUtils;

/**
 * Created by S.Lee on 2018/1/8.
 */

public class PointView extends View {
    private Paint circle_Paint;
    private int circleRadius;
    private int circle_color_Current;
    private int circle_color_others;
    private int circle_interval;
    private int pointSize;
    private float startCircleX;
    private float circleY;
    private int currentIndex;

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.pointview);
        float interval = array.getFloat(R.styleable.pointview_pointInterval,15);
        float radius = array.getFloat(R.styleable.pointview_pointRadius,3);
        circle_interval = DensityUtils.dip2px(context,interval);
        circleRadius = DensityUtils.dip2px(context,radius);
        pointSize = array.getInteger(R.styleable.pointview_pointSize,4);

        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width,height;

        if(specWidthMode == MeasureSpec.EXACTLY){
            width = specWidth;
        }else{
            width = (int)MyApplication.width;
        }

        if(specHeightMode == MeasureSpec.EXACTLY){
            height = specHeight;
        }else{
            height = (int)MyApplication.height/10;
        }

        if(pointSize%2 == 0){//偶数
            startCircleX = MyApplication.width/2 - (pointSize/2-0.5f)*circle_interval;
        }else{//奇数
            startCircleX = MyApplication.width/2 - pointSize/2*circle_interval;
        }
        circleY = height/2;
        setMeasuredDimension(width,height);

        LogUtils.i("width:",width+" height:"+height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0 ; i < pointSize; i++){
            if(currentIndex == i)
                circle_Paint.setColor(circle_color_Current);
            else
                circle_Paint.setColor(circle_color_others);
            onDrawCircle(canvas,startCircleX+circle_interval*i,circleY,circleRadius,circle_Paint);
        }
    }

    private void onDrawCircle(Canvas canvas, float cx, float cy, float radius, Paint mPaint){
        canvas.drawCircle(cx,cy,radius,mPaint);
    }

    public void setIsCurrent(int currentIndex){
        this.currentIndex = currentIndex;
        invalidate();
    }

    private void init(){
        circle_color_Current = Color.parseColor("#333333");
        circle_color_others = Color.parseColor("#b4b4b4");

        circle_Paint = new Paint();
        circle_Paint.setTextAlign(Paint.Align.CENTER);
    }
}

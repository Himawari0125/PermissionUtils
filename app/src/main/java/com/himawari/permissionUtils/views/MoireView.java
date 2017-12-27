package com.himawari.permissionUtils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.DensityUtils;

/**
 * Created by S.Lee on 2017/12/21.
 */

public class MoireView extends View {
    private boolean isSolid;
    private int paintColor;
    private Paint circlePaint;
    private Context context;
    private int insideRadius;
    private int outsideRadius;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mDuration;
    private int mInitialRadius;
    private int centerX,centerY;
    private int startTime = 10;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int width;
        if(specMode == MeasureSpec.EXACTLY){
            width = specWidth;
        }else{
            width = (int)MyApplication.width;
        }
        outsideRadius = width/2;
        insideRadius = width/3;
        centerX = width/2;
        centerY = centerX;
        setMeasuredDimension(width,specHeight);
    }

    public MoireView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.moireview);
        isSolid = typedArray.getBoolean(R.styleable.moireview_isStroke,false);
        paintColor = typedArray.getColor(R.styleable.moireview_moirecolor,getResources().getColor(R.color.history_titleBarstart));
        mDuration = typedArray.getInt(R.styleable.moireview_duration,1011);
        typedArray.recycle();

        init();
    }
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if(startTime < mDuration){
            onDrawCirCle(canvas,centerX,centerY,getCurrentRadius(startTime,insideRadius), getInsideCurrentAlpha(startTime));
            onDrawCirCle(canvas,centerX,centerY,getCurrentRadius(startTime,outsideRadius), getOutsideCurrentAlpha(startTime));
            startTime += 10;
        }else{
            startTime = 10;
        }
        postInvalidateDelayed(10);
    }
    private void onDrawCirCle(Canvas canvas, float cx, float cy, float radius, int Alpha){
        circlePaint.setAlpha(Alpha);
        canvas.drawCircle(cx,cy,radius,circlePaint);
    }
    private void init(){
        circlePaint = new Paint();
        if(isSolid)circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(paintColor);
        mInitialRadius = DensityUtils.dip2px(context,10);
    }
    private float getCurrentRadius(float intervalTimes,int mMaxRadius){
        float percent = intervalTimes * 1.0f / mDuration;
        return mInitialRadius + mInterpolator.getInterpolation(percent) * (mMaxRadius - mInitialRadius);
    }
    private int getInsideCurrentAlpha(float intervalTimes){
        float percent = intervalTimes * 1.0f / mDuration;
        return (int) ((1.0f - mInterpolator.getInterpolation(percent)) * 255);
    }
    private int getOutsideCurrentAlpha(float intervalTimes){
        float percent = intervalTimes * 1.0f / mDuration;
        return (int) ((1.0f - mInterpolator.getInterpolation(percent)) * 200);
    }

}

package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.support.annotation.Nullable;

import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.himawari.a24hoursrecord.MyApplication;
import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.utils.DensityUtils;



/**
 * Created by S.Lee on 2017/11/22.
 */

public class DeleteViewGroup extends LinearLayout {
    private ViewDragHelper viewDragHelper;
    private int leftBound,newLeft;
    public DeleteViewGroup(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        addViews(context);
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                leftBound = DensityUtils.dip2px(context,-66);
                newLeft = (left > 0)?0:(left > leftBound)?left:leftBound;
                return newLeft;

            }
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return 0;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                Log.i("onViewReleased","xvel:"+xvel+" yvel:"+yvel);
                View child = getChildAt(0);
                if(releasedChild.getLeft() > DensityUtils.dip2px(context,-33))
                    getChildAt(0).layout(0,0, (int) MyApplication.width,DensityUtils.dip2px(context,66));
                else getChildAt(0).layout(DensityUtils.dip2px(context,-66),0,child.getRight()-(DensityUtils.dip2px(context,66)+child.getLeft()),DensityUtils.dip2px(context,66));
                invalidate();

            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }


    public void addViews(Context mcontext){
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_acc,null);
        addView(view);
    }
}

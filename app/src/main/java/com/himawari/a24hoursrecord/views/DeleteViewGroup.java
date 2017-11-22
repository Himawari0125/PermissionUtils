package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.utils.DensityUtils;


/**
 * Created by S.Lee on 2017/11/22.
 */

public class DeleteViewGroup extends LinearLayout {
    private ViewDragHelper viewDragHelper;
    public DeleteViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                final int leftBound = getPaddingLeft();
//                final int rightBound = getWidth() - child.getWidth() - leftBound;
//
//                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
//
//                return newLeft;


                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void addViews(Context mcontext){
        for(int i = 0 ; i < 3 ; i ++){
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            View view = inflater.inflate(R.layout.item_delete,null);
            addView(view);

        }
    }
}

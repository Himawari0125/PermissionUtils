package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.support.annotation.Nullable;

import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.himawari.a24hoursrecord.MyApplication;
import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.utils.DensityUtils;



/**
 * Created by S.Lee on 2017/11/22.
 */

public class AboveItemView extends LinearLayout {
    private ViewDragHelper viewDragHelper;
    private int leftBound,newLeft;
    private float xDistance, yDistance, xLast, yLast;
    private CheckBoxListener checkBxlistener;


    public void setCheckBoxListener(CheckBoxListener listener){
        this.checkBxlistener = listener;
    }


    public AboveItemView(final Context context, @Nullable AttributeSet attrs) {
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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = event.getX();
                yLast = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = event.getX();
                final float curY = event.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if(xDistance < yDistance){
                    return false;
                }
        }
        return true;
    }


    public void setCheckBoxAppear(boolean isAppear){
        View child = getChildAt(0);
        CheckBox checkBox = child.findViewById(R.id.checkBox);
        checkBox.setVisibility(isAppear?View.VISIBLE:View.INVISIBLE);
    }


    private void addViews(Context mcontext){
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_acc,null);
        addView(view);

        final CheckBox checkBox = view.findViewById(R.id.checkBox);

        checkBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBxlistener!=null)
                    checkBxlistener.isBoxChecked(checkBox.isChecked());
            }
        });

    }

    public void setIsBxChecked(boolean isChecked){
        View child = getChildAt(0);
        CheckBox checkBox = child.findViewById(R.id.checkBox);
        checkBox.setChecked(isChecked);
    }


    public interface CheckBoxListener{
        void isBoxChecked(boolean isBoxChecked);
    }
}

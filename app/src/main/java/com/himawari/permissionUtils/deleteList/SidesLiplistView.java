package com.himawari.permissionUtils.deleteList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by S.Lee on 2017/11/24.
 */

public class SidesLiplistView extends ListView {
    private float xDistance, yDistance, xLast, yLast;
    public SidesLiplistView(Context context) {
        super(context);
    }

    public SidesLiplistView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SidesLiplistView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if(xDistance > yDistance){
                    return false;
                }
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

}

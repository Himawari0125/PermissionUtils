package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by S.Lee on 2017/11/22.
 */

public class DeleteListView extends ListView {
    public DeleteListView(Context context) {
        super(context);
    }

    public DeleteListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeleteListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}

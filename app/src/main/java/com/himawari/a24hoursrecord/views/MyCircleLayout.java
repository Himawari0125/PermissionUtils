package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by S.Lee on 2017/10/13.
 */

public class MyCircleLayout extends ViewGroup {
    private int countChild;
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
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        countChild = getChildCount();
        Log.i("test","countChild = "+countChild);
        for(int i = 0 ; i < countChild; i++){
            View childImg = getChildAt(i);
            childImg.layout(i*10,i*10,i*10+100,i*10+100);
        }
    }
}

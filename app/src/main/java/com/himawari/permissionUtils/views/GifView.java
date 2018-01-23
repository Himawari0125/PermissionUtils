package com.himawari.permissionUtils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.LogUtils;

/**
 * Created by S.Lee on 2018/1/5.
 */

public class GifView extends View {
    private long movieStart = 0;
    private Movie mMovie;

    public GifView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.gitview);
        int gifsrc = array.getResourceId(R.styleable.gitview_gitsrc,-1);
        if(gifsrc == -1)return;
        mMovie = Movie.decodeStream(getResources().openRawResource(
                gifsrc));
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    }

    public void setGifSource(int gifSource){
        mMovie = Movie.decodeStream(getResources().openRawResource(
                gifSource));
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mMovie.width(),mMovie.height());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mMovie == null)return;
        long now = android.os.SystemClock.uptimeMillis();
        if(movieStart == 0){
            movieStart = now;
        }
        int movieTime = (int)(now - movieStart)%mMovie.duration();
        LogUtils.i("movieTime",movieTime+"");
        mMovie.setTime(movieTime);
        mMovie.draw(canvas,0,0);
        invalidate();
    }



}

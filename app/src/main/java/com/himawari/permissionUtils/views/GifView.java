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

    private boolean isPlaying = false;

    private int repeatNumber = -1;

    private int gitResource;


    public GifView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.gitview);
//        int gifsrc = array.getResourceId(R.styleable.gitview_gitsrc,-1);
//        if(gifsrc == -1)return;
//        mMovie = Movie.decodeStream(getResources().openRawResource(
//                gifsrc));
//        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    }

    public void setGifSource(int gifSource){
        this.gitResource = gifSource;
        mMovie = Movie.decodeStream(getResources().openRawResource(gitResource));
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
        int movieTime = (int)(now - movieStart);

        if(repeatNumber < 0){
            movieTime = movieTime%mMovie.duration();
            playGif(canvas,movieTime);
        }else{
            if(movieTime < mMovie.duration()*repeatNumber){
                movieTime = movieTime%mMovie.duration();
            }else{
                movieTime = mMovie.duration();
            }
            playGif(canvas,movieTime);
        }

    }

    /**
     * 播放gif方法
     */
    private void playGif(Canvas canvas,int moviewtime){
        LogUtils.i(" resource"+gitResource+" "+moviewtime+"");
        mMovie.setTime(moviewtime);
        mMovie.draw(canvas,0,0);
        if(isPlaying)invalidate();

    }
    /**
     * 设置动画播放
     * @param isplay
     */
    public void setPlaying(boolean isplay){
        this.isPlaying = isplay;
        invalidate();
    }

    /**
     * 设置循环播放次数
     */
    public void setCirculate(int repeatNumbers){
        this.repeatNumber = repeatNumbers;
        movieStart = 0;
    }


    public void setNull(){
        mMovie = null;
    }


}

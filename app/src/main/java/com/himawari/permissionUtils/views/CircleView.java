package com.himawari.permissionUtils.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;

/**
 * Created by S.Lee on 2017/9/5 0005.
 */

public class CircleView extends View {
    private Context context;

    private Paint bgCircle_paint;//底圆画笔
    private int bgCircle_color;//底圆颜色
    private float padding_percent = 0.264f;//底圆左右间隔距离
    private int margin_top = 100;
    private float bitmap_percent = 0.042f;//透明部分补充

    private Paint arcCircle_paint;//圆弧画笔
    private int arcCircle_width;//圆弧宽度
    private int arcCircle_color;//圆弧颜色
    private float arc_padding_percent = 0.264f;//圆弧左右间隔距离
    private int arc_margin_top = 100;

    private float percent_Arc = 0.776f;


    public CircleView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bgCircleBitmap(canvas);
        arcCircle(canvas);
    }

    /**
     *底圆
     */
    private void bgCircle(Canvas canvas){
        RectF rf1 = new RectF(MyApplication.width * padding_percent ,
                margin_top, MyApplication.width * (1-padding_percent),
                MyApplication.width * (1-padding_percent*2) +margin_top);
        canvas.drawOval(rf1, bgCircle_paint);
    }

    private void bgCircleBitmap(Canvas canvas){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.main_backgroud_yuanhuan_blue);

        RectF rf1 = new RectF(MyApplication.width * (padding_percent - bitmap_percent),
                margin_top-bitmap_percent* MyApplication.width, MyApplication.width * (1-padding_percent+bitmap_percent),
                MyApplication.width * (1-padding_percent*2+bitmap_percent) +margin_top);
        Log.i("width:", MyApplication.width +"");

        canvas.drawBitmap(bitmap,null,rf1,null);
    }


    /**
     * 圆弧
     */
    private void arcCircle(Canvas canvas){
        RectF rf1 = new RectF(MyApplication.width * arc_padding_percent + arcCircle_width/2,
                margin_top+arcCircle_width/2, MyApplication.width * (1-arc_padding_percent)-arcCircle_width/2,
                MyApplication.width * (1-arc_padding_percent*2) +arc_margin_top-arcCircle_width/2);
       canvas.drawArc(rf1,360*(1-percent_Arc)/2+90,360*percent_Arc,false,arcCircle_paint);

    }
    private void init(){
        //底圆画笔init
        bgCircle_color = Color.BLACK;
        bgCircle_paint = new Paint();
        bgCircle_paint.setColor(bgCircle_color);

        //圆弧画笔
        arcCircle_width = 10;
        arcCircle_color = Color.WHITE;
        arcCircle_paint = new Paint();
        arcCircle_paint.setColor(arcCircle_color);
        arcCircle_paint.setStrokeWidth(arcCircle_width);
        arcCircle_paint.setStyle(Paint.Style.STROKE);



    }
}

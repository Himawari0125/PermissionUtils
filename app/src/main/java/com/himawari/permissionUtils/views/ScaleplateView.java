package com.himawari.permissionUtils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.DensityUtils;
import com.himawari.permissionUtils.utils.LogUtils;

/**
 * Created by S.Lee on 2017/12/27.
 */

public class ScaleplateView extends View{
    private String[] statusStrs;
    private String[] statusInterval;


    private Paint statusPaint;
    private Paint intervalPaint;
    private Paint detailPaint;

    private int width;
    private int height;

    private int scalePlateWidth;
    private int scalePlateHeight;
    private Context context;

    private int intervalMarginTop = 5;
    private int statusIntervalX;
    private int intervalX;
    private float pointWeight;
    private int bodyType;
    public ScaleplateView(Context context, @Nullable AttributeSet attrs) throws Exception {
        super(context, attrs);
        setBackground(getResources().getDrawable(R.mipmap.detailbg));
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.scaleview);
        String statusString = typedArray.getString(R.styleable.scaleview_statuStrs);
        if(!statusString.contains(","))throw new Exception("StatusStr must split by ','");
        statusStrs =  statusString.split(",");

        String statusIntervalString = typedArray.getString(R.styleable.scaleview_statusInterval);
        if(!statusIntervalString.contains(","))throw new Exception("statusInterval must split by ','");
        statusInterval =  statusIntervalString.split(",");

        this.context = context;

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawScalePlate(canvas,height*2/5);
        if(scalePlateWidth == 0||scalePlateHeight == 0)return;
        onDrawStatusStrs(canvas,statusStrs,height*2/5,statusPaint);
        onDrawIntervalStrs(canvas,statusInterval,height*2/5+scalePlateHeight+DensityUtils.dip2px(context,intervalMarginTop),intervalPaint);
        if(pointWeight <=0 )return;
        onDrawTypePoint(canvas,bodyType,height*2/5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);

        if(specMode == MeasureSpec.EXACTLY){
            width = specWidth;
        }else{
            width = (int) MyApplication.width;
        }
        height = width/4;
        setMeasuredDimension(width,height);
        init();
    }

    private void onDrawStatusStrs(Canvas canvas,String[] strs,int y,Paint paint){
        statusIntervalX = (int) Math.ceil (scalePlateWidth*29/30f/strs.length);
        int StartX = (width - scalePlateWidth)/2+statusIntervalX/2+scalePlateWidth/60;
        for(int i = 0 ;i < strs.length;i++){
            canvas.drawText(strs[i],StartX+statusIntervalX*i ,y,paint);
        }
    }

    private void onDrawIntervalStrs(Canvas canvas,String[] strs,int y,Paint paint){
        intervalX  = (int) Math.ceil  (scalePlateWidth*29/30f/(strs.length+1));
        int StartX = (width - scalePlateWidth)/2+scalePlateWidth/60+intervalX;
        for(int i = 0 ;i < strs.length;i++){
            canvas.drawText(strs[i],StartX+intervalX*i,y,paint);
        }
    }

    private void onDrawTypePoint(Canvas canvas,int type,float top){
        Bitmap bitmap = null;
        switch (type){
            case 1:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status_point1);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status_point2);
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status_point3);
                break;
            case 4:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status_point4);
                break;
            case 5:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status_point5);
                break;
            case 6:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status_point6);
                break;
//            case 7:
//                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status_point7);
//                break;
        }
        if(bitmap!=null){
            canvas.drawBitmap(bitmap,(width - scalePlateWidth)/2+scalePlateWidth/60f+statusIntervalX*pointWeight - bitmap.getWidth()/2f,top,null);
        }

    }
    private void onDrawScalePlate(Canvas canvas,int top){
        Bitmap bitmap = null;
        switch (statusStrs.length){
            case 1://身体年龄
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status1);
                break;
            case 2://骨量
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status2);
                break;
            case 3://肌肉重量
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status3);
                break;
            case 4://体脂率
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status4);
                break;
            case 5:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status5);
                break;
            case 6://BMI
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.status6);
                break;
            default:
                LogUtils.e("ScalePlate:"+statusStrs.length);
                break;
        }
        if(bitmap!=null){
            canvas.drawBitmap(bitmap,(width - bitmap.getWidth())/2,top,null);
            scalePlateWidth = bitmap.getWidth();
            scalePlateHeight = bitmap.getHeight();
        }
    }
    private void init(){
        int statusColor = Color.parseColor("#3c4047");
        int intervalColor = Color.parseColor("#393c40");
        int detailColor = Color.parseColor("#677991");
        statusPaint = new Paint();
        intervalPaint = new Paint();
        detailPaint = new Paint();
        statusPaint.setColor(statusColor);
        intervalPaint.setColor(intervalColor);
        detailPaint.setColor(detailColor);
        statusPaint.setAntiAlias(true);
        intervalPaint.setAntiAlias(true);
        detailPaint.setAntiAlias(true);
        statusPaint.setTextAlign(Paint.Align.CENTER);
        intervalPaint.setTextAlign(Paint.Align.CENTER);
        detailPaint.setTextAlign(Paint.Align.CENTER);
        statusPaint.setTextSize(DensityUtils.dip2px(context,14));
        intervalPaint.setTextSize(DensityUtils.dip2px(context,14));
        detailPaint.setTextSize(DensityUtils.dip2px(context,14));
    }

    /**
     * @param status type合集
     * @param intervals 标尺合集
     * @param type 当前身体类型
     * @param weight 身体类型位置在整条Scaleplate上占的比重
     */
    public void setBodyTypeAndWeight(String[] status,String[] intervals,int type,float weight){
        this.bodyType = type;
        this.pointWeight = weight;
        invalidate();
    }
}

package com.himawari.permissionUtils.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.TrendBean;
import com.himawari.permissionUtils.utils.DensityUtils;

import java.util.List;

/**
 * Created by S.Lee on 2017/12/18.
 */

public class TrendView extends View implements View.OnTouchListener{

    private float averageWidth;
    private float averageHeight;
    private float startX;
    private float width;
    private float height;
    private List<TrendBean> datas;

    private Paint horizonalLinePaint;
    private Paint dootedVerLinePaint;
    private Paint DrawPicPaint;
    private Paint textPressedPaint;
    private Paint textDatePaint;
    private Paint trendLinePaint;

    private int horizonalLinesize;

    private int verticalColumnCount = 5;
    private float heightScaleWidth = 2/3.0f;

    private Context context;

    private float maxWeight = 0 ,minWeight = 0,maxFat = 0,minFat = 0,maxMuscle = 0,minMuscle = 0;

    private float downX = 0,downY = 0;

    private int intervalWeight,intervalFat,intervalMuscle;

    private boolean isScrolling;

    private int splitSpaceCount = 5;


    public enum TrendType{
        Weight,Fat,Muscle
    }

    private TrendType trendType;

    public void setTrendType(TrendType type){
        this.trendType = type;
        invalidate();
    }

    public TrendView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TrendView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void setDatas(List<TrendBean> beans){
        this.datas = beans;
        isScrolling = (datas.size() > splitSpaceCount)?true:false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);

        if(datas == null || datas.size() == 0)return;
        if(specWidthMode == MeasureSpec.EXACTLY){
            width = specWidthSize;
        }else{
            width = MyApplication.width ;//- getPaddingLeft() - getPaddingRight();
        }
        if(isScrolling){
            int size = datas.size();
            averageWidth = width/splitSpaceCount;
            height = width*heightScaleWidth;
            averageHeight = height/verticalColumnCount;
            startX = averageWidth/2+getPaddingLeft();
            width = averageWidth*size+getPaddingLeft()+getPaddingRight();
            setMeasuredDimension((int)width,(int)height);
        }else{
            averageWidth = width/ splitSpaceCount;
            height = width*heightScaleWidth;
            averageHeight = height/verticalColumnCount;
            startX = averageWidth/2;
            setMeasuredDimension((int)width,(int)height);
        }
        calculateIntervals();
        setTrendNodes();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 1 ; i < verticalColumnCount ; i++){//绘制横轴四条线
            int muliple = isScrolling?datas.size():5;
            onDrawHorizonalLine(canvas,0,averageHeight*i,(int)averageWidth*muliple,averageHeight*i);
        }

        for(int j = 0 ; j < datas.size();j++){//绘制日期
            onDrawDateText(canvas,datas.get(j).getScaleDate(),startX+averageWidth*j,height - averageHeight/2);
        }

        switch (trendType){
            case Weight:
                /**
                 * 绘制体重折线
                 */
                for(int i = 0;i < datas.size();i++){
                    TrendBean bean = datas.get(i);
                    boolean isPressed = bean.isWeightPressed();
                    if(isPressed){
                        onDrawVerticalDottedLine(canvas,bean.getPositionWeightX(),averageHeight*0.9f,averageHeight*(verticalColumnCount - 0.8f));
                    }
                    if(!bean.getIsLastNode()){
                        TrendBean bean1 = datas.get(i+1);
                        onDrawTrendline(canvas,bean.getPositionWeightX(),bean.getPositionWeightY(),
                                bean1.getPositionWeightX(),bean1.getPositionWeightY(),
                                bean.getIsLastNode(),isPressed);
                    }else{
                        onDrawTrendline(canvas,bean.getPositionWeightX(),bean.getPositionWeightY(),
                                0,0,
                                bean.getIsLastNode(),isPressed);
                    }
                    if(isPressed){
                        onDrawPressData(canvas,bean.getWeight()+getResources().getString(R.string.kilogram),bean.getPositionWeightX(),bean.getPositionWeightY(),0,0);
                    }
                }
                break;
            case Fat:
                /**
                 * 绘制体脂折线
                 */
                for(int i = 0;i < datas.size();i++){
                    TrendBean bean = datas.get(i);
                    boolean isPressed = bean.isFatPressed();
                    if(isPressed){
                        onDrawVerticalDottedLine(canvas,bean.getPositionFatX(),averageHeight*0.9f,averageHeight*(verticalColumnCount - 0.8f));
                    }
                    if(!bean.getIsLastNode()){
                        TrendBean bean1 = datas.get(i+1);
                        onDrawTrendline(canvas,bean.getPositionFatX(),bean.getPositionFatY(),
                                bean1.getPositionFatX(),bean1.getPositionFatY(),
                                bean.getIsLastNode(),isPressed);
                    }else{
                        onDrawTrendline(canvas,bean.getPositionFatX(),bean.getPositionFatY(),
                                0,0,
                                bean.getIsLastNode(),isPressed);
                    }
                    if(isPressed){
                        onDrawPressData(canvas,bean.getFat()+getResources().getString(R.string.kilogram),bean.getPositionFatX(),bean.getPositionFatY(),0,0);
                    }
                }
                break;
            case Muscle:
                /**
                 * 绘制肌肉折线
                 */
                for(int i = 0;i < datas.size();i++){
                    TrendBean bean = datas.get(i);
                    boolean isPressed = bean.isMusclePressed();
                    if(isPressed){
                        onDrawVerticalDottedLine(canvas,bean.getPositionMuscleX(),averageHeight*0.9f,averageHeight*(verticalColumnCount - 0.8f));
                    }
                    if(!bean.getIsLastNode()){
                        TrendBean bean1 = datas.get(i+1);
                        onDrawTrendline(canvas,bean.getPositionMuscleX(),bean.getPositionMuscleY(),
                                bean1.getPositionMuscleX(),bean1.getPositionMuscleY(),
                                bean.getIsLastNode(),isPressed);
                    }else{
                        onDrawTrendline(canvas,bean.getPositionMuscleX(),bean.getPositionMuscleY(),
                                0,0,
                                bean.getIsLastNode(),isPressed);
                    }
                    if(isPressed){
                        onDrawPressData(canvas,bean.getMuscle()+getResources().getString(R.string.kilogram),bean.getPositionMuscleX(),bean.getPositionMuscleY(),0,0);
                    }
                }
                break;
        }
    }

    /**
     * 画横线条
     */
    private void onDrawHorizonalLine(Canvas canvas,float startX, float startY, float stopX, float stopY ){
        canvas.drawLine(startX,startY,stopX,stopY,horizonalLinePaint);

    }

    /**
     * 画竖虚线条
     */
    private void onDrawVerticalDottedLine(Canvas canvas,float startX,float startY,float stopY){
        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(startX, stopY);
        canvas.drawPath(path,dootedVerLinePaint);
    }

    /**
     * 画日期
     */
    private void onDrawDateText(Canvas canvas,String dateStr,float x,float y){
        canvas.drawText(dateStr,x ,y,textDatePaint);
    }

    /**
     * 绘制选中point hint
     */
    private void onDrawPressData(Canvas canvas,String textStr,float x,float y,float radiusX,float radiusY){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.tizhong_beijing_liangweishu);
        canvas.drawBitmap(bitmap,x - bitmap.getWidth()/2,y - bitmap.getHeight(),DrawPicPaint);
        canvas.drawText(textStr,x,y - bitmap.getHeight()/2,textPressedPaint);
    }

    /**
     * 绘制折线
     */
    private void onDrawTrendline(Canvas canvas,float startX,float startY,float stopX,float stopY,boolean isLast,boolean isPressed){
        if(!isLast){
            canvas.drawLine(startX,startY,stopX,stopY,trendLinePaint);
        }
        Bitmap bitmap;

        if(isPressed)
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.jiedian_xuanzhong);
        else
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.jiedian_weixuanzhong);
        canvas.drawBitmap(bitmap,startX-bitmap.getWidth()/2,startY-bitmap.getHeight()/2,DrawPicPaint);
    }

    private void init(){

        setOnTouchListener(this);

        int horizonalLineColor = Color.parseColor("#77ffffff");
        int dootedVerLineColor = Color.parseColor("#ffffff");
        int textPressedColor = Color.parseColor("#3aa4ff");
        int textDateColor = Color.parseColor("#ffffff");
        int trendLineColor = Color.parseColor("#ffffff");

        horizonalLinePaint = new Paint();
        dootedVerLinePaint = new Paint();
        textPressedPaint = new Paint();
        textDatePaint = new Paint();
        trendLinePaint = new Paint();

        DrawPicPaint = new Paint();

        horizonalLinePaint.setColor(horizonalLineColor);
        dootedVerLinePaint.setColor(dootedVerLineColor);
        textPressedPaint.setColor(textPressedColor);
        textDatePaint.setColor(textDateColor);
        trendLinePaint.setColor(trendLineColor);

        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{10,10},1);
        dootedVerLinePaint.setPathEffect(dashPathEffect);
        dootedVerLinePaint.setStyle(Paint.Style.STROKE);

        horizonalLinesize = DensityUtils.dip2px(context,0.5f);
        horizonalLinePaint.setTextSize(horizonalLinesize);
        textDatePaint.setTextSize(DensityUtils.dip2px(context,14f));
        trendLinePaint.setStrokeWidth(DensityUtils.dip2px(context,2));
        textPressedPaint.setTextSize(DensityUtils.dip2px(context,10f));

        textDatePaint.setTextAlign(Paint.Align.CENTER);
        textPressedPaint.setTextAlign(Paint.Align.CENTER);

        trendLinePaint.setAntiAlias(true);
    }

    private void calculateIntervals(){
        //初始化min
        TrendBean beanInit = datas.get(0);
        minWeight = beanInit.getWeight();
        minFat = beanInit.getFat();
        minMuscle = beanInit.getMuscle();

        for(int i = 0 ; i < datas.size() ;i++){
            TrendBean bean = datas.get(i);
            maxWeight = Math.max(maxWeight,bean.getWeight());
            minWeight = Math.min(minWeight,bean.getWeight());

            maxFat = Math.max(maxFat,bean.getFat());
            minFat = Math.min(minFat,bean.getFat());

            maxMuscle = Math.max(maxMuscle,bean.getMuscle());
            minMuscle = Math.min(minMuscle,bean.getMuscle());
        }
        maxWeight = (int)Math.floor(maxWeight);
        minWeight = Math.round(minWeight);
        intervalWeight = (int)(maxWeight - minWeight);

        maxFat = (int)Math.floor(maxFat);
        minFat = Math.round(minFat);
        intervalFat = (int)(maxFat - minFat);

        maxMuscle = (int)Math.floor(maxMuscle);
        minMuscle = Math.round(minMuscle);
        intervalMuscle = (int)(maxMuscle - minMuscle);

    }

    private void setTrendNodes(){
        for(int n = 0; n < datas.size();n++){
            TrendBean bean = datas.get(n);

            boolean isLast;
            if(n < datas.size() -1)isLast = false;
            else isLast = true;

            float weight = bean.getWeight();
            float heights;
            if(weight > maxWeight)
                heights = (verticalColumnCount - 3 - (weight - maxWeight)/(intervalWeight*1.0f))*averageHeight;
            else if(weight < minWeight)
                heights = ((minWeight - weight)/(intervalWeight*1.0f)+verticalColumnCount-2)*averageHeight;
            else
                heights = ((maxWeight - weight)/(intervalWeight*1.0f)+verticalColumnCount-3)*averageHeight;


            float fat = bean.getFat();
            float heights_fat;
            if(fat > maxFat)
                heights_fat = (verticalColumnCount - 3  - (fat - maxFat)/(intervalFat*1.0f))*averageHeight;
            else if(fat < minFat)
                heights_fat = ((minFat - fat)/(intervalFat*1.0f)+verticalColumnCount - 2)*averageHeight;
            else
                heights_fat = ((maxFat - fat)/(intervalFat*1.0f)+verticalColumnCount - 3 )*averageHeight;

            float muscle = bean.getMuscle();
            float heights_muscle;
            if(muscle > maxMuscle)
                heights_muscle = (verticalColumnCount - 3  - (muscle - maxMuscle)/(intervalMuscle*1.0f))*averageHeight;
            else if(muscle < minMuscle)
                heights_muscle = ((minMuscle - muscle)/(intervalMuscle*1.0f)+verticalColumnCount - 2)*averageHeight;
            else
                heights_muscle = ((maxMuscle - muscle)/(intervalMuscle*1.0f)+verticalColumnCount - 3)*averageHeight;

            datas.get(n).setPositionWeightX(startX+averageWidth*n,heights);
            datas.get(n).setPositionFatX(startX+averageWidth*n,heights_fat);
            datas.get(n).setPositionMuscleX(startX+averageWidth*n,heights_muscle);
            datas.get(n).setIsLastNode(isLast);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int range = DensityUtils.dip2px(context,10);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("----ACTION_DOWN-----","---------");
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("----ACTION_MOVE-----","---------");

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                Log.i("----ACTION_UP-----","---------");
                if(downX == event.getX() && downY == event.getY()){
                    boolean isNotChoosed = true;//间距比较小时，确保只press一个point
                    for(int i = 0 ; i < datas.size() ;i++){
                        TrendBean bean = datas.get(i);
                        switch (trendType){
                            case Weight:
                                if(isNotChoosed&&Math.abs(bean.getPositionWeightX() - event.getX()) < range && Math.abs(bean.getPositionWeightY() - event.getY()) < range){
                                    datas.get(i).setIsWeightPressed(true);
                                    isNotChoosed = false;
                                }else{
                                    datas.get(i).setIsWeightPressed(false);
                                }
                                break;
                            case Fat:
                                if(isNotChoosed&&Math.abs(bean.getPositionFatX() - event.getX()) < range && Math.abs(bean.getPositionFatY() - event.getY()) < range){
                                    datas.get(i).setFatPressed(true);
                                    isNotChoosed = false;
                                }else{
                                    datas.get(i).setFatPressed(false);
                                }
                                break;
                            case Muscle:
                                if(isNotChoosed&&Math.abs(bean.getPositionMuscleX() - event.getX()) < range && Math.abs(bean.getPositionMuscleY() - event.getY()) < range){
                                    datas.get(i).setMusclePressed(true);
                                    isNotChoosed = false;
                                }else{
                                    datas.get(i).setMusclePressed(false);
                                }
                                break;
                        }
                    }
                    invalidate();
                }
                break;
        }
        return true;
    }
}

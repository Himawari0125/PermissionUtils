package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.himawari.a24hoursrecord.MyApplication;
import com.himawari.a24hoursrecord.utils.DensityUtils;

/**
 * Created by S.Lee on 2017/10/13.
 */

public class MyCircleLayout extends ViewGroup {

    private double averageAngle;
    private double radius;
    private double center_X,center_Y;
    private int childCount;
    private double[] childAngles;
    private double[] nextAngles;

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            childLayout(13);
        }
    };


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
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //完成对childview位置的指定  onLayout要与onMeasure配合使用 子视图需要在onMeasure中测量一下，才能在显示出内容
        childCount = getChildCount();
        averageAngle = 360/(childCount*1.0f);
        childAngles = new double[childCount];
        nextAngles = new double[childCount];
        //初始化childLayout
        childLayout(0);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        Log.i("--widthMeasureSpec---"+widthMeasureSpec,"---heightMeasureSpec---"+heightMeasureSpec);
        for (int i = 0; i < count; i++){
            // 先测量子布局的宽高，然后在layout中在获取宽高。
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);//measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //完成对自己宽高的指定
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("Touchs_","onTouchEvent_"+event.toString());
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("Touchs_","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("Touchs_","ACTION_MOVE");
                mhandler.sendEmptyMessage(1);
                break;
            case MotionEvent.ACTION_UP:
                Log.i("Touchs_","ACTION_UP");

                break;

        }
        return true;
    }

    private void childLayout(int speed){
        for(int i = 0 ; i < childCount;i++){
            CircleImageViews child = (CircleImageViews) getChildAt(i );
            int cWidth = DensityUtils.px2dip(getContext(),child.getMeasuredWidth());
            int cHeight = DensityUtils.px2dip(getContext(),child.getMeasuredHeight());

            double childAngle = childAngles[i];
            if(speed == 0){
                childAngle = (i*averageAngle+90)%360;
                if(i < childCount-1 )
                    nextAngles[i] = ((i+1)*averageAngle+90);
                else
                    nextAngles[i] = 450;
            }else{
                double degress = averageAngle / speed;
                double nextAngle = nextAngles[i];
                Log.i("test","childAngle:"+childAngle);
                childAngle = (childAngle<90)?(childAngle+360):childAngle;

                for(int j = 0 ; j < childCount;j++){
                    if((nextAngle = nextAngles[j])>childAngle)
                        break;
                }
                childAngle = ((nextAngle - childAngle)/degress <= 1 )? nextAngle%360:(childAngle+degress)%360;
                Log.i("test","childAngle:"+childAngle+" nextAngle:"+nextAngle);
            }
            childAngles[i] = childAngle;
            radius = MyApplication.width/2;
            center_X = radius;
            center_Y = radius;
            radius = radius - Math.max(cWidth/2,cHeight/2);
            int child_X = 0,child_Y = 0;
            Log.i("childLayout","childangle:"+childAngle);
            if(childAngle > 0 && childAngle < 90){
                child_X = (int) (center_X+radius*Math.cos(Math.toRadians(childAngle)));
                child_Y = (int) (center_Y+radius*Math.sin(Math.toRadians(childAngle)));
            }else if(childAngle > 90 && childAngle < 180){
                childAngle = 180 - childAngle;
                child_X = (int) (center_X-radius*Math.cos(Math.toRadians(childAngle)));
                child_Y = (int) (center_Y+radius*Math.sin(Math.toRadians(childAngle)));
            }else if(childAngle > 180 && childAngle < 270){
                childAngle = childAngle - 180;
                child_X = (int) (center_X-radius*Math.cos(Math.toRadians(childAngle)));
                child_Y = (int) (center_Y-radius*Math.sin(Math.toRadians(childAngle)));
            }else if(childAngle > 270 && childAngle < 360){
                childAngle =360 - childAngle;
                child_X = (int) (center_X+radius*Math.cos(Math.toRadians(childAngle)));
                child_Y = (int) (center_Y-radius*Math.sin(Math.toRadians(childAngle)));
            }else {
                int temp = (int) (childAngle / 90);
                switch (temp){
                    case 0:
                        child_X = (int) (center_X+radius);
                        child_Y = (int) center_Y;
                        break;
                     case 1:
                         child_X = (int)center_X;
                         child_Y = (int) (center_Y+radius);
                    break;
                     case 2:
                         child_X = (int) (center_X-radius);
                         child_Y = (int) center_Y;
                    break;
                     case 3:
                         child_X = (int) center_X;
                         child_Y = (int) (center_Y-radius);
                    break;

                }

            }
            child.layout(child_X-cWidth/2,child_Y-cHeight/2,child_X+cWidth/2,child_Y+cHeight/2);
            Log.i("childLayout","childangle:"+childAngle+" left:"+(child_X-cWidth/2)+" top:"+(child_Y-cHeight/2)+" right:"+(child_X+cWidth/2)+" bottom:"+(child_Y+cHeight/2));
        }
    }

}

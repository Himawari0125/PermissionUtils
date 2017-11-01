package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
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
    private int cWidth;
    private int cHeight;

    private VelocityTracker velocityTracker;

    private boolean isAntiClock = false;
    private float startX,startY;

    private GestureDetector gestureDetector;

    private static final int CLOCKWISE = 1000;
    private static final int ANTICLOCKWISE = 1001;


    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1){
                case CLOCKWISE:
                    childLayoutClock(13);
                    break;
                case ANTICLOCKWISE:
                    childLayoutAntiClock(13);
                    break;
            }


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

        if(childCount > 0){
            CircleImageViews child = (CircleImageViews) getChildAt(0);
            cWidth = DensityUtils.px2dip(getContext(),child.getMeasuredWidth());
            cHeight = DensityUtils.px2dip(getContext(),child.getMeasuredHeight());
            drawInitChildLayout();
        }



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
        float currentX,currentY;
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("Touchs_","ACTION_DOWN");
                startX = event.getX();
                startY = event.getY();
                if(velocityTracker == null){
                    velocityTracker = VelocityTracker.obtain();
                }else{
                    velocityTracker.clear();
                }
                velocityTracker.addMovement(event);
                Log.i("xy","x:"+startX+" y:"+startY);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("Touchs_","ACTION_MOVE");
                currentX = event.getX();
                currentY = event.getY();
                isAntiClock = isAntiClock(startX,startY,currentX,currentY);
                startX = currentX;
                startY = currentY;
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                int xSpeed = (int)Math.abs(velocityTracker.getXVelocity());
                int ySpeed = (int)Math.abs(velocityTracker.getYVelocity());
                Message msg = new Message();
                if(isAntiClock){
                    msg.arg1 = ANTICLOCKWISE;
                    mhandler.sendMessage(msg);
                }else{
                    msg.arg1 = CLOCKWISE;
                    mhandler.sendMessage(msg);
                }
                Log.i("speed","xspeed:"+xSpeed+" yspeed:"+ySpeed);
                break;
            case MotionEvent.ACTION_UP:
                Log.i("Touchs_","ACTION_UP");
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                int mxSpeed = (int)Math.abs(velocityTracker.getXVelocity());
                int mySpeed = (int)Math.abs(velocityTracker.getYVelocity());
                Log.i("speed","mxspeed:"+mxSpeed+" myspeed:"+mySpeed);
                if(velocityTracker!=null){
                    velocityTracker.recycle();
                    velocityTracker = null;
                }
                break;
        }
       // gestureDetector.onTouchEvent(event);
        return true;
    }


    private void childLayoutClock(int speed){
        for(int i = 0 ; i < childCount;i++){
            CircleImageViews child = (CircleImageViews) getChildAt(i );
            double childAngle = childAngles[i];
            setNextAngleArray();
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

            childLayout(i,childAngle,child);

        }
    }

    private void childLayoutAntiClock(int speed){
        for(int i = 0 ; i < childCount;i++){
            CircleImageViews child = (CircleImageViews) getChildAt(i );

            double childAngle = childAngles[i];
            setNextAngleArray();
            double degress = averageAngle / speed;
            double nextAngle = nextAngles[i];
            Log.i("test","childAngle:"+childAngle);
            childAngle = (childAngle<90)?(childAngle+360):childAngle;

            for(int j = 0 ; j < childCount;j++){
                if((nextAngle = nextAngles[j])<childAngle)
                    break;
            }
            childAngle = ((childAngle - nextAngle)/degress <= 1 )? nextAngle%360:(childAngle-degress)%360;
            Log.i("test","childAngle:"+childAngle+" nextAngle:"+nextAngle);

            childLayout(i,childAngle,child);
        }
    }


    /**
     * 设置childview位置
     * @param i
     * @param childAngle
     * @param child
     */
    private void childLayout(int i,double childAngle,CircleImageViews child){
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

    private boolean isAntiClock(float startX, float startY, float currentX, float currentY) {
        boolean isAntiClock = false;
        //还需要处理在轴上的情况
        int quadrant = (startX > center_X)?((startY>center_Y)?2:1):((startY>center_Y)?3:4);
        switch (quadrant){
            case 1:
                if((currentX -startX) > 0 && (currentY - startY) > 0){
                    isAntiClock = false;
                }else if((currentX -startX) < 0 && (currentY - startY) < 0){
                    isAntiClock = true;
                }else{
                    Log.e(getLineInfo(),"Result is not expected.");
                }
                break;
            case 2:
                if((currentX -startX) < 0 && (currentY - startY) > 0){
                    isAntiClock = false;
                }else if((currentX -startX) > 0 && (currentY - startY) < 0){
                    isAntiClock = true;
                }else{
                    Log.e(getLineInfo(),"Result is not expected.");
                }
                break;
            case 3:
                if((currentX -startX) < 0 && (currentY - startY) < 0){
                    isAntiClock = false;
                }else if((currentX -startX) > 0 && (currentY - startY) > 0){
                    isAntiClock = true;
                }else{
                    Log.e(getLineInfo(),"Result is not expected.");
                }
                break;
            case 4:
                if((currentX -startX) > 0 && (currentY - startY) < 0){
                    isAntiClock = false;
                }else if((currentX -startX) < 0 && (currentY - startY) > 0){
                    isAntiClock = true;
                }else{
                    Log.e(getLineInfo(),"Result is not expected.");
                }
                break;
            default:
                Log.e(getLineInfo(),"The quadrant result is not expected.");
                break;
        }

        return isAntiClock;
    }

    private static String getLineInfo()
    {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }

    private void drawInitChildLayout(){
        for(int i = 0; i < childCount ; i++){
            double childAngle = (i*averageAngle+90)%360;
            childLayout(i,childAngle,(CircleImageViews) getChildAt(i));
            childAngles[i] = childAngle;
        }
    }

    private void setNextAngleArray(){
        for(int i = 0 ; i < childCount;i ++){
            double childAngle = childAngles[i];
            if(isAntiClock){
                if((childAngle - averageAngle) >= 0)
                    nextAngles[i] = (childAngle - averageAngle)%360;
                else
                    nextAngles[i] = (360+childAngle-averageAngle)%360;
            }else{
                if(i < childCount-1 )
                    nextAngles[i] = ((i+1)*averageAngle+90);
                else
                    nextAngles[i] = 450;
            }
        }

    }

}

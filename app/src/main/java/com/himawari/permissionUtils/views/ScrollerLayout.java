package com.himawari.permissionUtils.views;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.utils.LogUtils;


/**
 * Created by S.Lee on 2017/12/20.
 *
 * 配合TrendView使用
 */

public class ScrollerLayout extends ViewGroup {
    private Scroller mScroller;
    private int mTouchSlop;
    private float mXDown;
    private float mXMove;
    private float mXLastMove;
    private float width;
    private int leftBorder;
    private int rightBorder;

    private float heightScaleWidth = 2/3.0f;
    private int splitSpaceCount = 7;
    private final int STANDARDCOUNT = 7;
    private float averageWidth;
    private float originalWidth;
    private int size;
    private float height;


    private int widthSpec,heightSpec;

    private boolean userScrollIntention,isScrolling,isMorethanSplit,isSetedData;





    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // Distance in pixels a touch can wander before we think the user is scrolling a full page
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    /**
     *
     * @param size TrendView中数据的大小
     * @param isScroll 设置是否可滑动
     */
    public void reMeasure(int size,boolean isScroll){
        this.size = size;
        if(averageWidth!=0){
            averageWidth = averageWidth * splitSpaceCount / size;
            splitSpaceCount = STANDARDCOUNT;
        }

        this.userScrollIntention = isScroll;
        if(userScrollIntention){
            isScrolling = (size > STANDARDCOUNT)?true:false;//长条滚动模式
            if(isScrolling)
                splitSpaceCount = STANDARDCOUNT;
            else
                splitSpaceCount = size;
        }else{
            isMorethanSplit = (size > STANDARDCOUNT)?true:false;//多条（大于splitSpaceCount）数据一屏展示
            splitSpaceCount = size;//少于splitSpaceCount数据平均展示
        }


        if(isSetedData)
            averageWidth = originalWidth/splitSpaceCount;

        if (averageWidth!=0&&height!=0){
            LogUtils.i(LogUtils.originalIndex," width:"+(int)averageWidth*size+" height:"+height);
            this.measure(MeasureSpec.makeMeasureSpec((int)averageWidth*size,MeasureSpec.EXACTLY)
                    ,MeasureSpec.makeMeasureSpec((int) height,MeasureSpec.EXACTLY));

        }else{
            LogUtils.i(LogUtils.originalIndex," averageWidth:"+averageWidth+" height:"+height);
        }
        layoutChild();
        if(mScroller!=null)mScroller.startScroll(0, 0, 0, 0);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.i(LogUtils.superIndex," widthSpec:"+widthMeasureSpec+" heightSpec:"+heightMeasureSpec);

        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        if(specWidthSize!=0&&!isSetedData){
            originalWidth = specWidthSize;
            isSetedData = true;
            averageWidth = originalWidth/splitSpaceCount;

        }
        width = specWidthSize;

        this.widthSpec = widthMeasureSpec;
        this.heightSpec = heightMeasureSpec;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 为ScrollerLayout中的每一个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }


        height = width*heightScaleWidth;
        setMeasuredDimension((int) width,(int)height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed)layoutChild();
    }

    private void layoutChild(){
        if(getChildCount() <= 0)return;
        View childView = getChildAt(0);
        childView.layout(0, 0,(int)averageWidth*size, (int)height);
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getRight();
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int scrolledX = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                scrolledX = (int)(mXLastMove - mXMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, scrolledX, 0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * Called by a parent to request that a child update its values for mScrollX and mScrollY if necessary.
     * This will typically be done if the child is animating a scroll using a Scroller object.
     */
    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

}
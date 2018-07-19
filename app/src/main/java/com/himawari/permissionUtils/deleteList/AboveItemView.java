package com.himawari.permissionUtils.deleteList;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.DensityUtils;
import com.himawari.permissionUtils.utils.LogUtils;


/**
 * Created by S.Lee on 2017/11/22.
 */

public class AboveItemView extends LinearLayout {
    private ViewDragHelper viewDragHelper;
    private int leftBound,newLeft;
    private float xDistance, yDistance, xLast, yLast,xStart,yStart;
    private CheckBoxListener checkBxlistener;
    private ListView listView;
    private OnClickListener deleteListener;
    private Context context;
    private SlipListener slipListener;

    private boolean isSlipAble =true;

    private boolean isDown;//onTouchEvent 一次Action_Down后 返回多次Action_Up情况


    public void setSlipListener(SlipListener listener){
        this.slipListener = listener;
    }


    public void setCheckBoxListener(CheckBoxListener listener){
        this.checkBxlistener = listener;
    }

    public void setListener(ListView listview,OnClickListener listener){
        this.listView = listview;
        this.deleteListener = listener;
    }





    public AboveItemView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        addViews(context);

        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return isSlipAble;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                leftBound = DensityUtils.dip2px(context,-66);
                newLeft = (left > 0)?0:(left > leftBound)?left:leftBound;
                return newLeft;

            }
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return 0;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if(releasedChild.getLeft() > DensityUtils.dip2px(context,-33)){
                    releasedChild.layout(0,0, (int) MyApplication.width, DensityUtils.dip2px(context,66));
                    slipListener.isSliped((int)getTag(),false);

                }else{
                    releasedChild.layout(DensityUtils.dip2px(context,-66),0,releasedChild.getRight()-(DensityUtils.dip2px(context,66)+releasedChild.getLeft()), DensityUtils.dip2px(context,66));
                    slipListener.isSliped((int)getTag(),true);
                }
                invalidate();

            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    public void setIsSlipable(boolean isSlipAble){
        this.isSlipAble = isSlipAble;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xStart = event.getX();
                yStart = event.getY();
                xLast = xStart;
                yLast = yStart;

                isDown = true;
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = event.getX();
                final float curY = event.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if(xDistance < yDistance){
                    return false;
                }
            case MotionEvent.ACTION_UP:
                float upx =  event.getX();
                float upy =  event.getY();
                int position = (int) getTag();
                //按下和抬起时候是一样的，那么就是点击事件
                if(Math.abs(upx - xStart)<=5 && Math.abs(upy - yStart)<=5 && isDown){

                    isDown = false;

                    if(getChildAt(0).getLeft()==0){
                        AdapterView.OnItemClickListener itemClickListener = listView.getOnItemClickListener();
                        if(itemClickListener != null){
                            itemClickListener.onItemClick(listView,this,position,position); }
                    }else if(getChildAt(0).getLeft() == leftBound){
                        this.setTag(position);
                        deleteListener.onClick(this);
                    }

                }else{
                    LogUtils.i(LogUtils.superIndex,"upx:"+upx+" xStart:"+xStart+" upy:"+upy
                            +" yStart:"+yStart + " isDown:"+ isDown);
                }
                return false;
        }
        return true;
    }


    public void setCheckBoxAppear(boolean isAppear){
        CheckBox checkBox = this.findViewById(R.id.checkBox);
        checkBox.setVisibility(isAppear?View.VISIBLE:View.INVISIBLE);
    }



    public void addViews(Context mcontext){
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_acc,null);
        addView(view);

        final CheckBox checkBox = view.findViewById(R.id.checkBox);

        checkBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBxlistener!=null)
                    checkBxlistener.isBoxChecked(checkBox.isChecked());
            }
        });

    }

    public void setIsBxChecked(boolean isChecked){
        CheckBox checkBox = this.findViewById(R.id.checkBox);
        checkBox.setChecked(isChecked);
    }




    public interface CheckBoxListener{
        void isBoxChecked(boolean isBoxChecked);
    }

    public interface SlipListener{
        void isSliped(int position, boolean isSliped);
    }


    public void setIsSlipedLeft(boolean isSlipedLeft){
        View child = getChildAt(0);
        if(isSlipedLeft){
            child.layout(leftBound,0,(int) MyApplication.width+leftBound,DensityUtils.dip2px(context,66));
        }else{
            child.layout(0,0, (int) MyApplication.width, DensityUtils.dip2px(context,66));
        }
    }


}

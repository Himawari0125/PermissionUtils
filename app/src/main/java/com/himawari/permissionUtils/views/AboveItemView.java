package com.himawari.permissionUtils.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.DensityUtils;

import static android.support.v4.widget.ViewDragHelper.STATE_IDLE;


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


    public void setCheckBoxListener(CheckBoxListener listener){
        this.checkBxlistener = listener;
    }

    public void setListener(ListView listview,OnClickListener listener){
        this.listView = listview;
        this.deleteListener = listener;
    }


    public AboveItemView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Log.i("--------------","------------");
        this.context = context;


        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
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
                Log.i("----------","--------"+releasedChild);
                if(releasedChild.getLeft() > DensityUtils.dip2px(context,-33))
                    releasedChild.layout(0,0, (int) MyApplication.width, DensityUtils.dip2px(context,66));
                else releasedChild.layout(DensityUtils.dip2px(context,-66),0,releasedChild.getRight()-(DensityUtils.dip2px(context,66)+releasedChild.getLeft()), DensityUtils.dip2px(context,66));
                invalidate();

            }

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                switch (state){
                    case STATE_IDLE:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
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
                int upx = (int) event.getX();
                int upy = (int) event.getY();
                int position = (int) getTag();
                //按下和抬起时候是一样的，那么就是点击事件
                if(upx == xStart && upy == yStart){
                    if(getChildAt(0).getLeft()==0){
                        AdapterView.OnItemClickListener itemClickListener = listView.getOnItemClickListener();
                        if(itemClickListener != null){
                            itemClickListener.onItemClick(listView,this,position,position); }
                    }else if(upx >= (MyApplication.width+leftBound)){
                        this.setTag(position);
                        deleteListener.onClick(this);
                    }

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


}

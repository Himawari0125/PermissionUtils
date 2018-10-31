package com.himawari.permissionUtils.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.commons.Constant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kokosnuss on 2018/10/23
 */
public class ScrollCaptureUtils{

    private static List<Bitmap> bitmaps;
    private static final int SCROLLING = 10001;
    private static int DELAYSTART = 0;
    private static int DELAYINTERVALCAPTURE = 0;
    private static int usefulHeight;

    private static boolean isEnd;
    
    private static ListView mListView;

    private static Context mContext;
    private static SCScrollListener scScrollListener = new SCScrollListener();

    private static CaptureListener captureListener;
    
    private static Handler scrollHandler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SCROLLING:
                    mListView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(!isEnd){
                                mListView.scrollListBy(usefulHeight);
                                mListView.postDelayed(this,DELAYINTERVALCAPTURE);
                            }
                        }
                    },DELAYSTART);
                    break;


            }
        }
    };
    private ScrollCaptureUtils(){

    }


    public static class Builder{
        private ScrollCaptureUtils utils;

        public Builder(){
            utils = new ScrollCaptureUtils();
        }

        public Builder setContent(Context context){
            mContext = context;
            return this;
        }

        public Builder setListView(ListView listView){
            mListView = listView;
            return this;
        }

        public Builder setCaptureListener(CaptureListener listener){
            captureListener = listener;
            return this;
        }

        /**
         * 除去导航，标题栏等部分，屏幕实际能滑动的高度
         * @param uheight
         */
        public Builder setUsefulHeight(int uheight){
            usefulHeight = uheight;
            return this;
        }

        public ScrollCaptureUtils builder(){

            bitmaps = new ArrayList<>();

            return utils;
        }

    }

    public void scrollCapture(){

        isEnd = false;
        lastView = null;
        bitmaps.clear();
        mListView.setOnScrollListener(scScrollListener);

        scrollHandler.sendEmptyMessage(SCROLLING);


    }


    private static void addBitmap(){
        bitmaps.add(ViewCaptureUtils.getBitmapFromView(mListView, ContextCompat.getColor(mContext, R.color.history_titleBarstart)));

    }

    private static void addBitmapByStartY(int height){

        //获取最后一个bitmap
        if (usefulHeight - height <= 0){
            LogUtils.e(LogUtils.originalIndex,"usefulHeight - height <= 0");
            return;
        }
        Bitmap tempBitmap = ViewCaptureUtils.getBitmapFromView(mListView, ContextCompat.getColor(mContext, R.color.history_titleBarstart));
        Bitmap bitmapFinal = Bitmap.createBitmap(tempBitmap,0,height,mListView.getWidth(),usefulHeight - height);
        bitmaps.add(bitmapFinal);

        //拼接保存图片
        File file = ViewCaptureUtils.getViewCapture(mListView.getWidth(), Constant.appFolderCapturePath,bitmaps);
//                    if(file!=null)showShareDialog(file);
        //回调方法
        captureListener.onCaptureFile(file);

        //释放touch，滚动监听
        mListView.setOnTouchListener(null);
        mListView.setOnScrollListener(null);
    }

    private static class SCScrollListener implements AbsListView.OnScrollListener{

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            LogUtils.i(LogUtils.originalIndex,"stateChanged:"+scrollState);
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (lastView!=null&&(visibleItemCount+firstVisibleItem) == totalItemCount){//滑动到最后一屏
                isEnd = true;
                addBitmapByStartY(mListView.getChildAt(mListView.indexOfChild(lastView)).getTop() + lvDrawedHeight );
            }else{
                addBitmap();
                lvDrawedHeight = usefulHeight - mListView.getChildAt(visibleItemCount - 1).getTop();
            }
            lastView = mListView.getChildAt(visibleItemCount - 1);

        }
    }

    private static View lastView;//上次滑动后最后一个ChildView
    private static int lvDrawedHeight;//上次滑动后最后一个ChildView完成绘制的部分高度

    public interface CaptureListener{
        void onCaptureFile(File file);
    }
}

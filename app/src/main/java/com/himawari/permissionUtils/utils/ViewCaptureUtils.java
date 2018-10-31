package com.himawari.permissionUtils.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.View;


import com.himawari.permissionUtils.commons.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by S.Lee on 2018/1/12.
 */

public class ViewCaptureUtils {
    private static List<Bitmap> bitmaps;

    public static File getViewCapture(int width, String folderStr, List<Bitmap> bitmapList){
        Canvas canvas = new Canvas();
        bitmaps = bitmapList;
        int height = 0;
        for (int i = 0 ; i < bitmapList.size();i++){
            height+=bitmapList.get(i).getHeight();
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        canvas.setBitmap(bitmap);
        int itemHeight = 0;
        for (int i = 0 ; i < bitmapList.size();i++){
            Bitmap bitmap1 = bitmapList.get(i);
          //  saveOneBitmap(folderStr,FileNameUtils.getPicName()+"_"+i+".png",bitmap1,100);
            canvas.drawBitmap(bitmap1,0,itemHeight,null);
            itemHeight += bitmap1.getHeight();
            canvas.save();//保存 Canvas.ALL_SAVE_FLAG
            canvas.restore();//存储
        }
       return saveBitmap(folderStr,FileNameUtils.getPicName()+".png",bitmap);
    }

    /** 保存方法 */
    public static File saveBitmap(String folderStr, String picName, Bitmap bm) {

        File destDir = new File(Constant.appFolderPath+folderStr);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        File f = new File(Constant.appFolderPath+folderStr, picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        recycleBitmaps();
        return f;
    }

    /** 保存一张图片方法 */
    public static boolean saveOneBitmap(String folderStr, String picName, Bitmap bm, int quality) {

        File destDir = new File(Constant.appFolderPath+folderStr);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        File f = new File(Constant.appFolderPath+folderStr, picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, quality, out);
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void recycleBitmaps(){
        if(bitmaps == null)return;
        for(int i = 0 ;i < bitmaps.size();i++){
            bitmaps.get(i).recycle();
        }
    }


    /**
     * Convert View that have not display on Phone into Bitmap
     * @param view that you wanna convert into Bitmap
     * @param color bitmap's background color
     * @return
     */
    public static Bitmap getBitmapFromView(View view,int color) {
        if (view.getMeasuredHeight() <= 0) {
            view.measure(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(color);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.draw(canvas);
            return bitmap;
        }else{
            Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(returnedBitmap);
            Drawable bgDrawable =view.getBackground();
            if (bgDrawable!=null)
                bgDrawable.draw(canvas);
            else
                canvas.drawColor(Color.WHITE);
            view.draw(canvas);
            return returnedBitmap;
        }
       
    }
}

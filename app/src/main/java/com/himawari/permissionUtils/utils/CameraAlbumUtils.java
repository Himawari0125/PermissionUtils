package com.himawari.permissionUtils.utils;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;

/**
 * Created by S.Lee on 2017/8/30 0030.
 */

public class CameraAlbumUtils {
    public static final int PHOTOHRAPH = 1;
    public static final int PHOTOCROP = 2;


    /**
     * 启动系统相机
     * @param mActivity
     */
    public static void intentSystemCamera(FragmentActivity mActivity){
        Intent cameraIntent = new Intent(
                "android.media.action.IMAGE_CAPTURE");
        Uri imgUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            imgUri = getImageUriContent(mActivity);
            cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }else{
            imgUri = getImageUriFile();
        }
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
        cameraIntent.putExtra(
                MediaStore.EXTRA_VIDEO_QUALITY, 0);
        mActivity.startActivityForResult(cameraIntent,
                PHOTOHRAPH);
    }

    /**
     * 调用系统裁剪方法
     * @param mActivity
     */
    public static void startPhotoZoom(FragmentActivity mActivity) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            uri = getImageUriContent(mActivity);
        } else{
            uri = getImageUriFile();
        }
        if(uri == null)return;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        mActivity.startActivityForResult(intent, PHOTOCROP);
    }


    /**
     * 获取裁剪后的图片
     * @param requestCode
     * @param data
     * @param imageView
     */
    public static void dealWithResult(int requestCode,final Intent data,ImageView imageView){
        if (requestCode == PHOTOCROP) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                final Bitmap photo = extras.getParcelable("data");
                imageView.setImageBitmap(photo);
            }
        }
    }

    /**
     * android7.0以下uri获取方法
     * @return
     */
    private static Uri getImageUriFile() {

        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                "faceImage.png"));
    }

    /**
     * android7.0及以上获取uri方法
     * @param mActivity
     * @return
     */
    private static Uri getImageUriContent(FragmentActivity mActivity){
        String authorities = mActivity.getApplicationContext().getPackageName()+".fileprovider";
        return FileProvider.getUriForFile(mActivity,authorities,new File(Environment.getExternalStorageDirectory(),
                "faceImage.png"));
    }

    /**
     * ContentResolver直接获取系统相册图片
     * @param mContext
     * @param mImageView
     */
    public static void getPicture(FragmentActivity mContext, ImageView mImageView){
        ContentResolver contentProvider = mContext.getContentResolver();
        ParcelFileDescriptor mInputPFD;
        try {
            //获取contentProvider图片
            mInputPFD = contentProvider.openFileDescriptor(getImageUriContent(mContext), "r");
            FileDescriptor fileDescriptor = mInputPFD.getFileDescriptor();
            mImageView.setImageBitmap(BitmapFactory.decodeFileDescriptor(fileDescriptor));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

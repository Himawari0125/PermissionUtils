package com.himawari.permissionUtils.views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by admin on 2016/6/2.
 */
public class CustomMenu extends RelativeLayout {
    private int[] img = new int[]{R.styleable.custommenu_img1, R.styleable.custommenu_img2,
            R.styleable.custommenu_img3, R.styleable.custommenu_img4, R.styleable.custommenu_img5,
            R.styleable.custommenu_img6, R.styleable.custommenu_img7, R.styleable.custommenu_img8,
            R.styleable.custommenu_img9};
    private List<Drawable> ld = new ArrayList<>();
    private List<RelativeLayout> layoutList = new ArrayList<>();
    private static final String TAG = "flag_";

    private int xpoint = 1,ypoint = 1;
    private List<Integer> coordinate;

    public interface imgOnclick {
        void imgOnClick(int position);
    }

    private imgOnclick iclick;
    private boolean flag = true;
    private int distance;

    public void setImageClick(imgOnclick iclick) {
        this.iclick = iclick;
    }

    public CustomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(context.getResources().getColor(R.color.background_black));
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.custommenu);
        /**
         * 获取弹出距离
         */
        distance = DensityUtils.dip2px(context,ta.getInteger(R.styleable.custommenu_distance,0));
        /**
         * 获取控件位置
         */
        coordinate = getLocation(ta.getInteger(R.styleable.custommenu_position,0));
        /**
         * 获取添加的图片
         */
        for (int i = 0; i < img.length; i++) {
            Log.i(TAG,"ta.getDrawable(img[i]) = "+ta.getDrawable(img[i]));
            Drawable d = ta.getDrawable(img[i]);
            ld.add(d);
        }
        ta.recycle();
        /**
         * 将图片放入ImageView，并添加点击事件
         */
        for (int j = 0; j < ld.size(); j++) {
            if (ld.get(j) != null) {
                final ImageView img = new ImageView(context);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    img.setBackground(ld.get(j));
                }else {//低于Android 4.1(果冻豆)的版本
                    img.setBackgroundDrawable(ld.get(j));
                }
                img.setTag(j);

                RelativeLayout layout = new RelativeLayout(context);
                LayoutParams lpimg = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layout.setLayoutParams(lpimg);

                lpimg.addRule(CENTER_IN_PARENT);
                layout.addView(img,lpimg);

                layoutList.add(layout);

                img.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    int position = (int)v.getTag();
                    if(position==0) {
                        v.setSelected(!v.isSelected());
                        if(flag){
                            startAnim();
                            flag = false;
                        }else{
                            closeAnim();
                            flag = true;
                        }
                    }
                    iclick.imgOnClick(position);
                    }
                });
            }
        }
        /**
         *显示控件
         */
        for(int n = layoutList.size() - 1;n >= 0 ; n--){
            LayoutParams lp = new LayoutParams(DensityUtils.dip2px(context,45), DensityUtils.dip2px(context,45));
            for(int i = 0; i < coordinate.size();i++){
                lp.addRule(coordinate.get(i),TRUE);
            }
            addView(layoutList.get(n), lp);
        }
    }
    /**
     *开始动画
     */
    private void startAnim() {
        int total = layoutList.size();
        float radius = 90*1.0f/(total-2);
        for(int i = 1 ; i < total; i ++ ){
            ObjectAnimator anim3 = ObjectAnimator.ofFloat(layoutList.get(i), "translationX",0f,xpoint*(float)(distance* Math.cos(Math.toRadians(radius*(i - 1))))).setDuration(500);
            ObjectAnimator anim4 = ObjectAnimator.ofFloat(layoutList.get(i), "translationY",0f, ypoint*(float)(distance* Math.sin(Math.toRadians(radius*(i - 1))))).setDuration(500);
            AnimatorSet as3 = new AnimatorSet();
            as3.setInterpolator(new BounceInterpolator());
            as3.playTogether(anim3, anim4);
            as3.start();
        }
    }
    /**
     *结束动画
     */
    private void closeAnim() {
        int total = layoutList.size();
        float radius = 90*1.0f/(total-2);
        for(int i = 1 ; i < total; i ++ ){
            ObjectAnimator anim3 = ObjectAnimator.ofFloat(layoutList.get(i), "translationX",xpoint*(float)(distance* Math.cos(Math.toRadians(radius*(i-1)))), 0f).setDuration(500);
            ObjectAnimator anim4 = ObjectAnimator.ofFloat(layoutList.get(i), "translationY",ypoint*(float)(distance* Math.sin(Math.toRadians(radius*(i-1)))), 0f).setDuration(500);
            AnimatorSet as3 = new AnimatorSet();
            as3.setInterpolator(new BounceInterpolator());
            as3.playTogether(anim3, anim4);
            as3.start();
        }
    }


    /**
     * 获取控件的位置
     * @param data
     * @return
     */
    private List<Integer> getLocation(int data){
        List<Integer> datalist = new ArrayList<>();
        switch(data){
            case 1://左上角
                xpoint = 1;
                ypoint = 1;
                datalist.add(ALIGN_PARENT_LEFT);
                datalist.add(ALIGN_PARENT_TOP);
                break;
            case 2://右上角
                xpoint = -1;
                ypoint = 1;
                datalist.add(ALIGN_PARENT_TOP);
                datalist.add(ALIGN_PARENT_RIGHT);
                break;
            case 3://右下角
                xpoint = -1;
                ypoint = -1;
                datalist.add(ALIGN_PARENT_RIGHT);
                datalist.add(ALIGN_PARENT_BOTTOM);
                break;
            case 4://左下角
                xpoint = 1;
                ypoint = -1;
                datalist.add(ALIGN_PARENT_LEFT);
                datalist.add(ALIGN_PARENT_BOTTOM);
                break;
            default:
                xpoint = 1;
                ypoint = 1;
                datalist.add(ALIGN_PARENT_LEFT);
                datalist.add(ALIGN_PARENT_TOP);
                break;
        }
        return datalist;
    }

}

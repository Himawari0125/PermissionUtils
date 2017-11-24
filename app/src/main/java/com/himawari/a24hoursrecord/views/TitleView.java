package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.utils.DensityUtils;


/**
 * Created by S.Lee on 2017/11/20.
 */

public class TitleView extends RelativeLayout{
    private Drawable left_drawable,right_drawable;
    private String title_str,right_str;
    private int right_tv_color;
    private float right_tv_size;

    private leftIconClickListener leftIconClickListener;
    private rightIconClickListener rightIconClickListener;

    public void setLeftIconClickListener(leftIconClickListener listener){
        this.leftIconClickListener = listener;
    }
    public void setRightIconClickListener(rightIconClickListener listener){
        this.rightIconClickListener = listener;
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.titleview);
        left_drawable = typedArray.getDrawable(R.styleable.titleview_left_img);
        right_drawable = typedArray.getDrawable(R.styleable.titleview_right_img);
        title_str = typedArray.getString(R.styleable.titleview_title_tv);
        right_str = typedArray.getString(R.styleable.titleview_right_tv);
        right_tv_color = typedArray.getColor(R.styleable.titleview_right_tv_color,context.getResources().getColor(R.color.item_background));
        right_tv_size = typedArray.getFloat(R.styleable.titleview_right_tv_size, DensityUtils.sp2px(context,7.5f));
        typedArray.recycle();
        
        
        ImageView left_img = new ImageView(context);
        final ImageView right_img = new ImageView(context);
        TextView title_tv = new TextView(context);
        final TextView right_tv = new TextView(context);



        LayoutParams params_left_img = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        params_left_img.addRule(ALIGN_PARENT_LEFT);
        params_left_img.addRule(CENTER_VERTICAL);

        LayoutParams params_right_img = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_right_img.addRule(ALIGN_PARENT_RIGHT);
        params_right_img.addRule(CENTER_VERTICAL);

        LayoutParams params_right_tv = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_right_tv.addRule(ALIGN_PARENT_RIGHT);
        params_right_tv.addRule(CENTER_VERTICAL);

        LayoutParams params_title = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_title.addRule(CENTER_IN_PARENT);



        left_img.setLayoutParams(params_left_img);
        left_img.setPadding(DensityUtils.dip2px(context,15),0,DensityUtils.dip2px(context,15),0);
        right_img.setLayoutParams(params_right_img);
        right_img.setPadding(DensityUtils.dip2px(context,15),0,DensityUtils.dip2px(context,15),0);
        title_tv.setLayoutParams(params_title);
        right_tv.setLayoutParams(params_right_tv);
        right_tv.setPadding(DensityUtils.dip2px(context,15),0,DensityUtils.dip2px(context,15),0);


        if(left_drawable == null){
            left_img.setVisibility(View.INVISIBLE);
        }else{
            left_img.setImageDrawable(left_drawable);
            left_img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(leftIconClickListener!=null)
                        leftIconClickListener.leftIconClick();
                }
            });
        }



        if(right_drawable == null){
            right_img.setVisibility(View.INVISIBLE);
        }else{
            right_img.setImageDrawable(right_drawable);
            right_img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(rightIconClickListener!=null)
                        rightIconClickListener.rightIconClick();
                }
            });
        }


        if(right_str == null){
            right_tv.setVisibility(View.INVISIBLE);
        }else{
            right_tv.setText(right_str);
          //  right_tv.setTextSize(right_tv_size);
            right_tv.setTextColor(right_tv_color);
            right_tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(rightIconClickListener!=null){
                        boolean isedit = getResources().getString(R.string.edit).equals(right_tv.getText().toString());
                        right_tv.setText(isedit?getResources().getString(R.string.complete):getResources().getString(R.string.edit));
                        rightIconClickListener.rightTextViewClick(isedit);
                    }

                }
            });
        }


        if(title_str == null)
            title_tv.setVisibility(View.INVISIBLE);
        else
            title_tv.setText(title_str);

        addView(left_img);
        addView(right_img);
        addView(title_tv);
        addView(right_tv);

    }


    public interface leftIconClickListener{
        void leftIconClick();
    }

    public interface rightIconClickListener{
        void rightIconClick();
        void rightTextViewClick(boolean isEdit);
    }


}

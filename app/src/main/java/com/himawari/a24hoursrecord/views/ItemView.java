package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.utils.DensityUtils;

/**
 * Created by S.Lee on 2017/11/17.
 */

public class ItemView extends RelativeLayout {
    private Drawable img_icon,next_icon;
    private String title_tv;
    private float title_tv_size;
    private int icon_id = R.id.itemview_icon_id;
    private ItemView(Context context) {
        super(context);
    }



    private ItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        /**
         * 获取自定义控件，xml里设置的值。
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.itemview);
        img_icon = typedArray.getDrawable(R.styleable.itemview_img_icon);
        next_icon = typedArray.getDrawable(R.styleable.itemview_next_icon);
        title_tv = typedArray.getString(R.styleable.itemview_title_tv);
        title_tv_size = typedArray.getFloat(R.styleable.itemview_title_size,7.5f);
        typedArray.recycle();


        /**
         * 创建控件 设置值
         */
        ImageView imageView_icon = new ImageView(context);
        imageView_icon.setImageDrawable(img_icon);
        ImageView imageView_next = new ImageView(context);
        imageView_next.setImageDrawable(next_icon);
        TextView textView_title = new TextView(context);
        textView_title.setText(title_tv);

        /**
         * 添加控件进入自定义View
         */
        LayoutParams params_icon = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_icon.addRule(ALIGN_PARENT_LEFT);
        params_icon.addRule(CENTER_VERTICAL);
        params_icon.leftMargin = DensityUtils.dip2px(context,20);
        imageView_icon.setLayoutParams(params_icon);
        imageView_icon.setId(icon_id);
        addView(imageView_icon);


        LayoutParams params_next = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_next.addRule(ALIGN_PARENT_RIGHT);
        params_next.addRule(CENTER_VERTICAL);
        params_next.rightMargin = DensityUtils.dip2px(context,20);
        imageView_next.setLayoutParams(params_next);
        addView(imageView_next);

        LayoutParams params_title = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_title.addRule(RIGHT_OF,icon_id);
        params_title.addRule(CENTER_VERTICAL);
        params_title.leftMargin = DensityUtils.dip2px(context,20);
        textView_title.setLayoutParams(params_title);
        textView_title.setTextSize(DensityUtils.sp2px(context,title_tv_size));
        addView(textView_title);


    }

}

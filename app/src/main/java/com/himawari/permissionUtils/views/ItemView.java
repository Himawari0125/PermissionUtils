package com.himawari.permissionUtils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.DensityUtils;


/**
 * Created by S.Lee on 2017/11/17.
 */

public class ItemView extends RelativeLayout {
    private Drawable img_icon,next_icon,left_checkbox_icon;
    private String item_title_tv;
    private float item_title_tv_size;
    private int icon_id = R.id.itemview_icon_id,check_id = R.id.itemview_check_id;
    private int item_title_color;

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        /**
         * 获取自定义控件，xml里设置的值。
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.itemview);
        img_icon = typedArray.getDrawable(R.styleable.itemview_img_icon);
        next_icon = typedArray.getDrawable(R.styleable.itemview_next_icon);
        left_checkbox_icon = typedArray.getDrawable(R.styleable.itemview_left_checkbox_button);
        item_title_tv = typedArray.getString(R.styleable.itemview_item_title_tv);
        item_title_tv_size = typedArray.getFloat(R.styleable.itemview_item_title_size,7.5f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            item_title_color = typedArray.getColor(R.styleable.itemview_item_title_color,getResources().getColor(R.color.title,null));
        }else{
            item_title_color = typedArray.getColor(R.styleable.itemview_item_title_color,getResources().getColor(R.color.title));
        }
        typedArray.recycle();


        /**
         * 创建控件 设置值
         */
        ImageView imageView_icon = new ImageView(context);
        if(img_icon!=null)imageView_icon.setImageDrawable(img_icon);
        ImageView imageView_next = new ImageView(context);
        imageView_next.setImageDrawable(next_icon);
        TextView textView_title = new TextView(context);
        textView_title.setText(item_title_tv);
        CheckBox checkBox = new CheckBox(context);

        /**
         * 添加控件进入自定义View
         */
        LayoutParams params_icon = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_icon.addRule(ALIGN_PARENT_LEFT);
        params_icon.addRule(CENTER_VERTICAL);
        params_icon.leftMargin = DensityUtils.dip2px(context,15);
        imageView_icon.setLayoutParams(params_icon);
        imageView_icon.setId(icon_id);
        if(img_icon==null)imageView_icon.setVisibility(View.GONE);
        addView(imageView_icon);

        LayoutParams params_checkbox = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_checkbox.addRule(ALIGN_PARENT_LEFT);
        params_checkbox.addRule(CENTER_VERTICAL);
        params_checkbox.leftMargin = DensityUtils.sp2px(context,7.5f);
        checkBox.setId(check_id);
        checkBox.setLayoutParams(params_checkbox);
        checkBox.setButtonDrawable(left_checkbox_icon);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("--checkbox---","isChexked?"+isChecked);
            }
        });
        if(left_checkbox_icon==null)checkBox.setVisibility(View.GONE);
        addView(checkBox);

        LayoutParams params_next = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_next.addRule(ALIGN_PARENT_RIGHT);
        params_next.addRule(CENTER_VERTICAL);
        params_next.rightMargin = DensityUtils.dip2px(context,15);
        imageView_next.setLayoutParams(params_next);
        if(next_icon==null)imageView_next.setVisibility(View.GONE);
        addView(imageView_next);

        LayoutParams params_title = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        if(img_icon!=null)params_title.addRule(RIGHT_OF,icon_id);
        else if(left_checkbox_icon !=null)params_title.addRule(RIGHT_OF,check_id);
        params_title.addRule(CENTER_VERTICAL);
        params_title.leftMargin = DensityUtils.dip2px(context,7);
        textView_title.setLayoutParams(params_title);
        if(item_title_tv==null)textView_title.setVisibility(View.GONE);
    //    textView_title.setTextSize(DensityUtils.sp2px(context,item_title_tv_size));
        addView(textView_title);


    }
}

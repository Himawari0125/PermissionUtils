package com.himawari.permissionUtils.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.DensityUtils;

import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;
import static android.widget.RelativeLayout.CENTER_VERTICAL;

/**
 * Created by S.Lee on 2017/12/13.
 */

public class DrawerItemView extends LinearLayout{
    private LinearLayout subItem;
    private Context context;
    private int margin;
    private ImageView super_nextBtn;

    private String detail_Str = "";

    private DrawingListener listener;

    public DrawerItemView(Context context) {
        super(context);
        init(context);
    }
    public DrawerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public DrawerItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        this.context = context;
        setOrientation(VERTICAL);
        /**
         * 创建主item
         */
        RelativeLayout superItem = new RelativeLayout(context);
        superItem.setBackgroundColor(getResources().getColor(R.color.background_white));
        LayoutParams params_superItem = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(context,50));
        superItem.setLayoutParams(params_superItem);
        superItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subItem == null || super_nextBtn == null || listener==null)return;
                if(subItem.getChildCount() > 0){
                    subItem.removeAllViews();
                    super_nextBtn.setImageDrawable(getResources().getDrawable(R.mipmap.drawup));
                    listener.setIsDrawDown(false);
                }else{
                    addSubView();
                    super_nextBtn.setImageDrawable(getResources().getDrawable(R.mipmap.drawdown));
                    listener.setIsDrawDown(true);
                }
                invalidate();

            }
        });

        margin = DensityUtils.dip2px(context,10);
        TextView super_Title = new TextView(context);
        super_Title.setText("fihweoowhgorhgojtog");
        super_Title.setId(R.id.drawer_title);
        RelativeLayout.LayoutParams params_superTitle = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params_superTitle.leftMargin = margin;
        params_superTitle.addRule(CENTER_VERTICAL);
        super_Title.setLayoutParams(params_superTitle);
        superItem.addView(super_Title);

        super_nextBtn = new ImageView(context);
        super_nextBtn.setImageDrawable(getResources().getDrawable(R.mipmap.drawup));
        RelativeLayout.LayoutParams params_superBtn = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params_superBtn.addRule(ALIGN_PARENT_RIGHT);
        params_superBtn.addRule(CENTER_VERTICAL);
        params_superBtn.rightMargin = margin;
        super_nextBtn.setLayoutParams(params_superBtn);
        superItem.addView(super_nextBtn);

        addView(superItem);

        /**
         * 添加扩展item空间
         */
        subItem = new LinearLayout(context);
        subItem.setBackgroundColor(getResources().getColor(R.color.sub_item_background));
        LayoutParams params_subItem = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        subItem.setLayoutParams(params_subItem);
        addView(subItem);
    }

    private void addSubView(){
        TextView sub_tv = new TextView(context);
        LayoutParams params_subItem = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params_subItem.setMargins(margin,margin,margin,margin);
        sub_tv.setLayoutParams(params_subItem);
        sub_tv.setText(detail_Str);
        sub_tv.setTextColor(getResources().getColor(R.color.sub_item_text));

        subItem.addView(sub_tv);
    }

    public void setDetail(String detail){
        this.detail_Str = detail;
        invalidate();
    }

    public void setIsDrawDown(boolean isDrawDown){
        if(subItem == null || super_nextBtn == null)return;
        if(subItem.getChildCount() > 0 && !isDrawDown){
            subItem.removeAllViews();
            super_nextBtn.setImageDrawable(getResources().getDrawable(R.mipmap.drawup));
        }else if(subItem.getChildCount() == 0 && isDrawDown){
            addSubView();
            super_nextBtn.setImageDrawable(getResources().getDrawable(R.mipmap.drawdown));
        }
        invalidate();
    }

    public void setDrawinglistener(DrawingListener listener){
        this.listener = listener;
    }

    public interface DrawingListener{
        void setIsDrawDown(boolean isDrawDown);
    }

}

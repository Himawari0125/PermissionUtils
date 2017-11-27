package com.himawari.permissionUtils.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.DensityUtils;

/**
 * Created by S.Lee on 2017/11/22.
 */

public class DeleteItemView extends LinearLayout {
    public DeleteItemView(Context context) {
        super(context);


        RelativeLayout.LayoutParams relativewParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, DensityUtils.dip2px(context,66f));
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(relativewParams);
        addView(layout);

        RelativeLayout.LayoutParams img_Pramas = new RelativeLayout.LayoutParams(DensityUtils.dip2px(context,50), DensityUtils.dip2px(context,50));
        img_Pramas.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        img_Pramas.setMargins(DensityUtils.dip2px(context,8),
                DensityUtils.dip2px(context,8),
                DensityUtils.dip2px(context,8),
                DensityUtils.dip2px(context,8));
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(R.mipmap.ic_launcher);
        imageView.setLayoutParams(img_Pramas);
        layout.addView(imageView);

        LayoutParams img_Pramas2 = new LayoutParams(DensityUtils.dip2px(context,50), DensityUtils.dip2px(context,50));
        img_Pramas2.setMargins(DensityUtils.dip2px(context,8),
                DensityUtils.dip2px(context,8),
                DensityUtils.dip2px(context,8),
                DensityUtils.dip2px(context,8));
        ImageView imageView2 = new ImageView(context);
        imageView2.setBackgroundResource(R.mipmap.ic_launcher);
        imageView2.setLayoutParams(img_Pramas2);
        addView(imageView2);

    }

}

package com.himawari.permissionUtils.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.ScrollDeleteBean;
import com.himawari.permissionUtils.utils.LogUtils;

import java.util.List;

public class ScrollerDeleteAdapter extends RecyclerView.Adapter<ScrollerDeleteAdapter.ScrollDeleteHolder> {


    private Context mContext;
    private List<ScrollDeleteBean> scrollDeleteBeans;
    private int count = 0;

    public ScrollerDeleteAdapter(Context context,List<ScrollDeleteBean> beans){
        this.scrollDeleteBeans = beans;
        this.mContext = context;
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public ScrollDeleteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_scrolldelete,null);
        final ScrollDeleteHolder holder = new ScrollDeleteHolder(view);

        final int tag = count++;
        holder.hscrollview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                LogUtils.i(LogUtils.originalIndex," scrollX:"+scrollX+" scrollY:"+scrollY+" oldScrollX:"+oldScrollX+" oldScrollY:"+oldScrollY);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ScrollDeleteHolder holder, int position) {
        holder.title.setText(scrollDeleteBeans.get(position).getTitle());
        holder.detail.setText(scrollDeleteBeans.get(position).getDetail());
        holder.constraintLayout.setMinWidth((int) MyApplication.width);
        holder.hscrollview.setTag(position);
    }

    @Override
    public int getItemCount() {
        return scrollDeleteBeans.size();
    }

    class ScrollDeleteHolder extends RecyclerView.ViewHolder{
        ImageView img_del;
        TextView title,detail;
        ConstraintLayout constraintLayout;
        HorizontalScrollView hscrollview;
        public ScrollDeleteHolder(View itemView) {
            super(itemView);
            img_del = itemView.findViewById(R.id.img_del);
            title = itemView.findViewById(R.id.textView18);
            detail = itemView.findViewById(R.id.textView19);
            constraintLayout = itemView.findViewById(R.id.imageView14);
            hscrollview = itemView.findViewById(R.id.hscrollview);
        }
    }
}

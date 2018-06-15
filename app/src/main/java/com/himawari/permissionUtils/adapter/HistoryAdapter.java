package com.himawari.permissionUtils.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.HistoryListBean;
import com.himawari.permissionUtils.utils.LogUtils;
import com.himawari.permissionUtils.utils.TextViewUtils;

import java.util.List;


/**
 * Created by S.Lee on 2017/12/14.
 */

public class HistoryAdapter extends BaseAdapter {
    private List<HistoryListBean> datas;
    private LayoutInflater inflater;
    private Context context;
    public HistoryAdapter(Context context, List<HistoryListBean> beans){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.datas = beans;
    }

    public void addDatas(List<HistoryListBean> beans){
        this.datas.addAll(beans);
        this.notifyDataSetChanged();
        LogUtils.e("data size:"+datas.size());
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_historymeasure,null);
            holder.date_tv = convertView.findViewById(R.id.textView7);
            holder.time_tv = convertView.findViewById(R.id.textView8);
            holder.weight_tv = convertView.findViewById(R.id.weight_tv);
            holder.fatPercent_tv = convertView.findViewById(R.id.fat_percent);
            holder.timeBtn = convertView.findViewById(R.id.imageView5);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        HistoryListBean bean = datas.get(position);
        String date = bean.getMesureDate();
        holder.date_tv.setText(TextViewUtils.setSpecifiedTvButtonSize(context,date,0,2,20, Color.BLACK));
        holder.time_tv.setText(bean.getMeasureTime());
        holder.weight_tv.setText(bean.getWeight());
        holder.fatPercent_tv.setText(bean.getFatPercent());
        if(bean.isFirstMeasure()){
            holder.date_tv.setVisibility(View.VISIBLE);
            holder.timeBtn.setVisibility(View.VISIBLE);
        }else{
            holder.date_tv.setVisibility(View.INVISIBLE);
            holder.timeBtn.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    class ViewHolder{
        TextView date_tv;
        TextView time_tv;
        TextView weight_tv;
        TextView fatPercent_tv;
        ImageView timeBtn;
    }
}

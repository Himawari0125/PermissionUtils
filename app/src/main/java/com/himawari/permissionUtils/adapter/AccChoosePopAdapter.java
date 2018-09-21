package com.himawari.permissionUtils.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.AccManageBean;

import java.util.List;

/**
 * Created by S.Lee on 2018/1/2.
 */

public class AccChoosePopAdapter extends BaseAdapter {
    private List<AccManageBean> datas;
    private Context context;

    public AccChoosePopAdapter(Context context, List<AccManageBean> beans){
        this.context = context;
        this.datas = beans;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_accountchoose,null);
            holder = new ViewHolder();
            holder.account_img = convertView.findViewById(R.id.imageView17);
            holder.account_name = convertView.findViewById(R.id.textView24);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        AccManageBean bean = datas.get(position);

        if(position == 0){
            holder.account_img.setBackgroundResource(R.mipmap.guanbi);
            holder.account_name.setVisibility(View.GONE);
        }else if(position == datas.size()-1){
            holder.account_img.setBackgroundResource(R.mipmap.tianjiayonghu);
            holder.account_name.setVisibility(View.GONE);
        }else{
            holder.account_name.setText(bean.getUser_name());
            holder.account_name.setVisibility(View.VISIBLE);
            holder.account_img.setBackground(null);

        }

        return convertView;
    }

    class ViewHolder{
        TextView account_name;
        ImageView account_img;
    }
}

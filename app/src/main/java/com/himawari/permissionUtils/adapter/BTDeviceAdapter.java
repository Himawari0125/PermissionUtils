package com.himawari.permissionUtils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.himawari.permissionUtils.R;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by S.Lee on 2017/9/1 0001.
 */

public class BTDeviceAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Map<String,String> deviceData;
    public BTDeviceAdapter(Context mContext){
        layoutInflater = LayoutInflater.from(mContext);
        deviceData = new HashMap<>();
    }
    public void setDeviceData(Map<String,String> deviceData){
        if(this.deviceData!=null){
            this.deviceData.clear();
        }
        this.deviceData.putAll(deviceData);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return deviceData.size();
    }

    @Override
    public Object getItem(int i) {
        return deviceData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.item,null);
            holder.device_name = view.findViewById(R.id.textView);
            holder.device_mac = view.findViewById(R.id.textView2);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        for(Map.Entry entry:deviceData.entrySet()){
            holder.device_mac.setText(entry.getKey().toString());
            holder.device_name.setText(entry.getValue().toString()==null?"null":entry.getValue().toString());
        }
        return view;
    }

    class ViewHolder{
        TextView device_name,device_mac;
    }
}

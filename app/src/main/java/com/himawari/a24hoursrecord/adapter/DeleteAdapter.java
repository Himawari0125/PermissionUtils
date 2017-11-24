package com.himawari.a24hoursrecord.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.views.AboveItemView;

import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by S.Lee on 2017/11/23.
 */

public class DeleteAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private boolean checkbocAppear = false;
    private Map<Integer,Boolean> check_map;
    public DeleteAdapter(Context mcontext){
        inflater = LayoutInflater.from(mcontext);
        initCheckMap();
    }

    public void initCheckMap(){
        if(check_map != null){
            check_map.clear();
            check_map = null;
        }
        check_map = new HashMap<>();
        for(int i = 0 ; i < getCount();i++){
            check_map.put(i,false);
        }
    }
    public void setChoose(boolean iscanChoose){
        this.checkbocAppear = iscanChoose;
        notifyDataSetChanged();
    }

    public Map<Integer,Boolean> getCheckedBox(){
        return check_map;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_delete,null);
            holder = new ViewHolder();
            holder.above_item = convertView.findViewById(R.id.above_item);
            holder.delete_one_layout = convertView.findViewById(R.id.delete_one_layout);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.above_item.setCheckBoxAppear(checkbocAppear);
        holder.above_item.setIsBxChecked(check_map.get(position));
        holder.above_item.setCheckBoxListener(new AboveItemView.CheckBoxListener() {
            @Override
            public void isBoxChecked(boolean isBoxChecked) {
                check_map.put(position,isBoxChecked);
            }
        });
        holder.delete_one_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("delete","position: "+position);
            }
        });
        return convertView;
    }

    class ViewHolder{
        AboveItemView above_item;
        LinearLayout delete_one_layout;
    }


}

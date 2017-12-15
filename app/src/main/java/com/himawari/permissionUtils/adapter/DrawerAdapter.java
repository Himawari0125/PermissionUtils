package com.himawari.permissionUtils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.TitleDetailBean;
import com.himawari.permissionUtils.views.DrawerItemView;

import java.util.List;

/**
 * Created by S.Lee on 2017/12/13.
 */

public class DrawerAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<TitleDetailBean> datas;
    public DrawerAdapter(Context context,List<TitleDetailBean> list){
        inflater = LayoutInflater.from(context);
        this.datas = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_drawer,null);
            holder = new ViewHolder();
            holder.drawerItemView = convertView.findViewById(R.id.drawer);
            holder.title = holder.drawerItemView.findViewById(R.id.drawer_title);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        TitleDetailBean bean = datas.get(position);
        holder.title.setText(bean.getTitle());
        holder.drawerItemView.setDetail(bean.getDetail());
        holder.drawerItemView.setIsDrawDown(bean.isDrawDown());
        holder.drawerItemView.setDrawinglistener(new DrawerItemView.DrawingListener() {
            @Override
            public void setIsDrawDown(boolean isDrawDown) {
                datas.get(position).setDrawDown(isDrawDown);

            }
        });


        return convertView;
    }


    class ViewHolder{
        DrawerItemView drawerItemView;
        TextView title;
    }

}

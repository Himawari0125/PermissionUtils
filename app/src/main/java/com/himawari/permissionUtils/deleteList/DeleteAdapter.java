package com.himawari.permissionUtils.deleteList;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.AccManageBean;
import com.himawari.permissionUtils.deleteList.AboveItemView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by S.Lee on 2017/11/23.
 */

public class DeleteAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private boolean checkbocAppear = false;
    private Map<Integer, Boolean> check_map;
    private Map<Integer, Boolean> slip_map;
    private ListView listView;
    private View.OnClickListener delete_click;
    private ArrayList<AccManageBean> datas;
    private Context mContext;


    public DeleteAdapter(Context mcontext,ArrayList<AccManageBean> beans, ListView listView, View.OnClickListener listener) {
        inflater = LayoutInflater.from(mcontext);
        this.datas = beans;
        this.listView = listView;
        this.delete_click = listener;
        initCheckMap();
        initSlipMap();
        this.mContext = mcontext;
    }

    public void setDatas(ArrayList<AccManageBean> beans,boolean iscanChoose){
        this.checkbocAppear = iscanChoose;
        this.datas = beans;
        initCheckMap();
        initSlipMap();
        notifyDataSetChanged();

    }

    public void initCheckMap() {
        if (check_map != null) {
            check_map.clear();
            check_map = null;
        }
        check_map = new HashMap<>();
        for (int i = 0; i < getCount(); i++) {
            check_map.put(i, false);
        }
    }

    public void initSlipMap() {
        if (slip_map != null) {
            slip_map.clear();
            slip_map = null;
        }
        slip_map = new HashMap<>();
        for (int i = 0; i < getCount(); i++) {
            slip_map.put(i, false);
        }
    }

    public void setChoose(boolean iscanChoose) {
        this.checkbocAppear = iscanChoose;
        notifyDataSetChanged();
    }

    public Map<Integer, Boolean> getCheckedBox() {
        return check_map;
    }


    @Override
    public long getItemId(int position) {
        return position;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_delete, null);
            holder = new ViewHolder();
            holder.above_item = convertView.findViewById(R.id.above_item);
            holder.deleteLayout = convertView.findViewById(R.id.delete_one_layout);

            holder.img_icon = holder.above_item.findViewById(R.id.imageView);
            holder.user_name = holder.above_item.findViewById(R.id.textView2);
            holder.choose_icon = holder.above_item.findViewById(R.id.checkBox);
            //   holder.primary_sign = holder.above_item.findViewById(R.id.imageView2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AccManageBean bean = datas.get(position);

        if(bean.isLastItem()){
            holder.img_icon.setBackgroundResource(R.mipmap.xinzengyonghu_liebiao);
        }else{
            holder.img_icon.setBackgroundResource(R.mipmap.ic_launcher);
        }

        holder.user_name.setText(bean.getUser_name());
//        holder.primary_sign.setVisibility(bean.isPrimaryAccount()?View.VISIBLE:View.GONE);
        //  holder.choose_icon.setVisibility(?View.GONE:View.VISIBLE);
        if((bean.isPrimaryAccount()||bean.isLastItem()))
            holder.above_item.setCheckBoxAppear(false);
        else
            holder.above_item.setCheckBoxAppear(checkbocAppear);

        holder.above_item.setIsBxChecked(check_map.get(position));
        holder.above_item.setCheckBoxListener(new AboveItemView.CheckBoxListener() {
            @Override
            public void isBoxChecked(boolean isBoxChecked) {
                check_map.put(position, isBoxChecked);
            }
        });
        holder.above_item.setListener(listView, delete_click);

        holder.above_item.setTag(position);
        if(bean.isLastItem()||bean.isPrimaryAccount())
            holder.above_item.setIsSlipable(false);
        else
            holder.above_item.setIsSlipable(true);
        holder.above_item.setIsSlipedLeft(slip_map.get(position));
        holder.above_item.setSlipListener(new AboveItemView.SlipListener() {
            @Override
            public void isSliped(int position, boolean isSliped) {

                slip_map.put(position, isSliped);
            }
        });


        return convertView;
    }

    class ViewHolder {
        AboveItemView above_item;

        ImageView img_icon;
        TextView user_name;
        CheckBox choose_icon;

        LinearLayout deleteLayout;
        //  ImageView primary_sign;
    }

}

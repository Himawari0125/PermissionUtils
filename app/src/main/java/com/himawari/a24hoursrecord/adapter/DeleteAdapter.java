package com.himawari.a24hoursrecord.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.himawari.a24hoursrecord.R;

/**
 * Created by S.Lee on 2017/11/23.
 */

public class DeleteAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    public DeleteAdapter(Context mcontext){
        inflater = LayoutInflater.from(mcontext);
    }
    @Override
    public int getCount() {
        return 10;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_delete,null);
        return convertView;
    }
}

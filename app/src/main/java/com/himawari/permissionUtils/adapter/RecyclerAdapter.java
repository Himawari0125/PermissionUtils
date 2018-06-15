package com.himawari.permissionUtils.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.HistoryListBean;
import com.himawari.permissionUtils.utils.TextViewUtils;

import java.util.List;

/**
 * Created by S.Lee on 2018/3/21.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private Context mContext;
    private List<HistoryListBean> datas;
    private OnRecyclerItemClick onRecyclerItemClick;
    public RecyclerAdapter(Context context, List<HistoryListBean> beans,OnRecyclerItemClick onRecyclerItemClick){
        this.mContext = context;
        this.datas = beans;
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_historymeasure,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        HistoryListBean bean = datas.get(position);
        String date = bean.getMesureDate();
        holder.date_tv.setText(TextViewUtils.setSpecifiedTvButtonSize(mContext,date,0,2,20, Color.BLACK));
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

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView date_tv;
        TextView time_tv;
        TextView weight_tv;
        TextView fatPercent_tv;
        ImageView timeBtn;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            date_tv = itemView.findViewById(R.id.textView7);
            time_tv = itemView.findViewById(R.id.textView8);
            weight_tv = itemView.findViewById(R.id.weight_tv);
            fatPercent_tv = itemView.findViewById(R.id.fat_percent);
            timeBtn = itemView.findViewById(R.id.imageView5);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onRecyclerItemClick.onItemClick(getAdapterPosition());
        }
    }

    public interface OnRecyclerItemClick{
        void onItemClick(int position);
    }
}

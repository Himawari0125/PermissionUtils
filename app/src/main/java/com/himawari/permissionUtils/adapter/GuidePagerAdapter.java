package com.himawari.permissionUtils.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.GuideBean;
import com.himawari.permissionUtils.views.GifView;
import com.himawari.permissionUtils.views.PointView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by S.Lee on 2018/1/8.
 */

public class GuidePagerAdapter extends PagerAdapter {
    private List<View> views;
    private LayoutInflater inflater;
    private FragmentActivity mActivity;
    private List<GuideBean> datas;

    public GuidePagerAdapter(FragmentActivity activity, Context context, List<GuideBean> beans){
        inflater = LayoutInflater.from(context);
        this.datas = beans;
        this.mActivity = activity;
        views = new ArrayList<>();
        for(int i = 0 ; i < datas.size();i++){
            views.add(getView(datas.get(i)));
        }
    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        GifView gifView = views.get(position).findViewById(R.id.gifView);
//        gifView.setPlaying(false);
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    private View getView(GuideBean bean){
        View view = inflater.inflate(R.layout.page_guide,null);
        TextView titleView = view.findViewById(R.id.title_tv);
        titleView.setText(bean.getTitleStr());
        TextView detailView = view.findViewById(R.id.detail_tv);
        detailView.setText(bean.getDetailStr());
        GifView gifView = view.findViewById(R.id.gifView);
        gifView.setGifSource(bean.getGifResource());


        return view;
    }

    public void playingItem(int position){
        for(int i = 0; i < views.size();i++){
            View viewToshow = views.get(i);
            GifView gifView = viewToshow.findViewById(R.id.gifView);
            if (position == i){
                PointView pointView = viewToshow.findViewById(R.id.pointView);
                pointView.setIsCurrent(position);
                if(position == 2 || position == 3)gifView.setCirculate(1);
                gifView.setPlaying(true);
            }else{
                gifView.setPlaying(false);
            }
        }

    }

    public void stopPlaying(){
        for(int i = 0; i < views.size();i++){
            View viewToshow = views.get(i);
            GifView gifView = viewToshow.findViewById(R.id.gifView);
            gifView.setPlaying(false);
            gifView.setNull();
        }
    }

}

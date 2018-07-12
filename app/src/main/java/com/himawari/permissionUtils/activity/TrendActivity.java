package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;


import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.TrendBean;
import com.himawari.permissionUtils.views.ScrollerLayout;
import com.himawari.permissionUtils.views.TrendView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by S.Lee on 2017/12/18.
 */

public class TrendActivity extends BaseActivity{
    private TrendView trendView;
    private ScrollerLayout scrollerLayout;
    private Float[] weights = new Float[]{50.9f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f
    ,50.9f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f};
    private  List<TrendBean> datas;
    private  List<TrendBean> datasM;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_trend);
        super.onCreate(savedInstanceState);
        LoadWeekDatas(true);
    }

    @Override
    protected void initView() {
        trendView = findViewById(R.id.trendView);
        scrollerLayout = findViewById(R.id.scrollLayout);

    }


    public void onWeightClick(View view){
        trendView.setTrendType(TrendView.TrendType.Weight);
    }

    public void onFatClick(View view){
        trendView.setTrendType(TrendView.TrendType.Fat);

    }

    public void onMuscleClick(View view) {
        trendView.setTrendType(TrendView.TrendType.Muscle);
    }


    private void LoadWeekDatas(boolean isScroll){
        Float[] weights = new Float[]{50.9f,49.2f,49.0f,50.0f,49.9f,48.0f};
        datas = new ArrayList<>();
        for(int i = 0 ; i < 6 ;i++){
            TrendBean bean = new TrendBean();
            bean.setScaleDate("12/1"+i);
            bean.setWeight(weights[i]);
            bean.setFat(20+i);
            bean.setMuscle(30+i);
            datas.add(bean);
        }

        scrollerLayout.reMeasure(datas.size(),isScroll);
        trendView.setDatas(datas,isScroll);
        trendView.setTrendType(TrendView.TrendType.Weight);

    }
    private void LoadMonthDatas(boolean isScroll){
        Float[] weights = new Float[]{50.9f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f
                ,50.9f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f};
        datasM = new ArrayList<>();
        for(int i = 0 ; i < 30 ;i++){
            TrendBean bean = new TrendBean();
            bean.setScaleDate("12/1"+i);
            bean.setWeight(weights[i]);
            bean.setFat(20+i);
            bean.setMuscle(30+i);
            datasM.add(bean);
        }


        scrollerLayout.reMeasure(datasM.size(),isScroll);
        trendView.setDatas(datasM,isScroll);
        trendView.setTrendType(TrendView.TrendType.Weight);
    }

    public void onWTrueClick(View view){

        LoadWeekDatas(true);
    }

    public void onMfalseClick(View view){

        LoadMonthDatas(false);
    }

    public void onWfalseClick(View view){
        LoadWeekDatas(false);
    }

    public void onMtrueClick(View view){
        LoadMonthDatas(true);
    }
}

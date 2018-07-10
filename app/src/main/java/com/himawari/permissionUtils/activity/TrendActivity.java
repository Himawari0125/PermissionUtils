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
    private TrendView trendView,trendViewM;
    private ScrollerLayout scrollerLayout,scrollerLayoutM;
    private Float[] weights = new Float[]{50.9f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f
    ,50.9f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f};
    private  List<TrendBean> datas;
    private  List<TrendBean> datasM;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_trend);
        super.onCreate(savedInstanceState);
        LoadWeekDatas();
    }

    @Override
    protected void initView() {
        trendView = findViewById(R.id.trendView);
        scrollerLayout = findViewById(R.id.scrollLayout);

        trendViewM = findViewById(R.id.trendViewM);
        scrollerLayoutM = findViewById(R.id.scrollLayoutM);
    }


    public void onWeightClick(View view){

        trendView.setTrendType(TrendView.TrendType.Weight);
        trendViewM.setTrendType(TrendView.TrendType.Weight);
    }

    public void onFatClick(View view){
        trendView.setTrendType(TrendView.TrendType.Fat);
        trendViewM.setTrendType(TrendView.TrendType.Fat);

    }

    public void onMuscleClick(View view){
        trendView.setTrendType(TrendView.TrendType.Muscle);
        trendViewM.setTrendType(TrendView.TrendType.Muscle);
    }


    private void LoadWeekDatas(){
        Float[] weights = new Float[]{50.9f,49.2f,49.0f,50.0f,49.9f};
        datas = new ArrayList<>();
        for(int i = 0 ; i < 5 ;i++){
            TrendBean bean = new TrendBean();
            bean.setScaleDate("12/1"+i);
            bean.setWeight(weights[i]);
            bean.setFat(20+i);
            bean.setMuscle(30+i);
            datas.add(bean);
        }
        Log.i("----week----","-----------");
        scrollerLayout.reMeasure(datas.size());
        trendView.setDatas(datas,false);
        trendView.setTrendType(TrendView.TrendType.Weight);

    }
    private void LoadMonthDatas(){
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

        Log.i("----month----","-----------");
        scrollerLayoutM.reMeasure(datasM.size());
        trendViewM.setDatas(datasM,true);
        trendViewM.setTrendType(TrendView.TrendType.Weight);
    }

    public void onWeekClick(View view){
        scrollerLayout.setVisibility(View.VISIBLE);
        scrollerLayoutM.setVisibility(View.INVISIBLE);
        LoadWeekDatas();
    }

    public void onMonthclick(View view){
        scrollerLayoutM.setVisibility(View.VISIBLE);
        scrollerLayout.setVisibility(View.INVISIBLE);
        LoadMonthDatas();
    }
}

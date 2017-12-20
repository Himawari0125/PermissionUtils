package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.TrendBean;
import com.himawari.permissionUtils.views.TrendView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by S.Lee on 2017/12/18.
 */

public class TrendActivity extends BaseActivity{
    private TrendView trendView;
    private Float[] weights = new Float[]{50.9f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f
    ,50.9f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f,50.3f,49.2f,49.0f,50.0f,49.9f};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_trend);
        super.onCreate(savedInstanceState);
        List<TrendBean> datas = new ArrayList<>();
        for(int i = 0 ; i < 30 ;i++){
            TrendBean bean = new TrendBean();
            bean.setScaleDate("12/1"+i);
            bean.setWeight(weights[i]);
            bean.setFat(20+i);
            bean.setMuscle(30+i);
            datas.add(bean);
        }
        trendView.setDatas(datas);
        trendView.setTrendType(TrendView.TrendType.Weight);
    }

    @Override
    protected void initView() {
        trendView = findViewById(R.id.trendView);
    }


    public void onWeightClick(View view){
        trendView.setTrendType(TrendView.TrendType.Weight);
    }

    public void onFatClick(View view){
        trendView.setTrendType(TrendView.TrendType.Fat);
    }

    public void onMuscleClick(View view){
        trendView.setTrendType(TrendView.TrendType.Muscle);
    }
}

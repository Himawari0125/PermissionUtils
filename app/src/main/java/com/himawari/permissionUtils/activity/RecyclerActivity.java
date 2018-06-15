package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.adapter.RecyclerAdapter;
import com.himawari.permissionUtils.bean.HistoryListBean;
import com.himawari.permissionUtils.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_FLING;
import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;

/**
 * Created by S.Lee on 2018/3/21.
 */

public class RecyclerActivity extends BaseActivity {
    private RecyclerView recycler;
    private RecyclerAdapter adapter;
    private List<HistoryListBean> beans;

    private RecyclerAdapter.OnRecyclerItemClick onRecyclerItemClick = new RecyclerAdapter.OnRecyclerItemClick() {
        @Override
        public void onItemClick(int position) {
            LogUtils.i("position:"+position);
        }
    };

    @Override
    protected void initView() {
        recycler = findViewById(R.id.recyclerView);
        beans = new ArrayList<>();
        String[] dates = new String[]{"今天","昨天","1112月","1012月","0912月","0812月","0712月"
                ,"0612月","0512月","0412月","0312月","0212月","1212月","1112月","1012月","1012月"
                ,"0912月","0812月","0712月","0612月","0512月","0412月","0312月","0212月","0112月",
                "今天","昨天","1112月","1012月","0912月","0812月","0712月"
                ,"0612月","0512月","0412月","0312月","0212月","1212月","1112月","1012月","1012月"
                ,"0912月","0812月","0712月","0612月","0512月","0412月","0312月","0212月","0112月"};
        Random random = new Random();
        for(int i = 0 ; i < 50;i++){
            HistoryListBean bean = new HistoryListBean();
            bean.setFatPercent(random.nextInt(100)+"");
            if(i%5==0||i%3==0)
                bean.setFirstMeasure(true);
            else
                bean.setFirstMeasure(false);
            bean.setMeasureTime("11:1"+i);
            bean.setMesureDate(dates[i]);
            bean.setWeight(random.nextInt(100)+"");
            beans.add(bean);
        }
        adapter = new RecyclerAdapter(this,beans,onRecyclerItemClick);
        recycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                boolean lastPosition = recyclerView.canScrollVertically(1);
                if(!lastPosition&&newState==SCROLL_STATE_IDLE){//最后一个item
                    LogUtils.i("up:"+newState);
                }
            }
        });





    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_recycler);
        super.onCreate(savedInstanceState);
    }
}

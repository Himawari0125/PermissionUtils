package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.adapter.HistoryAdapter;
import com.himawari.permissionUtils.bean.HistoryListBean;
import com.himawari.permissionUtils.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by S.Lee on 2018/3/21.
 */

public class ListViewActivity extends BaseActivity {

    private List<HistoryListBean> beans;
    private ListView listView;
    private HistoryAdapter adapter;
    private SwipeRefreshLayout swipe_layout;
    private boolean canRefresh;
    private boolean isTouchScroll;
    @Override
    protected void initView() {
        listView = findViewById(R.id.listview);
        swipe_layout = findViewById(R.id.swipe_layout);
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

        adapter = new HistoryAdapter(this,beans);
        listView.setAdapter(adapter);
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               LogUtils.i(LogUtils.originalIndex," first:"+listView.getFirstVisiblePosition()+" position:"+position);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch(scrollState){//判断是否是滑动还是拉取
                    case SCROLL_STATE_FLING:
                        isTouchScroll = false;
                        break;
                    case SCROLL_STATE_IDLE:
                        canRefresh = isTouchScroll;
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        isTouchScroll = true;
                        break;
                }
                int lastPosition = view.getLastVisiblePosition();
                int firstPosition = view.getFirstVisiblePosition();

                if(scrollState == SCROLL_STATE_IDLE&&canRefresh){
                    if(lastPosition == beans.size()-1){//最后一个item
                       // PullUpRefresh();
                    }
//                    else if(firstPosition == 0){
//
//                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_list);
        super.onCreate(savedInstanceState);
    }
}

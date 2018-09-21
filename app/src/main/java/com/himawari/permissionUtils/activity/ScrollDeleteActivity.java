package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.adapter.ScrollerDeleteAdapter;
import com.himawari.permissionUtils.bean.ScrollDeleteBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by S.Lee on 2017/12/29.
 */

public class ScrollDeleteActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<ScrollDeleteBean> scrollDeleteBeans;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_scrolldelete);
        super.onCreate(savedInstanceState);
        ScrollerDeleteAdapter adapter = new ScrollerDeleteAdapter(this,scrollDeleteBeans);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void initView() {

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        scrollDeleteBeans = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i ++){
            ScrollDeleteBean bean = new ScrollDeleteBean();
            bean.setTitle("title:"+i);
            bean.setDetail("detail:"+i);
            scrollDeleteBeans.add(bean);
        }

    }
}

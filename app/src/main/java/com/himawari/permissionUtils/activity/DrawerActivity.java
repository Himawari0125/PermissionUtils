package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.TitleDetailBean;
import com.himawari.permissionUtils.adapter.DrawerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by S.Lee on 2017/12/13.
 */

public class DrawerActivity extends BaseActivity {
    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_drawer);

    }

    @Override
    protected void initView() {
        listView = findViewById(R.id.listview);
        List<TitleDetailBean> data = new ArrayList<>();
        for(int i = 0 ; i < 20; i++){
            TitleDetailBean bean = new TitleDetailBean();
            bean.setTitle("问题"+i+"：问题问题问题问题问题问题问题？");
            bean.setDetail("回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答回答"+i);
            data.add(bean);
        }
        DrawerAdapter adapter = new DrawerAdapter(this,data);
        listView.setAdapter(adapter);

    }
}

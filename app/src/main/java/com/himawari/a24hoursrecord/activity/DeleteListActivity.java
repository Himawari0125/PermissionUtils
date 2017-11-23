package com.himawari.a24hoursrecord.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.himawari.a24hoursrecord.BaseActvity;
import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.adapter.DeleteAdapter;
import com.himawari.a24hoursrecord.views.DeleteViewGroup;

/**
 * Created by S.Lee on 2017/11/22.
 */

public class DeleteListActivity extends BaseActvity {
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_deletelist);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        listView = (ListView)findViewById(R.id.listview);
        DeleteAdapter adapter = new DeleteAdapter(this);
        listView.setAdapter(adapter);



    }


}

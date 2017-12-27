package com.himawari.permissionUtils.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by S.Lee on 2017/12/27.
 */

public class MainViewActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private List<String> items;
    private String[] itemStr = new String[]{"Main","Scaleplate"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_mainview);
        super.onCreate(savedInstanceState);

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initView() {
        items = Arrays.asList(itemStr);

        listView = findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,ScalePlateActivity.class));
                break;
        }

    }
}

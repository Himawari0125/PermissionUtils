package com.himawari.a24hoursrecord.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.himawari.a24hoursrecord.BaseActvity;
import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.adapter.DeleteAdapter;
import com.himawari.a24hoursrecord.views.SidesLiplistView;
import com.himawari.a24hoursrecord.views.TitleView;

import java.util.Map;

/**
 * Created by S.Lee on 2017/11/22.
 */

public class DeleteListActivity extends BaseActvity {
    private SidesLiplistView listView;
    private TitleView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_deletelist);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        listView = (SidesLiplistView)findViewById(R.id.listview);
        final DeleteAdapter adapter = new DeleteAdapter(this);
        listView.setAdapter(adapter);
        title = (TitleView) findViewById(R.id.title);
        title.setRightIconClickListener(new TitleView.rightIconClickListener() {
            @Override
            public void rightIconClick() {

            }

            @Override
            public void rightTextViewClick(boolean isEdit) {
                adapter.setChoose(isEdit);
                if(!isEdit)
                    for(Map.Entry<Integer,Boolean> entry:adapter.getCheckedBox().entrySet()){
                        Log.i("--------",entry.getKey()+" : "+entry.getValue());
                    }
                    adapter.initCheckMap();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("-------------","item");
            }
        });


    }


}

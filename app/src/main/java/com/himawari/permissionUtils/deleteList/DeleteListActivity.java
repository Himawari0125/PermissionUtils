package com.himawari.permissionUtils.deleteList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.AccManageBean;
import com.himawari.permissionUtils.commons.Constant;
import com.himawari.permissionUtils.views.TitleView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by S.Lee on 2017/11/22.
 */

public class DeleteListActivity extends BaseActivity {
    private SidesLiplistView listView;
    private TitleView title;
    private ArrayList<AccManageBean> beans;
    private DeleteAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_deletelist);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("-------------","item"+" id:"+id+" position:"+position);
            }
        });
        beans = new ArrayList<>();
        Random random = new Random(47);
        String[] names = new String[]{"赵","钱","孙","王","李","独孤","慕容","西门","阿部"};
        String[] lastnames = new String[]{"吹雪","匡胤","世民","子","嘉尔","金宝","云海","擦擦","宽"};
        for(int i = 0 ; i < 10;i++){
            AccManageBean bean = new AccManageBean();

            bean.setUser_name(names[random.nextInt(names.length)]+lastnames[random.nextInt(lastnames.length)]);
            AccManageBean.Data data = new AccManageBean().new Data();

            bean.setData(data);
            bean.setIsChild(i == 0?true:false);

            bean.setChecked(false);
            bean.setLastItem(false);

            beans.add(bean);
        }

        //添加item
        AccManageBean accManageBean = new AccManageBean();
        accManageBean.setLastItem(true);
        accManageBean.setUser_name("添加新账号");
        beans.add(accManageBean);

       adapter = new DeleteAdapter(this,beans, listView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int)v.getTag();
                beans.remove(position);
                adapter.setDatas(beans,false);
                Log.i("delete","item_"+position);
            }
        });
        listView.setAdapter(adapter);


        title = findViewById(R.id.title);
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




    }



}

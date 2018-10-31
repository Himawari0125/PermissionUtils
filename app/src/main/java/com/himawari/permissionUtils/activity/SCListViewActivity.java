package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.MyApplication;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.adapter.HistoryAdapter;
import com.himawari.permissionUtils.bean.HistoryListBean;
import com.himawari.permissionUtils.utils.ScrollCaptureUtils;
import com.himawari.permissionUtils.views.TitleView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kokosnuss on 2018/10/23
 */
public class SCListViewActivity extends BaseActivity {

    private TitleView titleView;
    private List<HistoryListBean> beans;
    private ListView listView;
    private HistoryAdapter adapter;
    private ScrollCaptureUtils scrollCaptureUtils;

    private ScrollCaptureUtils.CaptureListener captureListener = new ScrollCaptureUtils.CaptureListener() {
        @Override
        public void onCaptureFile(File file) {
            Toast.makeText(SCListViewActivity.this,"截图完成",Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void initView() {
        listView = findViewById(R.id.listview);
        titleView = findViewById(R.id.titleView);


        beans = new ArrayList<>();
        String[] dates = new String[]{"今天","昨天","1112月","1012月","0912月","0812月","0712月"
                ,"0612月","0512月","0412月","0312月","0212月","1212月","1112月","1012月","1012月"
                ,"0912月","0812月","0712月","0612月","0512月","0412月","0312月","0212月","0112月",
                "今天","昨天","1112月","1012月","0912月","0812月","0712月"
                ,"0612月","0512月","0412月","0312月","0212月","1212月","1112月","1012月","1012月"
                ,"0912月","0812月","0712月","0612月","0512月","0412月","0312月","0212月","0112月"};
        Random random = new Random();
        for(int i = 0 ; i < 10;i++){
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
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_sclist);
        super.onCreate(savedInstanceState);

        titleView.post(new Runnable() {
            @Override
            public void run() {
                int usefulHeight = (int) (MyApplication.height ) - titleView.getMeasuredHeight() - MyApplication.statusBarHeight;
                scrollCaptureUtils = new ScrollCaptureUtils.Builder()
                        .setContent(SCListViewActivity.this)
                        .setListView(listView)
                        .setUsefulHeight(usefulHeight)
                        .setCaptureListener(captureListener)
                        .builder();
            }
        });


        titleView.setRightIconClickListener(new TitleView.rightIconClickListener() {
            @Override
            public void rightIconClick() {
                scrollCaptureUtils.scrollCapture();
            }

            @Override
            public void rightTextViewClick(boolean isEdit) {

            }
        });
    }


}

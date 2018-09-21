package com.himawari.permissionUtils.activity;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.adapter.AccChoosePopAdapter;
import com.himawari.permissionUtils.bean.AccManageBean;
import com.himawari.permissionUtils.utils.LogUtils;
import com.himawari.permissionUtils.utils.PopupWindowUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListRefreshActivity extends BaseActivity implements View.OnClickListener{
    private Button btn;
    private List<AccManageBean> familyList;
    private  AccChoosePopAdapter adapter;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            LogUtils.i(LogUtils.originalIndex," mhandlerLooperThread:"+this.getLooper().getThread());
            familyList.clear();

            resetList(9);

//            PopupWindowUtils.showAccountView(ListRefreshActivity.this,familyList, btn,new PopupWindowUtils.AccSelectListener() {
//                @Override
//                public void onAccSelect(int userId) {
//
//                }
//
//                @Override
//                public void onAddAcc() {
//
//                }
//            });
        }
    };
    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listrefresh);
        btn = findViewById(R.id.button33);
        btn.setOnClickListener(this);
        
        familyList = new ArrayList<>();
        resetList(3);
        LogUtils.i(LogUtils.originalIndex," MainLooperThread:"+ Looper.getMainLooper().getThread());
        LogUtils.i(LogUtils.originalIndex," onCreateLooperThread:"+ Looper.myLooper().getThread());
    }

    @Override
    public void onClick(View v) {
        LogUtils.i(LogUtils.originalIndex," id:"+v.getId()+" "+R.id.button33);
        switch (v.getId()){
            case R.id.button33:
                adapter = PopupWindowUtils.showAccountView(ListRefreshActivity.this,familyList, btn,new PopupWindowUtils.AccSelectListener() {
                    @Override
                    public void onAccSelect(int userId) {
                        
                    }

                    @Override
                    public void onAddAcc() {

                    }
                });

                mHandler.sendEmptyMessageDelayed(0,3000);
                //setInTask();
                //setInThread();
                break;
        }
    }

    private void setInTask(){
        TimerTask task = new TimerTask(){
            public void run(){

                LogUtils.i(LogUtils.originalIndex," setInTaskThread:"+this);
                familyList.clear();
                resetList(8);


            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 1000);

    }

    private void setInThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtils.i(LogUtils.originalIndex," setInThreadThread:"+this);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                familyList.clear();
                resetList(5);

            }
        });
        thread.start();
    }

    private void resetList(int n){
        //关闭item
        AccManageBean cloaseManageBean = new AccManageBean();
        familyList.add(cloaseManageBean);


        for(int i = 0 ; i < n ;i++){
            AccManageBean bean = new AccManageBean();
            bean.setUser_name("RefreshUser_"+i);
            bean.setIsChild(i/2==0?true:false);
            AccManageBean.Data data = new AccManageBean().new Data();
            data.setBindUserId(i);
            bean.setData(data);
            familyList.add(bean);
        }
        //添加item
        AccManageBean accManageBean = new AccManageBean();
        familyList.add(accManageBean);

        if (adapter!=null)
            adapter.notifyDataSetChanged();
    }





}

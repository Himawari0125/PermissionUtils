package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.adapter.GuidePagerAdapter;
import com.himawari.permissionUtils.bean.GuideBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by S.Lee on 2018/1/5.
 */

public class GuideActivity extends BaseActivity {


    ViewPager viewPager;

    private List<GuideBean> beans;
    private GuidePagerAdapter guidePagerAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
        super.onCreate(savedInstanceState);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                guidePagerAdapter.playingItem(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        Button btn = findViewById(R.id.loginOrRegister);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guidePagerAdapter.stopPlaying();

            }
        });
    }

    @Override
    public void initView() {
        loadDatas();

        viewPager = findViewById(R.id.viewpager);
        guidePagerAdapter = new GuidePagerAdapter(GuideActivity.this,this,beans);
        guidePagerAdapter.playingItem(0);
        viewPager.setAdapter(guidePagerAdapter);

    }


    public void loadDatas() {
        beans = new ArrayList<>();
        GuideBean bean0 = new GuideBean();
        bean0.setTitleStr(getResources().getString(R.string.twenty_title));
        bean0.setDetailStr(getResources().getString(R.string.twenty_detail));
        bean0.setGifResource(R.raw.twenty_gif);
        beans.add(bean0);

//        GuideBean bean1 = new GuideBean();
//        bean1.setTitleStr(getResources().getString(R.string.account_title));
//        bean1.setDetailStr(getResources().getString(R.string.account_detail));
//        bean1.setGifResource(R.raw.multi_account_gif);
//        beans.add(bean1);
//
//
//        GuideBean bean2 = new GuideBean();
//        bean2.setTitleStr(getResources().getString(R.string.health_title));
//        bean2.setDetailStr(getResources().getString(R.string.health_detail));
//        bean2.setGifResource(R.raw.health_trend_gif);
//
//        beans.add(bean2);
//
//        GuideBean bean3 = new GuideBean();
//        bean3.setTitleStr(getResources().getString(R.string.share_title));
//        bean3.setDetailStr(getResources().getString(R.string.share_detail));
//        bean3.setGifResource(R.raw.share_gif);
//        beans.add(bean3);

    }

}

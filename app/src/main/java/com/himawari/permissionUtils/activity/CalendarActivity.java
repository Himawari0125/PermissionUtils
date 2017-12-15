package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.bean.CalenderPickerBean;
import com.himawari.permissionUtils.views.CalenderPickerView;

import java.util.Calendar;

/**
 * Created by S.Lee on 2017/12/15.
 */

public class CalendarActivity extends BaseActivity implements View.OnClickListener{
    private ImageView proMonth_btn,nextMonth_btn;
    private CalenderPickerView calendarView;
    private TextView monthYear;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.dialog_datechoose);
        super.onCreate(savedInstanceState);
        proMonth_btn = findViewById(R.id.imageView2);
        nextMonth_btn = findViewById(R.id.imageView3);
        monthYear = findViewById(R.id.textView3);
        proMonth_btn.setOnClickListener(this);
        nextMonth_btn.setOnClickListener(this);
        calendarView = findViewById(R.id.calenderPickerView);
        long times = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(times);
        calendarView.setCurrentTime(calendar);
        monthYear.setText(calendar.get(Calendar.YEAR)+getResources().getString(R.string.year)+
                (calendar.get(Calendar.MONTH)+1)+getResources().getString(R.string.month));

        calendarView.setSelectedListener(new CalenderPickerView.selectListener() {
            @Override
            public void onSelectedListener(CalenderPickerBean bean) {

            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageView2:
                monthYear.setText(calendarView.proMonth());
                break;
            case R.id.imageView3:
                monthYear.setText(calendarView.nextMonth());
                break;
        }
    }
}

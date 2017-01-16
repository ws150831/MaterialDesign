package com.six.history;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.six.history.bean.RiQiBean;

import org.greenrobot.eventbus.EventBus;

public class DateActivity extends AppCompatActivity
{

    private MaterialCalendarView materialCalendarView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("选择日期");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));


        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            //2.让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        //获得日期的控件
        materialCalendarView= (MaterialCalendarView) findViewById(R.id.calendarView);
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected)
            {
                int mYear = date.getYear();
                int mMonth = date.getMonth() + 1;
                int mDay = date.getDay();

                //点击日期后吧值传过去把今天的日期传过去
                EventBus.getDefault().post(new RiQiBean(mYear, mMonth, mDay));



            }
        });
        //日期确定按钮
        btn= (Button) findViewById(R.id.btn_sure);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;

        }
        return true;
    }




}

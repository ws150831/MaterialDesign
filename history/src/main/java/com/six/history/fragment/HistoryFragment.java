package com.six.history.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.six.history.DetailsActivity;
import com.six.history.ImageActivity;
import com.six.history.R;
import com.six.history.adapter.AnimalAdapter;
import com.six.history.adapter.HistoryAdapter;
import com.six.history.bean.HistoryStatus;
import com.six.history.bean.RiQiBean;
import com.six.history.utils.OkHttp;
import com.six.history.view.CircleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2016/12/16/016.
 */

public class HistoryFragment extends Fragment implements View.OnClickListener
{

    //左右返回箭头图片
    private ImageView left,right;
    //中间日期
    private TextView data;
    //历史事件接口

    //历史事件的适配器
    private HistoryAdapter historyAdapter;

    private View history;
    //卡片式图片
    private CircleView circleview;
    //请求的数据对象
    private List<HistoryStatus.ResultBean> re;
//    private SimpleDateFormat dateFormat;
//    private Calendar mCalendar = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
    private String url="http://v.juhe.cn/todayOnhistory/queryEvent.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&date=";
    //下拉刷新
    private SwipeRefreshLayout swipe;
    private MaterialCalendarView materialCalendarView;
    private int currentYear;
    private int currentMonth;
    private int currentDay;

    //！！！！！！！！！！！RecyclerView的监听事件工具类
    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener{
        public interface OnItemClickListener {
            void onItemClick(View view, int position);
            void onItemLongClick(View view, int position);
        }
        private OnItemClickListener mListener;
        private GestureDetector mGestureDetector;
        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
                @Override
                public void onLongPress(MotionEvent e) {
                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (childView != null && mListener != null) {
                        mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
                    }
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            }
            return false;
        }
        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        //注册EventBus
        EventBus.getDefault().register(this);


        history=inflater.inflate(R.layout.history_fragment, null);


        circleview=(CircleView)history.findViewById(R.id.circleview);
         materialCalendarView= (MaterialCalendarView) history.findViewById(R.id.calendarView);

        //设置日期
        changeDate();

        return history;
    }

    private void initDatas(int i,int y)
    {
        OkHttp.getAsync(url+(i+1)+"/"+y+"", new OkHttp.DataCallBack()
        {
            @Override
            public void requestFailure(Request request, IOException e)
            {

            }

            @Override
            public void requestSuccess(String result) throws Exception
            {

                Gson gson=new Gson();

                HistoryStatus historystatus=gson.fromJson(result,HistoryStatus.class);

                re=(List<HistoryStatus.ResultBean>)historystatus.getResult();

                RecyclerView recy= (RecyclerView) history.findViewById(R.id.recycler);

                recy.setLayoutManager(new LinearLayoutManager(getActivity()));

                historyAdapter=new HistoryAdapter(getActivity(),re);
                recy.setAdapter(historyAdapter);


                historyAdapter.setOnItemClickLitener(new HistoryAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent=new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra("eid",re.get(position).getE_id());
                        intent.putExtra("data",re.get(position).getDate());
                        intent.putExtra("title",re.get(position).getTitle());
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });



            }
        });
    }

    //日期
    private void changeDate() {
        data = (TextView)history.findViewById(R.id.tv_date);
        data.setTextColor(getResources().getColor(android.R.color.white));

        //左边的箭头
        left = (ImageView)history.findViewById(R.id.image_left);
        left.setColorFilter(Color.WHITE);
        left.setOnClickListener(this);

        //右边的箭头
        right = (ImageView)history.findViewById(R.id.image_right);
        right.setColorFilter(Color.WHITE);
        right.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        //获取年
       currentYear = calendar.get(Calendar.YEAR);
        //获取月
        currentMonth = calendar.get(Calendar.MONTH);
        //获取日
       currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        //设置到textview上面
        data.setText(currentYear + "年" + (currentMonth + 1) + "月" + currentDay + "日");

//        //格式化日期
//        dateFormat = new SimpleDateFormat("yyyy"+"年"+"MM"+"月"+"dd"+"日");
//        data.setText(dateFormat.format(mCalendar.getTime()));
        //获取月份，0表示1月份
//        int month=mCalendar.get(Calendar.MONTH)+1;
//        int day=mCalendar.get(Calendar.DAY_OF_MONTH);
        //网络拼接的接口


          /*
        *网络请求
        **/
        initDatas(currentMonth,currentDay);


    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.image_left:
                currentMonth++;
                if (currentMonth == 3) {//如果当前的月份==3月
                    currentDay--;//那么当前的天数--
                    if (currentDay < 1) {//如果当前的天数小于1
                        if ((currentYear % 4 == 0 && currentYear % 100 != 0) || currentYear % 400 == 0) {
                            //闰年
                            currentDay = 29;//当前的天数=29天
                            currentMonth--;//当前的月份减1
                        } else {
                            //平年
                            currentDay = 28;
                            currentMonth--;
                        }
                    }
                } else if (currentMonth == 1) {//如果当前的月份是1月
                    currentDay--;//当前的天数-1
                    if (currentDay < 1) {//如果当前的天数小于1
                        currentDay = 31;//当前的天数=31天
                        currentMonth = 12;//当前的月份是12月
                        currentYear--;//当前的年数-1
                    }
                } else if (currentMonth == 2 || currentMonth == 4 || currentMonth == 6 || currentMonth == 8 || currentMonth == 9 || currentMonth == 11) {
                    currentDay--;
                    Log.d("eeeeeeeeeeeeeee", currentDay + "");
                    if (currentDay < 1) {
                        currentDay = 31;
                        currentMonth--;
                    }

                } else {
                    currentDay--;
                    if (currentDay < 1) {
                        currentDay = 30;
                        currentMonth--;
                    }
                }
                currentMonth--;
                data.setText(currentYear + "年" + (currentMonth + 1) + "月" + currentDay + "日");

                initDatas(currentMonth, currentDay);

//                //取当前日期的前一天.
//                mCalendar.add(Calendar.DAY_OF_MONTH,-1);
//                changeDate(mCalendar);
                break;

            case R.id.image_right:
                currentMonth++;
                if (currentMonth == 2) {
                    //二月
                    if ((currentYear % 4 == 0 && currentYear % 100 != 0) || currentYear % 400 == 0) {
                        //闰年
                        currentDay++;
                        if (currentDay > 29) {
                            currentDay = 1;
                            currentMonth++;
                        }

                    } else {
                        //平年
                        currentDay++;
                        if (currentDay > 28) {
                            currentDay = 1;
                            currentMonth++;
                        }
                    }

                } else if (currentMonth == 1 || currentMonth == 3 || currentMonth == 5 || currentMonth == 6 || currentMonth == 8 || currentMonth == 10) {
                    //大月
                    currentDay++;
                    if (currentDay > 31) {
                        currentDay = 1;
                        currentMonth++;
                    }


                } else if (currentMonth == 12) {
                    currentDay++;
                    if (currentDay > 31) {
                        currentDay = 1;
                        currentMonth = 1;
                        currentYear++;
                    }

                } else {
                    //小月
                    currentDay++;
                    if (currentDay > 30) {//当前的天数大于30
                        currentDay = 1;
                        currentMonth++;
                    }
                }
                currentMonth--;
                data.setText(currentYear + "年" + (currentMonth + 1) + "月" + currentDay + "日");
                initDatas(currentMonth, currentDay);

//                //取当前日期的后一天.
//                mCalendar.add(Calendar.DAY_OF_MONTH,+1);
//                changeDate(mCalendar);
                break;
        }

    }

    //得到值
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void chuanZhi(RiQiBean event) {
        int nian = event.getNian();
        int yue1 = event.getYue();
        int ri1 = event.getRi();

        currentYear = nian;
        currentMonth = yue1;
        currentDay = ri1;

        data.setText(currentYear + "年" + currentMonth + "月" + currentDay + "日");

        if (currentMonth <= 12) {
            currentMonth--;
        }

        initDatas(currentMonth, currentDay);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}

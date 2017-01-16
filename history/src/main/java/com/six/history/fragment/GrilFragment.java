package com.six.history.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.six.history.ImageActivity;
import com.six.history.R;
import com.six.history.adapter.AnimalAdapter;
import com.six.history.bean.Status;
import com.six.history.utils.OkHttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2016/12/16/016.
 */

public class GrilFragment extends Fragment
{

    private List<Status.ResultsBean> bean=new ArrayList<>();
    private AnimalAdapter adapter;
    private String url="http://gank.io/api/data/%E7%A6%8F%E5%88%A9";
    private int month=1;
    private int day=16;
    private boolean isLoadMore = true;
    private View gril;
    private ArrayList<Status.ResultsBean> mlist=new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerview;

    private MaterialRefreshLayout mRefreshLayout;
   private Handler myHandler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           Status s= (Status) msg.obj;
           mlist.addAll(s.getResults());


           if(adapter==null)
           {
               recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
               adapter =new AnimalAdapter(getActivity(),mlist);

               recyclerview.setAdapter(adapter);



           }else {
               adapter.notifyDataSetChanged();
           }

       }
   };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
       gril=inflater.inflate(R.layout.girl_fragment, null);

        initView();
        return gril;

    }

    private void initView()
    {
        //得到recyclerview控件
       recyclerview= (RecyclerView) gril.findViewById(R.id.recyclerview);
        mRefreshLayout = (MaterialRefreshLayout)gril.findViewById(R.id.refresh);


        initInternet(day,month);

        /**
         * 设置是否上拉加载更多，默认是false，要手动改为true，要不然不会出现上拉加载
         */
        mRefreshLayout.setLoadMore(isLoadMore);

        mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            /**
             * 刷新的方法，我这里演示的是下拉刷新，因为没有数据，我这里也就只是toast一下
             * 如果想要实现你们自己要的结果，就会在定义一个方法，获取最新数据，或者是在次
             * 在这里调用之前获取数据的方法，以达到刷新数据的功能
             * @param materialRefreshLayout
             */
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //一般加载数据都是在子线程中，这里我用到了handler
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this, "已经没有更多数据了", Toast.LENGTH_SHORT).show();
                        /**
                         * 刷新完成后调用此方法，要不然刷新效果不消失
                         */
                        adapter.notifyDataSetChanged();
                        bean.clear();

                        initInternet(day,month++);
                        mRefreshLayout.finishRefresh();

                    }
                }, 3000);
            }

            /**
             * 上拉加载更多的方法，在这里我只是简单的模拟了加载四条数据
             * 真正用的时候，就会去定义方法，获取数据，一般都是分页，在数据端获取的时候
             * 把页数去增加一，然后在去服务端去获取数据
             * @param materialRefreshLayout
             */


            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoadMore = false;

//                                //通知刷新
//                                mAdapter.addLists(mAdapter.getLists().size(), mList);
                        //mRecyclerView.scrollToPosition(mAdapter.getLists().size());
                        /**
                         * 完成加载数据后，调用此方法，要不然刷新的效果不会消失
                         */


                        initInternet(day,month++);
                        mRefreshLayout.finishRefreshLoadMore();

                    }
                }, 3000);
            }
        });


    }

    private void initInternet(int day,int month)
    {

        OkHttp.getAsync(url+"/"+day+"/"+month, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e)
            {

            }

            @Override
            public void requestSuccess(String result) throws Exception
            {
                Gson gson=new Gson();

               Status status=gson.fromJson(result,Status.class);
                Message msg= Message.obtain();
                msg.obj=status;
                myHandler.sendMessage(msg);
                bean=status.getResults();
//                for (int i = 0;i<status.getResults().size();i++) {
//                    bean.add(status.getResults().get(i));
//                }




                //点击事件
                adapter.setOnItemClickListener(new AnimalAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, String bean) {
                        Intent intent=new Intent(getActivity(), ImageActivity.class);
                        intent.putExtra("url",bean);

                        startActivity(intent);


                    }
                });



            }
        });
    }




}

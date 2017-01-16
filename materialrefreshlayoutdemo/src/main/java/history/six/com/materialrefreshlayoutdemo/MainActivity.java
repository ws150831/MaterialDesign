package history.six.com.materialrefreshlayoutdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity
{

    private MaterialRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;


    /**
     * 一个承接数据的数组
    */
    List<Status.ResultsBean> list = new ArrayList<>();
    private List<String> mList = new ArrayList<>();
    private MyAdapter mAdapter=new MyAdapter(list,MainActivity.this);

    /**
     * 在上拉刷新的时候，判断，是否处于上拉刷新，如果是的话，就禁止在一次刷新，保障在数据加载完成之前
     * 避免重复和多次加载
     */
    private boolean isLoadMore = true;
    String url="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/16/4";

    private Status status;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();



    }

    /**
     * 初始化布局控件
     */
    private void initView() {

        mRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);
        initRefresh();





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

                        list.clear();
                        mAdapter.notifyDataSetChanged();
                        url="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/16/9";
                        initRefresh();
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
                        url="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/16/8";
                        initRefresh();
                        mRefreshLayout.finishRefreshLoadMore();
                    }
                }, 3000);
            }
        });
    }

    /**
     * 初始化加载
     */
    private void initRefresh() {

        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception
            {
                  Gson gson=new Gson();
                status =gson.fromJson(result,Status.class);
//                list = status.getResults();
                for (int i = 0; i < status.getResults().size(); i++) {
                    list.add(status.getResults().get(i));
                }


                mRecyclerView.setAdapter(mAdapter);
                // mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

            }
        });

    }


}

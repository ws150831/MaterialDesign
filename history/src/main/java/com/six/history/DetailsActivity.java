package com.six.history;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.six.history.bean.ImageStatus;
import com.six.history.bean.ImageUrl;
import com.six.history.bean.RiQiBean;
import com.six.history.dao.CollectionDao;
import com.six.history.dao.ScUserDao;
import com.six.history.utils.OkHttp;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

public class DetailsActivity extends AppCompatActivity
{

    private List<ImageStatus.ResultBean> list1;
    private List<ImageStatus.ResultBean.PicUrlBean> list2;
    private String path;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView tvContent;
    private ImageView hisimageView;
    private FloatingActionButton shoucang;
    private ScUserDao scdao;
    private String images;
    private CollectionDao collectionDao;
    boolean flag = false;
    private ImageStatus imageStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        //得到Intent对象
        Intent intent=getIntent();

        final String id=intent.getStringExtra("eid");
       final  String data=intent.getStringExtra("data");
        final String title=intent.getStringExtra("title");

        path="http://v.juhe.cn/todayOnhistory/queryDetail.php?key=243a96cd7eb7dee275207810a781e9d4&e_id="+id;
        //得到历史事件的标题
        String historyTitle=intent.getStringExtra("htitle");

        //得到图片的网址
        String historyUrl=intent.getStringExtra("hurl");




        //找到ToolBar控件
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_history);
        //1.设置主标题字体颜色
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        //找到折叠布局栏
        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        //找到历史事件的图片
        hisimageView= (ImageView) findViewById(R.id.image_history);

        //找到历史事件的内容控件
        tvContent= (TextView) findViewById(R.id.tv_history);

        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();



       if(actionBar!=null)
       {
           actionBar.setDisplayHomeAsUpEnabled(true);
       }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        OkHttpFromInterNet();

        scdao = new ScUserDao(DetailsActivity.this);
        //收藏頁面
        shoucang= (FloatingActionButton) findViewById(R.id.shoucang);
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(DetailsActivity.this, "收藏", Toast.LENGTH_SHORT).show();
//                boolean inserts = scdao.insert(data, title,images , id);
                if (flag) {
                    boolean del = collectionDao.delete(id+"");
                    shoucang.setImageResource(R.mipmap.ic_unlike);
                    collectionDao.select();
                    if (del = true) {
                        //Toast.makeText(DetailsActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                    }
                    flag = false;
                } else {
                    shoucang.setImageResource(R.mipmap.ic_like);
                    boolean add = collectionDao.insert(id+"",data,title,images);
                    if (add = true) {
                        //Toast.makeText(DetailsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    }
                    flag = true;
                }
            }
        });

        collectionDao = new CollectionDao(DetailsActivity.this);
        int ids = collectionDao.idread(id);
        if (ids > 0) {
            flag = true;
            shoucang.setImageResource(R.mipmap.ic_like);
        } else {
            flag = false;
            shoucang.setImageResource(R.mipmap.ic_unlike);
        }

    }

    private void OkHttpFromInterNet()
    {
        OkHttp.getAsync(path, new OkHttp.DataCallBack()
        {
            @Override
            public void requestFailure(Request request, IOException e)
            {

            }

            @Override
            public void requestSuccess(String result) throws Exception
            {
                Gson gson=new Gson();
                
                imageStatus=gson.fromJson(result,ImageStatus.class);

                images=imageStatus.getResult().get(0).getPicUrl().get(0).getUrl();
                list1= imageStatus.getResult();
                list2= imageStatus.getResult().get(0).getPicUrl();
                //设置折叠布局的标题
                collapsingToolbarLayout.setTitle(imageStatus.getResult().get(0).getTitle());

                //点击日期后吧值传过去把今天的日期传过去
                EventBus.getDefault().post(new ImageUrl(images));

                //加载图片
                Glide.with(DetailsActivity.this).load(imageStatus.getResult().get(0).getPicUrl().get(0).getUrl()).into(hisimageView);


                //为历史事件的详细内容赋值
                tvContent.setText(imageStatus.getResult().get(0).getContent());

            }
        });
    }


}

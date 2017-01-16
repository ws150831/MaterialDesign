package com.six.history;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.six.history.fragment.AbFragment;
import com.six.history.fragment.CollectFragment;
import com.six.history.fragment.GrilFragment;
import com.six.history.fragment.HistoryFragment;

public class TooBarActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private Fragment hFragment,gFragment,cFragment,aFragment;
    private Fragment currentFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_too_bar);

        //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
         }


        //控件初始化
        initView();

    }

    private void initView() {
        //1.Toolbar专栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //1.设置主标题
        getSupportActionBar().setTitle("历史上的今天");
        //1.设置主标题字体颜色
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));


        //2.DrawerLayout专栏
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            //2.让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //2.设置导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.btn_category_normal);
        }


        //3.NavigationView专栏
        final NavigationView navigation= (NavigationView) findViewById(R.id.nav_view);
        //3.设置第一个默认选中
        navigation.setCheckedItem(R.id.history);
        //3.点击事件
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {

                switch (item.getItemId())
                {
                    case R.id.history:

                        No1();
                        getSupportActionBar().setTitle("历史上的今天");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.beautifulgirl:
                        No2();
                        getSupportActionBar().setTitle("美女");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.collect:
                        No3();
                        getSupportActionBar().setTitle("收藏");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.about:
                        No4();
                        getSupportActionBar().setTitle("关于");
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        //4.FloatingActionButton悬浮按钮专区
        FloatingActionButton floating= (FloatingActionButton) findViewById(R.id.floatingActionButton);
        //4.点击事件
        floating.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                startActivity(new Intent(TooBarActivity.this,DateActivity.class));
//                Snackbar.make(view,"是否点击",Snackbar.LENGTH_SHORT).setAction("Yes", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view)
//                    {
//                        Toast.makeText(TooBarActivity.this, "view:" + view, Toast.LENGTH_SHORT).show();
//
//                    }
//                }).show();
            }
        });

        //5.初始化Fragment
        initFragmetn();


    }

    private void initFragmetn()
    {
        if(hFragment==null)
        {
            hFragment=new HistoryFragment();

        }

        if(!hFragment.isAdded())
        {
            //提交事务
            //添加frament的布局到事务中
            getSupportFragmentManager().beginTransaction().add(R.id.content_layout,hFragment).commit();

            //记录当前Fragment
            currentFragment=hFragment;

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void addOrShowFragment(FragmentTransaction transaction, Fragment fragment)
    {
        if(currentFragment==fragment)
        {
            return;
        }

        //如果当前fragment未被添加,则添加到Fragment管理器中
        if(!fragment.isAdded())
        {
            transaction.hide(currentFragment).add(R.id.content_layout, fragment).commit();
        }else
        {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment=fragment;
    }

    public void No1()
    {

        if(hFragment==null)
        {
            hFragment=new HistoryFragment();

        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(), hFragment);

    }

    public void No2()
    {

        if(gFragment==null)
        {
            gFragment=new GrilFragment();

        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(),gFragment);

    }

    public void No3()
    {

        if(cFragment==null)
        {
            cFragment=new CollectFragment();

        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(), cFragment);

    }

    public void No4()
    {

        if(aFragment==null)
        {
            aFragment=new AbFragment();

        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(),aFragment);

    }



}

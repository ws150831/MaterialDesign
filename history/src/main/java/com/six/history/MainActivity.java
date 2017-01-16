package com.six.history;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);



    final Intent intent = new Intent(MainActivity.this,TooBarActivity.class);
        //系统会为需要启动的activity寻找与当前activity不同的task;
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //创建一个新的线程来显示欢迎动画，指定时间后结束，跳转至指定界面
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(3000);
                    //获取应用的上下文，生命周期是整个应用，应用结束才会结束
                    getApplicationContext().startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();
    }
}

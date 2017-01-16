package history.six.com.retrofitdemo;

import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import history.six.com.retrofitdemo.bean.Status;
import history.six.com.retrofitdemo.interfaces.RequestAPI;

import history.six.com.retrofitdemo.manager.HttpManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRequestBaidu,btnBaiduKnow,btnGetStatus,btnPostStatus,btngetPackage,btnpostPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //控件初始化
        initView();

    }

    private void initView()
    {
        btnRequestBaidu= (Button) findViewById(R.id.btn_requestBaidu);
        btnRequestBaidu.setOnClickListener(this);

        btnBaiduKnow= (Button) findViewById(R.id.btn_baiduKnow);
        btnBaiduKnow.setOnClickListener(this);

        btnGetStatus= (Button) findViewById(R.id.btn_getStatus);
        btnGetStatus.setOnClickListener(this);

        btnPostStatus= (Button) findViewById(R.id.btn_postStatus);
        btnPostStatus.setOnClickListener(this);

        btngetPackage= (Button) findViewById(R.id.btn_getpackage);
        btngetPackage.setOnClickListener(this);

        btnpostPackage= (Button) findViewById(R.id.btn_postpackage);
        btnpostPackage.setOnClickListener(this);





    }

    @Override
    public void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.btn_requestBaidu:

                //先创建Retrofit对象                              //设置Url                       //添加转换形式，指定转换类型为String
                Retrofit retrofit=new Retrofit.Builder().baseUrl("http://www.baidu.com").addConverterFactory(ScalarsConverterFactory.create()).build();

                //将请求的API转成Java接口
                RequestAPI requestApi= retrofit.create(RequestAPI.class);

                //获取调用对象
                Call<String> baidu= requestApi.getBaidu("http://www.baidu.com");

                //拿到Call對象進行請求,分为同步与异步


                //同步请求必须放在子线程中,所以不是首选
//                try {
//                    baidu.execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }



                //使用异步请求
                baidu.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        //得到请求的http文本
                        Toast.makeText(MainActivity.this,response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        //得到抛出的异常信息
                        Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            case R.id.btn_baiduKnow:

                //先创建Retrofit对象                              //设置Url                //添加转换形式       //指定转换类型为String
                Retrofit retrofit1=new Retrofit.Builder().baseUrl("http://www.baidu.com").addConverterFactory(ScalarsConverterFactory.create()).build();

                //将请求的API转成Java接口
                RequestAPI requestApi1= retrofit1.create(RequestAPI.class);

                //获取调用对象
                //view 指的是我们具体添加的path，替换掉接口中的v
                Call<String> know= requestApi1.getBaiduKnow("view");

                //拿到Call對象進行請求,分为同步与异步


                //同步请求必须放在子线程中,所以不是首选
//                try {
//                    baidu.execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }



                //使用异步请求
                know.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        //得到请求的http文本
                        Toast.makeText(MainActivity.this,response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        //得到抛出的异常信息
                        Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            case R.id.btn_getStatus:
                    Retrofit retrofit2=new Retrofit.Builder().baseUrl("http://v.juhe.cn/").addConverterFactory(GsonConverterFactory.create()).build();
                    RequestAPI requestAPI=retrofit2.create(RequestAPI.class);
                    Call<Status> status=requestAPI.getStatus("69a7eeba7869f8bdcdee7b2bc3bb5aa2","1/16");
                    status.enqueue(new Callback<Status>() {
                        @Override
                        public void onResponse(Call<Status> call, Response<Status> response)
                        {
                            Status status1=response.body();
                            Toast.makeText(MainActivity.this, status1.getResult().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Status> call, Throwable t)
                        {
                            Toast.makeText(MainActivity.this, "dsfefsdf", Toast.LENGTH_SHORT).show();
                        }
                    });



                break;
            case R.id.btn_postStatus:
                Retrofit retrofit3=new Retrofit.Builder().baseUrl("http://v.juhe.cn/").addConverterFactory(GsonConverterFactory.create()).build();
                RequestAPI requestAPI4=retrofit3.create(RequestAPI.class);

                Map<String,String> map=new HashMap<String,String>();
                map.put("key","69a7eeba7869f8bdcdee7b2bc3bb5aa2");
                map.put("date","1/17");

                Call<Status> statusCall=requestAPI4.postStatusMap(map);
                statusCall.enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                        Status status1=response.body();
                        Toast.makeText(MainActivity.this, status1.getResult().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "dsfefsdf", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.btn_getpackage:

                HttpManager.getMethod("http://v.juhe.cn/", "http://v.juhe.cn/todayOnhistory/queryEvent.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&date=1/16", new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(MainActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                break;
            case R.id.btn_postpackage:

                Map<String,String> map1=new HashMap<String,String>();
                map1.put("key","69a7eeba7869f8bdcdee7b2bc3bb5aa2");
                map1.put("date","1/15");

                HttpManager.postMethod("http://v.juhe.cn/", "todayOnhistory/queryEvent.php", map1, new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(MainActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                break;
            default:
                break;
        }

    }
}

package history.six.com.retrofitdemo.interfaces;

import java.util.Map;

import history.six.com.retrofitdemo.bean.Status;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/1/12/012.
 */

public interface RequestAPI
{
    /*
    //http://v.juhe.cn/todayOnhistory/queryEvent.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&date=1/16

    * get请求方式
    * */
    @GET
    Call<String> getBaidu(@Url String url);

    /*
    * https://zhidao.www.baidu.com/daily/view
    *
    * 基本的url是:https://zhidao.www.baidu.com ，我们需要对后面的实行拼接
    *
    * */
    //{v}代表所需要变化的path参数,比如id等,是变量
    //@Path("v") String v) 代表替换掉v,用String 后面的v代替,不全到daily后
    /*
    * 路径拼接的形式
    * */
    @GET("daily/{v}")
    Call<String> getBaiduKnow(@Path("v") String v);

    /*
    * http://v.juhe.cn/todayOnhistory/queryEvent.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&date=1/16
    * */
    @GET("todayOnhistory/queryEvent.php")
    Call<Status> getStatus(@Query("key") String key,@Query("date") String date);

    @GET("todayOnhistory/queryEvent.php")
    Call<Status> getStatusMap(@QueryMap Map<String,String> map);

    @POST("todayOnhistory/queryEvent.php")
    Call<Status> postStatus(@Field("key")String key,@Field("date") String date);

    //postmap必须指定
    @FormUrlEncoded
    @POST("todayOnhistory/queryEvent.php")
    Call<Status> postStatusMap(@FieldMap Map<String,String> map);





}

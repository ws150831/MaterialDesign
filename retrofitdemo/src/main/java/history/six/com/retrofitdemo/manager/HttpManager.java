package history.six.com.retrofitdemo.manager;

import java.util.Map;

import history.six.com.retrofitdemo.interfaces.ProjectAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/1/13/013.
 */

public class HttpManager
{
    public static void getMethod(String baseUrl, String url, final Callback<String> callback)
    {

        Retrofit retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).build();

        ProjectAPI projectAPI=retrofit.create(ProjectAPI.class);

        Call<String> stringCall=projectAPI.getMethod(url);

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public static void postMethod(String baseUrl, String url, Map<String,String> map, final Callback<String> callback)
    {

        Retrofit retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).build();

        ProjectAPI projectAPI=retrofit.create(ProjectAPI.class);

        Call<String> stringCall=projectAPI.postMethod(url,map);

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }
}

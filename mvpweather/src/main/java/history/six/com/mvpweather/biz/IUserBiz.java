package history.six.com.mvpweather.biz;

/**
 * Created by Administrator on 2017/1/6/006.
 */

public interface IUserBiz
{
    public void login(String username, String password, OnLoginListener loginListener);
}

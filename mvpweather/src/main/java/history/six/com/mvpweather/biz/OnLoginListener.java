package history.six.com.mvpweather.biz;

import history.six.com.mvpweather.bean.User;

/**
 * Created by Administrator on 2017/1/6/006.
 */

public interface OnLoginListener
{
    void loginSuccess(User user);

    void loginFailed();
}

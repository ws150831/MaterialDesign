package history.six.com.mvpweather.view;

import history.six.com.mvpweather.bean.User;

/**
 * Created by Administrator on 2017/1/6/006.
 */

public interface IUserLoginView
{
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}

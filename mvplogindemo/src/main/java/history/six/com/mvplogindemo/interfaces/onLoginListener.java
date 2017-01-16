package history.six.com.mvplogindemo.interfaces;

import history.six.com.mvplogindemo.bean.User;

/**
 * Created by Administrator on 2017/1/13/013.
 */

public interface onLoginListener
{
    void loginSuccess(User user);
    void loginFailed();
}

package history.six.com.wushuaitest.interfaces;

import history.six.com.wushuaitest.bean.User;

/**
 * Created by Administrator on 2017/1/7/007.
 */

public interface onLoginListener
{
    void LoginSuccess(User user);
    void LoginFailed();
}

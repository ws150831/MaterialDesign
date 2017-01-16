package history.six.com.wushuaimvplogindemo.interfacelist;

import history.six.com.wushuaimvplogindemo.bean.User;

/**
 * Created by Administrator on 2017/1/6/006.
 */

public interface onLoginListener
{
    void loginSuccess(User user);
    void loginFailed();
}

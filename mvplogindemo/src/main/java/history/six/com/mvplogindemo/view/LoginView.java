package history.six.com.mvplogindemo.view;

import history.six.com.mvplogindemo.bean.User;

/**
 * Created by Administrator on 2017/1/13/013.
 */

public interface   LoginView
{
    //得到用户名与密码
    String getUserName();
    String getPassword();

    //清除用户名与密码
    void clearUserName();
    void clearPassword();

    //显示与隐藏ProgressBar
    void showLoading();
    void hideLoading();

    //与用户交互成功与失败的信息
    void Main(User user);
    void Failed();
}

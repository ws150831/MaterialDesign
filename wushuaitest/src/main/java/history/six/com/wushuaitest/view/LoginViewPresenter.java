package history.six.com.wushuaitest.view;

import history.six.com.wushuaitest.bean.User;

/**
 * Created by Administrator on 2017/1/7/007.
 */

public interface LoginViewPresenter
{
    //抽象用户名，密码的方法
    String getUserName();
    String getPassword();

    //清除用户名,密码的方法
    void clearUserName();
    void clearPassword();

    //隐藏与显示ProgressBar的方法
    void hideLoading();
    void showLoading();

    //登录失败与成功的方法
    void LoginSuccess(User user);
    void LoginFailed();
}

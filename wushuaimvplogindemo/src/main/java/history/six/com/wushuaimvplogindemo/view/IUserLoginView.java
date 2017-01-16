package history.six.com.wushuaimvplogindemo.view;

import history.six.com.wushuaimvplogindemo.bean.User;

/**
 * Created by Administrator on 2017/1/6/006.
 */

public interface IUserLoginView
{
    //定义用户名与密码的抽象方法
    String getUsername();
    String getUserPassword();

    //定义清除用户名与密码的抽象方法
    void clearUserName();
    void clearUserPassword();

    //提示用户等待按钮的显示与隐藏
    void showLoading();
    void hideLoading();

    //登陆成功与失败的反馈信息
    void toMainActivity(User user);
    void showFailedError();



}

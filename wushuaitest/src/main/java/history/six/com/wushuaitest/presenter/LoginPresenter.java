package history.six.com.wushuaitest.presenter;

import android.os.Handler;

import history.six.com.wushuaitest.bean.User;
import history.six.com.wushuaitest.interfaces.MyUser;
import history.six.com.wushuaitest.interfaces.UserWushuai;
import history.six.com.wushuaitest.interfaces.onLoginListener;
import history.six.com.wushuaitest.view.LoginViewPresenter;

/**
 * Created by Administrator on 2017/1/7/007.
 */

public class LoginPresenter
{
    //声明登录的实现类
    private MyUser userWushuai;

    //声明接口
    private LoginViewPresenter loginViewPresenter;

    public LoginPresenter(LoginViewPresenter loginViewPresenter)
    {
        userWushuai=new MyUser();
        this.loginViewPresenter = loginViewPresenter;
    }

    private Handler handler=new Handler();


    public void login()
    {

        //调用接口中的方法
        loginViewPresenter.showLoading();

        userWushuai.login(loginViewPresenter.getUserName(), loginViewPresenter.getPassword(), new onLoginListener() {
            @Override
            public void LoginSuccess(final User user) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        loginViewPresenter.LoginSuccess(user);
                        loginViewPresenter.hideLoading();
                    }
                });
            }

            @Override
            public void LoginFailed() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginViewPresenter.LoginFailed();
                        loginViewPresenter.hideLoading();
                    }
                });
            }
        });
    }

    public void clear()
    {
        //调用接口的清除密码和用户名的方法
        loginViewPresenter.clearPassword();
        loginViewPresenter.clearUserName();
    }


}

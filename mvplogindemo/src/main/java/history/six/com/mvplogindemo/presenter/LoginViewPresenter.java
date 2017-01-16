package history.six.com.mvplogindemo.presenter;

import android.os.Handler;

import history.six.com.mvplogindemo.bean.User;
import history.six.com.mvplogindemo.interfaces.ILogin;
import history.six.com.mvplogindemo.interfaces.MyUser;
import history.six.com.mvplogindemo.interfaces.onLoginListener;
import history.six.com.mvplogindemo.view.LoginView;

/**
 * Created by Administrator on 2017/1/13/013.
 */

public class LoginViewPresenter
{
    private ILogin iLogin;
    private LoginView loginView;

    public LoginViewPresenter(LoginView loginView)
    {
        this.loginView = loginView;
        this.iLogin=new MyUser();
    }

    private Handler handler=new Handler();


    public void login()
    {
        loginView.showLoading();

        iLogin.login(loginView.getUserName(), loginView.getPassword(), new onLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程中执行
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.Main(user);
                        loginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {

                //需要在UI线程中执行
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.Failed();
                        loginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear()
    {
        loginView.clearPassword();
        loginView.clearUserName();
    }


}

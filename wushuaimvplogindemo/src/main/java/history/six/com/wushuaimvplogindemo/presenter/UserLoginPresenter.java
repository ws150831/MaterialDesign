package history.six.com.wushuaimvplogindemo.presenter;

import android.os.Handler;

import history.six.com.wushuaimvplogindemo.bean.User;
import history.six.com.wushuaimvplogindemo.interfacelist.UserWushuai;
import history.six.com.wushuaimvplogindemo.interfacelist.onLoginListener;
import history.six.com.wushuaimvplogindemo.view.IUserLoginView;

/**
 * Created by Administrator on 2017/1/6/006.
 */

public class UserLoginPresenter
{
    //声明登陆接口的实现类
    private UserWushuai userWushuai;
    //声明登陆接口
    private IUserLoginView iUserLoginView;

    //添加构造方法
    public UserLoginPresenter( IUserLoginView iUserLoginView) {
        //通过构造方法实例化登陆接口的实现类
        this.userWushuai = new UserWushuai();
        this.iUserLoginView = iUserLoginView;
    }


    //创建一个Handler对象
    private Handler handler=new Handler();


    public void login()
    {
        //调用接口中的showLoading()方法;
        iUserLoginView.showLoading();
        userWushuai.login(iUserLoginView.getUsername(), iUserLoginView.getUserPassword(), new onLoginListener() {
            @Override
            public void loginSuccess(final User user)
            {

                //需要在UI线程中执行
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserLoginView.toMainActivity(user);

                        iUserLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed()
            {
                //需要在UI线程中执行
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserLoginView.showFailedError();
                        iUserLoginView.hideLoading();
                    }
                });
            }
        });



    }

    public void clear()
    {
        //调用清除用户名与密码的方法
        iUserLoginView.clearUserName();
        iUserLoginView.clearUserPassword();
    }


}

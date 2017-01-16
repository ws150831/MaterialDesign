package history.six.com.mvplogindemo.interfaces;

import history.six.com.mvplogindemo.bean.User;

/**
 * Created by Administrator on 2017/1/13/013.
 */

public class MyUser implements ILogin
{

    @Override
    public void login(final String userName, final String password, final onLoginListener listener)
    {
            //
        new Thread()
        {
            @Override
            public void run() {
                super.run();

                //
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(userName.equals("wushuai")&&password.equals("123"))
                {
                    User user=new User();
                    user.setUserName(userName);
                    user.setPassword(password);
                    listener.loginSuccess(user);

                }else
                {
                    listener.loginFailed();
                }




            }
        }.start();
    }
}

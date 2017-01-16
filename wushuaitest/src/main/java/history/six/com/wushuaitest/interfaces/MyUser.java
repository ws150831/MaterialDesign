package history.six.com.wushuaitest.interfaces;

import history.six.com.wushuaitest.bean.User;

/**
 * Created by Administrator on 2017/1/7/007.
 */

public class MyUser implements UserWushuai
{

    @Override
    public void login(final String userName, final String password, final onLoginListener onLoginListener)
    {

        new Thread()
        {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if("wushuai".equals(userName)&&"123".equals(password))
                {
                    User user=new User();
                    user.setUserName(userName);
                    user.setPassword(password);

                    onLoginListener.LoginSuccess(user);
                }else
                {
                    onLoginListener.LoginFailed();
                }
            }
        }.start();
    }
}

package history.six.com.wushuaimvplogindemo.interfacelist;

import history.six.com.wushuaimvplogindemo.bean.User;

/**
 * Created by Administrator on 2017/1/6/006.
 */

public class UserWushuai implements IUserLogin
{


    @Override
    public void login(final String username, final String password, final onLoginListener listener)
    {

        new Thread(){

            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟登陆成功
                if("wushuai".equals(username)&&"123".equals(password))
                {
                    User user=new User();
                    user.setUsername(username);
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

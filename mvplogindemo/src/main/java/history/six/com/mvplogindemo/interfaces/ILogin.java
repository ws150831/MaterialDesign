package history.six.com.mvplogindemo.interfaces;

/**
 * Created by Administrator on 2017/1/13/013.
 */

public interface ILogin
{
    void login(String userName,String password,onLoginListener listener);
}

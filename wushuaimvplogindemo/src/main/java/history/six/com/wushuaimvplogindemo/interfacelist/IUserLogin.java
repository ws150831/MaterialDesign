package history.six.com.wushuaimvplogindemo.interfacelist;

/**
 * Created by Administrator on 2017/1/6/006.
 */

public interface IUserLogin
{
    public void login(String username,String password,onLoginListener listener);
}

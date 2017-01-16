package history.six.com.observerdemotest;

/**
 * Created by Administrator on 2017/1/11/011.
 */



/**
*功能:主题:(观察者，发布者)
 * autour: 武帅
 * date: 2017/1/11/011 9:27
 * update: 2017/1/11/011
 */

public interface Subject
{
    /**
     *
     * 注册观察者
     */

    void registerObserver(Observer observer);


    /**
     *
     * 移除观察者
     */

    void removeObserver(Observer observer);

    /**
     *
     * 通知观察者
     */

    void notifyObservers();




}

package history.six.com.observerdemotest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11/011.
 */

public class WeatherData implements Subject
{

    private List<Observer> observerList;

    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压
    private List<Float> forecastTemperatures;//未来几天的温度

    public float getHumidity() {
        return humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public List<Float> getForecastTemperatures() {
        return forecastTemperatures;
    }

    public void mesureMents(List<Float> forecastTemperatures, float pressure, float humidity, float temperature) {
        this.forecastTemperatures = forecastTemperatures;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temperature = temperature;

        mesurementsChanged();
    }



    public WeatherData() {
        this.observerList = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer observer) {

        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {

     for(Observer obser:observerList)
     {
         obser.update();
     }

    }

    public void mesurementsChanged()
    {
        notifyObservers();
    }


}

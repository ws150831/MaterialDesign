package history.six.com.observerdemotest;

import java.util.List;

/**
 * 佀晋元
 * 英俊潇洒，威武霸气，才华横溢
 * 人送外号：“砖石王老五”
 * Created by Administrator on 2017/1/11/011.
 */

public class ForecastDisplay implements Observer,DisplayElement
{

    private WeatherData weatherData;

    private List<Float> forecastTemperatures;//未来几天的温度

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }


    @Override
    public void display() {
        System.out.println("未来几天的气温");
        int count = forecastTemperatures.size();
        for (int i = 0; i < count; i++) {
            System.out.println("第" + i + "天:" + forecastTemperatures.get(i) + "℃");
        }

    }

    @Override
    public void update() {
        this.forecastTemperatures = this.weatherData.getForecastTemperatures();
        display();

    }
}


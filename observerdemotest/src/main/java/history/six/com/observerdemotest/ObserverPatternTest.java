package history.six.com.observerdemotest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11/011.
 */

public class ObserverPatternTest
{
    public static void main(String[]args)
    {

        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        List<Float> forecastTemperatures = new ArrayList<Float>();
        forecastTemperatures.add(22f);
        forecastTemperatures.add(-1f);
        forecastTemperatures.add(9f);
        forecastTemperatures.add(23f);
        forecastTemperatures.add(27f);
        forecastTemperatures.add(30f);
        forecastTemperatures.add(10f);
        weatherData.mesureMents(forecastTemperatures,22f,0.8f, 1.2f);

    }
}

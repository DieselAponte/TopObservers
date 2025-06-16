package DisplayElements;

import Subject.Subject;
import Observer.Observer;

public class CurrentConditionsDisplay implements Observer, DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;
    private float airQuality;
    private final Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure, float airQuality) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.airQuality = airQuality;
        display();
    }

    @Override
    public void display() {
        System.out.println("WeatherData:" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", airQuality=" + airQuality);
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public float getAirQuality() {
        return airQuality;
    }

    public Subject getWeatherData() {
        return weatherData;
    }
}
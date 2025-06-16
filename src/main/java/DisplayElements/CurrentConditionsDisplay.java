package DisplayElements;

import Subject.Subject;
import Observer.Observer;

import javax.swing.*;
import java.awt.*;

public class CurrentConditionsDisplay implements Observer, DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;
    private float airQuality;
    private final Subject weatherData;

    private final JPanel panel;
    private final JLabel lbl_Temperature ;
    private final JLabel lbl_Humidity ;
    private final JLabel lbl_Pressure ;
    private final JLabel lbl_AirQuality;

    public CurrentConditionsDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);

        panel = new JPanel(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Condiciones Actuales"));

        lbl_Temperature = new JLabel("Temperatura: ");
        lbl_Humidity = new JLabel("Humedad: ");
        lbl_Pressure = new JLabel("Presión: ");
        lbl_AirQuality = new JLabel("Calidad del Aire: ");

        panel.add(lbl_Temperature);
        panel.add(lbl_Humidity);
        panel.add(lbl_Pressure);
        panel.add(lbl_AirQuality);
    }

    @Override
    public void update(float temperature, float humidity, float pressure, float airQuality) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.airQuality = airQuality;
        display();

        lbl_Temperature.setText("Temperatura: " + temperature + " °C");
        lbl_Humidity.setText("Humedad: " + humidity + " %");
        lbl_Pressure.setText("Presión: " + pressure + " Pa");
        lbl_AirQuality.setText("Calidad del Aire: " + airQuality + " AQI");
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

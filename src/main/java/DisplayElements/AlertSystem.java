package DisplayElements;

import Subject.Subject;
import Observer.Observer;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class AlertSystem implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;
    private float airQuality;
    private boolean alertActive;
    private List<String> triggeredAlerts = new ArrayList<>();

    Subject weatherData;

    public AlertSystem(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    @Override
    public void update(float temperature, float humidity, float pressure, float airQuality) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.airQuality = airQuality;
        ActivateAlarm();
    }

    @Override
    public void display(){
        System.out.println("Alert System:");
        ActivateAlarm();
    }

    public void ActivateAlarm(){
        triggeredAlerts.clear();
        alertActive = false;

        if (temperature < 15 || temperature > 35) {
            triggeredAlerts.add("Temperatura fuera de rango: " + temperature + "°C");
        }
        if (humidity < 30 || humidity > 70) {
            triggeredAlerts.add("Humedad fuera de rango: " + humidity + "%");
        }
        if (pressure < 980 || pressure > 1050) {
            triggeredAlerts.add("Presión fuera de rango: " + pressure + " hPa");
        }
        if (airQuality > 100) {
            triggeredAlerts.add("Calidad de aire mala: AQI = " + airQuality);
        }

        if(triggeredAlerts.isEmpty()){
            alertActive = false;
            System.out.println("Todo en orden. No hay alertas");
        } else {
            alertActive = true;
            System.out.println("¡Alerta!");
            for(String alert: triggeredAlerts){
                System.out.println(alert);
            }
        }
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

    public boolean isAlertActive() {
        return alertActive;
    }

    public List<String> getTriggeredAlerts() {
        return triggeredAlerts;
    }
}
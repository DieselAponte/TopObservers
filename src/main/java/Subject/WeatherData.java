package Subject;

import Observer.Observer;
import java.util.ArrayList;

public class WeatherData implements Subject {
    private float temperature;
    private float humidity;
    private float pressure;
    private float airQuality;
    //Lista de todos los observers
    private final ArrayList<Observer> observers;


    public WeatherData(){
        observers = new ArrayList();
    }

    public void setMeasurements(float temperature, float humidity, float pressure, float airQuality){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.airQuality = airQuality;
        measurementsChanged();
    }

    public void registerObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
        int i = observers.indexOf(o);

        if(i >= 0){
            observers.remove(o);
        }
    }

    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update(temperature, humidity,pressure,airQuality);
        }
    }

    public void measurementsChanged(){
        notifyObservers();
    }

    //getters

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

    public ArrayList<Observer> getObservers() {
        return observers;
    }
}
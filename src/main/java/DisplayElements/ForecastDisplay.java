package DisplayElements;
import Observer.Observer;
import Subject.*;
public class ForecastDisplay implements Observer, DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;
    private float airQuality;
    private float lastPressure;
    private final Subject weatherData;

    public ForecastDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure, float airQuality) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.lastPressure = this.pressure;
        this.pressure = pressure;
        this.airQuality = airQuality;
        display();
    }

    @Override
    public void display(){
        System.out.println("Forecast for today:" +
                giveForeCast());
    }

    public String giveForeCast(){

        if(pressure > lastPressure){
            return "Mejorando";
        } else if(pressure == lastPressure){
            return "Sin cambios";
        } else {
            return "Empeorando";
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

    public Subject getWeatherData() {
        return weatherData;
    }
}
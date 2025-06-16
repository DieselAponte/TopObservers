package DisplayElements;

import Subject.Subject;
import Observer.Observer;

import javax.swing.*;
import java.awt.*;

public class StatisticsDisplay implements Observer, DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;
    private float airQuality;

    private float[] suma_temp;
    private int lecturas;
    private float averageTemp;
    private float maxTemp = Float.NEGATIVE_INFINITY;
    private float minTemp = Float.POSITIVE_INFINITY;

    private Subject weatherData;

    private final JPanel panel;
    private final JLabel lbl_Min;
    private final JLabel lbl_Max;
    private final JLabel lbl_Average;
    public StatisticsDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);

        suma_temp = new float[100];
        lecturas = 0;

        panel = new JPanel(new GridLayout(3, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Estadísticas"));

        lbl_Min = new JLabel("Temperatura Mínima: ");
        lbl_Max = new JLabel("Temperatura Máxima: ");
        lbl_Average = new JLabel("Temperatura Promedio: ");

        panel.add(lbl_Min);
        panel.add(lbl_Max);
        panel.add(lbl_Average);
    }

    @Override
    public void update(float temperature, float humidity, float pressure, float airQuality) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.airQuality = airQuality;
        lecturas++;

        suma_temp[lecturas - 1] = temperature;

        if(temperature > maxTemp){
            maxTemp = temperature;
        }

        if(temperature < minTemp){
            minTemp = temperature;
        }

        display();
    }

    @Override
    public void display() {
        System.out.println("\n Statistics Display");
        System.out.printf("Max Temperature: " + maxTemp + '\n');
        System.out.printf("Min Temperature: " + minTemp + '\n');
        calcAverageTemperature();
    }


    public void calcAverageTemperature(){
        if(lecturas < 1){
            System.out.println("No hay informacion de posibles lecturas");
        } else {
            float valor_suma_temp = 0.0f;
            for(int i = 0; i < lecturas; i++){
                valor_suma_temp += suma_temp[i];
            }
            averageTemp=valor_suma_temp/lecturas;
        }
        System.out.println("Average temperature: "+averageTemp);
    }



    public float getMaxTemp(){
        return maxTemp;
    }

    public float getMinTemp(){
        return minTemp;
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

    public float[] getSuma_temp() {
        return suma_temp;
    }

    public int getLecturas() {
        return lecturas;
    }

    public float getAverageTemp() {
        return averageTemp;
    }

}

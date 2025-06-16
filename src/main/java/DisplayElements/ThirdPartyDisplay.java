package DisplayElements;

import Subject.Subject;
import Observer.Observer;
import DisplayElements.DisplayElement;

public class ThirdPartyDisplay implements  Observer, DisplayElement{ //Clase de testeo
    private float temperature;
    private float humidity;
    private float pressure;
    private float airQuality;
    private float lastPressure;


    private float[] q_temperature;
    private int lecturas;

    private float maxTemp = Float.NEGATIVE_INFINITY;
    private float minTemp = Float.NEGATIVE_INFINITY;

    private Subject weatherData;

    public ThirdPartyDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);

        q_temperature = new float[100];
        lecturas = 0;
    }


    @Override
    public void update(float temperature, float humidity, float pressure, float airQuality){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.airQuality = airQuality;
        lecturas++;

        q_temperature[lecturas - 1] = temperature;

        if(temperature > maxTemp){
            maxTemp = temperature;
        }

        if(temperature < minTemp){
            minTemp = temperature;
        }

        display();
    }

    @Override
    public void display() {}

    public float getMaxTemp(){
        return maxTemp;
    }

    public float getMinTemp(){
        return minTemp;
    }

//    public String giveForeCast(){
//
//        if(pressure > lastPressure){
//            return "Mejorando";
//        } else if(pressure == lastPressure){
//            return "Sin cambios";
//        } else {
//            return "Empeorando";
//        }
//  }


//    public void calcAverageTemperature(){
//        if(lecturas < 1){
//            System.out.println("No hay informacion de posibles lecturas");
//        } else {
//            float valor_suma_temp = 0.0f;
//            for(int i = 0; i < lecturas; i++){
//                valor_suma_temp += suma_temp[i];
//            }
//            averageTemp=valor_suma_temp/lecturas;
//        }
//        System.out.println("Average temperature: "+averageTemp);
//    }
}
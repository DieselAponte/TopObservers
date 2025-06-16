package Controller;

import Subject.WeatherData;
import DisplayElements.*;
import View.WeatherORamaView;

import javax.swing.*;
import java.awt.*;

public class WeatherORamaController {

    private final WeatherData weatherData;
    private final WeatherORamaView weatherORamaview;

    private final CurrentConditionsDisplay currentConditionsDisplay;
    private final StatisticsDisplay statisticsDisplay;
    private final ForecastDisplay forecastDisplay;
    private final AlertSystem alertSystem;

    public WeatherORamaController(WeatherData weatherData, WeatherORamaView view) {
        this.weatherData = weatherData;
        this.weatherORamaview = view;


        currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        statisticsDisplay = new StatisticsDisplay(weatherData);
        forecastDisplay = new ForecastDisplay(weatherData);
        alertSystem = new AlertSystem(weatherData);

        addListeners();
    }

    private void addListeners() {
        weatherORamaview.getBtnAgregarCondicionesActuales().addActionListener(e -> agregarCondicionesActuales());
        weatherORamaview.getBtnAgregarEstadisticas().addActionListener(e -> agregarEstadisticas());
        weatherORamaview.getBtnAgregarPronostico().addActionListener(e -> agregarPronostico());
        weatherORamaview.getBtnAgregarCalidadAire().addActionListener(e -> agregarCalidadAire());
        weatherORamaview.getBtnActualizarDatos().addActionListener(e -> actualizarDatos());
        weatherORamaview.getBtn_EnviarGmail().addActionListener(e -> {
            try {
                String emailReceiver = weatherORamaview.getTfCorreoGmail().getText();
                enviarDatosGmail(new GMailer(), emailReceiver);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void actualizarDatos() {
        try {
            float temp = Float.parseFloat(weatherORamaview.getTfTemperatura().getText());
            float hum = Float.parseFloat(weatherORamaview.getTfHumedad().getText());
            float pres = Float.parseFloat(weatherORamaview.getTfPresion().getText());
            float airQ = Float.parseFloat(weatherORamaview.getTfCalidadAire().getText());
            weatherData.setMeasurements(temp, hum, pres, airQ);

            if(alertSystem.isAlertActive()){
                StringBuilder stringBuilder = new  StringBuilder();
                for(int i = 0; i < alertSystem.getTriggeredAlerts().size(); i++){
                    if(i == alertSystem.getTriggeredAlerts().size()-1){
                        stringBuilder.append(alertSystem.getTriggeredAlerts().get(i));
                    } else {
                        stringBuilder.append(alertSystem.getTriggeredAlerts().get(i));
                        stringBuilder.append(" | ");
                    }
                }
//                for(String alert : alertSystem.getTriggeredAlerts()){
//                    stringBuilder.append(alert);
//                    stringBuilder.append(" | ");
//                }
                weatherORamaview.setAlertText(stringBuilder.toString());
            } else {
                weatherORamaview.setAlertText("Todo en orden. No hay alertas");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa valores validos.");
        }
    }

    public void agregarCondicionesActuales() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Condiciones Actuales"));

        panel.add(new JLabel("Temperatura: " + currentConditionsDisplay.getTemperature() + " °C"));
        panel.add(new JLabel("Humedad: " + currentConditionsDisplay.getHumidity() + " %"));
        panel.add(new JLabel("Presion: " + currentConditionsDisplay.getPressure() + " Pa"));
        panel.add(new JLabel("Calidad del Aire: " + currentConditionsDisplay.getAirQuality() + " AQI"));

        weatherORamaview.addPanelToVisualizations(panel);
    }

    public void agregarEstadisticas() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Estadísticas"));

        panel.add(new JLabel("Temperatura Minima: " + statisticsDisplay.getMinTemp()));
        panel.add(new JLabel("Temperatura Maxima: " + statisticsDisplay.getMaxTemp()));


        statisticsDisplay.calcAverageTemperature();
        panel.add(new JLabel("Temperatura Promedio: " + statisticsDisplay.getAverageTemp()));

        weatherORamaview.addPanelToVisualizations(panel);
    }

    public void agregarPronostico() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Pronostico"));
        panel.add(new JLabel("Pronostico: " + forecastDisplay.giveForeCast()));
        weatherORamaview.addPanelToVisualizations(panel);
    }

    public void agregarCalidadAire() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Calidad del Aire"));
        panel.add(new JLabel("AQI Actual: " + forecastDisplay.getAirQuality()));
        weatherORamaview.addPanelToVisualizations(panel);
    }

    //Envio Gmail - Controller
    public void enviarDatosGmail(GMailer gMailer, String destinatario) {
        try {
            String subject = "Reporte de Clima - WeatherORama";
            StringBuilder message = new StringBuilder();
            message.append("\n");
            message.append("Temperatura: ").append(currentConditionsDisplay.getTemperature()).append(" °C\n");
            message.append("Humedad: ").append(currentConditionsDisplay.getHumidity()).append(" %\n");
            message.append("Presión: ").append(currentConditionsDisplay.getPressure()).append(" Pa\n");
            message.append("Calidad del Aire: ").append(currentConditionsDisplay.getAirQuality()).append(" AQI\n\n");
            message.append("Pronostico: ").append(forecastDisplay.giveForeCast()).append("\n\n");
            message.append("Estadisticas:\n");
            message.append("Temperatura Minima: ").append(statisticsDisplay.getMinTemp()).append("\n");
            message.append("Temperatura Maxima: ").append(statisticsDisplay.getMaxTemp()).append("\n");
            message.append("Temperatura Promedio: ").append(statisticsDisplay.getAverageTemp()).append("\n\n");

            if (alertSystem.isAlertActive()) {
                message.append("¡Alerta!\n");
                for (String alert : alertSystem.getTriggeredAlerts()) {
                    message.append("- ").append(alert).append("\n");
                }
            } else {
                message.append("Todo en orden. No hay alertas.\n");
            }

            gMailer.sendMail(subject, message.toString(), destinatario);
            JOptionPane.showMessageDialog(null, "Correo enviado con exito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al enviar el correo: " + e.getMessage());
        }
    }

}
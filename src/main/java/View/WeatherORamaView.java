package View;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.WeatherORamaController;
import Subject.*;
import Observer.*;
import DisplayElements.*;

import java.awt.*;

public class WeatherORamaView extends JFrame{

    private final static int MAIN_FRAME_WIDTH = 1080;
    private final static int MAIN_FRAME_HEIGHT = 600;
    private final static int MAIN_FRAME_X = 100;
    private final static int MAIN_FRAME_Y = 100;

    //Componentes
    JLabel lbl_Controles = new JLabel("Controles");
    JLabel lbl_Temperatura = new JLabel("Temperatura (C°): ");
    JLabel lbl_Humedad = new JLabel("Humedad (%): ");
    JLabel lbl_Presion = new JLabel("Presion (Pa): ");
    JLabel lbl_CalidadAire = new JLabel("Calidad Aire (AQI): ");
    JLabel lbl_EnvioGmail = new JLabel("Envio de Gmail: ");
    JTextField tf_temperatura, tf_humedad, tf_presion, tf_calidadAire, tf_CorreoGmail;
    JButton btn_ActualizarDatos = new JButton("Actualizar Datos");


    JLabel lbl_Visualizaciones = new JLabel("Visualizaciones ");
//    JLabel lbl_CondicionesActuales = new JLabel("Condiciones Actuales");
//    JLabel lbl_V_Temperatura = new JLabel("Temperatura: ");
//    JLabel lbl_V_Humedad = new JLabel("Humedad: " + " %");
//
//    JLabel lbl_Estadisticas = new JLabel("Estadisticas");
//    JLabel lbl_V_ResultadoEstadisticas = new JLabel();
//
//    JLabel lbl_V_Pronostico = new JLabel("Pronostico");
//    JLabel lbl_V_ResultadoPronostico = new JLabel();
//
//    JLabel lbl_V_CalidadAire = new JLabel("Calidad Aire");
//    JLabel lbl_V_ResultadoCalidadAire =  new JLabel();


    JButton btn_AgregarCondicionesActuales = new JButton("Agregar Condiciones Actuales");
    JButton btn_AgregarEstadisticas = new JButton("Agregar Estadisticas");
    JButton btn_AgregarPronostico = new JButton("Agregar Pronostico");
    JButton btn_AgregarCalidadAire = new JButton("Agregar Calidad Aire");
    JButton btn_EnviarGmail = new JButton("Enviar Gmail");

    JLabel lbl_SistemaAlertas = new JLabel("Sistema de Alertas");
    JLabel lbl_ResultadoSistemaAlertas = new JLabel();

    //Visualicacion dinamica de los paneles que accionan los botones
    private final JPanel dynamicVisualizationsPanel = new JPanel();
    private int panelYOffset = 10;

    public JButton getBtnActualizarDatos() { return btn_ActualizarDatos; }
    public JButton getBtnAgregarCondicionesActuales() { return btn_AgregarCondicionesActuales; }
    public JButton getBtnAgregarEstadisticas() { return btn_AgregarEstadisticas; }
    public JButton getBtnAgregarPronostico() { return btn_AgregarPronostico; }
    public JButton getBtnAgregarCalidadAire() { return btn_AgregarCalidadAire; }
    public JButton getBtn_EnviarGmail() { return btn_EnviarGmail; }

    public JTextField getTfTemperatura() { return tf_temperatura; }
    public JTextField getTfHumedad() { return tf_humedad; }
    public JTextField getTfPresion() { return tf_presion; }
    public JTextField getTfCalidadAire() { return tf_calidadAire; }
    public JTextField getTfCorreoGmail() { return tf_CorreoGmail; }

    public WeatherORamaView() {
        setTitle("WeatherORama - Weather Monitor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(MAIN_FRAME_X, MAIN_FRAME_Y, MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT));

        JPanel ControlsPanel = new JPanel(null);
        ControlsPanel.setPreferredSize(new Dimension(MAIN_FRAME_WIDTH, 160));
        make_WeatherControls(ControlsPanel);
        add(ControlsPanel, BorderLayout.NORTH);

        JPanel VisualizationsPanel = new JPanel(null);
        VisualizationsPanel.setPreferredSize(new Dimension(MAIN_FRAME_WIDTH, 600));
        make_Visualizations(VisualizationsPanel);
        JScrollPane scrollPane = new JScrollPane(VisualizationsPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel AlertSystemPanel = new JPanel(null);
        AlertSystemPanel.setPreferredSize(new Dimension(MAIN_FRAME_WIDTH, 120));
        make_AlertSystem(AlertSystemPanel);
        add(AlertSystemPanel, BorderLayout.SOUTH);

        JPanel ButtonsPanel = new JPanel(null);
        ButtonsPanel.setPreferredSize(new Dimension(220, MAIN_FRAME_HEIGHT));
        make_AddButtons(ButtonsPanel);
        add(ButtonsPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void make_WeatherControls(JPanel controlsPanel) {
        lbl_Controles.setBounds(10, 10, 100, 20);
        controlsPanel.add(lbl_Controles);

        lbl_Temperatura.setBounds(10, 40, 130, 20);
        controlsPanel.add(lbl_Temperatura);
        tf_temperatura = new JTextField();
        tf_temperatura.setBounds(150, 40, 100, 20);
        controlsPanel.add(tf_temperatura);

        lbl_Humedad.setBounds(10, 70, 130, 20);
        controlsPanel.add(lbl_Humedad);
        tf_humedad = new JTextField();
        tf_humedad.setBounds(150, 70, 100, 20);
        controlsPanel.add(tf_humedad);

        lbl_Presion.setBounds(10, 100, 130, 20);
        controlsPanel.add(lbl_Presion);
        tf_presion = new JTextField();
        tf_presion.setBounds(150, 100, 100, 20);
        controlsPanel.add(tf_presion);

        lbl_CalidadAire.setBounds(10, 130, 150, 20);
        controlsPanel.add(lbl_CalidadAire);
        tf_calidadAire = new JTextField();
        tf_calidadAire.setBounds(150, 130, 100, 20);
        controlsPanel.add(tf_calidadAire);

        btn_ActualizarDatos.setBounds(300, 80, 150, 30);
        controlsPanel.add(btn_ActualizarDatos);
    }

    private void make_AddButtons(JPanel buttonsPanel) {

        btn_AgregarCondicionesActuales.setText("Agregar Condiciones Actuales");
        btn_AgregarCondicionesActuales.setBounds(10, 20, 200, 30);
        buttonsPanel.add(btn_AgregarCondicionesActuales);

        btn_AgregarEstadisticas.setText("Agregar Estadisticas");
        btn_AgregarEstadisticas.setBounds(10, 70, 200, 30);
        buttonsPanel.add(btn_AgregarEstadisticas);

        btn_AgregarPronostico.setText("Agregar Pronostico");
        btn_AgregarPronostico.setBounds(10, 120, 200, 30);
        buttonsPanel.add(btn_AgregarPronostico);

        btn_AgregarCalidadAire.setText("Agregar Calidad Aire");
        btn_AgregarCalidadAire.setBounds(10, 170, 200, 30);
        buttonsPanel.add(btn_AgregarCalidadAire);

        //Envio de Gmail - View
        lbl_EnvioGmail.setBounds(10, 220, 130, 20);
        buttonsPanel.add(lbl_EnvioGmail);
        tf_CorreoGmail = new JTextField();
        tf_CorreoGmail.setBounds(10, 250, 200, 20);
        buttonsPanel.add(tf_CorreoGmail);
        btn_EnviarGmail.setText("Enviar Gmail");
        btn_EnviarGmail.setBounds(10, 280, 200, 30);
        buttonsPanel.add(btn_EnviarGmail);

    }

    private void make_Visualizations(JPanel visualizationsPanel) {
        visualizationsPanel.setLayout(null);

        lbl_Visualizaciones.setBounds(10,10,200,20);
        visualizationsPanel.add(lbl_Visualizaciones);

        dynamicVisualizationsPanel.setLayout(null);
        dynamicVisualizationsPanel.setBounds(10,40,740,1000);
        visualizationsPanel.add(dynamicVisualizationsPanel);
    }

    public void addPanelToVisualizations(JPanel panel) {
        panel.setBounds(10, panelYOffset, 700, panel.getPreferredSize().height + 30);
        dynamicVisualizationsPanel.add(panel);
        panelYOffset += panel.getHeight() + 10;

        dynamicVisualizationsPanel.setPreferredSize(new Dimension(700, panelYOffset + 50 ));
        dynamicVisualizationsPanel.revalidate();
        dynamicVisualizationsPanel.repaint();
    }

    public void setAlertText(String texto){
        lbl_ResultadoSistemaAlertas.setText(texto);
    }

    private void make_AlertSystem(JPanel alertSystemPanel) {
        lbl_SistemaAlertas.setBounds(10, 10, 200, 20);
        alertSystemPanel.add(lbl_SistemaAlertas);

        lbl_ResultadoSistemaAlertas.setForeground(Color.RED);
        lbl_ResultadoSistemaAlertas.setVerticalAlignment(SwingConstants.TOP);
        lbl_ResultadoSistemaAlertas.setBounds(10, 40, 1000, 60);
        lbl_ResultadoSistemaAlertas.setText("No hay alertas aún.");
        lbl_ResultadoSistemaAlertas.setFont(new Font("Arial", Font.PLAIN, 14));
        alertSystemPanel.add(lbl_ResultadoSistemaAlertas);
    }


}
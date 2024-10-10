/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Classes;

import MainPackage.App;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.Timer;

/**
 *
 * @author Alejandro Djukic 
 */
public class ChartManager {
    private final App app = App.getInstance();
    private XYSeries brandApple;
    private XYSeries brandDell;
    private XYSeriesCollection dataset;
    private JFreeChart xyLineChart;
    private Timer updateTimer;
    
    public ChartManager() {
        initializeBrand();
        initializeChart();
        startDataUpdateTimer();
    }
    

    private void initializeBrand() {
        brandApple = new XYSeries("Apple");
        brandDell = new XYSeries("Dell");
        dataset = new XYSeriesCollection();
        dataset.addSeries(brandApple);
        dataset.addSeries(brandDell);
    }

    /**
     * Crea el gráfico XY Line usando JFreeChart y configura la apariencia.
     */
    
    private void initializeChart() {
        xyLineChart = ChartFactory.createXYLineChart(
                "Tiempo vs Ganancia", // Título del gráfico
                "Tiempo",             // Etiqueta eje X
                "Ganancia",           // Etiqueta eje Y
                dataset,              // Dataset
                PlotOrientation.VERTICAL,
                true,                 // Mostrar leyenda
                true,                 // Generar tooltips
                false                 // URLs
        );

        customizeChart();
    }

     /**
     * Personaliza la apariencia del gráfico XY Line.
     */
    
    private void customizeChart() {
        XYPlot plot = xyLineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
    }

     /**
     * Inicia un temporizador que actualiza las series de datos con un intervalo
     * que se obtiene de la duración del día en la aplicación.
     */
    
    private void startDataUpdateTimer() {
        int delay = app.getDayDuration(); 
        updateTimer = new Timer(delay, e -> updateChartData());
        updateTimer.start();
    }

    
    public void updateChartData() {
        // Se obtienen las nuevas ganancias
        double appleProfit = app.getApple().getProfit(); 
        double dellProfit = app.getDell().getProfit(); 
        int newTimePoint = brandApple.getItemCount() + 1;

        brandApple.addOrUpdate(newTimePoint, appleProfit);
        brandDell.addOrUpdate(newTimePoint, dellProfit);
    }

    public ChartPanel getChartPanel() {
        return new ChartPanel(xyLineChart);
    }

    public void stopUpdateTimer() {
        if (updateTimer != null) {
            updateTimer.stop();
        }
    }
}
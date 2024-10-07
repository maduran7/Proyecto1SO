/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainPackage;

import GUI.Classes.Home;
import FileFunctions.FileFunctions;
import GUI.Classes.ChartManager;
import Helpers.HelpersFunctions;
import MainClasses.PcProduct;
import java.io.File;

/**
 *
 * @author Mauricio Duran & Alejandro Djukic 
 */
public class App {

    // FIle params
    private static String selectedPath = "test//params.txt";
    private static File selectedFile = new File(selectedPath);
    private static FileFunctions fileFunctions = new FileFunctions();

    // General params
    private static int dayDuration;
    private static int deadline;

    // General variables
    private PcProduct dell;
    private PcProduct apple;
    private static ChartManager chartManager;


    private static App app;

    
    public static synchronized App getInstance() {
        if (getApp() == null) {
            setApp(new App());
        }
        return getApp();
    }

    public void start() {
        
        HelpersFunctions.loadParams();
        
        // Inicia la simulacion
        getApple().start();
        getDell().start();
        chartManager = new ChartManager();


        Home home = new Home();
        home.setVisible(true);
    }

    /**
     * @return the selectedPath
     */
    public static String getSelectedPath() {
        return selectedPath;
    }

    /**
     * @param aSelectedPath the selectedPath to set
     */
    public static void setSelectedPath(String aSelectedPath) {
        selectedPath = aSelectedPath;
    }

    /**
     * @return the selectedFile
     */
    public static File getSelectedFile() {
        return selectedFile;
    }

    /**
     * @param aSelectedFile the selectedFile to set
     */
    public static void setSelectedFile(File aSelectedFile) {
        selectedFile = aSelectedFile;
    }

    /**
     * @return the fileFunctions
     */
    public static FileFunctions getFileFunctions() {
        return fileFunctions;
    }

    /**
     * @param aFileFunctions the fileFunctions to set
     */
    public static void setFileFunctions(FileFunctions aFileFunctions) {
        fileFunctions = aFileFunctions;
    }

    /**
     * @return the dayDuration
     */
    public static int getDayDuration() {
        return dayDuration;
    }

    /**
     * @param aDayDuration the dayDuration to set
     */
    public static void setDayDuration(int aDayDuration) {
        dayDuration = aDayDuration;
    }

    /**
     * @return the deadline
     */
    public static int getDeadline() {
        return deadline;
    }

    /**
     * @param aDeadline the deadline to set
     */
    public static void setDeadline(int aDeadline) {
        deadline = aDeadline;
    }

    /**
     * @return the cartoonNetwork
     */
    public PcProduct getDell() {
        return dell;
    }

    
    public void setDell(PcProduct dell) {
        this.dell = dell;
    }

    /**
     * @return the nickelodeon
     */
    public PcProduct getApple() {
        return apple;
    }

    
    public void setApple(PcProduct apple) {
        this.apple = apple;
    }

    /**
     * @return the app
     */
    public static App getApp() {
        return app;
    }

    /**
     * @param aApp the app to set
     */
    public static void setApp(App aApp) {
        app = aApp;
    }

     public static ChartManager getChartManager() {
        return chartManager;
    }
  

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileFunctions;

import MainPackage.App;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import MainClasses.PcProduct;
import MainPackage.App;

/**
 *
 * @author aleja
 */
public class FileFunctions {
    public static String read(File file) {
        String line;
        String data = "";

        try {
            if (!file.exists()) {
                file.createNewFile();

            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                while ((line = br.readLine()) != null) {
                    if (!(line.isEmpty())) {
                        data += line + "\n";
                    }
                }
                br.close();
            }
            return data;
        } catch (Exception e) {
        }
        return data;
    }

    public static int[] getGeneralParams(String fileData) {
        int startIndex = fileData.indexOf("[General Params]");
        if (startIndex == -1) {
            return null;
        }

        // Se Encuentra el final de la sección de General Params o el final del archivo
        // si no hay más secciones
        int endIndex = fileData.indexOf("[", startIndex + 1);
        if (endIndex == -1) {
            endIndex = fileData.length();
        }

        // Se extrae la sección de General Params
        String cnSection = fileData.substring(startIndex, endIndex);

        // Se divide la sección en líneas
        String[] lines = cnSection.split("\n");

        // Se crea un array para almacenar los valores enteros de la configuración
        int[] generalParams = new int[2];

        // Variable para recorrer el arreglo de líneas
        int valueIndex = 0;

        // Se Itera sobre las líneas, extrayendo los valores enteros
        for (String line : lines) {
            if (line.contains("=")) {
                // Divide la línea en la etiqueta y el valor
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    try {
                        // Se castea el valor entero y lo almacena en el array
                        generalParams[valueIndex++] = Integer.parseInt(parts[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("El valor no es entero");
                    }
                }
            }
        }
        return generalParams;
    }

    public static int[] getPcProductValues(int company, String fileData) {
        int[] values = new int[9];
        int startIndex = 0;

        if (company == 0) {
            startIndex = fileData.indexOf("[Apple]");
        } else if (company == 1) {
            startIndex = fileData.indexOf("[Dell]");
        }

        if (startIndex == -1) {
            // La sección no fue encontrada.
            return null;
        }

        // Se Encuentra el final de la sección de o el final del archivo
        int endIndex = fileData.indexOf("[", startIndex + 1);
        if (endIndex == -1) {
            endIndex = fileData.length();
        }

        // Se extrae la sección
        String cnSection = fileData.substring(startIndex, endIndex);

        // Se divide la sección en líneas
        String[] lines = cnSection.split("\n");

        // Variable para recorrer el arreglo de líneas
        int valueIndex = 0;

        // Se Itera sobre las líneas, extrayendo los valores enteros
        for (String line : lines) {
            if (line.contains("=")) {
                // Divide la línea en la etiqueta y el valor
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    try {
                        // Se castea el valor entero y lo almacena en el array
                        values[valueIndex++] = Integer.parseInt(parts[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("El valor no es entero");
                    }
                }
            }
        }

        return values;
    }

    public static void write(File file) {

        String data = getActualParams();
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception e) {

        }
    }

    public static String getActualParams() {
        App app = App.getInstance();
        String data = "[General Params]\n";
        int dayDuration = App.getDayDuration();
        int deadline = App.getDeadline();

        data += "DayDuration=" + dayDuration + "\n" + "Deadline=" + deadline + "\n\n";

        data += "[Apple]\n";
        PcProduct apple = app.getApple();
        int screenwriters = apple.countNonNull(apple.getScreenwriters());
        int setDesigners = apple.countNonNull(apple.getSetDesigners());
        int characterAnimators = apple.countNonNull(apple.getCharacterAnimators());
        int voiceActors = apple.countNonNull(apple.getVoiceActors());
        int plotTwistScriptwriters = apple.countNonNull(apple.getPlotTwistScreenwriters());
        int assemblers = apple.countNonNull(apple.getAssemblers());
        int projectManager = apple.getProjectManager();
        int director = apple.getDirector();
        int maxCapacity = apple.getMaxEmployeesQuantity();

        data += "Screenwriters=" + screenwriters + "\n" + "SetDesigners=" + setDesigners + "\n" + "CharacterAnimators="
                + characterAnimators + "\n" + "VoiceActors=" + voiceActors + "\n" + "PlotTwistScriptwriters="
                + plotTwistScriptwriters + "\n" + "Assemblers=" + assemblers + "\n" + "ProjectManager=" + projectManager
                + "\n" + "Director=" + director + "\n" + "MaxCapacity=" + maxCapacity + "\n\n";

        data += "[Dell]\n";
        PcProduct dell = app.getDell();
        screenwriters = dell.countNonNull(dell.getScreenwriters());
        setDesigners = dell.countNonNull(dell.getSetDesigners());
        characterAnimators = dell.countNonNull(dell.getCharacterAnimators());
        voiceActors = dell.countNonNull(dell.getVoiceActors());
        plotTwistScriptwriters = dell.countNonNull(dell.getPlotTwistScreenwriters());
        assemblers = dell.countNonNull(dell.getAssemblers());
        projectManager = dell.getProjectManager();
        director = dell.getDirector();
        maxCapacity = dell.getMaxEmployeesQuantity();

        data += "Screenwriters=" + screenwriters + "\n" + "SetDesigners=" + setDesigners + "\n" + "CharacterAnimators="
                + characterAnimators + "\n" + "VoiceActors=" + voiceActors + "\n" + "PlotTwistScriptwriters="
                + plotTwistScriptwriters + "\n" + "Assemblers=" + assemblers + "\n" + "ProjectManager=" + projectManager
                + "\n" + "Director=" + director + "\n" + "MaxCapacity=" + maxCapacity + "\n\n";

        return data;
    }
}

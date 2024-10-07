/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;

import MainClasses.Employee;
import MainClasses.ProjectManager;
import FileFunctions.FileFunctions;
import Helpers.ImportantConstants;
import MainClasses.Director;
import MainClasses.Drive;
import MainClasses.PcProduct;
import MainPackage.App;
import java.util.concurrent.Semaphore;

/**
 *
 * @author angel
 */
public class HelpersFunctions {

    public static PcProduct getPcProduct(int company) {
        return company == 0 ? App.getInstance().getApple() : App.getInstance().getDell();
    }

    public static void loadParams() {
        String fileData = FileFunctions.read(App.getSelectedFile());

        // Se obtiene los datos del TXT
        int[] params = FileFunctions.getGeneralParams(fileData);

        if (params != null && params.length >= 2) {
            App.setDayDuration(params[0]);
            App.setDeadline(params[1]);
        }

        App app = App.getInstance();
        app.setApple(HelpersFunctions.createPcProduct(0));
        app.setDell(HelpersFunctions.createPcProduct(1));

    }

    // NOTE - 0 para nickelodeon y 1 para cartoon network
    public static PcProduct createPcProduct (int company) {
        String fileData = FileFunctions.read(App.getSelectedFile());

        // Se obtiene los datos del TXT
        int[] pcProductValues = FileFunctions.getPcProductValues(company, fileData);

        if (pcProductValues != null && pcProductValues.length >= 9) {

            String name = ImportantConstants.companies[company];
            Employee[][] workers = new Employee[6][];
            Semaphore mutex = new Semaphore(1);
            Drive drive = new Drive(25, 20, 55, 35, 10);
            int projectManager = 1;
            int director = 1;
            int maxEmployees = pcProductValues[8];

            // Se crean los empleados de cada sección
            for (int type = 0; type <= 5; type++) {
                Employee[] employees = new Employee[maxEmployees];

                for (int j = 0; j < pcProductValues[type]; j++) {
                    int workerId = j + 1;
                    int numOfWorkDone = ImportantConstants.productionTimes[company][type][0];
                    int daysToFinish = ImportantConstants.productionTimes[company][type][1];
                    int hourlyWage = ImportantConstants.hourlyWages[type];
                    employees[j] = new Employee(company, workerId, type, daysToFinish, numOfWorkDone, hourlyWage,
                            drive, mutex);
                }
                workers[type] = employees;
            }
            PcProduct product = new PcProduct (name, maxEmployees, workers[0], workers[1], workers[2],
                    workers[3], workers[4],
                    workers[5], projectManager, director, drive, mutex);

            // Se crea al projectManager y al director, se les pasa la cadena televisiva.
            ProjectManager projectManagerInstance = new ProjectManager(company, 1, 5, 1, 1,
                    ImportantConstants.hourlyWages[5], drive, mutex);
            product.setProjectManagerInstance(projectManagerInstance);
            Director directorInstance = new Director(company, 1, 6, 2, 1, ImportantConstants.hourlyWages[6], drive,
                    mutex);
            product.setDirectorInstance(directorInstance);
            return product;

        }
        return null;
    }

    public void addWorker(int company, int workerType) {

        PcProduct product = company == 0 ? App.getInstance().getApple()
                : App.getInstance().getApple();

        // Se verifica si la cantidad actual de empleados es menor que la cantidad
        // máxima permitida
        if (product.getActualEmployeesQuantity() < product.getMaxEmployeesQuantity()) {
            Employee[] employees = getEmployeesArrayByType(product, workerType);

            // Se crea nuevo empleado
            int workerId = product.getActualEmployeesQuantity() + 1;
            int daysToFinish = ImportantConstants.productionTimes[company][workerType][1];
            int numOfWorkDone = ImportantConstants.productionTimes[company][workerType][0];
            int hourlyWage = ImportantConstants.hourlyWages[workerType];
            Employee newEmployee = new Employee(company, workerId, workerType, daysToFinish, numOfWorkDone, hourlyWage,
                    product.getDrive(), product.getMutex());

            // Se inicia el hilo del nuevo empleado
            newEmployee.start();

            // Se buscar la primera posición vacía en el arreglo y añadir allí el nuevo
            // empleado
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] == null) {
                    employees[i] = newEmployee;
                    product.setActualEmployeesQuantity(product.getActualEmployeesQuantity() + 1);
                    // Actualizar la cantidad de empleados
                    break;
                }
            }
        } else {
            System.out.println("Se ha alcanzado el número máximo de empleados.");
        }
    }

    public void deleteWorker(int company, int workerType) {
        PcProduct product = HelpersFunctions.getPcProduct(company);

        // Verifica si hay empleados para eliminar
        if (product.getActualEmployeesQuantity() > 0) {
            Employee[] employees = getEmployeesArrayByType(product, workerType);

            if (employees != null) {
                // Buscar el último empleado no nulo
                for (int i = employees.length - 1; i >= 0; i--) {
                    if (employees[i] != null) {
                        // Interrumpe el hilo del empleado
                        employees[i].interrupt();

                        // Elimina el empleado del arreglo
                        employees[i] = null;

                        // Actualiza la cantidad de empleados
                        product.setActualEmployeesQuantity(product.getActualEmployeesQuantity() - 1);
                        break;
                    }
                }
            }
        } else {
            System.out.println("No hay empleados para eliminar.");
        }
    }

    private Employee[] getEmployeesArrayByType(PcProduct product, int workerType) {
        switch (workerType) {
            case 0:
                return product.getScreenwriters();
            case 1:
                return product.getSetDesigners();
            case 2:
                return product.getCharacterAnimators();
            case 3:
                return product.getVoiceActors();
            case 4:
                return product.getPlotTwistScreenwriters();
            case 5:
                return product.getAssemblers();
            default:
                return null;
        }
    }

    private void setEmployeesArrayByType(PcProduct product, int workerType, Employee[] newEmployees) {
        switch (workerType) {
            case 0:
                product.setScreenwriters(newEmployees);
                break;
            case 1:
                product.setSetDesigners(newEmployees);
                break;
            case 2:
                product.setCharacterAnimators(newEmployees);
                break;
            case 3:
                product.setVoiceActors(newEmployees);
                break;
            case 4:
                product.setPlotTwistScreenwriters(newEmployees);
                break;
        }
    }

    public static void calculateTotalCost(int company, float accumulatedSalary) {
        PcProduct tv = getPcproduct(company);
        tv.setTotalCost(tv.getTotalCost() + accumulatedSalary);
    }

    public static void calculateTotalEarnings(int company) {
        PcProduct pc = getPcProduct(company);
        float earning = (pc.getNumNormalChapters() * ImportantConstants.profitPerChapter[company][0])
                + (pc.getNumChaptersWithPlotTwist() * ImportantConstants.profitPerChapter[company][1]);
        pc.setEarning(earning);
    }

    public static void calculateProfit(int company) {
        PcProduct pc = getPcProduct(company);
        float profit = pc.getEarning() - pc.getTotalCost();
        pc.setProfit(profit);
    }
}

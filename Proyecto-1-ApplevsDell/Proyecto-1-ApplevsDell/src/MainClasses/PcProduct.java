/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import MainPackage.App;
import java.util.concurrent.Semaphore;


public class PcProduct {

    private String name;
    private int maxEmployeesQuantity;
    private int actualEmployeesQuantity = 0;
    private Employee[] screenwriters;
    private Employee[] setDesigners;
    private Employee[] characterAnimators;
    private Employee[] voiceActors;
    private Employee[] plotTwistScreenwriters;
    private Employee[] Assemblers;
    private int projectManager;
    private ProjectManager projectManagerInstance;
    private int director;
    private Director directorInstance;
    private static Drive drive;
    private Semaphore mutex;
    private float totalCost = 0;
    private float earning = 0;
    private float profit = 0;
    private float lastOpsCost = 0;
    private float batchLastProfit = 0;
    private int totalDays = 0;
    private int remainingDays = App.getInstance().getDeadline();
    private int numChapters = 0;
    private int numNormalChapters = 0;
    private int numChaptersWithPlotTwist = 0;
    private int actualNumChapters = 0;
    private int actualNumNormalChapters = 0;
    private int actualNumChaptersWithPlotTwist = 0;
    private int lastNumNormalChapters = 0;
    private int lastNumChaptersWithPlotTwist = 0;
    private int plotTwistTrigger = 0;

    // CONSTRUCTOR
    public PcProduct(String name, int maxEmployeesQuantity, Employee[] screenwriters, Employee[] setDesigners,
            Employee[] characterAnimators, Employee[] voiceActors, Employee[] plotTwistScreenwriters,
            Employee[] Assemblers, int projectManager, int director, Drive drive, Semaphore mutex) {
        this.name = name;
        this.maxEmployeesQuantity = maxEmployeesQuantity;
        this.screenwriters = screenwriters;
        this.setDesigners = setDesigners;
        this.characterAnimators = characterAnimators;
        this.voiceActors = voiceActors;
        this.plotTwistScreenwriters = plotTwistScreenwriters;
        this.Assemblers = Assemblers;
        this.projectManager = projectManager;
        this.director = director;
        this.drive = drive;
        this.mutex = mutex;
        this.actualEmployeesQuantity();
    }

    public void start() {

        for (int i = 0; i < this.getScreenwriters().length; i++) {
            if (this.getScreenwriters()[i] != null) {
                this.getScreenwriters()[i].start();
            }
        }
        for (int i = 0; i < this.getSetDesigners().length; i++) {
            if (this.getSetDesigners()[i] != null) {
                this.getSetDesigners()[i].start();
            }
        }
        for (int i = 0; i < this.getCharacterAnimators().length; i++) {
            if (this.getCharacterAnimators()[i] != null) {
                this.getCharacterAnimators()[i].start();
            }
        }
        for (int i = 0; i < this.getVoiceActors().length; i++) {
            if (this.getVoiceActors()[i] != null) {
                this.getVoiceActors()[i].start();
            }
        }
        for (int i = 0; i < this.getPlotTwistScreenwriters().length; i++) {
            if (this.getPlotTwistScreenwriters()[i] != null) {
                this.getPlotTwistScreenwriters()[i].start();
            }
        }
        for (int i = 0; i < this.getAssemblers().length; i++) {
            if (this.getAssemblers()[i] != null) {
                this.getAssemblers()[i].start();
            }
        }
        this.getProjectManagerInstance().start();
        this.getDirectorInstance().start();

    }

    public void actualEmployeesQuantity() {
        int totalEmployees = 0;

        // Contar empleados no nulos en cada arreglo
        totalEmployees += countNonNull(screenwriters);
        totalEmployees += countNonNull(setDesigners);
        totalEmployees += countNonNull(characterAnimators);
        totalEmployees += countNonNull(voiceActors);
        totalEmployees += countNonNull(plotTwistScreenwriters);
        totalEmployees += countNonNull(Assemblers);

        this.setActualEmployeesQuantity(totalEmployees);
    }

    // Método auxiliar para contar los elementos no nulos en un arreglo de Employee
    public int countNonNull(Employee[] employees) {
        int count = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the maxEmployeesQuantity
     */
    public int getMaxEmployeesQuantity() {
        return maxEmployeesQuantity;
    }

    /**
     * @param maxEmployeesQuantity the maxEmployeesQuantity to set
     */
    public void setMaxEmployeesQuantity(int maxEmployeesQuantity) {
        this.maxEmployeesQuantity = maxEmployeesQuantity;
    }

    /**
     * @return the screenwriters
     */
    public Employee[] getScreenwriters() {
        return screenwriters;
    }

    /**
     * @param screenwriters the screenwriters to set
     */
    public void setScreenwriters(Employee[] screenwriters) {
        this.screenwriters = screenwriters;
    }

    /**
     * @return the setDesigners
     */
    public Employee[] getSetDesigners() {
        return setDesigners;
    }

    /**
     * @param setDesigners the setDesigners to set
     */
    public void setSetDesigners(Employee[] setDesigners) {
        this.setDesigners = setDesigners;
    }

    /**
     * @return the characterAnimators
     */
    public Employee[] getCharacterAnimators() {
        return characterAnimators;
    }

    /**
     * @param characterAnimators the characterAnimators to set
     */
    public void setCharacterAnimators(Employee[] characterAnimators) {
        this.characterAnimators = characterAnimators;
    }

    /**
     * @return the voiceActors
     */
    public Employee[] getVoiceActors() {
        return voiceActors;
    }

    /**
     * @param voiceActors the voiceActors to set
     */
    public void setVoiceActors(Employee[] voiceActors) {
        this.voiceActors = voiceActors;
    }

    /**
     * @return the plotTwistScreenwriters
     */
    public Employee[] getPlotTwistScreenwriters() {
        return plotTwistScreenwriters;
    }

    /**
     * @param plotTwistScreenwriters the plotTwistScreenwriters to set
     */
    public void setPlotTwistScreenwriters(Employee[] plotTwistScreenwriters) {
        this.plotTwistScreenwriters = plotTwistScreenwriters;
    }

    /**
     * @return the Assemblers
     */
    public Employee[] getAssemblers() {
        return Assemblers;
    }

    /**
     * @param Assemblers the Assemblers to set
     */
    public void setAssemblers(Employee[] Assemblers) {
        this.Assemblers = Assemblers;
    }

    /**
     * @return the projectManager
     */
    public int getProjectManager() {
        return projectManager;
    }

    /**
     * @param projectManager the projectManager to set
     */
    public void setProjectManager(int projectManager) {
        this.projectManager = projectManager;
    }

    /**
     * @return the director
     */
    public int getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(int director) {
        this.director = director;
    }

    /**
     * @return the drive
     */
    public static Drive getDrive() {
        return drive;
    }

    /**
     * @param aDrive the drive to set
     */
    public static void setDrive(Drive aDrive) {
        drive = aDrive;
    }

    /**
     * @return the mutex
     */
    public Semaphore getMutex() {
        return mutex;
    }

    /**
     * @param mutex the mutex to set
     */
    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    /**
     * @return the totalCost
     */
    public float getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public void increaseTotalCost(int cost) {
        this.totalCost += cost;
    }

    public void resetCost() {
        this.totalCost = 0;
    }

    /**
     * @return the earning
     */
    public float getEarning() {
        return earning;
    }

    /**
     * @param earning the earning to set
     */
    public void setEarning(float earning) {
        this.earning = earning;
    }

    /**
     * @return the profit
     */
    public float getProfit() {
        return profit;
    }

    /**
     * @param profit the profit to set
     */
    public void setProfit(float profit) {
        this.profit = profit;
    }

    /**
     * @return the actualEmployeesQuantity
     */
    public int getActualEmployeesQuantity() {
        return actualEmployeesQuantity;
    }

    /**
     * @param actualEmployeesQuantity the actualEmployeesQuantity to set
     */
    public void setActualEmployeesQuantity(int actualEmployeesQuantity) {
        this.actualEmployeesQuantity = actualEmployeesQuantity;
    }

    /**
     * @return the projectManagerInstance
     */
    public ProjectManager getProjectManagerInstance() {
        return projectManagerInstance;
    }

    /**
     * @param projectManagerInstance the projectManagerInstance to set
     */
    public void setProjectManagerInstance(ProjectManager projectManagerInstance) {
        this.projectManagerInstance = projectManagerInstance;
    }

    /**
     * @return the remainingDays
     */
    public int getRemainingDays() {
        return remainingDays;
    }

    public void decreaceRemainingDays() {
        this.remainingDays--;
    }

    public void resetRemainingDays() {
        this.remainingDays = App.getInstance().getDeadline();
    }

    public void setRemainingDays(int days) {
        this.remainingDays = days;
    }

    /**
     * @return the directorInstance
     */
    public Director getDirectorInstance() {
        return directorInstance;
    }

    /**
     * @param directorInstance the directorInstance to set
     */
    public void setDirectorInstance(Director directorInstance) {
        this.directorInstance = directorInstance;
    }

    /**
     * @return the numChapters
     */
    public int getNumChapters() {
        return numChapters;
    }

    /**
     * @param numChapters the numChapters to set
     */
    public void setNumChapters(int numChapters) {
        this.numChapters = numChapters;
    }

    /**
     * @return the numChaptersWithPlotTwist
     */
    public int getNumChaptersWithPlotTwist() {
        return numChaptersWithPlotTwist;
    }

    /**
     * @param numChaptersWithPlotTwist the numChaptersWithPlotTwist to set
     */
    public void setNumChaptersWithPlotTwist(int numChaptersWithPlotTwist) {
        this.numChaptersWithPlotTwist = numChaptersWithPlotTwist;
    }

    /**
     * @return the numNormalChapters
     */
    public int getNumNormalChapters() {
        return numNormalChapters;
    }

    /**
     * @param numNormalChapters the numNormalChapters to set
     */
    public void setNumNormalChapters(int numNormalChapters) {
        this.numNormalChapters = numNormalChapters;
    }

    /**
     * @return the totalDays
     */
    public int getTotalDays() {
        return totalDays;
    }

    /**
     * @param totalDays the totalDays to set
     */
    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    /**
     * @return the actualNumChapters
     */
    public int getActualNumChapters() {
        return actualNumChapters;
    }

    /**
     * @param actualNumChapters the actualNumChapters to set
     */
    public void setActualNumChapters(int actualNumChapters) {
        this.actualNumChapters = actualNumChapters;
    }

    /**
     * @return the actualNumNormalChapters
     */
    public int getActualNumNormalChapters() {
        return actualNumNormalChapters;
    }

    /**
     * @param actualNumNormalChapters the actualNumNormalChapters to set
     */
    public void setActualNumNormalChapters(int actualNumNormalChapters) {
        this.actualNumNormalChapters = actualNumNormalChapters;
    }

    /**
     * @return the actualNumChaptersWithPlotTwist
     */
    public int getActualNumChaptersWithPlotTwist() {
        return actualNumChaptersWithPlotTwist;
    }

    /**
     * @param actualNumChaptersWithPlotTwist the actualNumChaptersWithPlotTwist
     * to set
     */
    public void setActualNumChaptersWithPlotTwist(int actualNumChaptersWithPlotTwist) {
        this.actualNumChaptersWithPlotTwist = actualNumChaptersWithPlotTwist;
    }

    /**
     * @return the lastNumNormalChapters
     */
    public int getLastNumNormalChapters() {
        return lastNumNormalChapters;
    }

    /**
     * @param lastNumNormalChapters the lastNumNormalChapters to set
     */
    public void setLastNumNormalChapters(int lastNumNormalChapters) {
        this.lastNumNormalChapters = lastNumNormalChapters;
    }

    /**
     * @return the lastNumChaptersWithPlotTwist
     */
    public int getLastNumChaptersWithPlotTwist() {
        return lastNumChaptersWithPlotTwist;
    }

    /**
     * @param lastNumChaptersWithPlotTwist the lastNumChaptersWithPlotTwist to
     * set
     */
    public void setLastNumChaptersWithPlotTwist(int lastNumChaptersWithPlotTwist) {
        this.lastNumChaptersWithPlotTwist = lastNumChaptersWithPlotTwist;
    }

    /**
     * @return the lastOpsCost
     */
    public float getLastOpsCost() {
        return lastOpsCost;
    }

    /**
     * @param lastOpsCost the lastOpsCost to set
     */
    public void setLastOpsCost(float lastOpsCost) {
        this.lastOpsCost = lastOpsCost;
    }

    /**
     * @return the batchLastProfit
     */
    public float getBatchLastProfit() {
        return batchLastProfit;
    }

    /**
     * @param batchLastProfit the batchLastProfit to set
     */
    public void setBatchLastProfit(float batchLastProfit) {
        this.batchLastProfit = batchLastProfit;
    }

    /**
     * @return the plotTwistTrigger
     */
    public int getPlotTwistTrigger() {
        return plotTwistTrigger;
    }

    /**
     * @param plotTwistTrigger the plotTwistTrigger to set
     */
    public void setPlotTwistTrigger(int plotTwistTrigger) {
        this.plotTwistTrigger = plotTwistTrigger;
    }

    
}


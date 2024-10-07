/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import Helpers.HelpersFunctions;
import Helpers.ImportantConstants;
import MainPackage.App;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Employee extends Thread {

    int company;
    private int workerId;
    private int type;
    private int daysToFinish;
    private int numOfWorkDone;
    App app = App.getInstance();
    private Drive driveRef;
    Semaphore mutex;

    int hourlyWage;
    float accumulatedSalary;
    private float dailyProgress;
    private float totalWork;
    private int plotTwistCounter = 0;

    public Employee(int company, int workerId, int type, int daysToFinish, int numOfWorkDone, int hourlyWage,
            Drive driveRef, Semaphore mutex) {
        this.company = company;
        this.workerId = workerId;
        this.type = type;
        this.daysToFinish = daysToFinish;
        this.numOfWorkDone = numOfWorkDone;
        this.driveRef = driveRef;
        this.mutex = mutex;

        this.hourlyWage = ImportantConstants.hourlyWages[type];
        this.accumulatedSalary = 0;
        // Cantidad de trabajo realizado por dia
        this.dailyProgress = (float) numOfWorkDone / daysToFinish;
        this.totalWork = 0;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                this.getPaid();
                this.addDailyProduction();
                this.working();
                sleep(App.getDayDuration());
            } catch (InterruptedException ex) {
                // Interrupción manejada, preparándose para terminar el hilo
                Logger.getLogger(Employee.class.getName()).log(Level.INFO,
                        "Hilo de Employee interrumpido, terminando...");
                break;
            }
        }
    }

    private void getPaid() {
        this.setAccumulatedSalary(this.getAccumulatedSalary() + this.getHourlyWage() * 24);
        // FIXME
    }

    private void addDailyProduction() {
        if (this.type == 5 && !(this.evaluateAssemble())) {
            this.setTotalWork(0);
        }
        this.setTotalWork(this.getTotalWork() + this.getDailyProgress());
    }

    private void working() {

        if (getTotalWork() >= 1) {
            try {
                this.getMutex().acquire();
                int workToUpload = (int) Math.floor(this.getTotalWork());

                if (this.type != 5) {
                    this.getDriveRef().uploadFile(getType(), workToUpload);
                } else {
                    this.createChapter();
                }

                HelpersFunctions.calculateTotalCost(this.company, this.accumulatedSalary);
                setAccumulatedSalary(0);

                this.setTotalWork(Math.max(0, this.getTotalWork() - workToUpload));
                this.getMutex().release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean evaluateAssemble() {

        PcProduct pc = HelpersFunctions.getPcProduct(this.company);

        // Requisito minimo para un cap (cap regular)
        for (int i = 0; i < pc.getDrive().getSections().length - 2; i++) {
            // Si no hay la cantidad minima entonces el assembler no puede tranbajar
            if (pc.getDrive().getSections()[i] < ImportantConstants.chaptersComposition[this.company][i]) {
                return false;
            }
        }
        // si es plottwist
        boolean isNextPlotTwist = (pc.getNumChapters() != 0
                && ((pc.getNumChapters()) % ImportantConstants.plotTwistFreq[this.company]) == 0);

        if (isNextPlotTwist) {
            // Verifica si NO hay para hacer un plottwist
            if (pc.getDrive().getSections()[4] < ImportantConstants.chaptersComposition[this.company][4]) {
                return false;
            }
        }
        return true;
    }

    private void createChapter() {
        PcProduct pc = HelpersFunctions.getPcProduct(this.company);

        if (this.evaluateAssemble()) {
            // Evaluar si el siguiente capítulo debe ser un plot twist antes de incrementar
            // numChapters
            boolean isNextPlotTwist = (pc.getPlotTwistTrigger() != 0
                    && ((pc.getPlotTwistTrigger()) % ImportantConstants.plotTwistFreq[this.company]) == 0);

            if (isNextPlotTwist) {
                for (int i = 0; i < pc.getDrive().getSections().length - 1; i++) {
                    pc.getDrive().getSections()[i] = Math.max(0,
                            pc.getDrive().getSections()[i] - ImportantConstants.chaptersComposition[this.company][i]);
                }
                pc.setNumChaptersWithPlotTwist(pc.getNumChaptersWithPlotTwist() + 1);
                pc.setActualNumChaptersWithPlotTwist(pc.getActualNumChaptersWithPlotTwist() + 1);
                pc.setPlotTwistTrigger(0);
            } else {
                // Capitulo regular
                for (int i = 0; i < pc.getDrive().getSections().length - 2; i++) {
                    pc.getDrive().getSections()[i] = Math.max(0,
                            pc.getDrive().getSections()[i] - ImportantConstants.chaptersComposition[this.company][i]);
                }
                pc.setNumNormalChapters(pc.getNumNormalChapters() + 1);
                pc.setActualNumNormalChapters(pc.getActualNumNormalChapters() + 1);
                pc.setPlotTwistTrigger(pc.getPlotTwistTrigger() + 1);
            }

            // Incrementa el número de capítulos
            pc.setNumChapters(pc.getNumChapters() + 1);
            pc.setActualNumChapters(pc.getActualNumChapters() + 1);
            this.getDriveRef().getSections()[5] += 1;
        } else {
            this.setTotalWork(0);
        }
    }

    private boolean isPlotTwistChapter(PcProduct pc) {
        return true;
    }

    @Override
    public String toString() {
        return "Employee {\n" + "-Company= " + ImportantConstants.companies[getCompany()] + "\n"
                + "-workerId= " + getWorkerId() + "\n"
                + "-type= " + ImportantConstants.workesType[getType()] + "\n"
                + "-Days to Finish his part= " + getDaysToFinish() + "\n"
                + "- Num of work done per days= " + getNumOfWorkDone() + "\n"
                + "-hourlyWage= " + getHourlyWage() + "\n"
                + "-accumulatedSalary= " + getAccumulatedSalary() + "\n"
                + "-dailyProgress= " + getDailyProgress() + "\n"
                + "-Total Work= " + getTotalWork() + "\n"
                + "-driveRef= " + (getDriveRef() != null ? "assigned" : "not assigned") + "\n"
                + "-mutex= " + (getMutex() != null ? "assigned" : "not assigned") + "\n"
                + "\n}";
    }

    // Getters and Setters
    /**
     * @return the company
     */
    public int getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(int company) {
        this.company = company;
    }

    /**
     * @return the workerId
     */
    public int getWorkerId() {
        return workerId;
    }

    /**
     * @param workerId the workerId to set
     */
    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the daysToFinish
     */
    public int getDaysToFinish() {
        return daysToFinish;
    }

    /**
     * @param daysToFinish the daysToFinish to set
     */
    public void setDaysToFinish(int daysToFinish) {
        this.daysToFinish = daysToFinish;
    }

    /**
     * @return the numOfWorkDone
     */
    public int getNumOfWorkDone() {
        return numOfWorkDone;
    }

    /**
     * @param numOfWorkDone the numOfWorkDone to set
     */
    public void setNumOfWorkDone(int numOfWorkDone) {
        this.numOfWorkDone = numOfWorkDone;
    }

    /**
     * @return the driveRef
     */
    public Drive getDriveRef() {
        return driveRef;
    }

    /**
     * @param driveRef the driveRef to set
     */
    public void setDriveRef(Drive driveRef) {
        this.driveRef = driveRef;
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
     * @return the hourlyWage
     */
    public int getHourlyWage() {
        return hourlyWage;
    }

    /**
     * @param hourlyWage the hourlyWage to set
     */
    public void setHourlyWage(int hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    /**
     * @return the accumulatedSalary
     */
    public float getAccumulatedSalary() {
        return accumulatedSalary;
    }

    /**
     * @param accumulatedSalary the accumulatedSalary to set
     */
    public void setAccumulatedSalary(float accumulatedSalary) {
        this.accumulatedSalary = accumulatedSalary;
    }

    /**
     * @return the dailyProgress
     */
    public float getDailyProgress() {
        return dailyProgress;
    }

    /**
     * @param dailyProgress the dailyProgress to set
     */
    public void setDailyProgress(float dailyProgress) {
        this.dailyProgress = dailyProgress;
    }

    /**
     * @return the totalWork
     */
    public float getTotalWork() {
        return totalWork;
    }

    /**
     * @param totalWork the totalWork to set
     */
    public void setTotalWork(float totalWork) {
        this.totalWork = totalWork;
    }

}

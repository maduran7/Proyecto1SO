/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import MainPackage.App;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import Helpers.HelpersFunctions;

public class ProjectManager extends Employee {

    private String currentState;
    private int strikes;

    public ProjectManager(int company, int workerId, int type, int daysToFinish, int numOfWorkDone, int hourlyWage,
            Drive driveRef, Semaphore mutex) {
        super(company, workerId, type, daysToFinish, numOfWorkDone, hourlyWage, driveRef, mutex);
        this.currentState = "Inactivo";
        this.strikes = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Se obtiene la duración del día total directo de la variable de app.
                int dayDuration = App.getDayDuration();

                // Duración de una hora en la simulación
                int oneHour = dayDuration / 24;

                // Se sabe que pasa 16 horas alterando ver anime y trabajar cada 30 mins.
                // Por lo tanto se loopea cada 30 mins (32 intervalos de 30 mins) para las
                // primeras 16 h.
                for (int i = 0; i < 32; i++) {
                    if (i % 2 == 0) {
                        this.setCurrentState("***");

                    } else {
                        setCurrentState("Trabajando");

                    }
                    // Duerme por la mitad de una hora de simulación
                    Thread.sleep(oneHour / 2);
                }
                // La segunda parte del día traba para actualizar 1 vez el contador
                // Porque se actualiza 1 vez por día el dayCounter
                setCurrentState("Trabajando");
                Thread.sleep(oneHour * 8);

                // Culminado el día cobra su salario
                this.getPaid();

                this.getMutex().acquire();

                this.updateDeadlineCountdown();
                this.updateCountdown();
                HelpersFunctions.calculateTotalCost(this.company, this.accumulatedSalary);
                this.setAccumulatedSalary(0);

                this.getMutex().release();

            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void updateDeadlineCountdown() {
        // Lógica para actualizar el contador de días restantes
        if (this.company == 0) {
            if (app.getApple().getRemainingDays() != 0) {
                app.getApple().decreaceRemainingDays();
            }
        } else {
            if (app.getDell().getRemainingDays() != 0) {
                app.getDell().decreaceRemainingDays();
            }
        }
    }

    private void updateCountdown() {
        if (this.company == 0) {
            app.getApple().setTotalDays(app.getApple().getTotalDays() + 1);
        } else {
            app.getDell().setTotalDays(app.getDell().getTotalDays() + 1);
        }
    }

    private void getPaid() {
        // Asumiendo que el Project Manager trabaja las 24 horas del día, incluyendo ver
        // anime.
        this.setAccumulatedSalary(this.getAccumulatedSalary() + this.getHourlyWage() * 24);
    }

    @Override
    public String toString() {
        // Retorna información relevante sobre el Project Manager
        return "Project Manager [Salario acumulado del project Manager=" + this.getAccumulatedSalary() + "]";
    }

    /**
     * @return the currentState
     */
    public String getCurrentState() {
        return currentState;
    }

    /**
     * @param currentState the currentState to set
     */
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
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
     * @return the app
     */
    public App getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * @return the strikes
     */
    public int getStrikes() {
        return strikes;
    }

    /**
     * @param strikes the strikes to set
     */
    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    /**
     * @param strikes the strikes to set
     */
    public void addStrike() {
        this.strikes++;
    }

    public void resetStrikes() {
        this.strikes = 0;
    }
}

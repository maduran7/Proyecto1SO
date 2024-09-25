/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import Helpers.HelpersFunctions;
import Helpers.ImportantConstants;
import MainPackage.App;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Director extends Employee {

    private String status;
    private App app = App.getInstance();

    public Director(int company, int workerId, int type, int daysToFinish, int numOfWorkDone, int hourlyWage,
            Drive driveRef, Semaphore mutex) {
        super(company, workerId, type, daysToFinish, numOfWorkDone, hourlyWage, driveRef, mutex);
        this.status = "Inactivo";
    }

    @Override
    public void run() {
        while (true) {
            try {
                getPaid();
                int dayDuration = app.getDayDuration();
                int oneHour = dayDuration / 24;
                // Se determina cuanto son 35 minutos.
                int thirtyFiveMinutes = (int) (oneHour * (35.0 / 60.0));
                int remainingMinutes = oneHour - thirtyFiveMinutes;

                // Se buscan los días restantes.
                int remainingDays = this.company == 0 ? app.getApple().getRemainingDays()
                        : app.getDell().getRemainingDays();

                if (remainingDays <= 0) {
                    this.setStatus("Enviando...");

                    this.getMutex().acquire();
                    // Se envian los capitulos
                    this.sendChaptersToTV();

                    HelpersFunctions.calculateTotalCost(this.company, this.getAccumulatedSalary());
                    this.setAccumulatedSalary(0);

                    // Se calcula las ganancias
                    HelpersFunctions.calculateTotalEarnings(this.company);
                    HelpersFunctions.calculateProfit(this.company);

                    this.getMutex().release();

                } // Si es un dia diferente al 0 entonces hace sus labores administrativas y
                  // supervisa al PM
                else {
                    Random rand = new Random();
                    int randomHour = rand.nextInt(24);

                    for (int i = 0; i < 24; i++) {
                        if (i == randomHour) {
                            this.status = "Vigilando PM";
                            checkProjectManager();
                            Thread.sleep(thirtyFiveMinutes);
                            checkProjectManager();
                            // Basta con solo 2 revisadas porque solo puede cambiar 2 veces el status del PM
                            // en 1 hora.
                            Thread.sleep(remainingMinutes);
                        } else {
                            performAdministrativeTasks();
                            Thread.sleep(oneHour);
                        }
                    }
                }
                Thread.sleep(dayDuration);
            } catch (InterruptedException ex) {
                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void resetDeadline(int company) {
        PcProduct pc = HelpersFunctions.getPcProduct(company);
        pc.setRemainingDays(app.getDeadline());
    }

    private void sendChaptersToTV() {
        try {
            this.setStatus("Enviando...");

            // Esperar un día completo (simulado)
            Thread.sleep(app.getDayDuration());
            // Se reinicia el deadline
            this.resetDeadline(this.company);

            PcProduct pc = HelpersFunctions.getPcProduct(this.company);

            // Enviamos los capitulos
            pc.getDrive().resetChapters();

            // Settiamos los valores actuales como los anteriores para estadisticas
            pc.setLastNumChaptersWithPlotTwist(pc.getActualNumChaptersWithPlotTwist());
            pc.setLastNumNormalChapters(pc.getActualNumNormalChapters());

            // Settiamos los valores actuales a 0
            pc.setActualNumChaptersWithPlotTwist(0);
            pc.setActualNumNormalChapters(0);

            // Settiamos el costo operacional del último batch
            pc.setLastOpsCost(pc.getTotalCost() - pc.getLastOpsCost());

            // Calculamos las ganancias del último batch
            calculateBatchLastProfit(pc);

        } catch (InterruptedException ex) {
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void calculateBatchLastProfit(PcProduct pc) {
        float profit = (pc.getLastNumNormalChapters()
                * ImportantConstants.profitPerChapter[this.company][0])
                + (pc.getNumChaptersWithPlotTwist()
                        * ImportantConstants.profitPerChapter[this.company][1])
                - (pc.getLastOpsCost());

        pc.setBatchLastProfit(profit);
    }

    private void performAdministrativeTasks() {
        this.setStatus("Administrando");
    }

    private void checkProjectManager() {
        this.status = "Vigilando PM";
        PcProduct pc = HelpersFunctions.getPcProduct(this.company);

        if ("Viendo...".equals(pc.getProjectManagerInstance().getCurrentState())) {

            try {
                // Pedimos permiso al mutex para poder reducir el salario del PM al costo total
                this.getMutex().acquire();
                pc.getProjectManagerInstance().addStrike();
                pc.setTotalCost(pc.getTotalCost() - 100);
                // Se calcula las ganancias
                HelpersFunctions.calculateTotalEarnings(this.company);
                HelpersFunctions.calculateProfit(this.company);
                this.getMutex().release();

            } catch (InterruptedException ex) {
                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void getPaid() {
        this.accumulatedSalary += this.hourlyWage * 24;
    }

    @Override
    public String toString() {
        return "Director [Salario acumulado=" + this.accumulatedSalary + ", Estado actual=" + this.getStatus() + "]";
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
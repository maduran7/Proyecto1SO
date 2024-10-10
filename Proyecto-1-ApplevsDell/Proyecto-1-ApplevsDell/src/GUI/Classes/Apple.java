/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Classes;

import FileFunctions.FileFunctions;
import Helpers.HelpersFunctions;
import MainClasses.Employee;
import MainPackage.App;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author aleja
 */
public class Apple extends javax.swing.JFrame {
    
    private Point initialClick;
    private final App app = App.getInstance();
    private int maxEmployees;
    private int actualEmployees;
    private static Apple apple;
    private HelpersFunctions helper = new HelpersFunctions();
    private FileFunctions filefunctions = new FileFunctions();
    private File selectedFile = app.getSelectedFile();
    private JButton[] decreaseBtn = new JButton[6];
    private JButton[] increaseBtn = new JButton[6];
    private int[] values = {
        countNonNullEmployees(this.app.getApple().getMotherboard()),
        countNonNullEmployees(this.app.getApple().getCPU()),
        countNonNullEmployees(this.app.getApple().getRAM()),
        countNonNullEmployees(this.app.getApple().getPSU()),
        countNonNullEmployees(this.app.getApple().getGPU()),
        countNonNullEmployees(this.app.getApple().getAssemblers())
    };

    private void updateBtnStatus() {
        updateValues();

        if (this.actualEmployees == this.maxEmployees) {
            for (JButton btn : increaseBtn) {
                btn.setEnabled(false);
                btn.setFocusable(false);
            }
        } else {
            for (JButton btn : increaseBtn) {
                btn.setEnabled(true);
                btn.setFocusable(true);
            }
        }

        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == 1) {
                this.decreaseBtn[i].setEnabled(false);
                this.decreaseBtn[i].setFocusable(false);
            } else {
                this.decreaseBtn[i].setEnabled(true);
                this.decreaseBtn[i].setFocusable(true);

            }
        }
    }

    private void updateValues() {
        values[0] = countNonNullEmployees(this.app.getApple().getMotherboard());
        values[1] = countNonNullEmployees(this.app.getApple().getCPU());
        values[2] = countNonNullEmployees(this.app.getApple().getRAM());
        values[3] = countNonNullEmployees(this.app.getApple().getPSU());
        values[4] = countNonNullEmployees(this.app.getApple().getGPU());
        values[5] = countNonNullEmployees(this.app.getApple().getAssemblers());
    }

    public static synchronized Apple getInstance() {
        if (apple == null) {
            apple = new Apple();
        }
        return apple;
    }

    public Apple() {
        try {
            // Código para el Look and Feel
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initializeValues();
        //cartoonPlayMusic("/GUI/Assets/nickelodeonTheme.wav");


        this.decreaseBtn[0] = decreaseScripts;
        this.decreaseBtn[1] = decreaseScenary;
        this.decreaseBtn[2] = decreaseAnimation;
        this.decreaseBtn[3] = decreaseDubbing;
        this.decreaseBtn[4] = decreacePlotTwist;
        this.decreaseBtn[5] = decreaceAssembler;
        this.increaseBtn[0] = increaseScripts;
        this.increaseBtn[1] = increaseScenary;
        this.increaseBtn[2] = increaseAnimation;
        this.increaseBtn[3] = increaseDubbing;
        this.increaseBtn[4] = increasePlotTwist;
        this.increaseBtn[5] = increaseAssembler;

        updateBtnStatus();
        this.start();
    }

    private void start() {
        // Crear un nuevo hilo para el bucle infinito
        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Ejecutar las actualizaciones de la UI en el EDT
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                // Aquí van tus actualizaciones de la UI
                                scriptDrive
                                        .setText(String.valueOf(app.getApple().getDrive().getSections()[0]));
                                scenaryDrive
                                        .setText(String.valueOf(app.getApple().getDrive().getSections()[1]));
                                animationDrive
                                        .setText(String.valueOf(app.getApple().getDrive().getSections()[2]));
                                dubbingDrive
                                        .setText(String.valueOf(app.getApple().getDrive().getSections()[3]));
                                plotTwistDrive
                                        .setText(String.valueOf(app.getApple().getDrive().getSections()[4]));
                                assemblerDrive
                                        .setText(String.valueOf(app.getApple().getDrive().getSections()[5]));

                                projectManagerStatus
                                        .setText(app.getApple().getProjectManagerInstance().getCurrentState());

                                currentDeadline.setText(
                                        String.valueOf(app.getApple().getRemainingDays()));

                                totalDays.setText(String.valueOf(app.getApple().getTotalDays()));

                                strikeCounter.setText(String
                                        .valueOf(app.getApple().getProjectManagerInstance().getStrikes()));
                                cashPenality.setText(String.valueOf(Integer.parseInt(strikeCounter.getText()) * 100));
                                directorStatus.setText(app.getApple().getDirectorInstance().getStatus());

                                totalChapters.setText(
                                        String.valueOf(app.getApple().getNumChapters()));
                                standardChapters.setText(
                                        String.valueOf(app.getApple().getNumNormalChapters()));

                                plotTwistChapters.setText(
                                        String.valueOf(app.getApple().getNumChaptersWithPlotTwist()));

                                standardChaptes2.setText(
                                        String.valueOf(app.getApple().getActualNumNormalChapters())
                                );
                                plotTwistChapters2.setText(
                                        String.valueOf(app.getApple().getActualNumChaptersWithPlotTwist())
                                );

                                standardChaptes1.setText(
                                        String.valueOf(app.getApple().getLastNumNormalChapters())
                                );
                                plotTwistChapters1.setText(
                                        String.valueOf(app.getApple().getLastNumChaptersWithPlotTwist())
                                );

                                profit.setText(formatNumberAsK((int) app.getApple().getEarning() -  (int) app.getApple().getTotalCost()));
                                cost.setText(formatNumberAsK((int) app.getApple().getTotalCost()));
                                earning.setText(formatNumberAsK((int) app.getApple().getEarning()));
                                batchLastProfit.setText(
                                        formatNumberAsK((int) app.getApple().getBatchLastProfit()));

                            }
                        });

                        // Pausar el hilo separado, no el EDT
                        Thread.sleep(app.getDayDuration() / 48);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // Opcionalmente, podrías salir del bucle si el hilo es interrumpido
                        break;
                    }
                }
            }
        });

        // Iniciar el hilo
        updateThread.start();
    }

    private void initializeValues() {
        if (this.app.getApple() != null) {
            this.maxEmployees = this.app.getApple().getMaxEmployeesQuantity();
            this.actualEmployees = this.app.getApple().getActualEmployeesQuantity();
            this.scriptsValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getApple().getMotherboard())));
            this.scenaryValue
                    .setText(String.valueOf(countNonNullEmployees(this.app.getApple().getCPU())));
            this.animationValues.setText(
                    String.valueOf(countNonNullEmployees(this.app.getApple().getRAM())));
            this.dubbingValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getApple().getPSU())));
            this.plotTwistValues.setText(
                    String.valueOf(countNonNullEmployees(this.app.getApple().getGPU())));
            this.assemblerValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getApple().getAssemblers())));
            this.maxCap.setText(String.valueOf(this.maxEmployees) + "     trabajadores");
        }
    }

    private int countNonNullEmployees(Employee[] employees) {
        int count = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                count++;
            }
        }
        return count;
    }

    public String formatNumberAsK(int number) {
        // Se onverte el número a miles
        double thousands = number / 1000.0;

        // Se redondea a dos dígitos significativos
        double rounded = Math.round(thousands * 100.0) / 100.0;

        // Se convierte a cadena y se añade 'K'
        return rounded + "K";
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SidePanel = new javax.swing.JPanel();
        btn_Inicio = new javax.swing.JPanel();
        icono1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_nuevo_pedido = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btn_nueva_ruta = new javax.swing.JPanel();
        icono3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_nuevo_almacen = new javax.swing.JPanel();
        icono4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_reporte = new javax.swing.JPanel();
        icono5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_cargar_guardar = new javax.swing.JPanel();
        icono7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        driveTitle4 = new javax.swing.JLabel();
        plotTwistChapters = new javax.swing.JTextField();
        standardChapters = new javax.swing.JTextField();
        driveTitle5 = new javax.swing.JLabel();
        driveTitle25 = new javax.swing.JLabel();
        totalChapters = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        driveTitle6 = new javax.swing.JLabel();
        driveTitle8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        driveTitle19 = new javax.swing.JLabel();
        currentDeadline = new javax.swing.JTextField();
        driveTitle23 = new javax.swing.JLabel();
        totalDays = new javax.swing.JTextField();
        workersConfigurations = new javax.swing.JPanel();
        driveTitle1 = new javax.swing.JLabel();
        scripts = new javax.swing.JPanel();
        scriptsTitle = new javax.swing.JLabel();
        increaseScripts = new javax.swing.JButton();
        scriptsValues = new javax.swing.JTextField();
        decreaseScripts = new javax.swing.JButton();
        scenary = new javax.swing.JPanel();
        scenaryTitle = new javax.swing.JLabel();
        scenaryValue = new javax.swing.JTextField();
        increaseScenary = new javax.swing.JButton();
        decreaseScenary = new javax.swing.JButton();
        animations = new javax.swing.JPanel();
        animationsTitle = new javax.swing.JLabel();
        animationValues = new javax.swing.JTextField();
        decreaseAnimation = new javax.swing.JButton();
        increaseAnimation = new javax.swing.JButton();
        dubbing = new javax.swing.JPanel();
        dubbingTitle = new javax.swing.JLabel();
        decreaseDubbing = new javax.swing.JButton();
        dubbingValues = new javax.swing.JTextField();
        increaseDubbing = new javax.swing.JButton();
        plotTwist = new javax.swing.JPanel();
        plotTwistTitle = new javax.swing.JLabel();
        increasePlotTwist = new javax.swing.JButton();
        plotTwistValues = new javax.swing.JTextField();
        decreacePlotTwist = new javax.swing.JButton();
        plotTwist2 = new javax.swing.JPanel();
        assemblerTitle = new javax.swing.JLabel();
        increaseAssembler = new javax.swing.JButton();
        assemblerValues = new javax.swing.JTextField();
        decreaceAssembler = new javax.swing.JButton();
        driveTitle21 = new javax.swing.JLabel();
        maxCap = new javax.swing.JLabel();
        driveTitle27 = new javax.swing.JLabel();
        drivePanel = new javax.swing.JPanel();
        driveTitle2 = new javax.swing.JLabel();
        driveTitle3 = new javax.swing.JLabel();
        scripts1 = new javax.swing.JPanel();
        scriptTitle1 = new javax.swing.JLabel();
        scriptsLimit1 = new javax.swing.JLabel();
        scriptDrive = new javax.swing.JTextField();
        scenary1 = new javax.swing.JPanel();
        scenaryTitle1 = new javax.swing.JLabel();
        scenaryLimit1 = new javax.swing.JLabel();
        scenaryDrive = new javax.swing.JTextField();
        animations1 = new javax.swing.JPanel();
        animationsTitle1 = new javax.swing.JLabel();
        animationsLimit1 = new javax.swing.JLabel();
        animationDrive = new javax.swing.JTextField();
        dubbing1 = new javax.swing.JPanel();
        dubbingTitle1 = new javax.swing.JLabel();
        dubbingLimit1 = new javax.swing.JLabel();
        dubbingDrive = new javax.swing.JTextField();
        plotTwist1 = new javax.swing.JPanel();
        plotTwistLimit1 = new javax.swing.JLabel();
        plotTwistTitle1 = new javax.swing.JLabel();
        plotTwistDrive = new javax.swing.JTextField();
        plotTwist4 = new javax.swing.JPanel();
        plotTwistLimit3 = new javax.swing.JLabel();
        assemblerTitle1 = new javax.swing.JLabel();
        assemblerDrive = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        driveTitle9 = new javax.swing.JLabel();
        driveTitle10 = new javax.swing.JLabel();
        driveTitle11 = new javax.swing.JLabel();
        driveTitle17 = new javax.swing.JLabel();
        cost = new javax.swing.JTextField();
        earning = new javax.swing.JTextField();
        profit = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        driveTitle18 = new javax.swing.JLabel();
        directorStatus = new javax.swing.JTextField();
        driveTitle14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        driveTitle12 = new javax.swing.JLabel();
        driveTitle13 = new javax.swing.JLabel();
        driveTitle16 = new javax.swing.JLabel();
        projectManagerStatus = new javax.swing.JTextField();
        strikeCounter = new javax.swing.JTextField();
        cashPenality = new javax.swing.JTextField();
        driveTitle15 = new javax.swing.JLabel();
        driveTitle = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        driveTitle7 = new javax.swing.JLabel();
        plotTwistChapters1 = new javax.swing.JTextField();
        standardChaptes1 = new javax.swing.JTextField();
        driveTitle22 = new javax.swing.JLabel();
        driveTitle28 = new javax.swing.JLabel();
        batchLastProfit = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        driveTitle20 = new javax.swing.JLabel();
        plotTwistChapters2 = new javax.swing.JTextField();
        standardChaptes2 = new javax.swing.JTextField();
        driveTitle24 = new javax.swing.JLabel();
        driveTitle26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SidePanel.setBackground(new java.awt.Color(34, 46, 60));
        SidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_Inicio.setBackground(new java.awt.Color(55, 71, 90));
        btn_Inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_InicioMouseClicked(evt);
            }
        });

        icono1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Inicio");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_InicioLayout = new javax.swing.GroupLayout(btn_Inicio);
        btn_Inicio.setLayout(btn_InicioLayout);
        btn_InicioLayout.setHorizontalGroup(
            btn_InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_InicioLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(icono1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        btn_InicioLayout.setVerticalGroup(
            btn_InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_InicioLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btn_InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icono1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        SidePanel.add(btn_Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 330, 60));

        btn_nuevo_pedido.setBackground(new java.awt.Color(55, 71, 90));
        btn_nuevo_pedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nuevo_pedidoMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dashboard");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_nuevo_pedidoLayout = new javax.swing.GroupLayout(btn_nuevo_pedido);
        btn_nuevo_pedido.setLayout(btn_nuevo_pedidoLayout);
        btn_nuevo_pedidoLayout.setHorizontalGroup(
            btn_nuevo_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_pedidoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        btn_nuevo_pedidoLayout.setVerticalGroup(
            btn_nuevo_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_pedidoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btn_nuevo_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        SidePanel.add(btn_nuevo_pedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 330, 60));

        btn_nueva_ruta.setBackground(new java.awt.Color(55, 71, 90));
        btn_nueva_ruta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nueva_rutaMouseClicked(evt);
            }
        });

        icono3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono3MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dell");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_nueva_rutaLayout = new javax.swing.GroupLayout(btn_nueva_ruta);
        btn_nueva_ruta.setLayout(btn_nueva_rutaLayout);
        btn_nueva_rutaLayout.setHorizontalGroup(
            btn_nueva_rutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nueva_rutaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icono3)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        btn_nueva_rutaLayout.setVerticalGroup(
            btn_nueva_rutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nueva_rutaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btn_nueva_rutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(icono3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        SidePanel.add(btn_nueva_ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 330, 60));

        btn_nuevo_almacen.setBackground(new java.awt.Color(55, 71, 90));
        btn_nuevo_almacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nuevo_almacenMouseClicked(evt);
            }
        });

        icono4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono4MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Configuración");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_nuevo_almacenLayout = new javax.swing.GroupLayout(btn_nuevo_almacen);
        btn_nuevo_almacen.setLayout(btn_nuevo_almacenLayout);
        btn_nuevo_almacenLayout.setHorizontalGroup(
            btn_nuevo_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icono4)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        btn_nuevo_almacenLayout.setVerticalGroup(
            btn_nuevo_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btn_nuevo_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icono4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        SidePanel.add(btn_nuevo_almacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 330, 60));

        btn_reporte.setBackground(new java.awt.Color(55, 71, 90));
        btn_reporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_reporteMouseClicked(evt);
            }
        });

        icono5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono5MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Guardar");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_reporteLayout = new javax.swing.GroupLayout(btn_reporte);
        btn_reporte.setLayout(btn_reporteLayout);
        btn_reporteLayout.setHorizontalGroup(
            btn_reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_reporteLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icono5)
                .addContainerGap(213, Short.MAX_VALUE))
        );
        btn_reporteLayout.setVerticalGroup(
            btn_reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_reporteLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(icono5, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(0, 14, Short.MAX_VALUE))
            .addGroup(btn_reporteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        SidePanel.add(btn_reporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 330, 60));

        btn_cargar_guardar.setBackground(new java.awt.Color(243, 168, 71));
        btn_cargar_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cargar_guardarMouseClicked(evt);
            }
        });

        icono7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono7MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Apple");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_cargar_guardarLayout = new javax.swing.GroupLayout(btn_cargar_guardar);
        btn_cargar_guardar.setLayout(btn_cargar_guardarLayout);
        btn_cargar_guardarLayout.setHorizontalGroup(
            btn_cargar_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_cargar_guardarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(icono7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        btn_cargar_guardarLayout.setVerticalGroup(
            btn_cargar_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_cargar_guardarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btn_cargar_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icono7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        SidePanel.add(btn_cargar_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 330, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Unimet 2024 ®");
        SidePanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, -1, -1));

        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SidePanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 220, 26));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Assets/AS.png"))); // NOI18N
        SidePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel1.add(SidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 730));

        jPanel2.setBackground(new java.awt.Color(34, 46, 60));

        jPanel4.setBackground(new java.awt.Color(246, 183, 102));
        jPanel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel4MouseDragged(evt);
            }
        });
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });

        exit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Assets/exit.png"))); // NOI18N
        exit.setText("Exit");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );


        jPanel3.setBackground(new java.awt.Color(34, 46, 60));
        jPanel3.setForeground(new java.awt.Color(51, 51, 51));

        driveTitle4.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle4.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle4.setText("Computadoras con GPU:");
        driveTitle4.setFocusable(false);

        plotTwistChapters.setEditable(false);
        plotTwistChapters.setBackground(new java.awt.Color(34, 46, 60));
        plotTwistChapters.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistChapters.setForeground(new java.awt.Color(255, 255, 255));
        plotTwistChapters.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        plotTwistChapters.setText("0");
        plotTwistChapters.setBorder(null);
        plotTwistChapters.setFocusable(false);
        plotTwistChapters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotTwistChaptersActionPerformed(evt);
            }
        });

        standardChapters.setEditable(false);
        standardChapters.setBackground(new java.awt.Color(34, 46, 60));
        standardChapters.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        standardChapters.setForeground(new java.awt.Color(255, 255, 255));
        standardChapters.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        standardChapters.setText("0");
        standardChapters.setBorder(null);
        standardChapters.setFocusable(false);
        standardChapters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standardChaptersActionPerformed(evt);
            }
        });

        driveTitle5.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle5.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle5.setText("Computadoras Normales:");
        driveTitle5.setFocusable(false);

        driveTitle25.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle25.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle25.setText("Computadoras (total):");
        driveTitle25.setFocusable(false);

        totalChapters.setEditable(false);
        totalChapters.setBackground(new java.awt.Color(34, 46, 60));
        totalChapters.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        totalChapters.setForeground(new java.awt.Color(255, 255, 255));
        totalChapters.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalChapters.setText("0");
        totalChapters.setBorder(null);
        totalChapters.setFocusable(false);
        totalChapters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalChaptersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(driveTitle4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(driveTitle5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(standardChapters, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plotTwistChapters, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(driveTitle25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalChapters, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalChapters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driveTitle25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(standardChapters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driveTitle5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle4)
                    .addComponent(plotTwistChapters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5.setBackground(new java.awt.Color(34, 46, 60));
        jPanel5.setForeground(new java.awt.Color(51, 51, 51));

        driveTitle6.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle6.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle6.setText("Días Restantes:");

        driveTitle8.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle8.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle8.setText("Días transcurridos:");

        jTextField7.setBackground(new java.awt.Color(204, 204, 204));
        jTextField7.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(51, 51, 51));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText("0");
        jTextField7.setEnabled(false);

        jTextField8.setBackground(new java.awt.Color(204, 204, 204));
        jTextField8.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(51, 51, 51));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("0");
        jTextField8.setEnabled(false);

        driveTitle19.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle19.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle19.setText("Días para la entrega:");
        driveTitle19.setFocusable(false);

        currentDeadline.setEditable(false);
        currentDeadline.setBackground(new java.awt.Color(34, 46, 60));
        currentDeadline.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        currentDeadline.setForeground(new java.awt.Color(255, 255, 255));
        currentDeadline.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        currentDeadline.setText("0");
        currentDeadline.setBorder(null);
        currentDeadline.setFocusable(false);
        currentDeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentDeadlineActionPerformed(evt);
            }
        });

        driveTitle23.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle23.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle23.setText("Días:");
        driveTitle23.setFocusable(false);

        totalDays.setEditable(false);
        totalDays.setBackground(new java.awt.Color(34, 46, 60));
        totalDays.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        totalDays.setForeground(new java.awt.Color(255, 255, 255));
        totalDays.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalDays.setText("0");
        totalDays.setBorder(null);
        totalDays.setFocusable(false);
        totalDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalDaysActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(driveTitle23, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalDays, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(driveTitle19, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(currentDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(driveTitle8)
                            .addComponent(driveTitle6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle23)
                    .addComponent(totalDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driveTitle19, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(144, 144, 144)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 190));

        workersConfigurations.setBackground(new java.awt.Color(243, 168, 71));

        driveTitle1.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        driveTitle1.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle1.setText("TRABAJADORES");

        scripts.setBackground(java.awt.Color.lightGray);
        scripts.setForeground(new java.awt.Color(60, 63, 65));

        scriptsTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scriptsTitle.setForeground(new java.awt.Color(51, 51, 51));
        scriptsTitle.setText("Tarjetas Madre:");

        increaseScripts.setBackground(new java.awt.Color(51, 51, 51));
        increaseScripts.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseScripts.setForeground(new java.awt.Color(255, 255, 255));
        increaseScripts.setText("+");
        increaseScripts.setBorderPainted(false);
        increaseScripts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseScriptsMouseClicked(evt);
            }
        });
        increaseScripts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseScriptsActionPerformed(evt);
            }
        });

        scriptsValues.setBackground(java.awt.Color.lightGray);
        scriptsValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scriptsValues.setForeground(new java.awt.Color(51, 51, 51));
        scriptsValues.setText("0");
        scriptsValues.setBorder(null);
        scriptsValues.setFocusable(false);
        scriptsValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scriptsValuesActionPerformed(evt);
            }
        });

        decreaseScripts.setBackground(new java.awt.Color(51, 51, 51));
        decreaseScripts.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseScripts.setForeground(new java.awt.Color(255, 255, 255));
        decreaseScripts.setText("-");
        decreaseScripts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseScriptsMouseClicked(evt);
            }
        });
        decreaseScripts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseScriptsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scriptsLayout = new javax.swing.GroupLayout(scripts);
        scripts.setLayout(scriptsLayout);
        scriptsLayout.setHorizontalGroup(
            scriptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scriptsLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(scriptsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(decreaseScripts)
                .addGap(18, 18, 18)
                .addComponent(scriptsValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseScripts)
                .addGap(15, 15, 15))
        );
        scriptsLayout.setVerticalGroup(
            scriptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scriptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(scriptsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(increaseScripts)
                .addComponent(scriptsValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(decreaseScripts))
        );

        scenary.setBackground(java.awt.Color.lightGray);
        scenary.setForeground(new java.awt.Color(60, 63, 65));
        scenary.setPreferredSize(new java.awt.Dimension(257, 44));

        scenaryTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scenaryTitle.setForeground(new java.awt.Color(51, 51, 51));
        scenaryTitle.setText("CPUs:");

        scenaryValue.setBackground(java.awt.Color.lightGray);
        scenaryValue.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scenaryValue.setForeground(new java.awt.Color(51, 51, 51));
        scenaryValue.setText("0");
        scenaryValue.setBorder(null);
        scenaryValue.setFocusable(false);
        scenaryValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scenaryValueActionPerformed(evt);
            }
        });

        increaseScenary.setBackground(new java.awt.Color(51, 51, 51));
        increaseScenary.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseScenary.setForeground(new java.awt.Color(255, 255, 255));
        increaseScenary.setText("+");
        increaseScenary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseScenaryMouseClicked(evt);
            }
        });
        increaseScenary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseScenaryActionPerformed(evt);
            }
        });

        decreaseScenary.setBackground(new java.awt.Color(51, 51, 51));
        decreaseScenary.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseScenary.setForeground(new java.awt.Color(255, 255, 255));
        decreaseScenary.setText("-");
        decreaseScenary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseScenaryMouseClicked(evt);
            }
        });
        decreaseScenary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseScenaryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scenaryLayout = new javax.swing.GroupLayout(scenary);
        scenary.setLayout(scenaryLayout);
        scenaryLayout.setHorizontalGroup(
            scenaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scenaryLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(scenaryTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseScenary)
                .addGap(18, 18, 18)
                .addComponent(scenaryValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseScenary)
                .addGap(14, 14, 14))
        );
        scenaryLayout.setVerticalGroup(
            scenaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scenaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scenaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(scenaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(increaseScenary)
                        .addComponent(scenaryValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(decreaseScenary))
                    .addComponent(scenaryTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        animations.setBackground(java.awt.Color.lightGray);
        animations.setForeground(new java.awt.Color(255, 255, 255));
        animations.setPreferredSize(new java.awt.Dimension(257, 44));

        animationsTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        animationsTitle.setForeground(new java.awt.Color(51, 51, 51));
        animationsTitle.setText("Memorias RAM:");

        animationValues.setBackground(java.awt.Color.lightGray);
        animationValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        animationValues.setForeground(new java.awt.Color(51, 51, 51));
        animationValues.setText("0");
        animationValues.setBorder(null);
        animationValues.setFocusable(false);
        animationValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animationValuesActionPerformed(evt);
            }
        });

        decreaseAnimation.setBackground(new java.awt.Color(51, 51, 51));
        decreaseAnimation.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseAnimation.setForeground(new java.awt.Color(255, 255, 255));
        decreaseAnimation.setText("-");
        decreaseAnimation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseAnimationMouseClicked(evt);
            }
        });
        decreaseAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseAnimationActionPerformed(evt);
            }
        });

        increaseAnimation.setBackground(new java.awt.Color(51, 51, 51));
        increaseAnimation.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseAnimation.setForeground(new java.awt.Color(255, 255, 255));
        increaseAnimation.setText("+");
        increaseAnimation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseAnimationMouseClicked(evt);
            }
        });
        increaseAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseAnimationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout animationsLayout = new javax.swing.GroupLayout(animations);
        animations.setLayout(animationsLayout);
        animationsLayout.setHorizontalGroup(
            animationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(animationsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(animationsTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseAnimation)
                .addGap(18, 18, 18)
                .addComponent(animationValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseAnimation)
                .addGap(15, 15, 15))
        );
        animationsLayout.setVerticalGroup(
            animationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(animationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(animationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decreaseAnimation)
                    .addComponent(animationValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(increaseAnimation)
                    .addComponent(animationsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        dubbing.setBackground(java.awt.Color.lightGray);
        dubbing.setForeground(new java.awt.Color(255, 255, 255));
        dubbing.setPreferredSize(new java.awt.Dimension(257, 44));

        dubbingTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        dubbingTitle.setForeground(new java.awt.Color(51, 51, 51));
        dubbingTitle.setText("Fuentes de Poder:");

        decreaseDubbing.setBackground(new java.awt.Color(51, 51, 51));
        decreaseDubbing.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseDubbing.setForeground(new java.awt.Color(204, 204, 204));
        decreaseDubbing.setText("-");
        decreaseDubbing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseDubbingMouseClicked(evt);
            }
        });
        decreaseDubbing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseDubbingActionPerformed(evt);
            }
        });

        dubbingValues.setBackground(java.awt.Color.lightGray);
        dubbingValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        dubbingValues.setForeground(new java.awt.Color(51, 51, 51));
        dubbingValues.setText("0");
        dubbingValues.setBorder(null);
        dubbingValues.setFocusable(false);
        dubbingValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dubbingValuesActionPerformed(evt);
            }
        });

        increaseDubbing.setBackground(new java.awt.Color(51, 51, 51));
        increaseDubbing.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseDubbing.setForeground(new java.awt.Color(255, 255, 255));
        increaseDubbing.setText("+");
        increaseDubbing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseDubbingMouseClicked(evt);
            }
        });
        increaseDubbing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseDubbingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dubbingLayout = new javax.swing.GroupLayout(dubbing);
        dubbing.setLayout(dubbingLayout);
        dubbingLayout.setHorizontalGroup(
            dubbingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dubbingLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(dubbingTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseDubbing)
                .addGap(18, 18, 18)
                .addComponent(dubbingValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseDubbing)
                .addGap(15, 15, 15))
        );
        dubbingLayout.setVerticalGroup(
            dubbingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dubbingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dubbingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decreaseDubbing)
                    .addComponent(dubbingValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(increaseDubbing)
                    .addComponent(dubbingTitle))
                .addContainerGap())
        );

        plotTwist.setBackground(java.awt.Color.lightGray);
        plotTwist.setForeground(new java.awt.Color(255, 255, 255));
        plotTwist.setPreferredSize(new java.awt.Dimension(257, 44));

        plotTwistTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistTitle.setForeground(new java.awt.Color(51, 51, 51));
        plotTwistTitle.setText("Tarjetas de Video:");

        increasePlotTwist.setBackground(new java.awt.Color(51, 51, 51));
        increasePlotTwist.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increasePlotTwist.setForeground(new java.awt.Color(255, 255, 255));
        increasePlotTwist.setText("+");
        increasePlotTwist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increasePlotTwistMouseClicked(evt);
            }
        });
        increasePlotTwist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increasePlotTwistActionPerformed(evt);
            }
        });

        plotTwistValues.setBackground(java.awt.Color.lightGray);
        plotTwistValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistValues.setForeground(new java.awt.Color(51, 51, 51));
        plotTwistValues.setText("0");
        plotTwistValues.setBorder(null);
        plotTwistValues.setFocusable(false);
        plotTwistValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotTwistValuesActionPerformed(evt);
            }
        });

        decreacePlotTwist.setBackground(new java.awt.Color(51, 51, 51));
        decreacePlotTwist.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreacePlotTwist.setForeground(new java.awt.Color(255, 255, 255));
        decreacePlotTwist.setText("-");
        decreacePlotTwist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreacePlotTwistMouseClicked(evt);
            }
        });
        decreacePlotTwist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreacePlotTwistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plotTwistLayout = new javax.swing.GroupLayout(plotTwist);
        plotTwist.setLayout(plotTwistLayout);
        plotTwistLayout.setHorizontalGroup(
            plotTwistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plotTwistLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(plotTwistTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreacePlotTwist)
                .addGap(18, 18, 18)
                .addComponent(plotTwistValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increasePlotTwist)
                .addGap(16, 16, 16))
        );
        plotTwistLayout.setVerticalGroup(
            plotTwistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plotTwistLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plotTwistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(increasePlotTwist)
                    .addComponent(plotTwistValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decreacePlotTwist)
                    .addComponent(plotTwistTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        plotTwist2.setBackground(java.awt.Color.lightGray);
        plotTwist2.setForeground(new java.awt.Color(255, 255, 255));
        plotTwist2.setPreferredSize(new java.awt.Dimension(257, 44));

        assemblerTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        assemblerTitle.setForeground(new java.awt.Color(51, 51, 51));
        assemblerTitle.setText("Ensambladores:");

        increaseAssembler.setBackground(new java.awt.Color(51, 51, 51));
        increaseAssembler.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseAssembler.setForeground(new java.awt.Color(255, 255, 255));
        increaseAssembler.setText("+");
        increaseAssembler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseAssemblerMouseClicked(evt);
            }
        });
        increaseAssembler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseAssemblerActionPerformed(evt);
            }
        });

        assemblerValues.setBackground(java.awt.Color.lightGray);
        assemblerValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        assemblerValues.setForeground(new java.awt.Color(51, 51, 51));
        assemblerValues.setText("0");
        assemblerValues.setBorder(null);
        assemblerValues.setFocusable(false);
        assemblerValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assemblerValuesActionPerformed(evt);
            }
        });

        decreaceAssembler.setBackground(new java.awt.Color(51, 51, 51));
        decreaceAssembler.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaceAssembler.setForeground(new java.awt.Color(255, 255, 255));
        decreaceAssembler.setText("-");
        decreaceAssembler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaceAssemblerMouseClicked(evt);
            }
        });
        decreaceAssembler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaceAssemblerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plotTwist2Layout = new javax.swing.GroupLayout(plotTwist2);
        plotTwist2.setLayout(plotTwist2Layout);
        plotTwist2Layout.setHorizontalGroup(
            plotTwist2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plotTwist2Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(assemblerTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(decreaceAssembler)
                .addGap(18, 18, 18)
                .addComponent(assemblerValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseAssembler)
                .addGap(16, 16, 16))
        );
        plotTwist2Layout.setVerticalGroup(
            plotTwist2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plotTwist2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plotTwist2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(increaseAssembler)
                    .addComponent(assemblerValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decreaceAssembler)
                    .addComponent(assemblerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        driveTitle21.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        driveTitle21.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle21.setText("CONFIGURACIÓN");

        maxCap.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        maxCap.setForeground(new java.awt.Color(51, 51, 51));
        maxCap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        driveTitle27.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        driveTitle27.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        driveTitle27.setText("Máximo:");

        javax.swing.GroupLayout workersConfigurationsLayout = new javax.swing.GroupLayout(workersConfigurations);
        workersConfigurations.setLayout(workersConfigurationsLayout);
        workersConfigurationsLayout.setHorizontalGroup(
            workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(driveTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(driveTitle21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workersConfigurationsLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, workersConfigurationsLayout.createSequentialGroup()
                        .addComponent(driveTitle27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(plotTwist2, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(plotTwist, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(dubbing, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(animations, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(scenary, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(scripts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        workersConfigurationsLayout.setVerticalGroup(
            workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workersConfigurationsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(driveTitle21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(driveTitle1)
                .addGap(26, 26, 26)
                .addComponent(scripts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scenary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(animations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dubbing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plotTwist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plotTwist2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(driveTitle27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxCap, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(workersConfigurations, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 300, 510));

        drivePanel.setBackground(new java.awt.Color(243, 168, 71));

        driveTitle2.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        driveTitle2.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle2.setText("ESTATUS DEL DRIVE");

        driveTitle3.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        driveTitle3.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle3.setText("(Disponiblidad)");

        scripts1.setBackground(java.awt.Color.lightGray);
        scripts1.setForeground(new java.awt.Color(60, 63, 65));
        scripts1.setPreferredSize(new java.awt.Dimension(218, 44));

        scriptTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scriptTitle1.setForeground(new java.awt.Color(51, 51, 51));
        scriptTitle1.setText("Motherboard:");

        scriptsLimit1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scriptsLimit1.setForeground(new java.awt.Color(51, 51, 51));
        scriptsLimit1.setText("/25");
        scriptsLimit1.setFocusable(false);

        scriptDrive.setBackground(java.awt.Color.lightGray);
        scriptDrive.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scriptDrive.setForeground(new java.awt.Color(51, 51, 51));
        scriptDrive.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        scriptDrive.setText("0");
        scriptDrive.setBorder(null);
        scriptDrive.setFocusable(false);
        scriptDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scriptDriveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scripts1Layout = new javax.swing.GroupLayout(scripts1);
        scripts1.setLayout(scripts1Layout);
        scripts1Layout.setHorizontalGroup(
            scripts1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scripts1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(scriptTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scriptDrive, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scriptsLimit1)
                .addGap(16, 16, 16))
        );
        scripts1Layout.setVerticalGroup(
            scripts1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, scripts1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scripts1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scriptTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(scriptsLimit1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scriptDrive))
                .addContainerGap())
        );

        scenary1.setBackground(java.awt.Color.lightGray);
        scenary1.setForeground(new java.awt.Color(60, 63, 65));
        scenary1.setPreferredSize(new java.awt.Dimension(218, 44));

        scenaryTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scenaryTitle1.setForeground(new java.awt.Color(51, 51, 51));
        scenaryTitle1.setText("CPUs:");
        scenaryTitle1.setPreferredSize(new java.awt.Dimension(70, 21));

        scenaryLimit1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scenaryLimit1.setForeground(new java.awt.Color(51, 51, 51));
        scenaryLimit1.setText("/20");
        scenaryLimit1.setFocusable(false);

        scenaryDrive.setBackground(java.awt.Color.lightGray);
        scenaryDrive.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scenaryDrive.setForeground(new java.awt.Color(51, 51, 51));
        scenaryDrive.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        scenaryDrive.setText("0");
        scenaryDrive.setBorder(null);
        scenaryDrive.setFocusable(false);
        scenaryDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scenaryDriveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scenary1Layout = new javax.swing.GroupLayout(scenary1);
        scenary1.setLayout(scenary1Layout);
        scenary1Layout.setHorizontalGroup(
            scenary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scenary1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(scenaryTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scenaryDrive, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scenaryLimit1)
                .addGap(15, 15, 15))
        );
        scenary1Layout.setVerticalGroup(
            scenary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scenary1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scenary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scenaryDrive)
                    .addComponent(scenaryTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(scenaryLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        animations1.setBackground(java.awt.Color.lightGray);
        animations1.setForeground(new java.awt.Color(60, 63, 65));
        animations1.setPreferredSize(new java.awt.Dimension(218, 44));

        animationsTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        animationsTitle1.setForeground(new java.awt.Color(51, 51, 51));
        animationsTitle1.setText("RAMs:");
        animationsTitle1.setPreferredSize(new java.awt.Dimension(70, 21));

        animationsLimit1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        animationsLimit1.setForeground(new java.awt.Color(51, 51, 51));
        animationsLimit1.setText("/55");
        animationsLimit1.setFocusable(false);

        animationDrive.setBackground(java.awt.Color.lightGray);
        animationDrive.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        animationDrive.setForeground(new java.awt.Color(51, 51, 51));
        animationDrive.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        animationDrive.setText("0");
        animationDrive.setBorder(null);
        animationDrive.setFocusable(false);
        animationDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animationDriveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout animations1Layout = new javax.swing.GroupLayout(animations1);
        animations1.setLayout(animations1Layout);
        animations1Layout.setHorizontalGroup(
            animations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(animations1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(animationsTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(animationDrive, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animationsLimit1)
                .addGap(14, 14, 14))
        );
        animations1Layout.setVerticalGroup(
            animations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(animations1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(animations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(animationDrive, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(animationsTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(animationsLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        dubbing1.setBackground(java.awt.Color.lightGray);
        dubbing1.setForeground(new java.awt.Color(60, 63, 65));
        dubbing1.setPreferredSize(new java.awt.Dimension(218, 44));

        dubbingTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        dubbingTitle1.setForeground(new java.awt.Color(51, 51, 51));
        dubbingTitle1.setText("PSUs:");
        dubbingTitle1.setPreferredSize(new java.awt.Dimension(70, 21));

        dubbingLimit1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        dubbingLimit1.setForeground(new java.awt.Color(51, 51, 51));
        dubbingLimit1.setText("/35");
        dubbingLimit1.setFocusable(false);

        dubbingDrive.setBackground(java.awt.Color.lightGray);
        dubbingDrive.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        dubbingDrive.setForeground(new java.awt.Color(51, 51, 51));
        dubbingDrive.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        dubbingDrive.setText("0");
        dubbingDrive.setBorder(null);
        dubbingDrive.setFocusable(false);
        dubbingDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dubbingDriveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dubbing1Layout = new javax.swing.GroupLayout(dubbing1);
        dubbing1.setLayout(dubbing1Layout);
        dubbing1Layout.setHorizontalGroup(
            dubbing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dubbing1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(dubbingTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(dubbingDrive, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dubbingLimit1)
                .addGap(14, 14, 14))
        );
        dubbing1Layout.setVerticalGroup(
            dubbing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dubbing1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dubbing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dubbingTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addGroup(dubbing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dubbingLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dubbingDrive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        plotTwist1.setBackground(java.awt.Color.lightGray);
        plotTwist1.setForeground(new java.awt.Color(60, 63, 65));
        plotTwist1.setFocusable(false);
        plotTwist1.setPreferredSize(new java.awt.Dimension(218, 44));

        plotTwistLimit1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistLimit1.setForeground(new java.awt.Color(51, 51, 51));
        plotTwistLimit1.setText("/10");
        plotTwistLimit1.setFocusable(false);

        plotTwistTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistTitle1.setForeground(new java.awt.Color(51, 51, 51));
        plotTwistTitle1.setText("GPUs:");
        plotTwistTitle1.setPreferredSize(new java.awt.Dimension(70, 21));

        plotTwistDrive.setBackground(java.awt.Color.lightGray);
        plotTwistDrive.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistDrive.setForeground(new java.awt.Color(51, 51, 51));
        plotTwistDrive.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        plotTwistDrive.setText("0");
        plotTwistDrive.setBorder(null);
        plotTwistDrive.setFocusable(false);
        plotTwistDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotTwistDriveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plotTwist1Layout = new javax.swing.GroupLayout(plotTwist1);
        plotTwist1.setLayout(plotTwist1Layout);
        plotTwist1Layout.setHorizontalGroup(
            plotTwist1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plotTwist1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(plotTwistTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(plotTwistDrive, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plotTwistLimit1)
                .addGap(14, 14, 14))
        );
        plotTwist1Layout.setVerticalGroup(
            plotTwist1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plotTwist1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plotTwist1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plotTwistTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addGroup(plotTwist1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(plotTwistLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plotTwistDrive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        plotTwist4.setBackground(java.awt.Color.lightGray);
        plotTwist4.setForeground(new java.awt.Color(60, 63, 65));
        plotTwist4.setFocusable(false);
        plotTwist4.setPreferredSize(new java.awt.Dimension(218, 44));

        plotTwistLimit3.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistLimit3.setForeground(new java.awt.Color(51, 51, 51));
        plotTwistLimit3.setFocusable(false);

        assemblerTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        assemblerTitle1.setForeground(new java.awt.Color(51, 51, 51));
        assemblerTitle1.setText("Ensambladores:");
        assemblerTitle1.setPreferredSize(new java.awt.Dimension(70, 21));

        assemblerDrive.setBackground(java.awt.Color.lightGray);
        assemblerDrive.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        assemblerDrive.setForeground(new java.awt.Color(51, 51, 51));
        assemblerDrive.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        assemblerDrive.setText("0");
        assemblerDrive.setBorder(null);
        assemblerDrive.setFocusable(false);
        assemblerDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assemblerDriveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plotTwist4Layout = new javax.swing.GroupLayout(plotTwist4);
        plotTwist4.setLayout(plotTwist4Layout);
        plotTwist4Layout.setHorizontalGroup(
            plotTwist4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plotTwist4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(assemblerTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plotTwistLimit3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assemblerDrive, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        plotTwist4Layout.setVerticalGroup(
            plotTwist4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plotTwist4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plotTwist4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(plotTwistLimit3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(assemblerDrive)
                    .addComponent(assemblerTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout drivePanelLayout = new javax.swing.GroupLayout(drivePanel);
        drivePanel.setLayout(drivePanelLayout);
        drivePanelLayout.setHorizontalGroup(
            drivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drivePanelLayout.createSequentialGroup()
                .addGroup(drivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(driveTitle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(drivePanelLayout.createSequentialGroup()
                        .addGroup(drivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(drivePanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(drivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dubbing1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addComponent(animations1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addComponent(scenary1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addComponent(scripts1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addComponent(plotTwist1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addComponent(plotTwist4, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)))
                            .addGroup(drivePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(driveTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        drivePanelLayout.setVerticalGroup(
            drivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drivePanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(driveTitle2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(driveTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(scripts1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scenary1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(animations1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dubbing1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plotTwist1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plotTwist4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jPanel1.add(drivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 250, 510));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setForeground(new java.awt.Color(51, 51, 51));
        jPanel6.setEnabled(false);

        driveTitle9.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle9.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle9.setText("Ganancia bruta:");
        driveTitle9.setFocusable(false);

        driveTitle10.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle10.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle10.setText("Costos operativos:");
        driveTitle10.setFocusable(false);

        driveTitle11.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        driveTitle11.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle11.setText("COSTOS/ GANANCIAS");
        driveTitle11.setFocusable(false);

        driveTitle17.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle17.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle17.setText("Ganancia neta:");
        driveTitle17.setFocusable(false);

        cost.setBackground(new java.awt.Color(51, 51, 51));
        cost.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        cost.setForeground(new java.awt.Color(255, 255, 255));
        cost.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cost.setText("0");
        cost.setBorder(null);
        cost.setFocusable(false);
        cost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costActionPerformed(evt);
            }
        });

        earning.setBackground(new java.awt.Color(51, 51, 51));
        earning.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        earning.setForeground(new java.awt.Color(255, 255, 255));
        earning.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        earning.setText("0");
        earning.setBorder(null);
        earning.setFocusable(false);
        earning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                earningActionPerformed(evt);
            }
        });

        profit.setBackground(new java.awt.Color(51, 51, 51));
        profit.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        profit.setForeground(new java.awt.Color(255, 255, 255));
        profit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        profit.setText("0");
        profit.setBorder(null);
        profit.setFocusable(false);
        profit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(driveTitle17, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(driveTitle10))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(driveTitle9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(earning, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
            .addComponent(driveTitle11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(driveTitle11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle10)
                    .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(earning, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(driveTitle9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(driveTitle17)
                    .addComponent(profit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, 300, 120));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setForeground(new java.awt.Color(51, 51, 51));

        driveTitle18.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle18.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle18.setText("Estado:");
        driveTitle18.setFocusable(false);

        directorStatus.setBackground(new java.awt.Color(51, 51, 51));
        directorStatus.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        directorStatus.setForeground(new java.awt.Color(255, 255, 255));
        directorStatus.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        directorStatus.setText("0");
        directorStatus.setBorder(null);
        directorStatus.setFocusable(false);
        directorStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directorStatusActionPerformed(evt);
            }
        });

        driveTitle14.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        driveTitle14.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle14.setText("DIRECTOR");
        driveTitle14.setFocusable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(driveTitle18)
                .addGap(29, 29, 29)
                .addComponent(directorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(driveTitle14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(driveTitle14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle18)
                    .addComponent(directorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 320, 300, 60));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setForeground(new java.awt.Color(51, 51, 51));
        jPanel8.setEnabled(false);

        driveTitle12.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle12.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle12.setText("Estado:");
        driveTitle12.setFocusable(false);

        driveTitle13.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle13.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle13.setText("Faltas:");
        driveTitle13.setFocusable(false);

        driveTitle16.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle16.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle16.setText("Penalización ($):");
        driveTitle16.setFocusable(false);

        projectManagerStatus.setBackground(new java.awt.Color(51, 51, 51));
        projectManagerStatus.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        projectManagerStatus.setForeground(new java.awt.Color(255, 255, 255));
        projectManagerStatus.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        projectManagerStatus.setText("Por comenzar");
        projectManagerStatus.setBorder(null);
        projectManagerStatus.setFocusable(false);
        projectManagerStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectManagerStatusActionPerformed(evt);
            }
        });

        strikeCounter.setBackground(new java.awt.Color(51, 51, 51));
        strikeCounter.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        strikeCounter.setForeground(new java.awt.Color(255, 255, 255));
        strikeCounter.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        strikeCounter.setText("0");
        strikeCounter.setBorder(null);
        strikeCounter.setFocusable(false);
        strikeCounter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strikeCounterActionPerformed(evt);
            }
        });

        cashPenality.setBackground(new java.awt.Color(51, 51, 51));
        cashPenality.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        cashPenality.setForeground(new java.awt.Color(255, 255, 255));
        cashPenality.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cashPenality.setText("0");
        cashPenality.setBorder(null);
        cashPenality.setFocusable(false);
        cashPenality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashPenalityActionPerformed(evt);
            }
        });

        driveTitle15.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        driveTitle15.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle15.setText("STATUS PROJECT MANAGER");
        driveTitle15.setFocusable(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(driveTitle13, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(driveTitle16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(strikeCounter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cashPenality)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(driveTitle12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(projectManagerStatus)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(driveTitle15, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(driveTitle15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle12)
                    .addComponent(projectManagerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle13)
                    .addComponent(strikeCounter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle16)
                    .addComponent(cashPenality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 380, 300, 120));

        driveTitle.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        driveTitle.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle.setText("Lote Actual");
        jPanel1.add(driveTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 500, 300, -1));

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setForeground(new java.awt.Color(51, 51, 51));

        driveTitle7.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle7.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle7.setText("Computadoras con GPU:");
        driveTitle7.setFocusable(false);

        plotTwistChapters1.setBackground(new java.awt.Color(51, 51, 51));
        plotTwistChapters1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistChapters1.setForeground(new java.awt.Color(255, 255, 255));
        plotTwistChapters1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        plotTwistChapters1.setText("0");
        plotTwistChapters1.setBorder(null);
        plotTwistChapters1.setFocusable(false);
        plotTwistChapters1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotTwistChapters1ActionPerformed(evt);
            }
        });

        standardChaptes1.setBackground(new java.awt.Color(51, 51, 51));
        standardChaptes1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        standardChaptes1.setForeground(new java.awt.Color(255, 255, 255));
        standardChaptes1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        standardChaptes1.setText("0");
        standardChaptes1.setBorder(null);
        standardChaptes1.setFocusable(false);
        standardChaptes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standardChaptes1ActionPerformed(evt);
            }
        });

        driveTitle22.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle22.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle22.setText("Computadoras normales:");
        driveTitle22.setFocusable(false);

        driveTitle28.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle28.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle28.setText("Ganancia neta:");
        driveTitle28.setFocusable(false);

        batchLastProfit.setBackground(new java.awt.Color(51, 51, 51));
        batchLastProfit.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        batchLastProfit.setForeground(new java.awt.Color(255, 255, 255));
        batchLastProfit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        batchLastProfit.setText("0");
        batchLastProfit.setBorder(null);
        batchLastProfit.setFocusable(false);
        batchLastProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchLastProfitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(driveTitle28, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(batchLastProfit))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(driveTitle7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(driveTitle22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(plotTwistChapters1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(standardChaptes1))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(standardChaptes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driveTitle22, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle7)
                    .addComponent(plotTwistChapters1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle28)
                    .addComponent(batchLastProfit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 620, 300, 90));

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));
        jPanel10.setForeground(new java.awt.Color(51, 51, 51));

        driveTitle20.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle20.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle20.setText("Computadoras con GPU:");
        driveTitle20.setFocusable(false);

        plotTwistChapters2.setBackground(new java.awt.Color(51, 51, 51));
        plotTwistChapters2.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistChapters2.setForeground(new java.awt.Color(255, 255, 255));
        plotTwistChapters2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        plotTwistChapters2.setText("0");
        plotTwistChapters2.setBorder(null);
        plotTwistChapters2.setFocusable(false);
        plotTwistChapters2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotTwistChapters2ActionPerformed(evt);
            }
        });

        standardChaptes2.setBackground(new java.awt.Color(51, 51, 51));
        standardChaptes2.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        standardChaptes2.setForeground(new java.awt.Color(255, 255, 255));
        standardChaptes2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        standardChaptes2.setText("0");
        standardChaptes2.setBorder(null);
        standardChaptes2.setFocusable(false);
        standardChaptes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standardChaptes2ActionPerformed(evt);
            }
        });

        driveTitle24.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle24.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle24.setText("Computadoras normales:");
        driveTitle24.setFocusable(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(driveTitle20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(driveTitle24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plotTwistChapters2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(standardChaptes2))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(standardChaptes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driveTitle24, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle20)
                    .addComponent(plotTwistChapters2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 530, 300, -1));

        driveTitle26.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        driveTitle26.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle26.setText("Último Lote");
        jPanel1.add(driveTitle26, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 590, 300, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        initialClick = evt.getPoint();
    }                                    

    private void jPanel4MouseDragged(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        int x = getLocation().x - initialClick.x + evt.getX();
        int y = getLocation().y - initialClick.y + evt.getY();
        setLocation(x, y);
    }                                    

    private void exitMousePressed(java.awt.event.MouseEvent evt) {                                  
        // TODO add your handling code here:
        System.exit(0);
    }                                 

    private void btn_cargar_guardarMouseClicked(java.awt.event.MouseEvent evt) {                                                
        // TODO add your handling code here:

    }                                               

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:

    }                                     

    private void icono7MouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:

    }                                   

    private void btn_reporteMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        try {
            this.filefunctions.write(this.selectedFile);
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }                                        

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        try {
            this.filefunctions.write(this.selectedFile);
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }                                    

    private void icono5MouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
    }                                   

    private void btn_nuevo_almacenMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        ConfigParams v2 = new ConfigParams();
        v2.setVisible(true);
        this.dispose();
    }                                              

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        ConfigParams v2 = new ConfigParams();
        v2.setVisible(true);
        this.dispose();
    }                                    

    private void icono4MouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:

    }                                   

    private void btn_nueva_rutaMouseClicked(java.awt.event.MouseEvent evt) {                                            
        // TODO add your handling code here:
        Dell v3 = new Dell();
        v3.setVisible(true);
        this.dispose();
    }                                           

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        Dell v3 = new Dell();
        v3.setVisible(true);
        this.dispose();

    }                                    

    private void icono3MouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:

    }                                   

    private void btn_nuevo_pedidoMouseClicked(java.awt.event.MouseEvent evt) {                                              
        // TODO add your handling code here:
        Dashboard dashboard = Dashboard.getInstance();
        dashboard.setVisible(true);
        this.dispose();
    }                                             

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        Dashboard dashboard = Dashboard.getInstance();
        dashboard.setVisible(true);
        this.dispose();
    }                                    

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        Home v1 = new Home();
        v1.setVisible(true);
        this.dispose();
    }                                    

    private void btn_InicioMouseClicked(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        Home v1 = new Home();
        v1.setVisible(true);
        this.dispose();
    }                                       

    private void increaseScriptsMouseClicked(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
        if (this.canIncreaseQuantity(0)) {
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.scriptsValues.setText(increaseQuantity(this.scriptsValues.getText(), increaseScripts));
            helper.addWorker(0, 0);
        }
        updateBtnStatus();
    }                                            

    private void increaseScriptsActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void scriptsValuesActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void decreaseScriptsMouseClicked(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
        if (canDecreaseQuantity(0)) {
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.scriptsValues.setText(decreaseQuantity(this.scriptsValues.getText(), this.decreaseScripts));
            helper.deleteWorker(0, 0);
        }
        updateBtnStatus();
    }                                            

    private void decreaseScriptsActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void scenaryValueActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void increaseScenaryMouseClicked(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
        if (canIncreaseQuantity(1)) {
            this.scenaryValue.setText(increaseQuantity(this.scenaryValue.getText(), increaseScenary));
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            helper.addWorker(0, 1);
        }
        updateBtnStatus();
    }                                            

    private void increaseScenaryActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void decreaseScenaryMouseClicked(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
        if (canDecreaseQuantity(1)) {
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.scenaryValue.setText(decreaseQuantity(this.scenaryValue.getText(), decreaseScenary));
            helper.deleteWorker(0, 1);
        }
        updateBtnStatus();
    }                                            

    private void decreaseScenaryActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void animationValuesActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void decreaseAnimationMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        if (canDecreaseQuantity(2)) {
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.animationValues.setText(decreaseQuantity(this.animationValues.getText(), decreaseAnimation));
            helper.deleteWorker(0, 2);
        }
        updateBtnStatus();
    }                                              

    private void decreaseAnimationActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void increaseAnimationMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        if (canIncreaseQuantity(2)) {
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.animationValues.setText(increaseQuantity(this.animationValues.getText(), increaseAnimation));
            helper.addWorker(0, 2);
        }
        updateBtnStatus();
    }                                              

    private void increaseAnimationActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void decreaseDubbingMouseClicked(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
        if (canDecreaseQuantity(3)) {
           // cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.dubbingValues.setText(decreaseQuantity(this.dubbingValues.getText(), decreaseDubbing));
            helper.deleteWorker(0, 3);
        }
        updateBtnStatus();
    }                                            

    private void decreaseDubbingActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void dubbingValuesActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void increaseDubbingMouseClicked(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
        if (canIncreaseQuantity(3)) {
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.dubbingValues.setText(increaseQuantity(this.dubbingValues.getText(), increaseDubbing));
            helper.addWorker(0, 3);
        }
        updateBtnStatus();
    }                                            

    private void increaseDubbingActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void increasePlotTwistMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        if (canIncreaseQuantity(4)) {
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.plotTwistValues.setText(increaseQuantity(this.plotTwistValues.getText(), increasePlotTwist));
            helper.addWorker(0, 4);
        }
        updateBtnStatus();
    }                                              

    private void increasePlotTwistActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void plotTwistValuesActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void decreacePlotTwistMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        if (canDecreaseQuantity(4)) {
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.plotTwistValues.setText(decreaseQuantity(this.plotTwistValues.getText(), decreacePlotTwist));
            helper.deleteWorker(0, 4);
        }
        updateBtnStatus();
    }                                              

    private void decreacePlotTwistActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void increaseAssemblerMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        if (canIncreaseQuantity(5)) {
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.assemblerValues.setText(increaseQuantity(this.assemblerValues.getText(), increaseAssembler));
            helper.addWorker(0, 5);
        }
        updateBtnStatus();
    }                                              

    private void increaseAssemblerActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void assemblerValuesActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void decreaceAssemblerMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        if (canDecreaseQuantity(5)) {
            //cartoonPlayMusic("/GUI/Assets/cartoonClick.wav");
            this.assemblerValues.setText(decreaseQuantity(this.assemblerValues.getText(), decreaceAssembler));
            helper.deleteWorker(0, 5);
        }
        updateBtnStatus();
    }                                              

    private void decreaceAssemblerActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void scriptDriveActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void scenaryDriveActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void animationDriveActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void dubbingDriveActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void plotTwistDriveActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void assemblerDriveActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void plotTwistChaptersActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void standardChaptersActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void totalChaptersActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void currentDeadlineActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void totalDaysActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void costActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void earningActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void profitActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void directorStatusActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void projectManagerStatusActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    private void strikeCounterActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void cashPenalityActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void plotTwistChapters1ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void standardChaptes1ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void batchLastProfitActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void plotTwistChapters2ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void standardChaptes2ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private String increaseQuantity(String actualValue, JButton btn) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(actualValue);
            if (actualEmployees < maxEmployees) {
                intValue++;
                actualEmployees++;
            }
            return String.valueOf(intValue);
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el valor a int: " + e.getMessage());
            return actualValue; // Retorna el valor actual en caso de error
        }
    }

    private String decreaseQuantity(String actualValue, JButton btn) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(actualValue);
            if (intValue > 1) {
                intValue--;
                actualEmployees--;
                return String.valueOf(intValue);
            } else {
                return String.valueOf(intValue);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el valor a int: " + e.getMessage());
        }
        return null;
    }

    private boolean canDecreaseQuantity(int type) {
        updateValues();
        return values[type] > 1;
    }

    private boolean canIncreaseQuantity(int type) {
        updateValues();
        return actualEmployees < maxEmployees;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Apple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Apple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Apple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Apple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Apple().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel SidePanel;
    private javax.swing.JTextField animationDrive;
    private javax.swing.JTextField animationValues;
    private javax.swing.JPanel animations;
    private javax.swing.JPanel animations1;
    private javax.swing.JLabel animationsLimit1;
    private javax.swing.JLabel animationsTitle;
    private javax.swing.JLabel animationsTitle1;
    private javax.swing.JTextField assemblerDrive;
    private javax.swing.JLabel assemblerTitle;
    private javax.swing.JLabel assemblerTitle1;
    private javax.swing.JTextField assemblerValues;
    private javax.swing.JTextField batchLastProfit;
    private javax.swing.JPanel btn_Inicio;
    private javax.swing.JPanel btn_cargar_guardar;
    private javax.swing.JPanel btn_nueva_ruta;
    private javax.swing.JPanel btn_nuevo_almacen;
    private javax.swing.JPanel btn_nuevo_pedido;
    private javax.swing.JPanel btn_reporte;
    private javax.swing.JTextField cashPenality;
    private javax.swing.JTextField cost;
    private javax.swing.JTextField currentDeadline;
    private javax.swing.JButton decreaceAssembler;
    private javax.swing.JButton decreacePlotTwist;
    private javax.swing.JButton decreaseAnimation;
    private javax.swing.JButton decreaseDubbing;
    private javax.swing.JButton decreaseScenary;
    private javax.swing.JButton decreaseScripts;
    private javax.swing.JTextField directorStatus;
    private javax.swing.JPanel drivePanel;
    private javax.swing.JLabel driveTitle;
    private javax.swing.JLabel driveTitle1;
    private javax.swing.JLabel driveTitle10;
    private javax.swing.JLabel driveTitle11;
    private javax.swing.JLabel driveTitle12;
    private javax.swing.JLabel driveTitle13;
    private javax.swing.JLabel driveTitle14;
    private javax.swing.JLabel driveTitle15;
    private javax.swing.JLabel driveTitle16;
    private javax.swing.JLabel driveTitle17;
    private javax.swing.JLabel driveTitle18;
    private javax.swing.JLabel driveTitle19;
    private javax.swing.JLabel driveTitle2;
    private javax.swing.JLabel driveTitle20;
    private javax.swing.JLabel driveTitle21;
    private javax.swing.JLabel driveTitle22;
    private javax.swing.JLabel driveTitle23;
    private javax.swing.JLabel driveTitle24;
    private javax.swing.JLabel driveTitle25;
    private javax.swing.JLabel driveTitle26;
    private javax.swing.JLabel driveTitle27;
    private javax.swing.JLabel driveTitle28;
    private javax.swing.JLabel driveTitle3;
    private javax.swing.JLabel driveTitle4;
    private javax.swing.JLabel driveTitle5;
    private javax.swing.JLabel driveTitle6;
    private javax.swing.JLabel driveTitle7;
    private javax.swing.JLabel driveTitle8;
    private javax.swing.JLabel driveTitle9;
    private javax.swing.JPanel dubbing;
    private javax.swing.JPanel dubbing1;
    private javax.swing.JTextField dubbingDrive;
    private javax.swing.JLabel dubbingLimit1;
    private javax.swing.JLabel dubbingTitle;
    private javax.swing.JLabel dubbingTitle1;
    private javax.swing.JTextField dubbingValues;
    private javax.swing.JTextField earning;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel icono1;
    private javax.swing.JLabel icono3;
    private javax.swing.JLabel icono4;
    private javax.swing.JLabel icono5;
    private javax.swing.JLabel icono7;
    private javax.swing.JButton increaseAnimation;
    private javax.swing.JButton increaseAssembler;
    private javax.swing.JButton increaseDubbing;
    private javax.swing.JButton increasePlotTwist;
    private javax.swing.JButton increaseScenary;
    private javax.swing.JButton increaseScripts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel maxCap;
    private javax.swing.JPanel plotTwist;
    private javax.swing.JPanel plotTwist1;
    private javax.swing.JPanel plotTwist2;
    private javax.swing.JPanel plotTwist4;
    private javax.swing.JTextField plotTwistChapters;
    private javax.swing.JTextField plotTwistChapters1;
    private javax.swing.JTextField plotTwistChapters2;
    private javax.swing.JTextField plotTwistDrive;
    private javax.swing.JLabel plotTwistLimit1;
    private javax.swing.JLabel plotTwistLimit3;
    private javax.swing.JLabel plotTwistTitle;
    private javax.swing.JLabel plotTwistTitle1;
    private javax.swing.JTextField plotTwistValues;
    private javax.swing.JTextField profit;
    private javax.swing.JTextField projectManagerStatus;
    private javax.swing.JPanel scenary;
    private javax.swing.JPanel scenary1;
    private javax.swing.JTextField scenaryDrive;
    private javax.swing.JLabel scenaryLimit1;
    private javax.swing.JLabel scenaryTitle;
    private javax.swing.JLabel scenaryTitle1;
    private javax.swing.JTextField scenaryValue;
    private javax.swing.JTextField scriptDrive;
    private javax.swing.JLabel scriptTitle1;
    private javax.swing.JPanel scripts;
    private javax.swing.JPanel scripts1;
    private javax.swing.JLabel scriptsLimit1;
    private javax.swing.JLabel scriptsTitle;
    private javax.swing.JTextField scriptsValues;
    private javax.swing.JTextField standardChapters;
    private javax.swing.JTextField standardChaptes1;
    private javax.swing.JTextField standardChaptes2;
    private javax.swing.JTextField strikeCounter;
    private javax.swing.JTextField totalChapters;
    private javax.swing.JTextField totalDays;
    private javax.swing.JPanel workersConfigurations;
    // End of variables declaration 
}

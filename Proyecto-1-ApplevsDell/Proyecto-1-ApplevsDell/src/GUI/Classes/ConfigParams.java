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


public class ConfigParams extends javax.swing.JFrame {

    private Point initialClick;
    private final App app = App.getInstance();
    private int maxEmployees;
    private int maxEmployees1;
    private int actualEmployees;
    private int actualEmployees1;
    private static ConfigParams config;
    private HelpersFunctions helper = new HelpersFunctions();
    private FileFunctions filefunctions = new FileFunctions();
    private File selectedFile = app.getSelectedFile();
    private int dayDuration;
    private int deadline;

    private void initializeValues() {
        if (this.app.getDell() != null && this.app.getApple() != null) {
            this.maxEmployees = this.app.getApple().getMaxEmployeesQuantity();
            this.maxEmployees1 = this.app.getDell().getMaxEmployeesQuantity();
            this.actualEmployees1 = this.app.getApple().getActualEmployeesQuantity();
            this.actualEmployees = this.app.getApple().getActualEmployeesQuantity();
            this.dayDuration = (int) app.getDayDuration() / 1000;
            this.deadline = app.getDeadline();
            this.dayDurationValue.setText(String.valueOf(dayDuration));
            this.deadlineValue.setText(String.valueOf(deadline));

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

            this.scriptsValues1
                    .setText(String.valueOf(countNonNullEmployees(this.app.getDell().getMotherboard())));
            this.scenaryValue1
                    .setText(String.valueOf(countNonNullEmployees(this.app.getDell().getCPU())));
            this.animationValues1.setText(
                    String.valueOf(countNonNullEmployees(this.app.getDell().getRAM())));
            this.dubbingValues1
                    .setText(String.valueOf(countNonNullEmployees(this.app.getDell().getPSU())));
            this.plotTwistValues1.setText(
                    String.valueOf(countNonNullEmployees(this.app.getDell().getGPU())));
            this.assemblerValues1
                    .setText(String.valueOf(countNonNullEmployees(this.app.getDell().getAssemblers())));
            this.maxCap.setText(String.valueOf(this.maxEmployees) + "     trabajadores");
            this.maxCap1.setText(String.valueOf(this.maxEmployees1) + "     trabajadores");
        }
    }

    private void updateBtnParams() {
        if (this.dayDuration == 1) {
            this.decreaseDay.setEnabled(false);
            this.decreaseDay.setFocusable(false);
        } else {
            this.decreaseDay.setEnabled(true);
            this.decreaseDay.setFocusable(true);
        }

        if (this.deadline == 1) {
            this.decreaseDeadline.setEnabled(false);
            this.decreaseDeadline.setFocusable(false);
        } else {
            this.decreaseDeadline.setEnabled(true);
            this.decreaseDeadline.setFocusable(true);
        }
    }

    public static synchronized ConfigParams getInstance() {
        if (config == null) {
            config = new ConfigParams();
        }
        return config;
    }

    private void cartoonPlayMusic(String path) {
        try {
            URL url = this.getClass().getResource(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
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

    private JButton[] decreaseBtn1 = new JButton[6];
    private JButton[] increaseBtn1 = new JButton[6];

    private int[] values1 = {
            countNonNullEmployees(this.app.getDell().getMotherboard()),
            countNonNullEmployees(this.app.getDell().getCPU()),
            countNonNullEmployees(this.app.getDell().getRAM()),
            countNonNullEmployees(this.app.getDell().getPSU()),
            countNonNullEmployees(this.app.getDell().getGPU()),
            countNonNullEmployees(this.app.getDell().getAssemblers())
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

    private void updateBtnStatus1() {
        updateValues1();

        if (this.actualEmployees1 == this.maxEmployees1) {
            for (JButton btn : increaseBtn1) {
                btn.setEnabled(false);
                btn.setFocusable(false);
            }
        } else {
            for (JButton btn : increaseBtn1) {
                btn.setEnabled(true);
                btn.setFocusable(true);
            }
        }

        for (int i = 0; i < this.values1.length; i++) {
            if (this.values1[i] == 1) {
                this.decreaseBtn1[i].setEnabled(false);
                this.decreaseBtn1[i].setFocusable(false);
            } else {
                this.decreaseBtn1[i].setEnabled(true);
                this.decreaseBtn1[i].setFocusable(true);

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

    private void updateValues1() {
        values1[0] = countNonNullEmployees(this.app.getDell().getMotherboard());
        values1[1] = countNonNullEmployees(this.app.getDell().getCPU());
        values1[2] = countNonNullEmployees(this.app.getDell().getRAM());
        values1[3] = countNonNullEmployees(this.app.getDell().getPSU());
        values1[4] = countNonNullEmployees(this.app.getDell().getGPU());
        values1[5] = countNonNullEmployees(this.app.getDell().getAssemblers());
    }

    public ConfigParams() {
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
        updateBtnParams();

        this.decreaseBtn1[0] = decreaseScripts1;
        this.decreaseBtn1[1] = decreaseScenary1;
        this.decreaseBtn1[2] = decreaseAnimation1;
        this.decreaseBtn1[3] = decreaseDubbing1;
        this.decreaseBtn1[4] = decreacePlotTwist1;
        this.decreaseBtn1[5] = decreaceAssembler1;
        this.increaseBtn1[0] = increaseScripts1;
        this.increaseBtn1[1] = increaseScenary1;
        this.increaseBtn1[2] = increaseAnimation1;
        this.increaseBtn1[3] = increaseDubbing1;
        this.increaseBtn1[4] = increasePlotTwist1;
        this.increaseBtn1[5] = increaseAssembler1;
        updateBtnStatus1();

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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">                          
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        btn_cargar_guardar = new javax.swing.JPanel();
        icono7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        driveTitle = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        scripts1 = new javax.swing.JPanel();
        scriptsTitle1 = new javax.swing.JLabel();
        increaseDay = new javax.swing.JButton();
        dayDurationValue = new javax.swing.JTextField();
        decreaseDay = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        scripts3 = new javax.swing.JPanel();
        scriptsTitle3 = new javax.swing.JLabel();
        increaseDeadline = new javax.swing.JButton();
        deadlineValue = new javax.swing.JTextField();
        decreaseDeadline = new javax.swing.JButton();
        workersConfigurations = new javax.swing.JPanel();
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
        driveTitle27 = new javax.swing.JLabel();
        maxCap = new javax.swing.JLabel();
        workersConfigurations1 = new javax.swing.JPanel();
        scripts2 = new javax.swing.JPanel();
        scriptsTitle2 = new javax.swing.JLabel();
        increaseScripts1 = new javax.swing.JButton();
        scriptsValues1 = new javax.swing.JTextField();
        decreaseScripts1 = new javax.swing.JButton();
        scenary1 = new javax.swing.JPanel();
        scenaryTitle1 = new javax.swing.JLabel();
        scenaryValue1 = new javax.swing.JTextField();
        increaseScenary1 = new javax.swing.JButton();
        decreaseScenary1 = new javax.swing.JButton();
        animations1 = new javax.swing.JPanel();
        animationsTitle1 = new javax.swing.JLabel();
        animationValues1 = new javax.swing.JTextField();
        decreaseAnimation1 = new javax.swing.JButton();
        increaseAnimation1 = new javax.swing.JButton();
        dubbing1 = new javax.swing.JPanel();
        dubbingTitle1 = new javax.swing.JLabel();
        decreaseDubbing1 = new javax.swing.JButton();
        dubbingValues1 = new javax.swing.JTextField();
        increaseDubbing1 = new javax.swing.JButton();
        plotTwist1 = new javax.swing.JPanel();
        plotTwistTitle1 = new javax.swing.JLabel();
        increasePlotTwist1 = new javax.swing.JButton();
        plotTwistValues1 = new javax.swing.JTextField();
        decreacePlotTwist1 = new javax.swing.JButton();
        plotTwist3 = new javax.swing.JPanel();
        assemblerTitle1 = new javax.swing.JLabel();
        increaseAssembler1 = new javax.swing.JButton();
        assemblerValues1 = new javax.swing.JTextField();
        decreaceAssembler1 = new javax.swing.JButton();
        driveTitle22 = new javax.swing.JLabel();
        driveTitle28 = new javax.swing.JLabel();
        maxCap1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(1130, 720));
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
                                .addGap(18, 18, 18)));
        btn_InicioLayout.setVerticalGroup(
                btn_InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(btn_InicioLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(btn_InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(icono1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 16, Short.MAX_VALUE)));

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
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addContainerGap(183, Short.MAX_VALUE)));
        btn_nuevo_pedidoLayout.setVerticalGroup(
                btn_nuevo_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(btn_nuevo_pedidoLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(btn_nuevo_pedidoLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 14, Short.MAX_VALUE)));

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
                                .addContainerGap(123, Short.MAX_VALUE)));
        btn_nueva_rutaLayout.setVerticalGroup(
                btn_nueva_rutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(btn_nueva_rutaLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(btn_nueva_rutaLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(icono3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 14, Short.MAX_VALUE)));

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
        jLabel7.setText("Apple");
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
                                .addContainerGap(170, Short.MAX_VALUE)));
        btn_nuevo_almacenLayout.setVerticalGroup(
                btn_nuevo_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(btn_nuevo_almacenLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(icono4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                        .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(0, 7, Short.MAX_VALUE)));

        SidePanel.add(btn_nuevo_almacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 330, 60));

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
                                .addContainerGap(213, Short.MAX_VALUE)));
        btn_reporteLayout.setVerticalGroup(
                btn_reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(btn_reporteLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(
                                        btn_reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(icono5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 14, Short.MAX_VALUE)));

        SidePanel.add(btn_reporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 330, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Unimet 2024 ®");
        SidePanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 690, -1, -1));

        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SidePanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 220, 26));
        SidePanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 280, 80));

        btn_cargar_guardar.setBackground(new java.awt.Color(243, 168, 71));
        btn_cargar_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cargar_guardarMouseClicked(evt);
            }
        });
        btn_cargar_guardar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icono7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono7MouseClicked(evt);
            }
        });
        btn_cargar_guardar.add(icono7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 14, -1, 32));

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Configuración");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        btn_cargar_guardar.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        SidePanel.add(btn_cargar_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 330, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Assets/AS.png"))); // NOI18N
        SidePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jPanel1.add(SidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 740));

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
                                .addGap(0, 1059, Short.MAX_VALUE)
                                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));

        driveTitle.setFont(new java.awt.Font("Montserrat", 1, 48)); // NOI18N
        driveTitle.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        driveTitle.setText("Configuracion de parámetros");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(driveTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 866,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(driveTitle)
                                .addContainerGap(36, Short.MAX_VALUE)));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 160));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBackground(new java.awt.Color(243, 168, 71));

        scripts1.setBackground(java.awt.Color.lightGray);
        scripts1.setForeground(new java.awt.Color(60, 63, 65));

        scriptsTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scriptsTitle1.setForeground(new java.awt.Color(51, 51, 51));
        scriptsTitle1.setText("Duración de los días (seg):");

        increaseDay.setBackground(new java.awt.Color(51, 51, 51));
        increaseDay.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseDay.setForeground(new java.awt.Color(255, 255, 255));
        increaseDay.setText("+");
        increaseDay.setBorderPainted(false);
        increaseDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseDayMouseClicked(evt);
            }
        });
        increaseDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseDayActionPerformed(evt);
            }
        });

        dayDurationValue.setBackground(java.awt.Color.lightGray);
        dayDurationValue.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        dayDurationValue.setForeground(new java.awt.Color(51, 51, 51));
        dayDurationValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dayDurationValue.setText("0");
        dayDurationValue.setBorder(null);
        dayDurationValue.setFocusable(false);
        dayDurationValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayDurationValueActionPerformed(evt);
            }
        });

        decreaseDay.setBackground(new java.awt.Color(51, 51, 51));
        decreaseDay.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseDay.setForeground(new java.awt.Color(255, 255, 255));
        decreaseDay.setText("-");
        decreaseDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseDayMouseClicked(evt);
            }
        });
        decreaseDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseDayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scripts1Layout = new javax.swing.GroupLayout(scripts1);
        scripts1.setLayout(scripts1Layout);
        scripts1Layout.setHorizontalGroup(
                scripts1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(scripts1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(scriptsTitle1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(decreaseDay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dayDurationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseDay)
                                .addContainerGap()));
        scripts1Layout.setVerticalGroup(
                scripts1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(scripts1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(scriptsTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(increaseDay)
                                .addComponent(dayDurationValue, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(decreaseDay)));

        jButton1.setBackground(new java.awt.Color(34, 46, 60));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Assets/save_btn.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        scripts3.setBackground(java.awt.Color.lightGray);
        scripts3.setForeground(new java.awt.Color(60, 63, 65));

        scriptsTitle3.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scriptsTitle3.setForeground(new java.awt.Color(51, 51, 51));
        scriptsTitle3.setText("Días entre las entregas:");

        increaseDeadline.setBackground(new java.awt.Color(51, 51, 51));
        increaseDeadline.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseDeadline.setForeground(new java.awt.Color(255, 255, 255));
        increaseDeadline.setText("+");
        increaseDeadline.setBorderPainted(false);
        increaseDeadline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseDeadlineMouseClicked(evt);
            }
        });
        increaseDeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseDeadlineActionPerformed(evt);
            }
        });

        deadlineValue.setBackground(java.awt.Color.lightGray);
        deadlineValue.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        deadlineValue.setForeground(new java.awt.Color(51, 51, 51));
        deadlineValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        deadlineValue.setText("0");
        deadlineValue.setBorder(null);
        deadlineValue.setFocusable(false);
        deadlineValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deadlineValueActionPerformed(evt);
            }
        });

        decreaseDeadline.setBackground(new java.awt.Color(51, 51, 51));
        decreaseDeadline.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseDeadline.setForeground(new java.awt.Color(255, 255, 255));
        decreaseDeadline.setText("-");
        decreaseDeadline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseDeadlineMouseClicked(evt);
            }
        });
        decreaseDeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseDeadlineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scripts3Layout = new javax.swing.GroupLayout(scripts3);
        scripts3.setLayout(scripts3Layout);
        scripts3Layout.setHorizontalGroup(
                scripts3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(scripts3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(scriptsTitle3, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(decreaseDeadline)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deadlineValue, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseDeadline)
                                .addGap(16, 16, 16)));
        scripts3Layout.setVerticalGroup(
                scripts3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(scripts3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(scriptsTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(increaseDeadline)
                                .addComponent(deadlineValue, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(decreaseDeadline)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(scripts1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(scripts3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)));
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(21, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scripts3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scripts1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)));

        workersConfigurations.setBackground(new java.awt.Color(243, 168, 71));

        scripts.setBackground(java.awt.Color.lightGray);
        scripts.setForeground(new java.awt.Color(60, 63, 65));

        scriptsTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scriptsTitle.setForeground(new java.awt.Color(51, 51, 51));
        scriptsTitle.setText("Motherboard:");

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
                                .addComponent(scriptsTitle, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(36, 36, 36)
                                .addComponent(decreaseScripts)
                                .addGap(18, 18, 18)
                                .addComponent(scriptsValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseScripts)
                                .addGap(15, 15, 15)));
        scriptsLayout.setVerticalGroup(
                scriptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(scriptsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(scriptsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(increaseScripts)
                                .addComponent(scriptsValues, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(decreaseScripts)));

        scenary.setBackground(java.awt.Color.lightGray);
        scenary.setForeground(new java.awt.Color(60, 63, 65));

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decreaseScenary)
                                .addGap(18, 18, 18)
                                .addComponent(scenaryValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseScenary)
                                .addGap(14, 14, 14)));
        scenaryLayout.setVerticalGroup(
                scenaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(scenaryLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(scenaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(scenaryLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(increaseScenary)
                                                .addComponent(scenaryValue, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(decreaseScenary))
                                        .addComponent(scenaryTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));

        animations.setBackground(java.awt.Color.lightGray);
        animations.setForeground(new java.awt.Color(255, 255, 255));

        animationsTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        animationsTitle.setForeground(new java.awt.Color(51, 51, 51));
        animationsTitle.setText("RAMs:");

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decreaseAnimation)
                                .addGap(18, 18, 18)
                                .addComponent(animationValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseAnimation)
                                .addGap(15, 15, 15)));
        animationsLayout.setVerticalGroup(
                animationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(animationsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        animationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(decreaseAnimation)
                                                .addComponent(animationValues, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(increaseAnimation)
                                                .addComponent(animationsTitle, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)));

        dubbing.setBackground(java.awt.Color.lightGray);
        dubbing.setForeground(new java.awt.Color(255, 255, 255));

        dubbingTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        dubbingTitle.setForeground(new java.awt.Color(51, 51, 51));
        dubbingTitle.setText("PSU:");

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decreaseDubbing)
                                .addGap(18, 18, 18)
                                .addComponent(dubbingValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseDubbing)
                                .addGap(15, 15, 15)));
        dubbingLayout.setVerticalGroup(
                dubbingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dubbingLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(dubbingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(decreaseDubbing)
                                        .addComponent(dubbingValues, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(increaseDubbing)
                                        .addComponent(dubbingTitle))
                                .addContainerGap()));

        plotTwist.setBackground(java.awt.Color.lightGray);
        plotTwist.setForeground(new java.awt.Color(255, 255, 255));

        plotTwistTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistTitle.setForeground(new java.awt.Color(51, 51, 51));
        plotTwistTitle.setText("GPU:");

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decreacePlotTwist)
                                .addGap(18, 18, 18)
                                .addComponent(plotTwistValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increasePlotTwist)
                                .addGap(16, 16, 16)));
        plotTwistLayout.setVerticalGroup(
                plotTwistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(plotTwistLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        plotTwistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(increasePlotTwist)
                                                .addComponent(plotTwistValues, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(decreacePlotTwist)
                                                .addComponent(plotTwistTitle, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)));

        plotTwist2.setBackground(java.awt.Color.lightGray);
        plotTwist2.setForeground(new java.awt.Color(255, 255, 255));

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
                                .addContainerGap(13, Short.MAX_VALUE)
                                .addComponent(assemblerTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(decreaceAssembler)
                                .addGap(18, 18, 18)
                                .addComponent(assemblerValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseAssembler)
                                .addGap(16, 16, 16)));
        plotTwist2Layout.setVerticalGroup(
                plotTwist2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(plotTwist2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        plotTwist2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(increaseAssembler)
                                                .addComponent(assemblerValues, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(decreaceAssembler)
                                                .addComponent(assemblerTitle, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)));

        driveTitle21.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        driveTitle21.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle21.setText("Apple");

        driveTitle27.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        driveTitle27.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        driveTitle27.setText("Máximo:");

        maxCap.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        maxCap.setForeground(new java.awt.Color(51, 51, 51));
        maxCap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout workersConfigurationsLayout = new javax.swing.GroupLayout(workersConfigurations);
        workersConfigurations.setLayout(workersConfigurationsLayout);
        workersConfigurationsLayout.setHorizontalGroup(
                workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(driveTitle21, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workersConfigurationsLayout
                                .createSequentialGroup()
                                .addContainerGap(15, Short.MAX_VALUE)
                                .addGroup(workersConfigurationsLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(workersConfigurationsLayout.createSequentialGroup()
                                                .addComponent(driveTitle27, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(maxCap, javax.swing.GroupLayout.PREFERRED_SIZE, 173,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(workersConfigurationsLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(plotTwist2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(plotTwist, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(dubbing, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(animations, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(scenary, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(scripts, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(15, 15, 15)));
        workersConfigurationsLayout.setVerticalGroup(
                workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(workersConfigurationsLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(driveTitle21)
                                .addGap(18, 18, 18)
                                .addComponent(scripts, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scenary, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(animations, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dubbing, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(plotTwist, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(plotTwist2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(workersConfigurationsLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(driveTitle27, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(maxCap, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        workersConfigurations1.setBackground(new java.awt.Color(243, 168, 71));

        scripts2.setBackground(java.awt.Color.lightGray);
        scripts2.setForeground(new java.awt.Color(60, 63, 65));

        scriptsTitle2.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scriptsTitle2.setForeground(new java.awt.Color(51, 51, 51));
        scriptsTitle2.setText("Motherboard:");

        increaseScripts1.setBackground(new java.awt.Color(51, 51, 51));
        increaseScripts1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseScripts1.setForeground(new java.awt.Color(255, 255, 255));
        increaseScripts1.setText("+");
        increaseScripts1.setBorderPainted(false);
        increaseScripts1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseScripts1MouseClicked(evt);
            }
        });
        increaseScripts1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseScripts1ActionPerformed(evt);
            }
        });

        scriptsValues1.setBackground(java.awt.Color.lightGray);
        scriptsValues1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scriptsValues1.setForeground(new java.awt.Color(51, 51, 51));
        scriptsValues1.setText("0");
        scriptsValues1.setBorder(null);
        scriptsValues1.setFocusable(false);
        scriptsValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scriptsValues1ActionPerformed(evt);
            }
        });

        decreaseScripts1.setBackground(new java.awt.Color(51, 51, 51));
        decreaseScripts1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseScripts1.setForeground(new java.awt.Color(255, 255, 255));
        decreaseScripts1.setText("-");
        decreaseScripts1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseScripts1MouseClicked(evt);
            }
        });
        decreaseScripts1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseScripts1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scripts2Layout = new javax.swing.GroupLayout(scripts2);
        scripts2.setLayout(scripts2Layout);
        scripts2Layout.setHorizontalGroup(
                scripts2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(scripts2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(scriptsTitle2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(36, 36, 36)
                                .addComponent(decreaseScripts1)
                                .addGap(18, 18, 18)
                                .addComponent(scriptsValues1, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseScripts1)
                                .addGap(15, 15, 15)));
        scripts2Layout.setVerticalGroup(
                scripts2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(scripts2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(scriptsTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(increaseScripts1)
                                .addComponent(scriptsValues1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(decreaseScripts1)));

        scenary1.setBackground(java.awt.Color.lightGray);
        scenary1.setForeground(new java.awt.Color(60, 63, 65));

        scenaryTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scenaryTitle1.setForeground(new java.awt.Color(51, 51, 51));
        scenaryTitle1.setText("CPUs:");

        scenaryValue1.setBackground(java.awt.Color.lightGray);
        scenaryValue1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        scenaryValue1.setForeground(new java.awt.Color(51, 51, 51));
        scenaryValue1.setText("0");
        scenaryValue1.setBorder(null);
        scenaryValue1.setFocusable(false);
        scenaryValue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scenaryValue1ActionPerformed(evt);
            }
        });

        increaseScenary1.setBackground(new java.awt.Color(51, 51, 51));
        increaseScenary1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseScenary1.setForeground(new java.awt.Color(255, 255, 255));
        increaseScenary1.setText("+");
        increaseScenary1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseScenary1MouseClicked(evt);
            }
        });
        increaseScenary1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseScenary1ActionPerformed(evt);
            }
        });

        decreaseScenary1.setBackground(new java.awt.Color(51, 51, 51));
        decreaseScenary1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseScenary1.setForeground(new java.awt.Color(255, 255, 255));
        decreaseScenary1.setText("-");
        decreaseScenary1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseScenary1MouseClicked(evt);
            }
        });
        decreaseScenary1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseScenary1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scenary1Layout = new javax.swing.GroupLayout(scenary1);
        scenary1.setLayout(scenary1Layout);
        scenary1Layout.setHorizontalGroup(
                scenary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(scenary1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(scenaryTitle1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decreaseScenary1)
                                .addGap(18, 18, 18)
                                .addComponent(scenaryValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseScenary1)
                                .addGap(14, 14, 14)));
        scenary1Layout.setVerticalGroup(
                scenary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(scenary1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(scenary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(scenary1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(increaseScenary1)
                                                .addComponent(scenaryValue1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(decreaseScenary1))
                                        .addComponent(scenaryTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));

        animations1.setBackground(java.awt.Color.lightGray);
        animations1.setForeground(new java.awt.Color(255, 255, 255));

        animationsTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        animationsTitle1.setForeground(new java.awt.Color(51, 51, 51));
        animationsTitle1.setText("RAMs:");

        animationValues1.setBackground(java.awt.Color.lightGray);
        animationValues1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        animationValues1.setForeground(new java.awt.Color(51, 51, 51));
        animationValues1.setText("0");
        animationValues1.setBorder(null);
        animationValues1.setFocusable(false);
        animationValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animationValues1ActionPerformed(evt);
            }
        });

        decreaseAnimation1.setBackground(new java.awt.Color(51, 51, 51));
        decreaseAnimation1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseAnimation1.setForeground(new java.awt.Color(255, 255, 255));
        decreaseAnimation1.setText("-");
        decreaseAnimation1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseAnimation1MouseClicked(evt);
            }
        });
        decreaseAnimation1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseAnimation1ActionPerformed(evt);
            }
        });

        increaseAnimation1.setBackground(new java.awt.Color(51, 51, 51));
        increaseAnimation1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseAnimation1.setForeground(new java.awt.Color(255, 255, 255));
        increaseAnimation1.setText("+");
        increaseAnimation1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseAnimation1MouseClicked(evt);
            }
        });
        increaseAnimation1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseAnimation1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout animations1Layout = new javax.swing.GroupLayout(animations1);
        animations1.setLayout(animations1Layout);
        animations1Layout.setHorizontalGroup(
                animations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(animations1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(animationsTitle1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decreaseAnimation1)
                                .addGap(18, 18, 18)
                                .addComponent(animationValues1, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseAnimation1)
                                .addGap(15, 15, 15)));
        animations1Layout.setVerticalGroup(
                animations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(animations1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(animations1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(decreaseAnimation1)
                                        .addComponent(animationValues1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(increaseAnimation1)
                                        .addComponent(animationsTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)));

        dubbing1.setBackground(java.awt.Color.lightGray);
        dubbing1.setForeground(new java.awt.Color(255, 255, 255));

        dubbingTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        dubbingTitle1.setForeground(new java.awt.Color(51, 51, 51));
        dubbingTitle1.setText("PSUs:");

        decreaseDubbing1.setBackground(new java.awt.Color(51, 51, 51));
        decreaseDubbing1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseDubbing1.setForeground(new java.awt.Color(204, 204, 204));
        decreaseDubbing1.setText("-");
        decreaseDubbing1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseDubbing1MouseClicked(evt);
            }
        });
        decreaseDubbing1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseDubbing1ActionPerformed(evt);
            }
        });

        dubbingValues1.setBackground(java.awt.Color.lightGray);
        dubbingValues1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        dubbingValues1.setForeground(new java.awt.Color(51, 51, 51));
        dubbingValues1.setText("0");
        dubbingValues1.setBorder(null);
        dubbingValues1.setFocusable(false);
        dubbingValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dubbingValues1ActionPerformed(evt);
            }
        });

        increaseDubbing1.setBackground(new java.awt.Color(51, 51, 51));
        increaseDubbing1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseDubbing1.setForeground(new java.awt.Color(255, 255, 255));
        increaseDubbing1.setText("+");
        increaseDubbing1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseDubbing1MouseClicked(evt);
            }
        });
        increaseDubbing1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseDubbing1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dubbing1Layout = new javax.swing.GroupLayout(dubbing1);
        dubbing1.setLayout(dubbing1Layout);
        dubbing1Layout.setHorizontalGroup(
                dubbing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dubbing1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(dubbingTitle1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decreaseDubbing1)
                                .addGap(18, 18, 18)
                                .addComponent(dubbingValues1, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseDubbing1)
                                .addGap(15, 15, 15)));
        dubbing1Layout.setVerticalGroup(
                dubbing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dubbing1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(dubbing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(decreaseDubbing1)
                                        .addComponent(dubbingValues1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(increaseDubbing1)
                                        .addComponent(dubbingTitle1))
                                .addContainerGap()));

        plotTwist1.setBackground(java.awt.Color.lightGray);
        plotTwist1.setForeground(new java.awt.Color(255, 255, 255));

        plotTwistTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistTitle1.setForeground(new java.awt.Color(51, 51, 51));
        plotTwistTitle1.setText("GPUs:");

        increasePlotTwist1.setBackground(new java.awt.Color(51, 51, 51));
        increasePlotTwist1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increasePlotTwist1.setForeground(new java.awt.Color(255, 255, 255));
        increasePlotTwist1.setText("+");
        increasePlotTwist1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increasePlotTwist1MouseClicked(evt);
            }
        });
        increasePlotTwist1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increasePlotTwist1ActionPerformed(evt);
            }
        });

        plotTwistValues1.setBackground(java.awt.Color.lightGray);
        plotTwistValues1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistValues1.setForeground(new java.awt.Color(51, 51, 51));
        plotTwistValues1.setText("0");
        plotTwistValues1.setBorder(null);
        plotTwistValues1.setFocusable(false);
        plotTwistValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotTwistValues1ActionPerformed(evt);
            }
        });

        decreacePlotTwist1.setBackground(new java.awt.Color(51, 51, 51));
        decreacePlotTwist1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreacePlotTwist1.setForeground(new java.awt.Color(255, 255, 255));
        decreacePlotTwist1.setText("-");
        decreacePlotTwist1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreacePlotTwist1MouseClicked(evt);
            }
        });
        decreacePlotTwist1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreacePlotTwist1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plotTwist1Layout = new javax.swing.GroupLayout(plotTwist1);
        plotTwist1.setLayout(plotTwist1Layout);
        plotTwist1Layout.setHorizontalGroup(
                plotTwist1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plotTwist1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(plotTwistTitle1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decreacePlotTwist1)
                                .addGap(18, 18, 18)
                                .addComponent(plotTwistValues1, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increasePlotTwist1)
                                .addGap(16, 16, 16)));
        plotTwist1Layout.setVerticalGroup(
                plotTwist1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(plotTwist1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        plotTwist1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(increasePlotTwist1)
                                                .addComponent(plotTwistValues1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(decreacePlotTwist1)
                                                .addComponent(plotTwistTitle1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)));

        plotTwist3.setBackground(java.awt.Color.lightGray);
        plotTwist3.setForeground(new java.awt.Color(255, 255, 255));

        assemblerTitle1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        assemblerTitle1.setForeground(new java.awt.Color(51, 51, 51));
        assemblerTitle1.setText("Ensambladores:");

        increaseAssembler1.setBackground(new java.awt.Color(51, 51, 51));
        increaseAssembler1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseAssembler1.setForeground(new java.awt.Color(255, 255, 255));
        increaseAssembler1.setText("+");
        increaseAssembler1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseAssembler1MouseClicked(evt);
            }
        });
        increaseAssembler1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseAssembler1ActionPerformed(evt);
            }
        });

        assemblerValues1.setBackground(java.awt.Color.lightGray);
        assemblerValues1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        assemblerValues1.setForeground(new java.awt.Color(51, 51, 51));
        assemblerValues1.setText("0");
        assemblerValues1.setBorder(null);
        assemblerValues1.setFocusable(false);
        assemblerValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assemblerValues1ActionPerformed(evt);
            }
        });

        decreaceAssembler1.setBackground(new java.awt.Color(51, 51, 51));
        decreaceAssembler1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaceAssembler1.setForeground(new java.awt.Color(255, 255, 255));
        decreaceAssembler1.setText("-");
        decreaceAssembler1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaceAssembler1MouseClicked(evt);
            }
        });
        decreaceAssembler1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaceAssembler1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plotTwist3Layout = new javax.swing.GroupLayout(plotTwist3);
        plotTwist3.setLayout(plotTwist3Layout);
        plotTwist3Layout.setHorizontalGroup(
                plotTwist3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plotTwist3Layout.createSequentialGroup()
                                .addContainerGap(13, Short.MAX_VALUE)
                                .addComponent(assemblerTitle1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(decreaceAssembler1)
                                .addGap(18, 18, 18)
                                .addComponent(assemblerValues1, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(increaseAssembler1)
                                .addGap(16, 16, 16)));
        plotTwist3Layout.setVerticalGroup(
                plotTwist3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(plotTwist3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        plotTwist3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(increaseAssembler1)
                                                .addComponent(assemblerValues1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(decreaceAssembler1)
                                                .addComponent(assemblerTitle1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)));

        driveTitle22.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        driveTitle22.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle22.setText("Dell");

        driveTitle28.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        driveTitle28.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        driveTitle28.setText("Máximo:");

        maxCap1.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        maxCap1.setForeground(new java.awt.Color(51, 51, 51));
        maxCap1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout workersConfigurations1Layout = new javax.swing.GroupLayout(workersConfigurations1);
        workersConfigurations1.setLayout(workersConfigurations1Layout);
        workersConfigurations1Layout.setHorizontalGroup(
                workersConfigurations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(driveTitle22, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workersConfigurations1Layout
                                .createSequentialGroup()
                                .addContainerGap(21, Short.MAX_VALUE)
                                .addGroup(workersConfigurations1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(workersConfigurations1Layout.createSequentialGroup()
                                                .addComponent(driveTitle28, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(maxCap1, javax.swing.GroupLayout.DEFAULT_SIZE, 167,
                                                        Short.MAX_VALUE))
                                        .addComponent(plotTwist3, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plotTwist1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dubbing1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(animations1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scenary1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scripts2, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(15, 15, 15)));
        workersConfigurations1Layout.setVerticalGroup(
                workersConfigurations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(workersConfigurations1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(driveTitle22)
                                .addGap(18, 18, 18)
                                .addComponent(scripts2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scenary1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(animations1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dubbing1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(plotTwist1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(plotTwist3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(workersConfigurations1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(driveTitle28, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(maxCap1, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(workersConfigurations, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(workersConfigurations1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(workersConfigurations, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(workersConfigurations1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(25, Short.MAX_VALUE)));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 890, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE));

        pack();
    }// </editor-fold>                        

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jPanel4MousePressed
        // TODO add your handling code here:
        initialClick = evt.getPoint();
    }// GEN-LAST:event_jPanel4MousePressed

    private void jPanel4MouseDragged(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jPanel4MouseDragged
        // TODO add your handling code here:
        int x = getLocation().x - initialClick.x + evt.getX();
        int y = getLocation().y - initialClick.y + evt.getY();
        setLocation(x, y);
    }// GEN-LAST:event_jPanel4MouseDragged

    private void exitMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_exitMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }// GEN-LAST:event_exitMousePressed

    private void btn_cargar_guardarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_cargar_guardarMouseClicked
        // TODO add your handling code here:

    }// GEN-LAST:event_btn_cargar_guardarMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:

    }// GEN-LAST:event_jLabel10MouseClicked

    private void icono7MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_icono7MouseClicked
        // TODO add your handling code here:

    }// GEN-LAST:event_icono7MouseClicked

    private void btn_reporteMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_reporteMouseClicked
        // TODO add your handling code here:
        try {
            this.filefunctions.write(this.selectedFile);
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }// GEN-LAST:event_btn_reporteMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        try {
            this.filefunctions.write(this.selectedFile);
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }// GEN-LAST:event_jLabel8MouseClicked

    private void icono5MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_icono5MouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_icono5MouseClicked

    private void btn_nuevo_almacenMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_nuevo_almacenMouseClicked
        // TODO add your handling code here:
        Apple v3 = new Apple();
        v3.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btn_nuevo_almacenMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        Apple v3 = new Apple();
        v3.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_jLabel7MouseClicked

    private void icono4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_icono4MouseClicked
        // TODO add your handling code here:

    }// GEN-LAST:event_icono4MouseClicked

    private void btn_nueva_rutaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_nueva_rutaMouseClicked
        // TODO add your handling code here:
        Dell v2 = new Dell();
        v2.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btn_nueva_rutaMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        Dell v2 = new Dell();
        v2.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_jLabel6MouseClicked

    private void icono3MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_icono3MouseClicked
        // TODO add your handling code here:

    }// GEN-LAST:event_icono3MouseClicked

    private void btn_nuevo_pedidoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_nuevo_pedidoMouseClicked
        // TODO add your handling code here:
        Dashboard dashboard = Dashboard.getInstance();
        dashboard.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btn_nuevo_pedidoMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        Dashboard dashboard = Dashboard.getInstance();
        dashboard.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_jLabel5MouseClicked

    private void increaseDayMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseDayMouseClicked
        // TODO add your handling code here:
        this.dayDuration += 1;
        app.setDayDuration(dayDuration * 1000);
        this.dayDurationValue.setText(String.valueOf(app.getDayDuration() / 1000));
        this.updateBtnParams();
    }// GEN-LAST:event_increaseDayMouseClicked

    private void increaseDayActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseDayActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseDayActionPerformed

    private void dayDurationValueActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_dayDurationValueActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_dayDurationValueActionPerformed

    private void decreaseDayMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaseDayMouseClicked
        // TODO add your handling code here:
        if (this.canDecreaseDay()) {
            this.dayDuration -= 1;
            app.setDayDuration(dayDuration * 1000);
            this.dayDurationValue.setText(String.valueOf(app.getDayDuration() / 1000));
        }
        this.updateBtnParams();
    }// GEN-LAST:event_decreaseDayMouseClicked

    private void decreaseDayActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaseDayActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaseDayActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        try {
            this.filefunctions.write(this.selectedFile);
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }// GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_jButton1ActionPerformed

    private void increaseDeadlineMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseDeadlineMouseClicked
        // TODO add your handling code here:
        this.deadline += 1;
        app.setDeadline(deadline);
        this.deadlineValue.setText(String.valueOf(app.getDeadline()));
        this.updateBtnParams();
    }// GEN-LAST:event_increaseDeadlineMouseClicked

    private void increaseDeadlineActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseDeadlineActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseDeadlineActionPerformed

    private void deadlineValueActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deadlineValueActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_deadlineValueActionPerformed

    private void decreaseDeadlineMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaseDeadlineMouseClicked
        // TODO add your handling code here:
        if (this.canDecreaseDeadline()) {
            this.deadline -= 1;
            app.setDeadline(deadline);
            this.deadlineValue.setText(String.valueOf(app.getDeadline()));
        }
        updateBtnParams();
    }// GEN-LAST:event_decreaseDeadlineMouseClicked

    private void decreaseDeadlineActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaseDeadlineActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaseDeadlineActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Home v1 = new Home();
        v1.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_jLabel4MouseClicked

    private void btn_InicioMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_InicioMouseClicked
        // TODO add your handling code here:
        Home v1 = new Home();
        v1.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btn_InicioMouseClicked

    private void increaseScriptsMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseScriptsMouseClicked
        // TODO add your handling code here:
        if (this.canIncreaseQuantity(0)) {
            
            this.scriptsValues.setText(increaseQuantity(this.scriptsValues.getText(), increaseScripts));
            helper.addWorker(0, 0);
        }
        updateBtnStatus();
    }// GEN-LAST:event_increaseScriptsMouseClicked

    private void increaseScriptsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseScriptsActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseScriptsActionPerformed

    private void scriptsValuesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_scriptsValuesActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_scriptsValuesActionPerformed

    private void decreaseScriptsMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaseScriptsMouseClicked
        // TODO add your handling code here:
        updateValues();
        if (canDecreaseQuantity(0)) {
            
            this.scriptsValues.setText(decreaseQuantity(this.scriptsValues.getText(), this.decreaseScripts));
            helper.deleteWorker(0, 0);
        }
        updateBtnStatus();
    }// GEN-LAST:event_decreaseScriptsMouseClicked

    private void decreaseScriptsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaseScriptsActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaseScriptsActionPerformed

    private void scenaryValueActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_scenaryValueActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_scenaryValueActionPerformed

    private void increaseScenaryMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseScenaryMouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity(1)) {
            this.scenaryValue.setText(increaseQuantity(this.scenaryValue.getText(), increaseScenary));
            
            helper.addWorker(0, 1);
        }
        updateBtnStatus();
    }// GEN-LAST:event_increaseScenaryMouseClicked

    private void increaseScenaryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseScenaryActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseScenaryActionPerformed

    private void decreaseScenaryMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaseScenaryMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(1)) {
            
            this.scenaryValue.setText(decreaseQuantity(this.scenaryValue.getText(), decreaseScenary));
            helper.deleteWorker(0, 1);
        }
        updateBtnStatus();
    }// GEN-LAST:event_decreaseScenaryMouseClicked

    private void decreaseScenaryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaseScenaryActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaseScenaryActionPerformed

    private void animationValuesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_animationValuesActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_animationValuesActionPerformed

    private void decreaseAnimationMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaseAnimationMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(2)) {
            
            this.animationValues.setText(decreaseQuantity(this.animationValues.getText(), decreaseAnimation));
            helper.deleteWorker(0, 2);
        }
        updateBtnStatus();
    }// GEN-LAST:event_decreaseAnimationMouseClicked

    private void decreaseAnimationActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaseAnimationActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaseAnimationActionPerformed

    private void increaseAnimationMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseAnimationMouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity(2)) {
            
            this.animationValues.setText(increaseQuantity(this.animationValues.getText(), increaseAnimation));
            helper.addWorker(0, 2);
        }
        updateBtnStatus();
    }// GEN-LAST:event_increaseAnimationMouseClicked

    private void increaseAnimationActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseAnimationActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseAnimationActionPerformed

    private void decreaseDubbingMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaseDubbingMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(3)) {
            
            this.dubbingValues.setText(decreaseQuantity(this.dubbingValues.getText(), decreaseDubbing));
            helper.deleteWorker(0, 3);
        }
        updateBtnStatus();
    }// GEN-LAST:event_decreaseDubbingMouseClicked

    private void decreaseDubbingActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaseDubbingActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaseDubbingActionPerformed

    private void dubbingValuesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_dubbingValuesActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_dubbingValuesActionPerformed

    private void increaseDubbingMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseDubbingMouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity(3)) {
            
            this.dubbingValues.setText(increaseQuantity(this.dubbingValues.getText(), increaseDubbing));
            helper.addWorker(0, 3);
        }
        updateBtnStatus();
    }// GEN-LAST:event_increaseDubbingMouseClicked

    private void increaseDubbingActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseDubbingActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseDubbingActionPerformed

    private void increasePlotTwistMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increasePlotTwistMouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity(4)) {
            
            this.plotTwistValues.setText(increaseQuantity(this.plotTwistValues.getText(), increasePlotTwist));
            helper.addWorker(0, 4);
        }
        updateBtnStatus();
    }// GEN-LAST:event_increasePlotTwistMouseClicked

    private void increasePlotTwistActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increasePlotTwistActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increasePlotTwistActionPerformed

    private void plotTwistValuesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_plotTwistValuesActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_plotTwistValuesActionPerformed

    private void decreacePlotTwistMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreacePlotTwistMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(4)) {
            
            this.plotTwistValues.setText(decreaseQuantity(this.plotTwistValues.getText(), decreacePlotTwist));
            helper.deleteWorker(0, 4);
        }
        updateBtnStatus();
    }// GEN-LAST:event_decreacePlotTwistMouseClicked

    private void decreacePlotTwistActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreacePlotTwistActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreacePlotTwistActionPerformed

    private void increaseAssemblerMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseAssemblerMouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity(5)) {
            
            this.assemblerValues.setText(increaseQuantity(this.assemblerValues.getText(), increaseAssembler));
            helper.addWorker(0, 5);
        }
        updateBtnStatus();
    }// GEN-LAST:event_increaseAssemblerMouseClicked

    private void increaseAssemblerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseAssemblerActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseAssemblerActionPerformed

    private void assemblerValuesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_assemblerValuesActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_assemblerValuesActionPerformed

    private void decreaceAssemblerMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaceAssemblerMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(5)) {
            
            this.assemblerValues.setText(decreaseQuantity(this.assemblerValues.getText(), decreaceAssembler));
            helper.deleteWorker(0, 5);
        }
        updateBtnStatus();
    }// GEN-LAST:event_decreaceAssemblerMouseClicked

    private void decreaceAssemblerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaceAssemblerActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaceAssemblerActionPerformed

    private void increaseScripts1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseScripts1MouseClicked
        // TODO add your handling code here:
        if (this.canIncreaseQuantity1(0)) {
            
            this.scriptsValues1.setText(increaseQuantity1(this.scriptsValues1.getText(), increaseScripts1));
            helper.addWorker(1, 0);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_increaseScripts1MouseClicked

    private void increaseScripts1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseScripts1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseScripts1ActionPerformed

    private void scriptsValues1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_scriptsValues1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_scriptsValues1ActionPerformed

    private void decreaseScripts1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaseScripts1MouseClicked
        // TODO add your handling code here:
        updateValues1();
        if (canDecreaseQuantity1(0)) {
            
            this.scriptsValues1.setText(decreaseQuantity1(this.scriptsValues1.getText(), this.decreaseScripts1));
            helper.deleteWorker(1, 0);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_decreaseScripts1MouseClicked

    private void decreaseScripts1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaseScripts1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaseScripts1ActionPerformed

    private void scenaryValue1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_scenaryValue1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_scenaryValue1ActionPerformed

    private void increaseScenary1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseScenary1MouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity1(1)) {
            this.scenaryValue1.setText(increaseQuantity1(this.scenaryValue1.getText(), increaseScenary1));
            
            helper.addWorker(1, 1);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_increaseScenary1MouseClicked

    private void increaseScenary1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseScenary1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseScenary1ActionPerformed

    private void decreaseScenary1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaseScenary1MouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity1(1)) {
            
            this.scenaryValue1.setText(decreaseQuantity1(this.scenaryValue1.getText(), decreaseScenary1));
            helper.deleteWorker(1, 1);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_decreaseScenary1MouseClicked

    private void decreaseScenary1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaseScenary1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaseScenary1ActionPerformed

    private void animationValues1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_animationValues1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_animationValues1ActionPerformed

    private void decreaseAnimation1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaseAnimation1MouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity1(2)) {
            
            this.animationValues1.setText(decreaseQuantity1(this.animationValues1.getText(), decreaseAnimation1));
            helper.deleteWorker(1, 2);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_decreaseAnimation1MouseClicked

    private void decreaseAnimation1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaseAnimation1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaseAnimation1ActionPerformed

    private void increaseAnimation1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseAnimation1MouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity1(2)) {
            
            this.animationValues1.setText(increaseQuantity1(this.animationValues1.getText(), increaseAnimation1));
            helper.addWorker(1, 2);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_increaseAnimation1MouseClicked

    private void increaseAnimation1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseAnimation1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseAnimation1ActionPerformed

    private void decreaseDubbing1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaseDubbing1MouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity1(3)) {
            
            this.dubbingValues1.setText(decreaseQuantity1(this.dubbingValues1.getText(), decreaseDubbing1));
            helper.deleteWorker(1, 3);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_decreaseDubbing1MouseClicked

    private void decreaseDubbing1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaseDubbing1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaseDubbing1ActionPerformed

    private void dubbingValues1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_dubbingValues1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_dubbingValues1ActionPerformed

    private void increaseDubbing1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseDubbing1MouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity1(3)) {
            
            this.dubbingValues1.setText(increaseQuantity1(this.dubbingValues1.getText(), increaseDubbing1));
            helper.addWorker(1, 3);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_increaseDubbing1MouseClicked

    private void increaseDubbing1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseDubbing1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseDubbing1ActionPerformed

    private void increasePlotTwist1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increasePlotTwist1MouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity1(4)) {
            
            this.plotTwistValues1.setText(increaseQuantity1(this.plotTwistValues1.getText(), increasePlotTwist1));
            helper.addWorker(1, 4);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_increasePlotTwist1MouseClicked

    private void increasePlotTwist1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increasePlotTwist1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increasePlotTwist1ActionPerformed

    private void plotTwistValues1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_plotTwistValues1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_plotTwistValues1ActionPerformed

    private void decreacePlotTwist1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreacePlotTwist1MouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity1(4)) {
            
            this.plotTwistValues1.setText(decreaseQuantity1(this.plotTwistValues1.getText(), decreacePlotTwist1));
            helper.deleteWorker(1, 4);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_decreacePlotTwist1MouseClicked

    private void decreacePlotTwist1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreacePlotTwist1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreacePlotTwist1ActionPerformed

    private void increaseAssembler1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_increaseAssembler1MouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity1(5)) {
            
            this.assemblerValues1.setText(increaseQuantity1(this.assemblerValues1.getText(), increaseAssembler1));
            helper.addWorker(1, 5);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_increaseAssembler1MouseClicked

    private void increaseAssembler1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_increaseAssembler1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_increaseAssembler1ActionPerformed

    private void assemblerValues1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_assemblerValues1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_assemblerValues1ActionPerformed

    private void decreaceAssembler1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_decreaceAssembler1MouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity1(5)) {
            
            this.assemblerValues1.setText(decreaseQuantity1(this.assemblerValues1.getText(), decreaceAssembler1));
            helper.deleteWorker(1, 5);
        }
        updateBtnStatus1();
    }// GEN-LAST:event_decreaceAssembler1MouseClicked

    private void decreaceAssembler1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decreaceAssembler1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_decreaceAssembler1ActionPerformed

    private boolean canDecreaseDay() {
        return this.dayDuration > 1;
    }

    private boolean canDecreaseDeadline() {
        return this.deadline > 1;
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

    private String increaseQuantity1(String actualValue, JButton btn) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(actualValue);
            if (actualEmployees1 < maxEmployees1) {
                intValue++;
                actualEmployees1++;
            }
            return String.valueOf(intValue);
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el valor a int: " + e.getMessage());
            return actualValue; // Retorna el valor actual en caso de error
        }
    }

    private String decreaseQuantity1(String actualValue, JButton btn) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(actualValue);
            if (intValue > 1) {
                intValue--;
                actualEmployees1--;
                return String.valueOf(intValue);
            } else {
                return String.valueOf(intValue);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el valor a int: " + e.getMessage());
        }
        return null;
    }

    private boolean canDecreaseQuantity1(int type) {
        updateValues1();
        return values1[type] > 1;
    }

    private boolean canIncreaseQuantity1(int type) {
        updateValues1();
        return actualEmployees1 < maxEmployees1;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfigParams.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigParams.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigParams.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigParams.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfigParams().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel SidePanel;
    private javax.swing.JTextField animationValues;
    private javax.swing.JTextField animationValues1;
    private javax.swing.JPanel animations;
    private javax.swing.JPanel animations1;
    private javax.swing.JLabel animationsTitle;
    private javax.swing.JLabel animationsTitle1;
    private javax.swing.JLabel assemblerTitle;
    private javax.swing.JLabel assemblerTitle1;
    private javax.swing.JTextField assemblerValues;
    private javax.swing.JTextField assemblerValues1;
    private javax.swing.JPanel btn_Inicio;
    private javax.swing.JPanel btn_cargar_guardar;
    private javax.swing.JPanel btn_nueva_ruta;
    private javax.swing.JPanel btn_nuevo_almacen;
    private javax.swing.JPanel btn_nuevo_pedido;
    private javax.swing.JPanel btn_reporte;
    private javax.swing.JTextField dayDurationValue;
    private javax.swing.JTextField deadlineValue;
    private javax.swing.JButton decreaceAssembler;
    private javax.swing.JButton decreaceAssembler1;
    private javax.swing.JButton decreacePlotTwist;
    private javax.swing.JButton decreacePlotTwist1;
    private javax.swing.JButton decreaseAnimation;
    private javax.swing.JButton decreaseAnimation1;
    private javax.swing.JButton decreaseDay;
    private javax.swing.JButton decreaseDeadline;
    private javax.swing.JButton decreaseDubbing;
    private javax.swing.JButton decreaseDubbing1;
    private javax.swing.JButton decreaseScenary;
    private javax.swing.JButton decreaseScenary1;
    private javax.swing.JButton decreaseScripts;
    private javax.swing.JButton decreaseScripts1;
    private javax.swing.JLabel driveTitle;
    private javax.swing.JLabel driveTitle21;
    private javax.swing.JLabel driveTitle22;
    private javax.swing.JLabel driveTitle27;
    private javax.swing.JLabel driveTitle28;
    private javax.swing.JPanel dubbing;
    private javax.swing.JPanel dubbing1;
    private javax.swing.JLabel dubbingTitle;
    private javax.swing.JLabel dubbingTitle1;
    private javax.swing.JTextField dubbingValues;
    private javax.swing.JTextField dubbingValues1;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel icono1;
    private javax.swing.JLabel icono3;
    private javax.swing.JLabel icono4;
    private javax.swing.JLabel icono5;
    private javax.swing.JLabel icono7;
    private javax.swing.JButton increaseAnimation;
    private javax.swing.JButton increaseAnimation1;
    private javax.swing.JButton increaseAssembler;
    private javax.swing.JButton increaseAssembler1;
    private javax.swing.JButton increaseDay;
    private javax.swing.JButton increaseDeadline;
    private javax.swing.JButton increaseDubbing;
    private javax.swing.JButton increaseDubbing1;
    private javax.swing.JButton increasePlotTwist;
    private javax.swing.JButton increasePlotTwist1;
    private javax.swing.JButton increaseScenary;
    private javax.swing.JButton increaseScenary1;
    private javax.swing.JButton increaseScripts;
    private javax.swing.JButton increaseScripts1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel maxCap;
    private javax.swing.JLabel maxCap1;
    private javax.swing.JPanel plotTwist;
    private javax.swing.JPanel plotTwist1;
    private javax.swing.JPanel plotTwist2;
    private javax.swing.JPanel plotTwist3;
    private javax.swing.JLabel plotTwistTitle;
    private javax.swing.JLabel plotTwistTitle1;
    private javax.swing.JTextField plotTwistValues;
    private javax.swing.JTextField plotTwistValues1;
    private javax.swing.JPanel scenary;
    private javax.swing.JPanel scenary1;
    private javax.swing.JLabel scenaryTitle;
    private javax.swing.JLabel scenaryTitle1;
    private javax.swing.JTextField scenaryValue;
    private javax.swing.JTextField scenaryValue1;
    private javax.swing.JPanel scripts;
    private javax.swing.JPanel scripts1;
    private javax.swing.JPanel scripts2;
    private javax.swing.JPanel scripts3;
    private javax.swing.JLabel scriptsTitle;
    private javax.swing.JLabel scriptsTitle1;
    private javax.swing.JLabel scriptsTitle2;
    private javax.swing.JLabel scriptsTitle3;
    private javax.swing.JTextField scriptsValues;
    private javax.swing.JTextField scriptsValues1;
    private javax.swing.JPanel workersConfigurations;
    private javax.swing.JPanel workersConfigurations1;
    // End of variables declaration                   
}

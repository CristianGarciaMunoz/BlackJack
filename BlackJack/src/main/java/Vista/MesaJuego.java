package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Vista principal que representa la mesa de juego del Blackjack.
 * Gestiona la disposición de los jugadores, la zona de la banca y los campos 
 * interactivos para realizar apuestas durante la partida.
 * * @author Cristiangm05
 */
public class MesaJuego extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MesaJuego.class.getName());

    /**
     * Constructor que inicializa los componentes de la interfaz, aplica la configuración
     * de colores, tipografías y carga el diseño de fondo y los campos de apuesta.
     */
    public MesaJuego() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(20, 100, 40));
        configuracionMesaJuego();
        
        // Configuración de los campos de texto asociados a cada asiento
        configurarCampoNombre(txtApuesta1, jLabel1);
        configurarCampoNombre(txtApuesta2, jLabel2);
        configurarCampoNombre(txtApuesta3, jLabel3);
        configurarCampoNombre(txtApuesta4, jLabel4);
        
        configurarFondoVentana("/img/fondoMesa.png");
    }

    /**
     * Aplica estilos, colores y fuentes personalizadas a todos los componentes de la mesa,
     * asegurando una consistencia visual temática.
     */
    public final void configuracionMesaJuego() {
        // Configuración de la zona de la banca
        lblBancaPuntos.setBackground(new java.awt.Color(255, 255, 255));
        lblBancaPuntos.setForeground(java.awt.Color.BLACK);
        lblBancaPuntos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        // Configuración de etiquetas y campos de entrada
        javax.swing.JLabel[] saldos = {lblSaldo1, lblSaldo2, lblSaldo3, lblSaldo4};
        javax.swing.JTextField[] apuestas = {txtApuesta1, txtApuesta2, txtApuesta3, txtApuesta4};
        javax.swing.JLabel[] cartas = {lblCartas1, lblCartas2, lblCartas3, lblCartas4};

        for (int i = 0; i < 4; i++) {
            saldos[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            apuestas[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            cartas[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            
            saldos[i].setFont(new Font("Serif", Font.BOLD, 18));
            cartas[i].setFont(new Font("Serif", Font.BOLD, 18));
            cartas[i].setBackground(new java.awt.Color(235, 235, 235));
        }
        
        lblMensajeTurno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensajeTurno.setFont(new Font("Serif", Font.BOLD, 18));

        Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logoIniciarPartida.png"));
        this.setIconImage(logo);
    }

    /**
     * Implementa un sistema de capas (JLayeredPane) para superponer un panel de fondo 
     * personalizado, permitiendo que los componentes de juego floten sobre la mesa.
     * * @param rutaImagen La ruta del recurso de la imagen de fondo de la mesa.
     */
    public final void configurarFondoVentana(String rutaImagen) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(this.getSize());

        PanelFondo panelFondo = new PanelFondo(rutaImagen);
        panelFondo.setBounds(0, 0, this.getWidth(), this.getHeight());

        Container contentPane = this.getContentPane();
        if (contentPane instanceof JPanel) {
            ((JPanel) contentPane).setOpaque(false);
        }
        
        layeredPane.add(panelFondo, Integer.valueOf(0));
        layeredPane.add(contentPane, Integer.valueOf(1));

        this.setContentPane(layeredPane);
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Ajusta un campo de texto (JTextField) para integrarse visualmente dentro de un 
     * JLabel que actúa como contenedor decorativo.
     * * @param apuesta El campo de texto donde el jugador ingresa su apuesta.
     * @param label   El JLabel que contiene la imagen decorativa del campo de apuesta.
     */
    public final void configurarCampoNombre(JTextField apuesta, JLabel label) {
        label.setLayout(new BorderLayout());

        apuesta.setOpaque(false);
        apuesta.setBorder(BorderFactory.createEmptyBorder(10, 130, 30, 20));
        apuesta.setForeground(Color.BLACK);
        apuesta.setCaretColor(Color.BLACK);
        apuesta.setFont(new Font("Serif", Font.BOLD, 18));

        label.add(apuesta, BorderLayout.CENTER);
        apuesta.setBackground(new Color(0, 0, 0, 0));

        label.revalidate();
        label.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalirMesa = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        comboJugador1 = new javax.swing.JComboBox<>();
        lblSaldo1 = new javax.swing.JLabel();
        txtApuesta1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblCartas1 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        comboJugador2 = new javax.swing.JComboBox<>();
        lblSaldo2 = new javax.swing.JLabel();
        txtApuesta2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblCartas2 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        comboJugador3 = new javax.swing.JComboBox<>();
        lblSaldo3 = new javax.swing.JLabel();
        txtApuesta3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblCartas3 = new javax.swing.JLabel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        comboJugador4 = new javax.swing.JComboBox<>();
        lblSaldo4 = new javax.swing.JLabel();
        txtApuesta4 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lblCartas4 = new javax.swing.JLabel();
        lblMensajeTurno = new javax.swing.JLabel();
        lblBancaPuntos = new javax.swing.JLabel();
        btnPedir = new javax.swing.JButton();
        btnRepartir = new javax.swing.JButton();
        btnPlantarse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("♦ Tapete de Juego - ¡Hagan sus Apuestas! ♦");
        setMaximumSize(new java.awt.Dimension(980, 730));
        setMinimumSize(new java.awt.Dimension(980, 730));
        setPreferredSize(new java.awt.Dimension(980, 730));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalirMesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonSalirMesa.png"))); // NOI18N
        btnSalirMesa.setBorder(null);
        btnSalirMesa.setBorderPainted(false);
        btnSalirMesa.setContentAreaFilled(false);
        btnSalirMesa.setFocusPainted(false);
        btnSalirMesa.addActionListener(this::btnSalirMesaActionPerformed);
        getContentPane().add(btnSalirMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 70));

        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonComoJugar.png"))); // NOI18N
        btnInfo.setBorderPainted(false);
        btnInfo.setContentAreaFilled(false);
        btnInfo.setFocusPainted(false);
        btnInfo.addActionListener(this::btnInfoActionPerformed);
        getContentPane().add(btnInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(818, 17, -1, 44));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/asiento1.png"))); // NOI18N

        comboJugador1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir Jugador" }));
        comboJugador1.setOpaque(true);

        lblSaldo1.setForeground(new java.awt.Color(0, 0, 0));

        txtApuesta1.setBackground(new java.awt.Color(255, 255, 255));
        txtApuesta1.setColumns(4);
        txtApuesta1.setForeground(new java.awt.Color(0, 0, 0));
        txtApuesta1.setOpaque(true);

        jPanel1.setMaximumSize(new java.awt.Dimension(173, 50));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCartas1.setBackground(new java.awt.Color(240, 240, 240));
        lblCartas1.setForeground(new java.awt.Color(0, 0, 0));
        lblCartas1.setMaximumSize(new java.awt.Dimension(173, 50));
        lblCartas1.setMinimumSize(new java.awt.Dimension(173, 50));
        lblCartas1.setOpaque(true);
        lblCartas1.setPreferredSize(new java.awt.Dimension(173, 50));
        jPanel1.add(lblCartas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 12, 160, 30));

        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(comboJugador1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblSaldo1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtApuesta1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblSaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(comboJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(txtApuesta1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap(34, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(lblSaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(comboJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(txtApuesta1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap(165, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 240, 290));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/asiento2.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(243, 283));

        comboJugador2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir Jugador" }));
        comboJugador2.setOpaque(true);

        lblSaldo2.setForeground(new java.awt.Color(0, 0, 0));

        txtApuesta2.setBackground(new java.awt.Color(255, 255, 255));
        txtApuesta2.setColumns(4);
        txtApuesta2.setForeground(new java.awt.Color(0, 0, 0));
        txtApuesta2.setOpaque(true);

        jPanel2.setMaximumSize(new java.awt.Dimension(173, 50));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCartas2.setBackground(new java.awt.Color(240, 240, 240));
        lblCartas2.setForeground(new java.awt.Color(0, 0, 0));
        lblCartas2.setMaximumSize(new java.awt.Dimension(173, 50));
        lblCartas2.setMinimumSize(new java.awt.Dimension(173, 50));
        lblCartas2.setOpaque(true);
        lblCartas2.setPreferredSize(new java.awt.Dimension(173, 50));
        jPanel2.add(lblCartas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 12, 160, 30));

        jLayeredPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(comboJugador2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(lblSaldo2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtApuesta2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblSaldo2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(comboJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(txtApuesta2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                    .addContainerGap(35, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblSaldo2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(comboJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(txtApuesta2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                    .addContainerGap(164, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(76, Short.MAX_VALUE)))
        );

        getContentPane().add(jLayeredPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 240, 290));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/asiento3.png"))); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(243, 283));

        comboJugador3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir Jugador" }));
        comboJugador3.setOpaque(true);

        lblSaldo3.setForeground(new java.awt.Color(0, 0, 0));

        txtApuesta3.setBackground(new java.awt.Color(255, 255, 255));
        txtApuesta3.setColumns(4);
        txtApuesta3.setForeground(new java.awt.Color(0, 0, 0));
        txtApuesta3.setOpaque(true);

        jPanel3.setMaximumSize(new java.awt.Dimension(173, 50));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCartas3.setBackground(new java.awt.Color(240, 240, 240));
        lblCartas3.setForeground(new java.awt.Color(0, 0, 0));
        lblCartas3.setMaximumSize(new java.awt.Dimension(173, 50));
        lblCartas3.setMinimumSize(new java.awt.Dimension(173, 50));
        lblCartas3.setOpaque(true);
        lblCartas3.setPreferredSize(new java.awt.Dimension(173, 50));
        jPanel3.add(lblCartas3, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 12, 160, 30));

        jLayeredPane3.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(comboJugador3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(lblSaldo3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtApuesta3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblSaldo3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(comboJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(txtApuesta3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                    .addContainerGap(35, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblSaldo3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(comboJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(txtApuesta3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                    .addContainerGap(164, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(76, Short.MAX_VALUE)))
        );

        getContentPane().add(jLayeredPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 240, 290));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/asiento4.png"))); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(243, 283));

        comboJugador4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir Jugador" }));
        comboJugador4.setOpaque(true);

        lblSaldo4.setForeground(new java.awt.Color(0, 0, 0));

        txtApuesta4.setBackground(new java.awt.Color(255, 255, 255));
        txtApuesta4.setColumns(4);
        txtApuesta4.setForeground(new java.awt.Color(0, 0, 0));
        txtApuesta4.setOpaque(true);

        jPanel4.setMaximumSize(new java.awt.Dimension(173, 50));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCartas4.setBackground(new java.awt.Color(240, 240, 240));
        lblCartas4.setForeground(new java.awt.Color(0, 0, 0));
        lblCartas4.setMaximumSize(new java.awt.Dimension(173, 50));
        lblCartas4.setMinimumSize(new java.awt.Dimension(173, 50));
        lblCartas4.setOpaque(true);
        lblCartas4.setPreferredSize(new java.awt.Dimension(173, 50));
        jPanel4.add(lblCartas4, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 12, 160, 30));

        jLayeredPane4.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(comboJugador4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(lblSaldo4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(txtApuesta4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblSaldo4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(comboJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(txtApuesta4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane4Layout.createSequentialGroup()
                    .addContainerGap(35, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblSaldo4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(comboJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(txtApuesta4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane4Layout.createSequentialGroup()
                    .addContainerGap(164, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(76, Short.MAX_VALUE)))
        );

        getContentPane().add(jLayeredPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, 240, 290));

        lblMensajeTurno.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lblMensajeTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 640, 380, 40));

        lblBancaPuntos.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        lblBancaPuntos.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lblBancaPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 70, 50));

        btnPedir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonPedir.png"))); // NOI18N
        btnPedir.setBorder(null);
        btnPedir.setBorderPainted(false);
        btnPedir.setContentAreaFilled(false);
        btnPedir.setFocusPainted(false);
        getContentPane().add(btnPedir, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 570, 100, 50));

        btnRepartir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonRepartir.png"))); // NOI18N
        btnRepartir.setBorder(null);
        btnRepartir.setBorderPainted(false);
        btnRepartir.setContentAreaFilled(false);
        btnRepartir.setFocusPainted(false);
        getContentPane().add(btnRepartir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 570, 110, 50));

        btnPlantarse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonPlantarse.png"))); // NOI18N
        btnPlantarse.setBorder(null);
        btnPlantarse.setBorderPainted(false);
        btnPlantarse.setContentAreaFilled(false);
        btnPlantarse.setFocusPainted(false);
        getContentPane().add(btnPlantarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, 100, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirMesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirMesaActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInfoActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MesaJuego().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnInfo;
    public javax.swing.JButton btnPedir;
    public javax.swing.JButton btnPlantarse;
    public javax.swing.JButton btnRepartir;
    public javax.swing.JButton btnSalirMesa;
    public javax.swing.JComboBox<String> comboJugador1;
    public javax.swing.JComboBox<String> comboJugador2;
    public javax.swing.JComboBox<String> comboJugador3;
    public javax.swing.JComboBox<String> comboJugador4;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JLabel lblBancaPuntos;
    public javax.swing.JLabel lblCartas1;
    public javax.swing.JLabel lblCartas2;
    public javax.swing.JLabel lblCartas3;
    public javax.swing.JLabel lblCartas4;
    public javax.swing.JLabel lblMensajeTurno;
    public javax.swing.JLabel lblSaldo1;
    public javax.swing.JLabel lblSaldo2;
    public javax.swing.JLabel lblSaldo3;
    public javax.swing.JLabel lblSaldo4;
    public javax.swing.JTextField txtApuesta1;
    public javax.swing.JTextField txtApuesta2;
    public javax.swing.JTextField txtApuesta3;
    public javax.swing.JTextField txtApuesta4;
    // End of variables declaration//GEN-END:variables
}

package interfaz;

import dominio.Ficha;
import dominio.Jugador;
import dominio.Partida;
import dominio.Sistema;
import dominio.Tablero;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * Creado por:
 *   Juan Balian - 211150
 *   Agustín Introini - 211064
 * */

public class Interfaz extends javax.swing.JFrame {
    public static final Color colorJugadorUnoValido = Color.decode("#ff3300");
    public static final Color colorJugadorUnoInvalido = Color.decode("#a66959");
    public static final Color colorJugadorDosValido = Color.decode("#0033cc");
    public static final Color colorJugadorDosInvalido = Color.decode("#475785");
    public static final Color colorMovimientosValidos = Color.decode("#50C878");
    public static final Color colorMenuBar = Color.decode("#328CC1");
    public static final Color colorFondo = Color.decode("#19181A");
    public static final Color colorBarra = Color.decode("#3AAFA9");
    public static final Color colorBotonesVacios = Color.decode("#DEF2F1");
   
    JButton[][] botones = new JButton[8][9];
    private int jugadorActivo = 0;
    private Partida partida;
    private ArrayList<String> movimientosReplicar = null;
    private Ficha fichaSeleccionada = null;
    private final Sistema sistema = new Sistema();
    private boolean sonidoActivado = true;
    
    public void setPartida(Partida partida){
        this.partida = partida;
    }
    
    public Sistema getSistema(){
        return sistema;
    }
    
    public Interfaz() {
        initComponents();
      
        LblMovimientosRestantes.setVisible(false);
        lblAzul.setVisible(false);
        lblRojo.setVisible(false);
        
        ImageIcon img = new ImageIcon(getClass().getResource("/multimedia/icons8-controller_filled.png"));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/multimedia/icons8-controller_filled.png")));

        botonMenuEnablear(btnPasarTurno, false);
        botonMenuEnablear(btnAbandonar, false);
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt){
                sistema.guardar();
            }
        });
        
        inicializarTablero();
    }
    
    private void inicializarTablero(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                botones[i][j] = new JButton();

                botones[i][j].addActionListener(new ListenerBoton(i, j));
                panelJuego.add(botones[i][j]);
                botones[i][j].setBackground(colorBotonesVacios);
                botones[i][j].setForeground(Color.WHITE);
                botones[i][j].setEnabled(false);
                botones[i][j].setFont(new java.awt.Font("Heiti SC", 0, 22));
                botones[i][j].setMargin(new Insets(0,0, 0, 0));
            }
        }
    }

    public void reproducirSonido(URL direccion){
        if(!sonidoActivado)
            return;
        
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(direccion.toURI())));
            clip.start();
        }
        catch(Exception ex){ }
    }
 
    void actualizar(ArrayList<Integer> fichasValidas){
        if(partida.getFormaDeTerminar() == 1)
            lblMovRest.setText("" + (partida.getCantMovimientos() - partida.getMovimientos().size()));
        
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 9; ++j){
                botones[i][j].setBackground(colorBotonesVacios);
                botones[i][j].setText("");
            }
        }
        
        for(int i = 0; i < 2; ++i){
            for(Ficha ficha : partida.getJugadores()[i].getFichas()){
                Color background = pintarFichasDeJugador(i, fichasValidas, ficha.getNumero());
                
                JButton boton = botones[ficha.getY()][ficha.getX()];
                
                boton.setBackground(background);
                boton.setText(ficha.getNumero() + "");
            }
        }
        
       Color color = jugadorActivo == 0 ? colorJugadorUnoValido : colorJugadorDosValido;
       Color co = jugadorActivo == 0 ? colorJugadorUnoInvalido : colorJugadorDosInvalido;
       
       for(int i = 0, fila = jugadorActivo == 0 ? 0 : Tablero.LARGO - 1; i < botones[fila].length; ++i)
           if(botones[fila][i].getBackground() == color)
                botones[fila][i].setBackground(co);
    }
    
    private void limpiarCeldasVerdes(){
        for(int i = 0; i < 8; ++i)
            for(int j = 0; j < 9; ++j)
                if(botones[i][j].getBackground() == colorMovimientosValidos)
                    botones[i][j].setBackground(colorBotonesVacios);
    }
    
    private Color pintarFichasDeJugador(int numJugador, ArrayList<Integer> fichasValidas, int numFicha){
        if (jugadorActivo == numJugador){
            if(fichasValidas == null)
                return numJugador == 0 ? colorJugadorUnoValido : colorJugadorDosValido;
            else if (fichasValidas.contains(numFicha))
                return numJugador == 0 ? colorJugadorUnoValido : colorJugadorDosValido;
            return numJugador == 0 ?  colorJugadorUnoInvalido : colorJugadorDosInvalido;
        }
        return numJugador == 0 ?  colorJugadorUnoInvalido : colorJugadorDosInvalido;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LblMovimientosRestantes = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panelPartida = new javax.swing.JPanel();
        btnAbandonar = new javax.swing.JButton();
        btnPasarTurno = new javax.swing.JButton();
        panelReplicarPartida = new javax.swing.JPanel();
        btnSiguienteMov = new javax.swing.JButton();
        btnRetomarPartida = new javax.swing.JButton();
        lblMovRest = new javax.swing.JLabel();
        btnSonido = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        panelTransparente = new javax.swing.JPanel();
        lblFelicitaciones = new javax.swing.JLabel();
        lblAlias = new javax.swing.JLabel();
        lblGanador = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        panelJuego = new javax.swing.JPanel();
        lblRojo = new javax.swing.JLabel();
        lblAzul = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnNuevaPartida = new javax.swing.JButton();
        btnReplicarPartida = new javax.swing.JButton();
        btnAgregarJugador = new javax.swing.JButton();
        btnRanking = new javax.swing.JButton();
        btnGuardarJugadores = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 23, 650, 510));
        setResizable(false);
        setSize(new java.awt.Dimension(660, 550));

        jPanel1.setBackground(colorFondo);
        jPanel1.setLayout(null);

        LblMovimientosRestantes.setFont(new java.awt.Font("Heiti SC", 0, 18)); // NOI18N
        LblMovimientosRestantes.setForeground(colorBotonesVacios);
        LblMovimientosRestantes.setText("Movimientos restantes:");
        jPanel1.add(LblMovimientosRestantes);
        LblMovimientosRestantes.setBounds(40, 500, 220, 20);

        jPanel2.setBackground(colorFondo);

        panelPartida.setBackground(colorFondo);
        panelPartida.setLayout(new java.awt.GridLayout(2, 0, 0, 20));

        btnAbandonar.setBackground(Interfaz.colorBarra);
        btnAbandonar.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnAbandonar.setForeground(colorBarra);
        btnAbandonar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-exit_sign_filled.png"))); // NOI18N
        btnAbandonar.setText("Abandonar");
        btnAbandonar.setBorder(javax.swing.BorderFactory.createLineBorder(colorBarra));
        btnAbandonar.setContentAreaFilled(false);
        btnAbandonar.setEnabled(false);
        btnAbandonar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAbandonar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonarActionPerformed(evt);
            }
        });
        panelPartida.add(btnAbandonar);

        btnPasarTurno.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnPasarTurno.setForeground(colorBarra);
        btnPasarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-right2_filled.png"))); // NOI18N
        btnPasarTurno.setText("Pasar turno");
        btnPasarTurno.setBorder(javax.swing.BorderFactory.createLineBorder(colorBarra));
        btnPasarTurno.setContentAreaFilled(false);
        btnPasarTurno.setEnabled(false);
        btnPasarTurno.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnPasarTurno.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPasarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasarTurnoActionPerformed(evt);
            }
        });
        panelPartida.add(btnPasarTurno);

        panelReplicarPartida.setBackground(colorFondo);
        panelReplicarPartida.setLayout(new java.awt.GridLayout(2, 0, 0, 20));

        btnSiguienteMov.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnSiguienteMov.setForeground(colorBarra);
        btnSiguienteMov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-advance_filled.png"))); // NOI18N
        btnSiguienteMov.setText("Siguiente movimiento");
        btnSiguienteMov.setBorder(javax.swing.BorderFactory.createLineBorder(colorBarra));
        btnSiguienteMov.setContentAreaFilled(false);
        btnSiguienteMov.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSiguienteMov.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSiguienteMov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteMovActionPerformed(evt);
            }
        });
        panelReplicarPartida.add(btnSiguienteMov);

        btnRetomarPartida.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnRetomarPartida.setForeground(colorBarra);
        btnRetomarPartida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-rotate_left_filled.png"))); // NOI18N
        btnRetomarPartida.setText("Retomar partida");
        btnRetomarPartida.setBorder(javax.swing.BorderFactory.createLineBorder(colorBarra));
        btnRetomarPartida.setContentAreaFilled(false);
        btnRetomarPartida.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnRetomarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRetomarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetomarPartidaActionPerformed(evt);
            }
        });
        panelReplicarPartida.add(btnRetomarPartida);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelReplicarPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelReplicarPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPartida, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
        );

        panelReplicarPartida.setVisible(false);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(440, 140, 162, 320);

        lblMovRest.setFont(new java.awt.Font("Heiti TC", 2, 18)); // NOI18N
        lblMovRest.setForeground(colorBarra);
        jPanel1.add(lblMovRest);
        lblMovRest.setBounds(270, 500, 50, 20);

        btnSonido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-room_sound_filled.png"))); // NOI18N
        btnSonido.setBorderPainted(false);
        btnSonido.setContentAreaFilled(false);
        btnSonido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSonidoActionPerformed(evt);
            }
        });
        jPanel1.add(btnSonido);
        btnSonido.setBounds(580, 470, 40, 40);

        jPanel3.setVerifyInputWhenFocusTarget(false);
        jPanel3.setLayout(null);

        panelTransparente.setBackground(new Color(0,0,0,0));
        panelTransparente.setLayout(null);

        lblFelicitaciones.setFont(new java.awt.Font("Heiti SC", 0, 24)); // NOI18N
        lblFelicitaciones.setForeground(new java.awt.Color(255, 255, 255));
        lblFelicitaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFelicitaciones.setText("¡FELICITACIONES!");
        panelTransparente.add(lblFelicitaciones);
        lblFelicitaciones.setBounds(0, 150, 370, 30);

        lblAlias.setFont(new java.awt.Font("Heiti SC", 0, 18)); // NOI18N
        lblAlias.setForeground(new java.awt.Color(255, 0, 51));
        lblAlias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlias.setText("Jugador");
        panelTransparente.add(lblAlias);
        lblAlias.setBounds(30, 190, 100, 30);

        lblGanador.setFont(new java.awt.Font("Heiti SC", 0, 18)); // NOI18N
        lblGanador.setForeground(new java.awt.Color(255, 255, 255));
        lblGanador.setText("Ha ganado la partida");
        panelTransparente.add(lblGanador);
        lblGanador.setBounds(140, 190, 210, 30);

        fondo.setBackground(new Color(0,0,0,0));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/fondo.png"))); // NOI18N
        panelTransparente.add(fondo);
        fondo.setBounds(0, 0, 370, 370);

        panelTransparente.setVisible(false);

        jPanel3.add(panelTransparente);
        panelTransparente.setBounds(0, 0, 370, 370);

        panelJuego.setBackground(colorFondo);
        panelJuego.setLayout(new java.awt.GridLayout(8, 9));
        jPanel3.add(panelJuego);
        panelJuego.setBounds(0, 0, 370, 370);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(30, 120, 370, 370);

        lblRojo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblRojo.setForeground(colorBotonesVacios);
        lblRojo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRojo.setText("Turno de:");
        jPanel1.add(lblRojo);
        lblRojo.setBounds(30, 90, 100, 22);

        lblAzul.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAzul.setForeground(new java.awt.Color(0, 0, 255));
        lblAzul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAzul.setText("Azul");
        jPanel1.add(lblAzul);
        lblAzul.setBounds(140, 90, 250, 22);

        jPanel4.setBackground(Interfaz.colorBarra);
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        btnNuevaPartida.setFont(new java.awt.Font("Heiti SC", 0, 10)); // NOI18N
        btnNuevaPartida.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaPartida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-play_filled.png"))); // NOI18N
        btnNuevaPartida.setText("Nueva partida");
        btnNuevaPartida.setToolTipText("Nueva Partida");
        btnNuevaPartida.setBorderPainted(false);
        btnNuevaPartida.setContentAreaFilled(false);
        btnNuevaPartida.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnNuevaPartida.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaPartidaActionPerformed(evt);
            }
        });
        jPanel4.add(btnNuevaPartida);

        btnReplicarPartida.setFont(new java.awt.Font("Heiti SC", 0, 10)); // NOI18N
        btnReplicarPartida.setForeground(new java.awt.Color(255, 255, 255));
        btnReplicarPartida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-recurring_appointment_filled.png"))); // NOI18N
        btnReplicarPartida.setText("Replicar partida");
        btnReplicarPartida.setToolTipText("Replicar Partida");
        btnReplicarPartida.setBorderPainted(false);
        btnReplicarPartida.setContentAreaFilled(false);
        btnReplicarPartida.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnReplicarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
        btnReplicarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReplicarPartidaActionPerformed(evt);
            }
        });
        jPanel4.add(btnReplicarPartida);

        btnAgregarJugador.setFont(new java.awt.Font("Heiti SC", 0, 10)); // NOI18N
        btnAgregarJugador.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarJugador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-add_user_filled.png"))); // NOI18N
        btnAgregarJugador.setText("Agregar jugador");
        btnAgregarJugador.setToolTipText("Agregar jugador");
        btnAgregarJugador.setBorderPainted(false);
        btnAgregarJugador.setContentAreaFilled(false);
        btnAgregarJugador.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAgregarJugador.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAgregarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarJugadorActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregarJugador);

        btnRanking.setFont(new java.awt.Font("Heiti SC", 0, 10)); // NOI18N
        btnRanking.setForeground(new java.awt.Color(255, 255, 255));
        btnRanking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-leaderboard_filled.png"))); // NOI18N
        btnRanking.setText("Ranking");
        btnRanking.setToolTipText("Ranking de jugadores");
        btnRanking.setBorderPainted(false);
        btnRanking.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnRanking.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRanking.setContentAreaFilled(false);
        btnRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRankingActionPerformed(evt);
            }
        });
        jPanel4.add(btnRanking);

        btnGuardarJugadores.setFont(new java.awt.Font("Heiti SC", 0, 10)); // NOI18N
        btnGuardarJugadores.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarJugadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/guardarUsuarios.png"))); // NOI18N
        btnGuardarJugadores.setText("Guardar jugadores");
        btnGuardarJugadores.setToolTipText("Guardar jugadores");
        btnGuardarJugadores.setBorderPainted(false);
        btnGuardarJugadores.setContentAreaFilled(false);
        btnGuardarJugadores.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnGuardarJugadores.setHorizontalTextPosition(SwingConstants.CENTER);
        btnGuardarJugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarJugadoresActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardarJugadores);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 0, 660, 60);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbandonarActionPerformed
        reproducirSonido(getClass().getResource("/multimedia/tap-crisp.aif"));
        
        String nombreJugador = "<font style='font-weight:bold' color=" + 
                (jugadorActivo == 0 ? "#ff0000>" : "#0000ff>") + 
                partida.getJugadores()[jugadorActivo].getAlias() + "</font>";
        
        String[] opciones = {"SI", "NO"};
        int i = JOptionPane.showOptionDialog(this, 
                "<html>¿Está seguro que desea abandonar la partida, " + 
                nombreJugador + "?</html>", "¿Está seguro?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,"NO");
        
        if(i == JOptionPane.YES_OPTION){  
            partida.hacerMovimiento("X", jugadorActivo);
            
            //Si abandona el jugador activo, el ganador es el otro
            jugadorActivo = jugadorActivo == 0 ? 1 : 0;
            
            terminarPartida(false);
        }  
    }//GEN-LAST:event_btnAbandonarActionPerformed

    private void btnPasarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasarTurnoActionPerformed
        reproducirSonido(getClass().getResource("/multimedia/tap-crisp.aif"));
        
        jugadorActivo = jugadorActivo == 0 ? 1 : 0;
        lblAzul.setText(partida.getJugadores()[jugadorActivo].getAlias());
        lblAzul.setForeground(jugadorActivo == 0 ? Color.RED: Color.BLUE);
        botonMenuEnablear(btnPasarTurno, false);
        actualizar(null);
    }//GEN-LAST:event_btnPasarTurnoActionPerformed

    private void btnSiguienteMovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteMovActionPerformed
        reproducirSonido(getClass().getResource("/multimedia/tap-warm.aif"));
        //SIGUIENTE PASO
        String mov = movimientosReplicar.remove(0);

        if(mov.startsWith("X")){
            botonMenuEnablear(btnSiguienteMov, false);
            botonMenuEnablear(btnRetomarPartida, false);
            movimientosReplicar = null;
            
            //Si abandona el jugador activo, el ganador es el otro4
            jugadorActivo = Integer.parseInt(mov.charAt(2) + "");
            
            partida.hacerMovimiento("X", jugadorActivo);
            
            jugadorActivo = jugadorActivo == 0 ? 1 : 0;
            terminarPartida(true);
            return;
        }
        
        jugadorActivo = Integer.parseInt("" + mov.charAt(4));

        lblAzul.setText(partida.getJugadores()[jugadorActivo].getAlias());
        lblAzul.setForeground(jugadorActivo == 0 ? Color.RED: Color.BLUE);
        
        ArrayList<Integer> fichasValidas = partida.hacerMovimiento(mov, jugadorActivo);
        
        // Termino la partida
        if(movimientosReplicar.isEmpty()){
            botonMenuEnablear(btnSiguienteMov, false);
            botonMenuEnablear(btnRetomarPartida, false);
            movimientosReplicar = null;
            terminarPartida(true);
        }
        else{
            if(fichasValidas.isEmpty())
                jugadorActivo = jugadorActivo == 0 ? 1 : 0;
            
            actualizar(fichasValidas.isEmpty() ? null : fichasValidas);                            
        }
    }//GEN-LAST:event_btnSiguienteMovActionPerformed

    private void btnRetomarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetomarPartidaActionPerformed
        reproducirSonido(getClass().getResource("/multimedia/tap-crisp.aif"));
        
        panelPartida.setVisible(true);
        panelReplicarPartida.setVisible(false);
        botonMenuEnablear(btnAbandonar, true);
        
        movimientosReplicar = null;
    }//GEN-LAST:event_btnRetomarPartidaActionPerformed

    private void btnSonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSonidoActionPerformed
        cambiarEstadoSonido();
;    }//GEN-LAST:event_btnSonidoActionPerformed

    private void btnReplicarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplicarPartidaActionPerformed
        reproducirSonido(getClass().getResource("/multimedia/tap-crisp.aif"));
        
        ReplicarPartida repPartida= new ReplicarPartida(this);
        repPartida.setVisible(true);
        this.setEnabled(false);    }//GEN-LAST:event_btnReplicarPartidaActionPerformed

    private void btnNuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaPartidaActionPerformed
        reproducirSonido(getClass().getResource("/multimedia/tap-crisp.aif"));
        
        NuevaPartida nuevaPartida=new NuevaPartida(this);
        nuevaPartida.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_btnNuevaPartidaActionPerformed

    private void btnGuardarJugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarJugadoresActionPerformed
        reproducirSonido(getClass().getResource("/multimedia/tap-crisp.aif"));
        if(sistema.getJugadores().isEmpty()){
            JOptionPane.showMessageDialog(this, "Aún no se han registrado jugadores","No hay jugadores",JOptionPane.INFORMATION_MESSAGE);

        }else{
        JFileChooser chooser = new JFileChooser();
        String ruta = null;
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos json", "json"));
                chooser.setAcceptAllFileFilterUsed(false);

        int op = chooser.showSaveDialog(this);
        if (op == JFileChooser.APPROVE_OPTION){
            ruta = chooser.getSelectedFile().toString();
            if(!ruta.endsWith(".json"))
                ruta += ".json";
        
            sistema.guardarJugadores(ruta);
        }
        }    }//GEN-LAST:event_btnGuardarJugadoresActionPerformed

    private void btnAgregarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarJugadorActionPerformed
        reproducirSonido(getClass().getResource("/multimedia/tap-crisp.aif"));
        
        AgregarJugador ventana= new AgregarJugador(this);
        this.setEnabled(false);
        ventana.setVisible(true);
    }//GEN-LAST:event_btnAgregarJugadorActionPerformed

    private void btnRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRankingActionPerformed
        reproducirSonido(getClass().getResource("/multimedia/tap-crisp.aif"));
        
        Ranking ventana= new Ranking(this);
        this.setEnabled(false);
        ventana.setVisible(true);     }//GEN-LAST:event_btnRankingActionPerformed

    private void cambiarEstadoSonido(){
        if(sonidoActivado)
            btnSonido.setIcon(new ImageIcon(getClass().getResource("/multimedia/icons8-mute_filled.png"))); 
        else 
            btnSonido.setIcon(new ImageIcon(getClass().getResource("/multimedia/icons8-room_sound_filled.png")));
       
        sonidoActivado = !sonidoActivado;
    }
    
    public void jugarPartida(Partida p){
        panelPartida.setVisible(true);
        panelReplicarPartida.setVisible(false);
        lblAzul.setVisible(true);
        lblRojo.setVisible(true);
        
        movimientosReplicar = null;
        lblAzul.setText(p.getJugadores()[0].getAlias());
        lblAzul.setForeground(new Color(255,0,0));
        
        enabledBotones(true);
        partida = p;
        panelTransparente.setVisible(false);
        
        if(p.getFormaDeTerminar() == 1){
            lblMovRest.setVisible(true);
            LblMovimientosRestantes.setVisible(true);
        }
        else{
            lblMovRest.setVisible(false);
            LblMovimientosRestantes.setVisible(false);
        }
        
        botonMenuEnablear(btnPasarTurno,false);
        botonMenuEnablear(btnAbandonar,true);
        
        jugadorActivo = 0;
        actualizar(null);
    }
    
    public void replicar(Partida p){
        panelPartida.setVisible(false);
        panelReplicarPartida.setVisible(true);
        panelTransparente.setVisible(false);
        lblAzul.setVisible(true);
        
        lblAzul.setText(p.getJugadores()[0].getAlias());
        lblAzul.setForeground(Color.RED);
        
        if(p.getFormaDeTerminar() == 1){
            lblMovRest.setVisible(true);
            LblMovimientosRestantes.setVisible(true);
        }
        
        movimientosReplicar = (ArrayList<String>)p.getMovimientos().clone();
        
        Date fecha = GregorianCalendar.getInstance().getTime();
        
        // Selecciona los jugadores del sistema para en el caso de que 
        // se retome la partida y haya que sumar el partida ganadas
        // se pueda hacer y se guarde sin problemas
        Jugador jugadores[] = new Jugador[2];
        int i = 0;
        for(Jugador j : sistema.getJugadores())
            for(Jugador ja : p.getJugadores())
                if(j.getAlias().equals(ja.getAlias()))
                    jugadores[i++] = j;
        
        partida = new Partida(jugadores, p.getFormaDeTerminar(), fecha, p.getCantMovimientos());
        
        botonMenuEnablear(btnSiguienteMov, true);
        botonMenuEnablear(btnRetomarPartida, true);
        
        enabledBotones(true);
        
        jugadorActivo = 0;
        actualizar(null);
    }
    
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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    
    private class ListenerBoton implements ActionListener {
        private int x,y;
        public ListenerBoton(int i, int j) {
            x = i;
            y = j;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            // Si se esta replicando una partida no queremos que el clcik
            if(movimientosReplicar == null)
                clickBoton(x, y);
        }
    }
   
    private void enabledBotones(boolean estado){
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 9; j++)
                botones[i][j].setEnabled(estado);
    }
    
    private void clickBoton(int fila, int columna) {
        if(botones[fila][columna].getBackground().equals(colorMovimientosValidos)){
            reproducirSonido(getClass().getResource("/multimedia/slide-paper.wav"));
            int mov = columna - fichaSeleccionada.getX();
            
            String movi = "A";
            if(mov == 1)
                movi = "D";
            else if(mov == -1)
                movi = "I";
            
            String movimiento = fichaSeleccionada.getNumero() + "," + movi;
            ArrayList<Integer> fichasValidas = partida.hacerMovimiento(movimiento, jugadorActivo);
            
            if(partida.debeTerminar(jugadorActivo == 0)){
                terminarPartida(false);
                return;
            }
            
            botonMenuEnablear(btnPasarTurno,!fichasValidas.isEmpty());
            
            if(fichasValidas.isEmpty()){
                jugadorActivo = jugadorActivo == 0 ? 1 : 0;
                lblAzul.setText(partida.getJugadores()[jugadorActivo].getAlias());
                lblAzul.setForeground(jugadorActivo == 0 ? Color.RED : Color.BLUE);
            }
            
            actualizar(fichasValidas.isEmpty() ? null : fichasValidas);
            fichaSeleccionada = null;
        }
        else if (botones[fila][columna].getBackground() != Color.GRAY){
            boolean esJugadorUno = botones[fila][columna].getBackground() == colorJugadorUnoValido;
            boolean esJugadorDos = botones[fila][columna].getBackground() == colorJugadorDosValido;
            
            limpiarCeldasVerdes();
            
            if(!((jugadorActivo == 0 && esJugadorUno) || (jugadorActivo == 1 && esJugadorDos))){
                reproducirSonido(getClass().getResource("/multimedia/beep-attention.aif"));
                return;
            }
            
            reproducirSonido(getClass().getResource("/multimedia/tap-warm.aif"));

            ArrayList<Integer> posPosibles = partida.posicionesPosibles(columna, fila, esJugadorUno);

            for(int i = 0; i < posPosibles.size(); i+=2)
                botones[posPosibles.get(i+1)][posPosibles.get(i)].setBackground(colorMovimientosValidos);

            int f = Integer.parseInt(botones[fila][columna].getText());

            fichaSeleccionada = new Ficha(columna, fila, f, esJugadorUno);
        }
        else
            reproducirSonido(getClass().getResource("/multimedia/beep-attention.aif"));
    }

    private void terminarPartida(boolean esReplicar){
        panelTransparente.setVisible(true);
        botonMenuEnablear(btnAbandonar,false);
        botonMenuEnablear(btnPasarTurno, false);
        lblMovRest.setVisible(false);
        LblMovimientosRestantes.setVisible(false);
        lblAzul.setVisible(false);
        lblRojo.setVisible(false);
        
        panelJuego.removeAll();
        inicializarTablero();
        
        Jugador ganador = partida.ganador();
        if(ganador == null){
            lblFelicitaciones.setText("Empate");
            lblAlias.setText("");
            lblGanador.setText("");
        }
        else{
            if(!esReplicar)
                ganador.setpGanadas(ganador.getpGanadas() + 1);

            reproducirSonido(getClass().getResource("/multimedia/ganar.wav"));
            lblFelicitaciones.setText("¡FELICITACIONES!");
            lblGanador.setText("Ha ganado la partida");
            lblAlias.setText(ganador.getAlias());
            lblAlias.setForeground(ganador.equals(partida.getJugadores()[0]) ? Color.RED : Color.BLUE);
        }
        
        if(!esReplicar)
            sistema.agregarPartida(partida);
    }
    
    private void botonMenuEnablear(JButton button, boolean enabled){
        button.setEnabled(enabled);
        button.setForeground(enabled ? colorBarra : Color.DARK_GRAY);
        button.setBorder(BorderFactory.createLineBorder(enabled ? colorBarra : Color.DARK_GRAY));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblMovimientosRestantes;
    private javax.swing.JButton btnAbandonar;
    private javax.swing.JButton btnAgregarJugador;
    private javax.swing.JButton btnGuardarJugadores;
    private javax.swing.JButton btnNuevaPartida;
    private javax.swing.JButton btnPasarTurno;
    private javax.swing.JButton btnRanking;
    private javax.swing.JButton btnReplicarPartida;
    private javax.swing.JButton btnRetomarPartida;
    private javax.swing.JButton btnSiguienteMov;
    private javax.swing.JButton btnSonido;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblAlias;
    private javax.swing.JLabel lblAzul;
    private javax.swing.JLabel lblFelicitaciones;
    private javax.swing.JLabel lblGanador;
    private static javax.swing.JLabel lblMovRest;
    private javax.swing.JLabel lblRojo;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelPartida;
    private javax.swing.JPanel panelReplicarPartida;
    private javax.swing.JPanel panelTransparente;
    // End of variables declaration//GEN-END:variables
}

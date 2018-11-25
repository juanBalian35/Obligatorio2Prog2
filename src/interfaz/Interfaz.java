
package interfaz;

import dominio.Ficha;
import dominio.Jugador;
import dominio.Partida;
import dominio.Sistema;
import dominio.Tablero;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Interfaz extends javax.swing.JFrame {
    private static final Color colorJugadorUnoValido = Color.decode("#ff3300");
    private static final Color colorJugadorUnoInvalido = Color.decode("#a66959");
    private static final Color colorJugadorDosValido = Color.decode("#0033cc");
    private static final Color colorJugadorDosInvalido = Color.decode("#475785");
    private static final Color colorMovimientosValidos = Color.decode("#50C878");
   
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
                botones[i][j].setForeground(Color.WHITE);
                botones[i][j].setEnabled(false);
                botones[i][j].setFont(new java.awt.Font("Heiti SC", 0, 22));
                botones[i][j].setMargin(new Insets(0,0, 0, 0)); 
                
            }
        }
    }

    public void reproducirSonido(String direccion){
        if(!sonidoActivado)
            return;
        
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(direccion)));
            clip.start();
        }
        catch(Exception ex){
            System.out.println("Error al reproducir archivo");
        }
    }
 
    void actualizar(ArrayList<Integer> fichasValidas){
        if(partida.getFormaDeTerminar() == 1)
            lblMovRest.setText("" + (partida.getCantMovimientos() - partida.getMovimientos().size()));
        
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 9; ++j){
                botones[i][j].setBackground(Color.GRAY);
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
                    botones[i][j].setBackground(Color.GRAY);
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
        jPanel3 = new javax.swing.JPanel();
        panelTransparente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblAlias = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        panelJuego = new javax.swing.JPanel();
        btnSonido = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuPartida = new javax.swing.JMenu();
        nuevaPartida = new javax.swing.JMenuItem();
        replicarPartida = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        cbSonido = new javax.swing.JCheckBoxMenuItem();
        guardarJugadores = new javax.swing.JMenuItem();
        jMenuJugador = new javax.swing.JMenu();
        agregarJugador = new javax.swing.JMenuItem();
        ranking = new javax.swing.JMenuItem();
        ayuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 23, 650, 510));
        setResizable(false);

        jPanel1.setLayout(null);

        LblMovimientosRestantes.setFont(new java.awt.Font("Heiti SC", 0, 18)); // NOI18N
        LblMovimientosRestantes.setText("Movimientos restantes:");
        jPanel1.add(LblMovimientosRestantes);
        LblMovimientosRestantes.setBounds(32, 417, 184, 24);

        panelPartida.setLayout(new java.awt.GridLayout(2, 0));

        btnAbandonar.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnAbandonar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-exit_sign.png"))); 
        btnAbandonar.setText("Abandonar");
        btnAbandonar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAbandonar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAbandonar.setEnabled(false);
        btnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonarActionPerformed(evt);
            }
        });
        panelPartida.add(btnAbandonar);

        btnPasarTurno.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnPasarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-double_right.png")));
        btnPasarTurno.setText("Pasar turno");
        btnPasarTurno.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnPasarTurno.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPasarTurno.setEnabled(false);
        btnPasarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasarTurnoActionPerformed(evt);
            }
        });
        panelPartida.add(btnPasarTurno);

        panelReplicarPartida.setMaximumSize(new java.awt.Dimension(180, 260));
        panelReplicarPartida.setMinimumSize(new java.awt.Dimension(180, 260));
        panelReplicarPartida.setPreferredSize(new java.awt.Dimension(180, 260));
        panelReplicarPartida.setSize(new java.awt.Dimension(180, 260));
        panelReplicarPartida.setLayout(new java.awt.GridLayout(2, 0));

        btnSiguienteMov.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnSiguienteMov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-advance.png"))); // NOI18N
        btnSiguienteMov.setText("Movimiento Siguiente");
        btnSiguienteMov.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSiguienteMov.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSiguienteMov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteMovActionPerformed(evt);
            }
        });
        panelReplicarPartida.add(btnSiguienteMov);

        btnRetomarPartida.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnRetomarPartida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-forward_arrow.png"))); // NOI18N
        btnRetomarPartida.setText("Retomar partida");
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
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelReplicarPartida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPartida, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelReplicarPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPartida, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
        );

        panelReplicarPartida.setVisible(false);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(437, 59, 180, 260);

        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);

        lblMovRest.setFont(new java.awt.Font("Heiti TC", 2, 18)); // NOI18N
        lblMovRest.setForeground(new java.awt.Color(0, 153, 0));
        jPanel1.add(lblMovRest);
        lblMovRest.setBounds(240, 420, 30, 20);

        jPanel3.setLayout(null);

        panelTransparente.setBackground(new Color(0,0,0,0));
        panelTransparente.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Heiti SC", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("¡FELICITACIONES!");
        panelTransparente.add(jLabel2);
        jLabel2.setBounds(0, 150, 370, 30);

        lblAlias.setFont(new java.awt.Font("Heiti SC", 0, 18)); // NOI18N
        lblAlias.setForeground(new java.awt.Color(255, 0, 51));
        lblAlias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlias.setText("Jugador");
        panelTransparente.add(lblAlias);
        lblAlias.setBounds(30, 190, 100, 30);

        jLabel4.setFont(new java.awt.Font("Heiti SC", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ha ganado la partida");
        panelTransparente.add(jLabel4);
        jLabel4.setBounds(140, 190, 210, 30);

        fondo.setBackground(new Color(0,0,0,0));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/sisi.png"))); // NOI18N
        panelTransparente.add(fondo);
        fondo.setBounds(0, 0, 370, 370);

        panelTransparente.setVisible(false);

        jPanel3.add(panelTransparente);
        panelTransparente.setBounds(0, 0, 370, 370);

        panelJuego.setLayout(new java.awt.GridLayout(8, 9));
        jPanel3.add(panelJuego);
        panelJuego.setBounds(0, 0, 370, 370);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(32, 29, 370, 370);

        btnSonido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-room_sound.png"))); // NOI18N
        btnSonido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSonidoActionPerformed(evt);
            }
        });
        jPanel1.add(btnSonido);
        btnSonido.setBounds(600, 400, 40, 40);

        jMenuPartida.setText("Partida");

        nuevaPartida.setText("Nueva partida...");
        nuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaPartidaActionPerformed(evt);
            }
        });
        jMenuPartida.add(nuevaPartida);

        replicarPartida.setText("Replicar partida...");
        replicarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replicarPartidaActionPerformed(evt);
            }
        });
        jMenuPartida.add(replicarPartida);

        jMenu1.setText("Más opciones");

        cbSonido.setSelected(true);
        cbSonido.setText("Sonido");
        cbSonido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSonidoActionPerformed(evt);
            }
        });
        jMenu1.add(cbSonido);

        guardarJugadores.setText("Guardar jugadores...");
        guardarJugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarJugadoresActionPerformed(evt);
            }
        });
        jMenu1.add(guardarJugadores);

        jMenuPartida.add(jMenu1);

        jMenuBar1.add(jMenuPartida);

        jMenuJugador.setText("Jugador");

        agregarJugador.setText("Agregar jugador...");
        agregarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarJugadorActionPerformed(evt);
            }
        });
        jMenuJugador.add(agregarJugador);

        ranking.setText("Ranking...");
        ranking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankingActionPerformed(evt);
            }
        });
        jMenuJugador.add(ranking);

        jMenuBar1.add(jMenuJugador);

        ayuda.setText("Ayuda");
        jMenuBar1.add(ayuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void agregarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarJugadorActionPerformed
        AgregarJugador ventana= new AgregarJugador(this);
        this.setEnabled(false);
        ventana.setVisible(true);
    }//GEN-LAST:event_agregarJugadorActionPerformed

    private void rankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankingActionPerformed
        Ranking ventana= new Ranking(this);
        this.setEnabled(false);
        ventana.setVisible(true);    }//GEN-LAST:event_rankingActionPerformed

    private void nuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaPartidaActionPerformed
        NuevaPartida nuevaPartida=new NuevaPartida(this);
        nuevaPartida.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_nuevaPartidaActionPerformed

    private void replicarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replicarPartidaActionPerformed
        ReplicarPartida repPartida=new ReplicarPartida(this);
        repPartida.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_replicarPartidaActionPerformed

    private void btnAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbandonarActionPerformed
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
            terminarPartida();
        }  
    }//GEN-LAST:event_btnAbandonarActionPerformed

    private void btnPasarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasarTurnoActionPerformed
        jugadorActivo = jugadorActivo == 0 ? 1 : 0;
        btnPasarTurno.setEnabled(false);
        actualizar(null);
    }//GEN-LAST:event_btnPasarTurnoActionPerformed

    private void btnSiguienteMovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteMovActionPerformed
        //SIGUIENTE PASO
        String mov = movimientosReplicar.remove(0);

        if(mov.startsWith("X")){
            btnSiguienteMov.setEnabled(false);
            btnRetomarPartida.setEnabled(false);
            movimientosReplicar = null;
            
            //Si abandona el jugador activo, el ganador es el otro4
            jugadorActivo = jugadorActivo == 0 ? 1 : 0;
            
            terminarPartida();
            return;
        }
        
        jugadorActivo = Integer.parseInt("" + mov.charAt(4));
        
        ArrayList<Integer> fichasValidas = partida.hacerMovimiento(mov, jugadorActivo);
        
        // Termino la partida
        if(movimientosReplicar.isEmpty()){
            btnSiguienteMov.setEnabled(false);
            btnRetomarPartida.setEnabled(false);
            movimientosReplicar = null;
            terminarPartida();
        }
        else
            actualizar(fichasValidas);                            
    }//GEN-LAST:event_btnSiguienteMovActionPerformed

    private void btnRetomarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetomarPartidaActionPerformed
        panelPartida.setVisible(true);
        panelReplicarPartida.setVisible(false);
        
        movimientosReplicar = null;
    }//GEN-LAST:event_btnRetomarPartidaActionPerformed

    private void btnSonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSonidoActionPerformed
        cambiarEstadoSonido();
;    }//GEN-LAST:event_btnSonidoActionPerformed

    private void cbSonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSonidoActionPerformed
        cambiarEstadoSonido();
    }//GEN-LAST:event_cbSonidoActionPerformed

    private void guardarJugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarJugadoresActionPerformed
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
    }//GEN-LAST:event_guardarJugadoresActionPerformed

    private void cambiarEstadoSonido(){
        if(sonidoActivado)
            btnSonido.setIcon(new ImageIcon(getClass().getResource("/multimedia/icons8-mute.png"))); 
        else 
            btnSonido.setIcon(new ImageIcon(getClass().getResource("/multimedia/icons8-room_sound.png")));
       
        sonidoActivado = !sonidoActivado;
        
        cbSonido.setSelected(sonidoActivado);
    }
    
    public void jugarPartida(Partida p){
        panelPartida.setVisible(true);
        panelReplicarPartida.setVisible(false);
        
        enabledBotones(true);
        partida = p;
        panelTransparente.setVisible(false);
        
        if(p.getFormaDeTerminar() == 1){
            lblMovRest.setVisible(true);
            LblMovimientosRestantes.setVisible(true);
        }
        
        btnPasarTurno.setEnabled(false);
        btnAbandonar.setEnabled(true);
        
        jugadorActivo = 0;
        actualizar(null);
    }
    
    public void replicar(Partida p){
        panelPartida.setVisible(false);
        panelReplicarPartida.setVisible(true);
        panelTransparente.setVisible(false);
        
        if(p.getFormaDeTerminar() == 1){
            lblMovRest.setVisible(true);
            LblMovimientosRestantes.setVisible(true);
        }
        
        movimientosReplicar = (ArrayList<String>)p.getMovimientos().clone();
        
        Date fecha = GregorianCalendar.getInstance().getTime();
        partida = new Partida(p.getJugadores(), p.getFormaDeTerminar(), fecha, p.getCantMovimientos());
        
        btnSiguienteMov.setEnabled(true);
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
            System.out.println(movimientosReplicar == null);
   
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
        reproducirSonido(getClass().getResource("/multimedia/tap-warm.aif").getPath());
        if(botones[fila][columna].getBackground().equals(colorMovimientosValidos)){
            int mov = columna - fichaSeleccionada.getX();
            
            String movi = "A";
            if(mov == 1)
                movi = "D";
            else if(mov == -1)
                movi = "I";
            
            String movimiento = fichaSeleccionada.getNumero() + "," + movi;
            ArrayList<Integer> fichasValidas = partida.hacerMovimiento(movimiento, jugadorActivo);
            
            if(fichasValidas.isEmpty())
                jugadorActivo = jugadorActivo == 0 ? 1 : 0;
            
            btnPasarTurno.setEnabled(!fichasValidas.isEmpty());
            
            actualizar(fichasValidas.isEmpty() ? null : fichasValidas);
            fichaSeleccionada = null;
            
            if(partida.debeTerminar(jugadorActivo == 0)){
                terminarPartida();
            }
        }
        else if (botones[fila][columna].getBackground() != Color.GRAY){
            boolean esJugadorUno = botones[fila][columna].getBackground() == colorJugadorUnoValido;
            boolean esJugadorDos = botones[fila][columna].getBackground() == colorJugadorDosValido;
            
            limpiarCeldasVerdes();
            
            if(!((jugadorActivo == 0 && esJugadorUno) || (jugadorActivo == 1 && esJugadorDos))){
                System.out.println("cual haces");
                return;
            }
            
            ArrayList<Integer> posPosibles = partida.posicionesPosibles(columna, fila, esJugadorUno);

            for(int i = 0; i < posPosibles.size(); i+=2)
                botones[posPosibles.get(i+1)][posPosibles.get(i)].setBackground(colorMovimientosValidos);

            int f = Integer.parseInt(botones[fila][columna].getText());

            partida.getTablero().mostrar(true);
            
            fichaSeleccionada = new Ficha(columna, fila, f, esJugadorUno);
        }
    }

    private void terminarPartida(){
        panelTransparente.setVisible(true);
        btnAbandonar.setEnabled(false);
        btnPasarTurno.setEnabled(false);
        lblMovRest.setVisible(false);
        LblMovimientosRestantes.setVisible(false);
                
        panelJuego.removeAll();
        inicializarTablero();
              
        Jugador ganador = partida.getJugadores()[jugadorActivo];
        ganador.setpGanadas(ganador.getpGanadas() + 1);

        lblAlias.setText(ganador.getAlias());
        lblAlias.setForeground(jugadorActivo == 0 ? Color.RED : Color.BLUE);
        
        sistema.agregarPartida(partida);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblMovimientosRestantes;
    private javax.swing.JMenuItem agregarJugador;
    private javax.swing.JMenu ayuda;
    private javax.swing.JButton btnAbandonar;
    private javax.swing.JButton btnPasarTurno;
    private javax.swing.JButton btnRetomarPartida;
    private javax.swing.JButton btnSiguienteMov;
    private javax.swing.JButton btnSonido;
    private javax.swing.JCheckBoxMenuItem cbSonido;
    private javax.swing.JLabel fondo;
    private javax.swing.JMenuItem guardarJugadores;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuJugador;
    private javax.swing.JMenu jMenuPartida;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAlias;
    private static javax.swing.JLabel lblMovRest;
    private javax.swing.JMenuItem nuevaPartida;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelPartida;
    private javax.swing.JPanel panelReplicarPartida;
    private javax.swing.JPanel panelTransparente;
    private javax.swing.JMenuItem ranking;
    private javax.swing.JMenuItem replicarPartida;
    // End of variables declaration//GEN-END:variables
}

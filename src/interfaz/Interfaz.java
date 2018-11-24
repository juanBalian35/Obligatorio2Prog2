
package interfaz;

import dominio.Ficha;
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panelReplicarPartida = new javax.swing.JPanel();
        btnSiguienteMov = new javax.swing.JButton();
        btnRetomarPartida = new javax.swing.JButton();
        panelPartida = new javax.swing.JPanel();
        btnIniciarPartida = new javax.swing.JButton();
        btnAbandonar = new javax.swing.JButton();
        btnPasarTurno = new javax.swing.JButton();
        lblMovRest = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
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

        jLabel1.setFont(new java.awt.Font("Heiti SC", 0, 18)); // NOI18N
        jLabel1.setText("Movimientos restantes:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(32, 417, 184, 24);

        btnSiguienteMov.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnSiguienteMov.setText("Mov.Siguiente");
        btnSiguienteMov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteMovActionPerformed(evt);
            }
        });

        btnRetomarPartida.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnRetomarPartida.setText("Retomar partida");
        btnRetomarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetomarPartidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelReplicarPartidaLayout = new javax.swing.GroupLayout(panelReplicarPartida);
        panelReplicarPartida.setLayout(panelReplicarPartidaLayout);
        panelReplicarPartidaLayout.setHorizontalGroup(
            panelReplicarPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReplicarPartidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelReplicarPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelReplicarPartidaLayout.createSequentialGroup()
                        .addComponent(btnSiguienteMov)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReplicarPartidaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRetomarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelReplicarPartidaLayout.setVerticalGroup(
            panelReplicarPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelReplicarPartidaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSiguienteMov)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRetomarPartida)
                .addContainerGap())
        );

        btnIniciarPartida.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnIniciarPartida.setText("Iniciar partida");
        btnIniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarPartidaActionPerformed(evt);
            }
        });

        btnAbandonar.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnAbandonar.setText("Abandonar");
        btnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonarActionPerformed(evt);
            }
        });

        btnPasarTurno.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        btnPasarTurno.setText("Pasar turno");
        btnPasarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasarTurnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPartidaLayout = new javax.swing.GroupLayout(panelPartida);
        panelPartida.setLayout(panelPartidaLayout);
        panelPartidaLayout.setHorizontalGroup(
            panelPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPartidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIniciarPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAbandonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPasarTurno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPartidaLayout.setVerticalGroup(
            panelPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPartidaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIniciarPartida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbandonar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPasarTurno)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelReplicarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(panelReplicarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(34, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panelReplicarPartida.setVisible(false);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(437, 109, 171, 132);

        lblMovRest.setFont(new java.awt.Font("Heiti TC", 2, 18)); // NOI18N
        lblMovRest.setForeground(new java.awt.Color(0, 153, 0));
        jPanel1.add(lblMovRest);
        lblMovRest.setBounds(220, 420, 30, 20);

        panelJuego.setLayout(new java.awt.GridLayout(8, 9));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(32, 29, 370, 370);

        btnSonido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/icons8-medium_volume.png"))); // NOI18N
        btnSonido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSonidoActionPerformed(evt);
            }
        });
        jPanel1.add(btnSonido);
        btnSonido.setBounds(560, 390, 40, 40);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnIniciarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarPartidaActionPerformed
        
    }//GEN-LAST:event_btnIniciarPartidaActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAbandonarActionPerformed

    private void btnPasarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasarTurnoActionPerformed
        jugadorActivo = jugadorActivo == 0 ? 1 : 0;
        btnPasarTurno.setEnabled(false);
        actualizar(null);
    }//GEN-LAST:event_btnPasarTurnoActionPerformed

    private void btnSiguienteMovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteMovActionPerformed
        //SIGUIENTE PASO
        String mov = movimientosReplicar.remove(0);
        
        jugadorActivo = Integer.parseInt("" + mov.charAt(4));
        
        ArrayList<Integer> fichasValidas = partida.hacerMovimiento(mov, jugadorActivo);
        
        // Termino la partida
        if(movimientosReplicar.isEmpty()){
            actualizar(new ArrayList<>());
            btnSiguienteMov.setEnabled(false);
            movimientosReplicar = null;
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
            btnSonido.setIcon(new ImageIcon(getClass().getResource("/interfaz/icons8-mute.png"))); 
        else 
            btnSonido.setIcon(new ImageIcon(getClass().getResource("/interfaz/icons8-medium_volume.png")));
       
        sonidoActivado = !sonidoActivado;
        
        cbSonido.setSelected(sonidoActivado);
    }
    
    public void jugarPartida(Partida p){
        panelPartida.setVisible(true);
        panelReplicarPartida.setVisible(false);
        
        enabledBotones(true);
        partida = p;
        
        btnPasarTurno.setEnabled(false);
        jugadorActivo = 0;
        actualizar(null);
    }
    
    public void replicar(Partida p){
        panelPartida.setVisible(false);
        panelReplicarPartida.setVisible(true);
        
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
       reproducirSonido("src/tap-warm.aif");
        if(botones[fila][columna].getBackground().equals(colorMovimientosValidos)){
            int mov = columna - fichaSeleccionada.getX();
            
            String movi = "A";
            if(mov == 1)
                movi = "D";
            else if(mov == -1)
                movi = "I";
            
            String movimiento = fichaSeleccionada.getNumero() + "," + movi;
            ArrayList<Integer> fichasValidas = partida.hacerMovimiento(movimiento, jugadorActivo);
            
            if(partida.debeTerminar(jugadorActivo == 0)){
                //TERMINO LA PARTIDA
                enabledBotones(false);
                
                sistema.agregarPartida(partida);
            }
            
            if(fichasValidas.isEmpty())
                jugadorActivo = jugadorActivo == 0 ? 1 : 0;
            
            btnPasarTurno.setEnabled(!fichasValidas.isEmpty());
            
            actualizar(fichasValidas.isEmpty() ? null : fichasValidas);
            fichaSeleccionada = null;
        }
        else if (botones[fila][columna].getBackground() != Color.GRAY){
            boolean esJugadorUno = botones[fila][columna].getBackground() == colorJugadorUnoValido;
            boolean esJugadorDos = botones[fila][columna].getBackground() == colorJugadorDosValido;
            
            limpiarCeldasVerdes();
            
            if(!((jugadorActivo == 0 && esJugadorUno) || (jugadorActivo == 1 && esJugadorDos))){
                System.out.println("cual haces");
                return;
            }
            
            ArrayList<Integer> posPos = partida.posicionesPosibles(columna, fila, esJugadorUno);

            for(int i = 0; i < posPos.size(); i+=2)
                botones[posPos.get(i+1)][posPos.get(i)].setBackground(colorMovimientosValidos);

            int f = Integer.parseInt(botones[fila][columna].getText());

            partida.getTablero().mostrar(true);
            
            fichaSeleccionada = new Ficha(columna, fila, f, esJugadorUno);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem agregarJugador;
    private javax.swing.JMenu ayuda;
    private javax.swing.JButton btnAbandonar;
    private javax.swing.JButton btnIniciarPartida;
    private javax.swing.JButton btnPasarTurno;
    private javax.swing.JButton btnRetomarPartida;
    private javax.swing.JButton btnSiguienteMov;
    private javax.swing.JButton btnSonido;
    private javax.swing.JCheckBoxMenuItem cbSonido;
    private javax.swing.JMenuItem guardarJugadores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuJugador;
    private javax.swing.JMenu jMenuPartida;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private static javax.swing.JLabel lblMovRest;
    private javax.swing.JMenuItem nuevaPartida;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelPartida;
    private javax.swing.JPanel panelReplicarPartida;
    private javax.swing.JMenuItem ranking;
    private javax.swing.JMenuItem replicarPartida;
    // End of variables declaration//GEN-END:variables

}

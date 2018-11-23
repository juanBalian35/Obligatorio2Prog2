/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;


import dominio.Jugador;
import dominio.Partida;
import dominio.Sistema;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;

/**
 *
 * @author agustinintroini
 */
public class NuevaPartida extends javax.swing.JFrame {
    int azulElementoNoValido = -1,rojoElementoNoValido = -1;
    Color colorListaAzul, colorListaRoja;
    Interfaz interfaz;
    
    // Hace que el elemento en el indice que sea apropiado no sea clickeable.
    class SelectionModelInvalidar extends DefaultListSelectionModel {
        boolean esListaAzul;
        
        SelectionModelInvalidar(boolean esListaAzul){
            this.esListaAzul = esListaAzul;
        }
        
        @Override
        public void setSelectionInterval(int index0, int index1) {
            int indiceNoValido = esListaAzul ? azulElementoNoValido : rojoElementoNoValido;
            
            if(index0 == indiceNoValido && index1 == indiceNoValido)
                super.setSelectionInterval(-1, -1);
            else
                super.setSelectionInterval(index0, index1);
        }
    }
    
    // Hace que el elemento en el indice que sea apropiado se vea distinto.
    private class ListCellRenderer extends DefaultListCellRenderer {
        boolean esListaAzul;
        
        ListCellRenderer(boolean esListaAzul){
            this.esListaAzul = esListaAzul;
        }
        
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component comp = super.getListCellRendererComponent(list, value, index, false, false);
            comp.setEnabled(true);
            
            int indiceNoValido = esListaAzul ? azulElementoNoValido : rojoElementoNoValido;
            
            if(index == indiceNoValido)
                comp.setEnabled(false);
            else if(isSelected)
                comp.setBackground(esListaAzul ? colorListaAzul : colorListaRoja);
            
            return comp;
        }
    }
    public NuevaPartida(Interfaz interfaz) {
        initComponents();
        
        this.interfaz = interfaz;
        
        colorListaAzul = jListAzul.getSelectionBackground();
        colorListaRoja = jListRojo.getSelectionBackground();
        
        if(Sistema.getJugadores().isEmpty()){
            jPanel1.setVisible(false);
            btnIniciarPartida.setEnabled(false);
        }
        
        llenarLista(Sistema.getJugadores(),jListAzul);
        llenarLista(Sistema.getJugadores(),jListRojo);
        
        ListSelectionListener listener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    boolean esListaAzul = ((JList)e.getSource()).getSelectionBackground().equals(colorListaAzul);
                    
                    int seleccion = ((esListaAzul ? jListAzul : jListRojo).getSelectedIndex());

                    if(esListaAzul)
                        rojoElementoNoValido = seleccion;
                    else
                        azulElementoNoValido = seleccion;
                    
                    (esListaAzul ? jListRojo : jListAzul).repaint();
                }
            }
        };
        
        jListAzul.setSelectionModel(new SelectionModelInvalidar(true));
        jListAzul.setCellRenderer(new ListCellRenderer(true));
        jListRojo.setSelectionModel(new SelectionModelInvalidar(false));
        jListRojo.setCellRenderer(new ListCellRenderer(false));
        jListAzul.addListSelectionListener(listener);
        jListRojo.addListSelectionListener(listener);
    }
    
    public final void llenarLista (ArrayList<Jugador> lista, JList list){
        DefaultListModel listModel = new DefaultListModel();
        
        for(int i=0; i<lista.size(); i++)
            listModel.add(i, lista.get(i).getAlias());
        
        list.setModel(listModel);
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
        jLabel2 = new javax.swing.JLabel();
        jCB_FTerminar = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        spinnerMovimientos = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jSPRojo = new javax.swing.JScrollPane();
        jListRojo = new javax.swing.JList<>();
        jSPAzul = new javax.swing.JScrollPane();
        jListAzul = new javax.swing.JList<>();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnIniciarPartida = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar jugador");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(0, 23, 500, 380));
        setMinimumSize(new java.awt.Dimension(500, 380));
        setName("NuevaPartida"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Heiti SC", 0, 14)); // NOI18N
        jLabel2.setText("Forma de terminar:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(60, 170, 140, 15);

        jCB_FTerminar.setFont(new java.awt.Font("Heiti SC", 0, 10)); // NOI18N
        jCB_FTerminar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cantidad de movimientos", "Alcanzar con una ficha el lado contrario", "Alcanzar con todas las fichas el lado contrario" }));
        jCB_FTerminar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_FTerminarItemStateChanged(evt);
            }
        });
        jPanel1.add(jCB_FTerminar);
        jCB_FTerminar.setBounds(200, 160, 240, 27);

        jLabel3.setFont(new java.awt.Font("Heiti SC", 0, 14)); // NOI18N
        jLabel3.setText("Cantidad de movimientos:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(60, 210, 190, 15);

        spinnerMovimientos.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N
        spinnerMovimientos.setModel(new javax.swing.SpinnerNumberModel(10, 10, 120, 1));
        ((DefaultEditor) spinnerMovimientos.getEditor()).getTextField().setEditable(false);
        jPanel1.add(spinnerMovimientos);
        spinnerMovimientos.setBounds(250, 200, 52, 26);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Seleccionar jugadores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Heiti SC", 0, 13))); // NOI18N
        jPanel2.setLayout(null);

        jSPRojo.setBackground(javax.swing.UIManager.getDefaults().getColor("window"));
        jSPRojo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 51, 51), new java.awt.Color(255, 51, 0)), "Jugador rojo", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Heiti SC", 0, 13), new java.awt.Color(255, 51, 0))); // NOI18N
        jSPRojo.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N

        jListRojo.setBackground(javax.swing.UIManager.getDefaults().getColor("window"));
        jListRojo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jListRojo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListRojo.setSelectionBackground(new java.awt.Color(255, 51, 0));
        jListRojo.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListRojoValueChanged(evt);
            }
        });
        jSPRojo.setViewportView(jListRojo);

        jPanel2.add(jSPRojo);
        jSPRojo.setBounds(20, 20, 180, 100);

        jSPAzul.setBackground(javax.swing.UIManager.getDefaults().getColor("window"));
        jSPAzul.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 51, 204), new java.awt.Color(0, 51, 204)), "Jugador azul", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Heiti SC", 0, 13), new java.awt.Color(0, 51, 204))); // NOI18N
        jSPAzul.setFont(new java.awt.Font("Heiti SC", 0, 13)); // NOI18N

        jListAzul.setBackground(javax.swing.UIManager.getDefaults().getColor("window"));
        jListAzul.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jListAzul.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListAzul.setSelectionBackground(new java.awt.Color(0, 51, 204));
        jSPAzul.setViewportView(jListAzul);

        jPanel2.add(jSPAzul);
        jSPAzul.setBounds(210, 20, 180, 100);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(40, 10, 420, 140);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 70, 500, 240);

        btnSalir.setFont(new java.awt.Font("Heiti SC", 0, 14)); // NOI18N
        btnSalir.setText("Cancelar");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(260, 330, 140, 40);

        jLabel1.setFont(new java.awt.Font("Heiti SC", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Deben existir al menos dos jugadores para iniciar la partida");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 150, 490, 30);

        btnIniciarPartida.setFont(new java.awt.Font("Heiti SC", 0, 14)); // NOI18N
        btnIniciarPartida.setText("Iniciar partida");
        btnIniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarPartidaActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciarPartida);
        btnIniciarPartida.setBounds(110, 330, 140, 40);

        jPanel3.setBackground(java.awt.SystemColor.windowBorder);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Heiti TC", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nueva partida");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(0, 0, 500, 50);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 0, 500, 50);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        interfaz.setEnabled(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIniciarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarPartidaActionPerformed
        int formaDeTerminar = jCB_FTerminar.getSelectedIndex()+1;
        Date fecha = GregorianCalendar.getInstance().getTime();
        Jugador jugadores[] = new Jugador[2];
        
        if(jListRojo.isSelectionEmpty()||jListAzul.isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "Debe seleccionar dos jugadores","Atención",JOptionPane.WARNING_MESSAGE);
        }else{
            jugadores[0] = Sistema.getJugadores().get(jListRojo.getSelectedIndex());
            jugadores[1] = Sistema.getJugadores().get(jListAzul.getSelectedIndex());

            Partida p = new Partida (jugadores,formaDeTerminar,fecha, (int) spinnerMovimientos.getValue());
            interfaz.jugarPartida(p);
            interfaz.setEnabled(true);
            
            this.dispose();
        }
    }//GEN-LAST:event_btnIniciarPartidaActionPerformed

    private void jListRojoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListRojoValueChanged
 
    }//GEN-LAST:event_jListRojoValueChanged

    private void jCB_FTerminarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_FTerminarItemStateChanged
        if(jCB_FTerminar.getSelectedIndex()!=0){
            spinnerMovimientos.setEnabled(false);
            jLabel3.setEnabled(false);
        }else{
            spinnerMovimientos.setEnabled(true);
            jLabel3.setEnabled(true);
        }
    }//GEN-LAST:event_jCB_FTerminarItemStateChanged


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevaPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevaPartida(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarPartida;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> jCB_FTerminar;
    private static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jListAzul;
    private javax.swing.JList<String> jListRojo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jSPAzul;
    private javax.swing.JScrollPane jSPRojo;
    private javax.swing.JSpinner spinnerMovimientos;
    // End of variables declaration//GEN-END:variables
}

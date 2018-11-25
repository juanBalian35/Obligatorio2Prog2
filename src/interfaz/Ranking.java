/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import dominio.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author agustinintroini
 */
public class Ranking extends javax.swing.JFrame {
   Interfaz interfaz;
    public Ranking(Interfaz interfaz) {
        initComponents();
        this.interfaz = interfaz;

        String[] nombreColumnas = new String [] {
            "N°", "Nombre", "Alias", "Edad", "N° de ganadas"
        };
        
        
        ArrayList<Jugador> jugadores = interfaz.getSistema().getJugadores();
        String datos[][] = new String[jugadores.size()][5];
        Collections.sort(jugadores);
      
        if (!jugadores.isEmpty()) {
            Jugador jugadorAnterior = jugadores.get(0);
            int posicion = 1;
            for (int i = 0; i < jugadores.size(); i++) {
                Jugador j1 = jugadores.get(i);
                if (jugadorAnterior.getpGanadas() == j1.getpGanadas()){
                   datos[i][0] = Integer.toString(posicion);
                   datos[i][1] = j1.getNombre();
                   datos[i][2] = j1.getAlias();
                   datos[i][3] = Integer.toString(j1.getEdad());
                   datos[i][4] = Integer.toString(j1.getpGanadas());
                }
                else{
                    posicion = i + 1;
                    datos[i][0] = Integer.toString(posicion);
                    datos[i][1] = j1.getNombre();
                    datos[i][2] = j1.getAlias();
                    datos[i][3] = Integer.toString(j1.getEdad());
                    datos[i][4] = Integer.toString(j1.getpGanadas());
                }
                jugadorAnterior = jugadores.get(i);
            }
        };
        
        DefaultTableModel modelo = new DefaultTableModel(datos, nombreColumnas){
                Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        jTabla.setModel(modelo);
        
        jTabla.getColumnModel().getColumn(0).setPreferredWidth(10);
        for(int i = 0; i < jTabla.getColumnModel().getColumnCount(); ++i)
            jTabla.getColumnModel().getColumn(i).setResizable(false);
        
        if(interfaz.getSistema().getJugadores().isEmpty()){
            jSP.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSP = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar jugador");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(0, 23, 472, 198));
        setMinimumSize(new java.awt.Dimension(472, 198));
        setName("agregarJugador"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(null);

        btnSalir.setFont(new java.awt.Font("Heiti SC", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(190, 150, 80, 40);

        jLabel4.setFont(new java.awt.Font("Heiti TC", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ranking de jugadores");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 20, 470, 30);

        jTabla.setFont(new java.awt.Font("Heiti SC", 0, 12)); // NOI18N
        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jTabla.getTableHeader().setReorderingAllowed(false);
        jSP.setViewportView(jTabla);

        getContentPane().add(jSP);
        jSP.setBounds(20, 50, 430, 90);

        jLabel1.setFont(new java.awt.Font("Heiti SC", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Aún no se han registrado jugadores");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 90, 470, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        interfaz.setEnabled(true);
        dispose();
        
    }//GEN-LAST:event_btnSalirActionPerformed
/*public static void agregar(String matriz[][]){
    jTabla.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "nro", "nombre", "alias", "edad", "cant ganadas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
}*/
   

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
            java.util.logging.Logger.getLogger(Ranking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ranking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ranking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ranking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ranking(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jSP;
    private static javax.swing.JTable jTabla;
    // End of variables declaration//GEN-END:variables
}

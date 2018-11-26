package interfaz;

import dominio.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;

/*
 * Creado por:
 *   Juan Balian - 211150
 *   Agustín Introini - 211064
 * */
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
        }
        
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

        jPanel1 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        jSP = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

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

        jPanel1.setBackground(Interfaz.colorFondo);
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(Interfaz.colorBotonesVacios));
        jPanel1.setPreferredSize(new java.awt.Dimension(472, 198));
        jPanel1.setSize(new java.awt.Dimension(472, 198));
        jPanel1.setLayout(null);

        btnSalir.setBackground(Interfaz.colorFondo);
        btnSalir.setFont(new java.awt.Font("Heiti SC", 0, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/icons8-reply_arrow.png"))); // NOI18N
        btnSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnSalir.setContentAreaFilled(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir);
        btnSalir.setBounds(180, 140, 110, 50);

        jSP.setBackground(Interfaz.colorFondo);
        jSP.setForeground(new java.awt.Color(255, 255, 255));

        jTabla.setBackground(Interfaz.colorFondo);
        jTabla.setFont(new java.awt.Font("Heiti SC", 0, 12)); // NOI18N
        jTabla.setForeground(new java.awt.Color(255, 255, 255));
        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jTabla.setSelectionBackground(Interfaz.colorBarra);
        jTabla.getTableHeader().setReorderingAllowed(false);
        jSP.setViewportView(jTabla);
        //jTabla.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel1.add(jSP);
        jSP.setBounds(20, 50, 430, 80);

        jLabel1.setBackground(Interfaz.colorFondo);
        jLabel1.setFont(new java.awt.Font("Heiti SC", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Aún no se han registrado jugadores");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 90, 470, 30);

        jPanel5.setBackground(Interfaz.colorBarra);
        jPanel5.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel5.setLayout(null);

        jLabel5.setBackground(Interfaz.colorBarra);
        jLabel5.setFont(new java.awt.Font("Heiti TC", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ranking de jugadores");
        jPanel5.add(jLabel5);
        jLabel5.setBounds(0, 0, 470, 50);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(0, 0, 470, 50);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 472, 198);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jSP;
    private static javax.swing.JTable jTabla;
    // End of variables declaration//GEN-END:variables
}

[1mdiff --git a/jugadores.txt b/jugadores.txt[m
[1mindex 6ffae4c..ca7d6ff 100644[m
Binary files a/jugadores.txt and b/jugadores.txt differ
[1mdiff --git a/partidas.txt b/partidas.txt[m
[1mindex 4589551..f83422a 100644[m
Binary files a/partidas.txt and b/partidas.txt differ
[1mdiff --git a/src/interfaz/Interfaz.java b/src/interfaz/Interfaz.java[m
[1mindex e999c68..bddf4fe 100644[m
[1m--- a/src/interfaz/Interfaz.java[m
[1m+++ b/src/interfaz/Interfaz.java[m
[36m@@ -732,16 +732,17 @@[m [mpublic class Interfaz extends javax.swing.JFrame {[m
             lblGanador.setText("");[m
         }[m
         else{[m
[31m-            ganador.setpGanadas(ganador.getpGanadas() + 1);[m
[32m+[m[32m            if(!esReplicar)[m
[32m+[m[32m                ganador.setpGanadas(ganador.getpGanadas() + 1);[m
[32m+[m[41m            [m
             lblFelicitaciones.setText("¡FELICITACIONES!");[m
             lblGanador.setText("Ha ganado la partida");[m
             lblAlias.setText(ganador.getAlias());[m
             lblAlias.setForeground(jugadorActivo == 0 ? Color.RED : Color.BLUE);[m
         }[m
         [m
[31m-        if(!esReplicar){[m
[32m+[m[32m        if(!esReplicar)[m
             sistema.agregarPartida(partida);[m
[31m-        }[m
     }[m
     // Variables declaration - do not modify//GEN-BEGIN:variables[m
     private javax.swing.JLabel LblMovimientosRestantes;[m

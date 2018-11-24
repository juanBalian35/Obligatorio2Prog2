
package dominio;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.JLabel;

/*
 * Creado por:
 *   Juan Balian - 211150
 *   Agustín Introini - 211064
 * */

public class Sistema {
    private static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private static ArrayList<Partida> partidas = new ArrayList<>();
    
    public Sistema(){
        try{
            FileInputStream fff = new FileInputStream("partidas.txt");
            BufferedInputStream bbb = new BufferedInputStream(fff);
            ObjectInputStream sss = new ObjectInputStream(bbb);
            Object s = sss.readObject();
            partidas = (ArrayList<Partida>)s;
        }
        catch(java.io.EOFException e){
            System.out.println("goodbye");
        }
        catch(Exception e){
            //TODO: whatefac hacer en las exepciones?
            e.printStackTrace();
        }
    }
    
    public ArrayList<Partida> getPartidas() {
        return partidas;
    }
    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }
    
    public void agregarPartida(Partida partida){
        partidas.add(partida);
        guardarPartida();
    }

    public static ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public static void registrarJugador(Jugador jugador){
        jugadores.add(jugador);
    }

    /*public void jugar(Jugador jugador1, Jugador jugador2, int formaDeTerminar, Date fecha){
        Partida partida = new Partida(jugador1, jugador2, formaDeTerminar, fecha);
        //partidas.add(partida.comenzar());
    }*/

    public void replicar(){
    }
public static String[][] partidas(){
    String matriz[][] = new String[partidas.size()][4];
       
       
      Collections.sort(partidas);
      
        if (!partidas.isEmpty()) {
         
            for (int i = 0; i < partidas.size(); i++) {
                
                  
                   matriz[i][0]= partidas.get(i).getJugadores()[0].getAlias();
                   matriz[i][1]= partidas.get(i).getJugadores()[1].getAlias();
                   matriz[i][2]= partidas.get(i).getDia();
                   matriz[i][3]=partidas.get(i).getHora();
                  
                   
           
          
                }

                
            }
 return matriz;
}
    
    public static String[][] ranking(){
   
    
       
       
        String matriz[][] = new String[jugadores.size()][5];
       
       
      Collections.sort(jugadores);
      
        if (!jugadores.isEmpty()) {
            
          
            
            Jugador jugadorAnterior = jugadores.get(0);
            int posicion = 1;
            for (int i = 0; i < jugadores.size(); i++) {
                Jugador j1 = jugadores.get(i);
                if (jugadorAnterior.getpGanadas() == j1.getpGanadas()){
                  
                   matriz[i][0]=Integer.toString(posicion);
                   matriz[i][1]= j1.getNombre();
                   matriz[i][2]= j1.getAlias();
                   matriz[i][3]= Integer.toString(j1.getEdad());
                   matriz[i][4]= Integer.toString(j1.getpGanadas());
                   
                }else {
                    posicion = i + 1;
                   matriz[i][0]=Integer.toString(posicion);
                   matriz[i][1]= j1.getNombre();
                   matriz[i][2]= j1.getAlias();
                   matriz[i][3]= Integer.toString(j1.getEdad());
                   matriz[i][4]= Integer.toString(j1.getpGanadas());
          
                }

                jugadorAnterior = jugadores.get(i);
            }
           
        
    
    }    
        return matriz;
}

    public Jugador buscarJugador(String alias){
        for(Jugador jugador : jugadores)
            if(jugador.getAlias().equals(alias))
                return jugador;

        return null;
    }
    
    private void guardarPartida(){
        System.out.println("bad b");
        try{
            File yourFile = new File("partidas.txt");
            yourFile.createNewFile(); // if file already exists will do nothing 
            FileOutputStream ff = new FileOutputStream(yourFile);
            BufferedOutputStream b = new BufferedOutputStream(ff);
            ObjectOutputStream ss = new ObjectOutputStream(b);
            ss.writeObject(partidas);
            ss.flush();
            ss.close();
        }
        catch(Exception e){
            //TODO: whatefac hacer en las exepciones?
            System.out.println("ke");
        }
    }
}


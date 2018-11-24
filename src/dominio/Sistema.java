
package dominio;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * Creado por:
 *   Juan Balian - 211150
 *   Agustín Introini - 211064
 * */

public class Sistema {
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private ArrayList<Partida> partidas = new ArrayList<>();
    
    public Sistema(){
        partidas = (ArrayList<Partida>)leerArchivo("partidas.txt");
        jugadores = (ArrayList<Jugador>)leerArchivo("jugadores.txt");
    }
    
    public String[][] partidas(){
        String matriz[][] = new String[partidas.size()][4];
        Collections.sort(partidas);

        if (!partidas.isEmpty()) {
            for (int i = 0; i < partidas.size(); i++) {
                matriz[i][0] = partidas.get(i).getJugadores()[0].getAlias();
                matriz[i][1] = partidas.get(i).getJugadores()[1].getAlias();
                matriz[i][2] = partidas.get(i).getDia();
                matriz[i][3] = partidas.get(i).getHora();
            }
        }
        return matriz;
    }
    
    public String[][] ranking(){
        String matriz[][] = new String[jugadores.size()][5];
        Collections.sort(jugadores);
      
        if (!jugadores.isEmpty()) {
            Jugador jugadorAnterior = jugadores.get(0);
            int posicion = 1;
            for (int i = 0; i < jugadores.size(); i++) {
                Jugador j1 = jugadores.get(i);
                if (jugadorAnterior.getpGanadas() == j1.getpGanadas()){
                   matriz[i][0] = Integer.toString(posicion);
                   matriz[i][1] = j1.getNombre();
                   matriz[i][2] = j1.getAlias();
                   matriz[i][3] = Integer.toString(j1.getEdad());
                   matriz[i][4] = Integer.toString(j1.getpGanadas());
                }
                else{
                    posicion = i + 1;
                    matriz[i][0] = Integer.toString(posicion);
                    matriz[i][1] = j1.getNombre();
                    matriz[i][2] = j1.getAlias();
                    matriz[i][3] = Integer.toString(j1.getEdad());
                    matriz[i][4] = Integer.toString(j1.getpGanadas());
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
    
    public void guardar(){
        escribirArchivo("partidas.txt", partidas);
        escribirArchivo("jugadores.txt", jugadores);
    }
    
    public void guardarJugadores(String ruta){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Jugador jugador : jugadores){
            JSONObject jugadoSr = new JSONObject();
            
            jugadoSr.put("nombre", jugador.getNombre());
            jugadoSr.put("alias", jugador.getAlias());
            jugadoSr.put("edad", jugador.getEdad());
            jugadoSr.put("pGanadas", jugador.getpGanadas());
            
            array.add(jugadoSr);
        }
        //object.put("jugadores", array);
        try (FileWriter file = new FileWriter(ruta)) {
            file.write(array.toJSONString());
            System.out.println(array.toJSONString());
	}
        catch(Exception e){
            //TODO: whatefac hacer en las exepciones?
            System.out.println("ke");
        }
    }
    
    private void escribirArchivo(String ruta, Object objeto){
        try{
            File yourFile = new File(ruta);
            yourFile.createNewFile();
            FileOutputStream ff = new FileOutputStream(yourFile);
            BufferedOutputStream b = new BufferedOutputStream(ff);
            ObjectOutputStream ss = new ObjectOutputStream(b);
            ss.writeObject(objeto);
            ss.flush();
            ss.close();
        }
        catch(Exception e){
            //TODO: whatefac hacer en las exepciones?
            System.out.println("ke");
        }
    }
    
    private Object leerArchivo(String ruta){
        try{
            FileInputStream fff = new FileInputStream(ruta);
            BufferedInputStream bbb = new BufferedInputStream(fff);
            ObjectInputStream sss = new ObjectInputStream(bbb);
            return sss.readObject();
        }
        catch(Exception e){
            //TODO: whatefac hacer en las exepsssssssssssssssssss
        }
        return null;
    }
    
    public ArrayList<Partida> getPartidas() {
        return partidas;
    }
    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }
    
    public void agregarPartida(Partida partida){
        partidas.add(partida);
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void registrarJugador(Jugador jugador){
        jugadores.add(jugador);
    }
}


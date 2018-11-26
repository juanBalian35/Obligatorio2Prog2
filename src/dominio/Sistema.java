package dominio;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * Creado por:
 *   Juan Balian - 211150
 *   Agustín Introini - 211064
 * */

public class Sistema {
    private ArrayList<Jugador> jugadores;
    private ArrayList<Partida> partidas;
    
    public Sistema(){
        partidas = (ArrayList<Partida>)leerArchivo("partidas.txt");
        if(partidas == null)
            partidas = new ArrayList<>();
        jugadores = (ArrayList<Jugador>)leerArchivo("jugadores.txt");
        if(jugadores == null)
             jugadores = new ArrayList<>();
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
        JSONArray array = new JSONArray();
        for(Jugador jugador : jugadores){
            JSONObject jugadorJSON = new JSONObject();
            
            jugadorJSON.put("nombre", jugador.getNombre());
            jugadorJSON.put("alias", jugador.getAlias());
            jugadorJSON.put("edad", jugador.getEdad());
            jugadorJSON.put("pGanadas", jugador.getpGanadas());
            
            array.add(jugadorJSON);
        }
        
        try (FileWriter file = new FileWriter(ruta)) {
            file.write(array.toJSONString());
	}
        catch(Exception e){}
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
        catch(Exception e){}
    }
    
    private Object leerArchivo(String ruta){
        try{
            FileInputStream fff = new FileInputStream(ruta);
            BufferedInputStream bbb = new BufferedInputStream(fff);
            ObjectInputStream sss = new ObjectInputStream(bbb);
            return sss.readObject();
        }
        catch(Exception e){}
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


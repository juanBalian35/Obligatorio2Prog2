
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
        try{
            FileInputStream fff = new FileInputStream("partidas.txt");
            BufferedInputStream bbb = new BufferedInputStream(fff);
            ObjectInputStream sss = new ObjectInputStream(bbb);
            Object s = sss.readObject();
            partidas = (ArrayList<Partida>)s;
        }
        catch(Exception e){
            //TODO: whatefac hacer en las exepsssssssssssssssssss
        }
        JSONParser parser = new JSONParser();
        JSONArray a = new JSONArray();
        try{
            a = (JSONArray)parser.parse(new FileReader("jugadores.json"));
        }
        catch(Exception e){
            //TODO: whatefac hacer en las exepsssssssssssssssssss
        }

        for(Object o : a){
            JSONObject jugador = (JSONObject) o;
            
            String nombre = (String)jugador.get("nombre");
            String alias = (String)jugador.get("alias");
            int pGanadas = (int)((Long)jugador.get("pGanadas")).longValue();
            int edad = (int)((Long)jugador.get("edad")).longValue();
            
            Jugador j = new Jugador(nombre, alias, edad);
            j.setpGanadas(pGanadas);
            
            jugadores.add(j);
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

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void registrarJugador(Jugador jugador){
        jugadores.add(jugador);
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
    
    private void guardarPartida(){
        System.out.println("bad b");
        escribirArchivo("partidas.txt", partidas);
    }
    
    public void guardarJugadores(){
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
        try (FileWriter file = new FileWriter("jugadores.json");) {
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
}


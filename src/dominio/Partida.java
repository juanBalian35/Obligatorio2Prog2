package dominio;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/*
* Creado por:
*   Juan Balian - 211150
*   Agustín Introini - 211064
* */
public class Partida implements Comparable<Partida>, Serializable {
    private Tablero tablero = new Tablero();
    private Jugador[] jugadores;
    private int formaDeTerminar;
    private ArrayList<String> movimientos = new ArrayList<>();
    private ArrayList<Integer> numFichasValidas = new ArrayList<>();
    private Date fecha;
    private int cantMovimientos;

    public Partida(Jugador[] jugadores_, int forma, Date d,int cantMov){
	jugadores = jugadores_;
	formaDeTerminar = forma;
	fecha = d;
        cantMovimientos=cantMov;
	inicializarFichas();
    }
    public int getCantMovimientos() {
        return cantMovimientos;
    }
    public int getFormaDeTerminar(){
        return formaDeTerminar;
    }

    public void setCantMovimientos(int cantMovimientos) {
        this.cantMovimientos = cantMovimientos;
    }
    public Date getFecha() {
        return fecha;
    }
    public String getDia(){
        DateFormat formatoDia = new SimpleDateFormat("dd/MM/yyyy");
        return formatoDia.format(fecha);
    }
     public String getHora(){
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        return formatoHora.format(fecha);
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tablero getTablero(){
        return tablero;
    }

    public Jugador[] getJugadores(){
        return jugadores;
    }
    
    public ArrayList<String> getMovimientos() {
        return movimientos;
    }

    public void setTablero(Tablero tablero){this.tablero = tablero; }

    public void setMovimientos(ArrayList<String> movimientos) {
        this.movimientos = movimientos;
    }

    public void inicializarFichas(){
        Ficha[] fichas1 = jugadores[0].getFichas();
        Ficha[] fichas2 = jugadores[1].getFichas();

        // Ubicamos las fichas
        for(int i = 0; i < Jugador.NUM_FICHAS; ++i){
            Ficha ficha = new Ficha(i, Tablero.LARGO - 1, Jugador.NUM_FICHAS-i, true);
            fichas1[i] = ficha;

            ficha = new Ficha(Tablero.ANCHO - 1 - i, 0, Jugador.NUM_FICHAS-i, false);
            fichas2[i] = ficha;
        }

        jugadores[0].setFichas(fichas1);
        jugadores[1].setFichas(fichas2);

        tablero.actualizar(jugadores);
    }
   
    public ArrayList<Integer> posicionesPosibles(int x, int y, boolean esJugadorUno){
        int nuevaY = esJugadorUno ? y - 1: y + 1;
        ArrayList<Integer> asd = new ArrayList();
        for(int i = -1; i <= 1; ++i){
            if(tablero.esPosValida(x + i, nuevaY) && !tablero.estaPosOcupada(x + i, nuevaY)){
                asd.add(x + i);
                asd.add(nuevaY);
            }
        }
        return asd;
    }

    public Jugador ganador(){
        int a = tablero.contarFichasLadoContrario(true);
        int b = tablero.contarFichasLadoContrario(false);

        if(a > b){
            return jugadores[0];
        }
        else if(a == b){
            if("X".equals(movimientos.get(movimientos.size() - 1).charAt(0) + "")) {
                if("1".equals(movimientos.get(movimientos.size() - 1).charAt(2) + ""))
                    return jugadores[0];
                else
                    return jugadores[1];
            }
            return null;
        }
        return jugadores[1];
    }
    
    public ArrayList<Integer> hacerMovimiento(String movimiento, int numJugador){
        movimientos.add(movimiento + " " + numJugador);
        
        if("X".equals(movimiento))
            return null;
        
        String[] a = movimiento.split(",");

        Ficha ficha = fichaAMover(movimiento, jugadores[numJugador]);

        ficha.setY(ficha.getY() + (numJugador == 0 ? -1 : 1));

        if(a[1].contains("D"))
            ficha.setX(ficha.getX() + 1);
        else if(a[1].contains("I"))
            ficha.setX(ficha.getX() - 1);
        
        tablero.actualizar(jugadores);
        
        return tablero.fichasValidas(ficha, numJugador == 0);
    }

    private static Ficha fichaAMover(String movimiento, Jugador jugador){
        String[] a = movimiento.split(",");
        int num = Integer.parseInt(a[0]);

        return Arrays.stream(jugador.getFichas()).
                filter(x -> x.getNumero() == num).
                findFirst().
                get();
    }

    public boolean debeTerminar(boolean esJugadorUno){
        switch(formaDeTerminar){
            case 1:
                //Alcanzar cantidad maxima de movimientos
                if(movimientos.size() == cantMovimientos)
                    return true;

                // En el caso de que se este jugando con una cantidad maxima de movmimientos y un jugador llego con
                // todas sus fichas al otro extremo del tablero, entonces no va a poder seguir jugando, entonces
                // a pesar de seguir habiendo movimientos restantes el juego debe terminar.
            case 3:
                //Alcanzar con todas las fichas al otro lado.
                if(tablero.todasFichaLadoContrario(esJugadorUno))
                    return true;
                break;
            case 2:
                //Alcanzar con una ficha al otro lado
                if(tablero.unaFichaLadoContrario(esJugadorUno))
                    return true;
        }
        return false;
    }

    @Override
    public int compareTo(Partida partida){
        return partida.getFecha().compareTo(getFecha());
    }
    @Override
    public String toString() {
        return jugadores[0].getAlias() + 
                " vs " + jugadores[1].getAlias() + 
                ", Fecha: " + fecha;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.awt.Color;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/*
* Creado por:
*   Juan Balian - 211150
*   Agustín Introini - 211064
* */

public class Partida implements Comparable<Partida> {
    private Tablero tablero = new Tablero();
    private Jugador[] jugadores;
    private int formaDeTerminar;
    private ArrayList<String> movimientos = new ArrayList<>();
    private ArrayList<Integer> numFichasValidas = new ArrayList<>();
    private Date fecha;
    private DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private Scanner scanner = new Scanner(System.in);

    public Partida(Jugador[] jugadores_, int forma, Date d){
	jugadores = jugadores_;
	formaDeTerminar = forma;
	fecha = d;
	inicializarFichas();
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tablero getTablero(){
        return tablero;
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

    public void hacerMovimiento(String movimiento, Jugador jugador, boolean esJugadorUno){
        String[] a = movimiento.split(",");

        Ficha ficha = fichaAMover(movimiento, jugador);

        ficha.setY(ficha.getY() + (esJugadorUno ? -1 : 1));

        if(a[1].equals("D"))
            ficha.setX(ficha.getX() + 1);
        if(a[1].equals("I"))
            ficha.setX(ficha.getX() - 1);
        
        movimientos.add(movimiento);
        tablero.actualizar(jugadores);
        //tablero.mostrar(true);
    }

    private static Ficha fichaAMover(String movimiento, Jugador jugador){
        String[] a = movimiento.split(",");
        int num = Integer.parseInt(a[0]);

        return Arrays.stream(jugador.getFichas()).
                filter(x -> x.getNumero() == num).
                findFirst().
                get();
    }

    private int ultimaFichaMovida(boolean esJugador1){
        if(!movimientos.isEmpty())
            if(movimientos.get(movimientos.size() - 1).charAt(4) == (esJugador1 ? '1' : '2'))
                return Integer.parseInt(movimientos.get(movimientos.size() - 1).charAt(0) + "");
        return -1;
    }

    private boolean debeTerminar(boolean esJugadorUno, int cantMaxima){
        switch(formaDeTerminar){
            case 1:
                //Alcanzar cantidad maxima de movimientos
                if(movimientos.size() == cantMaxima){
                    System.out.println("Se ha alcanzado la cantidad máxima de movimientos ("+cantMaxima+"). JUEGO FINALIZADO");
                    return true;
                }

                // En el caso de que se este jugando con una cantidad maxima de movmimientos y un jugador llego con
                // todas sus fichas al otro extremo del tablero, entonces no va a poder seguir jugando, entonces
                // a pesar de seguir habiendo movimientos restantes el juego debe terminar.
            case 3:
                //Alcanzar con todas las fichas al otro lado.
                if(tablero.todasFichaLadoContrario(esJugadorUno)){
                    System.out.println("Todas las fichas han alcanzado el lado contrario del tablero. JUEGO FINALIZADO");
                    return true;
                }
                break;
            case 2:
                //Alcanzar con una ficha al otro lado
                if(tablero.unaFichaLadoContrario(esJugadorUno)){
                    System.out.println("Una ficha ha alcanzado el lado contrario del tablero. JUEGO FINALIZADO");
                    return true;
                }
        }
        return false;
    }

    @Override
    public int compareTo(Partida partida){
        return partida.getFecha().compareTo(getFecha());
    }
    @Override
    public String toString() {
        return Ficha.ROJO + jugadores[0].getAlias() + Ficha.RESET +
                " vs " + Ficha.AZUL + jugadores[1].getAlias() + Ficha.RESET+
                ", Fecha: " + formato.format(fecha);
    }
}


package dominio;

import java.io.Serializable;
import java.util.*;

/*
 * Creado por:
 *   Juan Balian - 211150
 *   Agustín Introini - 211064
 * */

public class Tablero implements Serializable {
    public static final int ANCHO = 9;
    public static final int LARGO = 8;

    private Ficha[][] matriz;

    public Tablero(){
        matriz = new Ficha[LARGO][ANCHO];
    }

    public void actualizar(Jugador[] jugadores){
        for(int i = 0; i < matriz.length; ++i)
            for(int j = 0; j < matriz[i].length; ++j)
                matriz[i][j] = null;

        for(Ficha ficha : jugadores[0].getFichas())
            matriz[ficha.getY()][ficha.getX()] = ficha;

        for(Ficha ficha : jugadores[1].getFichas())
            matriz[ficha.getY()][ficha.getX()] = ficha;
    }
    
    public ArrayList<Integer> fichasValidas(Ficha ficha, boolean esJugadorUno){
        ArrayList<Integer> movValidos = new ArrayList<>();

        int[] s1 = sumaDiagonales(ficha);
        int s2 = sumaHorizontal(ficha);
        int s3 = sumaVertical(ficha);
        
        if(s1[0] <= Jugador.NUM_FICHAS)
            movValidos.add(s1[0]);
        if(s1[1] <= Jugador.NUM_FICHAS)
            movValidos.add(s1[1]);
        if(s2 <= Jugador.NUM_FICHAS)
            movValidos.add(s2);
        if(s3 <= Jugador.NUM_FICHAS)
            movValidos.add(s3);

        // Removemos duplicados del ArrayList
        Set<Integer> hs = new HashSet<>();
        hs.addAll(movValidos);
        movValidos.clear();
        movValidos.addAll(hs);

        // Si no se puede mover mas la ficha, la sacamos de sugerencias
        ArrayList<Integer> fichasABorrar = new ArrayList<>();

        for(Integer numFicha : movValidos){
            int fila = esJugadorUno ? 0 : Tablero.LARGO - 1;
            for(int x = 0; x < Tablero.ANCHO; ++x){
                if(matriz[fila][x] == null)
                    continue;

                boolean esFichaDeJugadorUno = matriz[fila][x].getEsRojo();
                boolean esFichaDelJugadorCorrecto = (esFichaDeJugadorUno && esJugadorUno) || (!esFichaDeJugadorUno && !esJugadorUno);

                if(matriz[fila][x].getNumero() == numFicha && esFichaDelJugadorCorrecto)
                    fichasABorrar.add(numFicha);
            }
        }

        if(!fichasABorrar.contains(ficha.getNumero()))
            fichasABorrar.add(ficha.getNumero());
        
        for(Integer i : fichasABorrar){
            System.out.println("sacaa " + i);
        }
        
        movValidos.removeAll(fichasABorrar);

        return movValidos;
    }

    private int[] sumaDiagonales(Ficha ficha){
        int x = ficha.getX(), y = ficha.getY();
        int suma1 = ficha.getNumero(), suma2 = ficha.getNumero();

        for(int i = 1; i < Tablero.ANCHO; ++i){
            int xAtras = x - i, xAdelante = x + i;

            int yAtras = y - i, yAdelante = y + i;

            if(esPosValida(xAtras, yAtras))
                suma1 += matriz[yAtras][xAtras] == null ? 0 : matriz[yAtras][xAtras].getNumero();
            if(esPosValida(xAdelante, yAdelante))
                suma1 += matriz[yAdelante][xAdelante] == null ? 0 : matriz[yAdelante][xAdelante].getNumero();

            if(esPosValida(xAtras, yAdelante))
                suma2 += matriz[yAdelante][xAtras] == null ? 0 : matriz[yAdelante][xAtras].getNumero();
            if(esPosValida(xAdelante, yAtras))
                suma2 += matriz[yAtras][xAdelante] == null ? 0 : matriz[yAtras][xAdelante].getNumero();
        }

        int[] ret = {suma1, suma2};
        return ret;
    }

    private int sumaHorizontal(Ficha ficha){
        int y = ficha.getY();

        int suma = 0;

        for(int i = 0; i < Tablero.ANCHO; ++i)
            if(esPosValida(i, y))
                suma += matriz[y][i] == null ? 0 : matriz[y][i].getNumero();

        return suma;
    }

    private int sumaVertical(Ficha ficha){
        int x = ficha.getX();

        int suma = 0;

        for(int i = 0; i < Tablero.LARGO; ++i)
            if(esPosValida(x, i))
                suma += matriz[i][x] == null ? 0 : matriz[i][x].getNumero();

        return suma;
    }

    public int contarFichasLadoContrario(boolean esJugadorUno){
        int numFichasJugadorUno = 0;
        int numFichasJugadorDos = 0;
        for(int i = 0, fila = esJugadorUno ? 0 : Tablero.LARGO - 1; i < Tablero.ANCHO; ++i){
            if(esPosValida(i, fila) && matriz[fila][i] != null){
                boolean esFichaDeJugadorUno = matriz[fila][i].getEsRojo();

                if (esFichaDeJugadorUno && esJugadorUno)
                    numFichasJugadorUno++;

                if (!esFichaDeJugadorUno && !esJugadorUno)
                    numFichasJugadorDos++;
            }
        }
        return esJugadorUno ? numFichasJugadorUno : numFichasJugadorDos;
    }

    public boolean unaFichaLadoContrario(boolean esJugadorUno){
        return contarFichasLadoContrario(esJugadorUno) > 0;
    }

    public boolean todasFichaLadoContrario(boolean esJugadorUno){
        return contarFichasLadoContrario(esJugadorUno) == Jugador.NUM_FICHAS;
    }

    public boolean esPosValida(int x, int y){
        return x >= 0 && x < Tablero.ANCHO && y >= 0 && y < Tablero.LARGO;
    }
    public boolean estaPosOcupada(int x, int y){
        return matriz[y][x] != null;
    }
    
    //DEBUG: koakoakoakoala
    private String lineaSeparatoria(){
        String s = "";

        for(int j = 0; j < ANCHO*2+1; ++j)
            s += ((j % 2 == 0 ? "+" : "-"));

        return s;
    }

    public void mostrar(boolean reducido){
        System.out.println(reducido ? "" : lineaSeparatoria());
        for(int i = 0; i < LARGO; ++i){
            for(int j = 0; j < ANCHO; ++j){
                System.out.print(reducido ? " " : "|");
                System.out.print((matriz[i][j] == null ? (reducido ? "-" : " ") : matriz[i][j]));
                System.out.print(reducido ? " " : (j == ANCHO - 1 ? "|" : ""));
            }

            System.out.println(reducido ? "" : "\n" + lineaSeparatoria());
        }
    }
}

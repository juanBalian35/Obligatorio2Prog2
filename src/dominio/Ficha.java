package dominio;

import java.io.Serializable;

/*
 * Creado por:
 *   Juan Balian - 211150
 *   Agustín Introini - 211064
 * */
public class Ficha implements Serializable {
    private int x;
    private int y;
    private int numero;
    private boolean esRojo;

    public Ficha(int x, int y, int numero, boolean b){
        this.x = x;
        this.y = y;
        this.numero = numero;
        esRojo = b;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }

    public boolean getEsRojo(){return esRojo; }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getNumero(){
        return numero;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Ficha){
            Ficha f = (Ficha) o;
            
            return f.x == x && f.y == y && f.esRojo == esRojo && f.numero == numero;
        }
        return false;
    }
}

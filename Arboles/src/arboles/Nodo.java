/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author MIO
 */
public class Nodo implements java.io.Serializable {
    private Nodo padre = null;
    private Nodo izq = null, der = null;
    private Object valor;
    
    private int nivel;
    
    public Nodo(Nodo padre, Object valor) {
        this.padre = padre;
        this.valor = valor;
    }
    
    public Nodo(Object valor) {
        this.valor = valor;
    }
    
    public int comparar(Nodo a) {
        int x = Integer.parseInt(String.valueOf(valor));
        int y = Integer.parseInt(String.valueOf(a.getValor()));
        if(x<y) {
            return -1;
        }
        else if( x== y) {
            return 0;
        }
        else {
            return 1;
        }
    }
    
    public Nodo getPadre() {
        return padre;
    }
    
    public void setPadre(Nodo padre) {
        this.padre = padre;
    }
    
    public Nodo getIzq() {
        return izq;
    }
    
    public void setIzq(Nodo izq) {
        this.izq = izq;
    }
    
    public Nodo getDer() {
        return der;
    }
    
    public void setDer(Nodo der) {
        this.der = der;
    }
    
    public Object getValor() {
        return valor;
    }
    
    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

}

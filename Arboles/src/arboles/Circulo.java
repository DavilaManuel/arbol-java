/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.awt.Color;

/**
 *
 * @author LABORATORIO UCV
 */
public class Circulo implements java.io.Serializable{
    public Color borde = Color.BLACK;
    public Color relleno = Color.BLACK;
    public int tam = 50;
    public int com, x, y;
    public Nodo nodo;
    
    public Circulo() {
        
    }
    
    public Circulo(Object com, int x, int y) {
        this.com = Integer.parseInt(String.valueOf(com));
        this.x = x;
        this.y = y;
    }
}

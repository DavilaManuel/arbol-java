/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author LABORATORIO UCV
 */
public class Arbol extends JPanel implements java.io.Serializable{
    public Nodo cabeza = null;
    public int TAM;
    public LinkedList lineas = new LinkedList();
    public LinkedList circulos = new LinkedList();
    
    public Nodo sacarNodo(Object con) {
        Nodo aux = null;
        for (int i = 0; i < circulos.size(); i++) {
            Circulo c = (Circulo)circulos.get(i);
            if(c.com == Integer.parseInt(String.valueOf(con))) {
                aux = c.nodo;
            }
        }
        return aux;
    }
    
    public void borrar(Nodo padre, Nodo eli) {
        System.out.println("Padre:"+padre.getValor());
        System.out.println("Elim:"+eli.getValor());
        if(padre != null) {
            if(padre.getDer() != null) {
                System.out.println("Me voy a la derecha");
                if(padre.getDer().comparar(eli) == 0) {
                    padre.setDer(null);
                    System.out.println("Se elimino correctamente");
                }
                else {
                    borrar(padre.getDer(), eli);
                }
            }
            if(padre.getIzq() != null) {
                System.out.println("Me voy a la izquierda");
                if(padre.getIzq().comparar(eli) == 0) {
                    padre.setIzq(null);
                    System.out.println("Se elimino correctamente");
                }
                else {
                    borrar(padre.getIzq(), eli);
                }
            }
        }
    }
    
    public void elementoMasLado(int tipo, Nodo act, Nodo cam) {
        if(tipo == 1) {
            if(act.getIzq() != null) {
                elementoMasLado(1, act.getIzq(), cam);
            }
            else {
                System.out.println("Act: "+act.getValor());
                System.out.println("Cam: "+cam.getValor());
                System.out.println("Se encontro se intercambia info");
                Object ele = act.getValor();
                act.setValor(cam.getValor());
                cam.setValor(ele);
                System.out.println("Act: "+act.getValor());
                System.out.println("Cam: "+cam.getValor());
            } 
        }
        else {
            if(act.getDer() != null) {
                elementoMasLado(1, act.getDer(), cam);
            }
            else {
                System.out.println("Act: "+act.getValor());
                System.out.println("Cam: "+cam.getValor());
                System.out.println("Se encontro se intercambia info");
                Object ele = act.getValor();
                act.setValor(cam.getValor());
                cam.setValor(ele);
                System.out.println("Act: "+act.getValor());
                System.out.println("Cam: "+cam.getValor());
            }
        }
    }
    
    public void borrarNodo(Object con) {
        Nodo aux;
        if(seBusca(con) == true) {
            aux = sacarNodo(con);
            System.out.println("Llego para eliminar el: "+aux.getValor());
            
            if(aux.getDer() != null & aux.getIzq() == null) {
                System.out.println("Esta hoja se eliminarà" +aux.getValor());
                borrar(cabeza, aux);
            }
            else {
                if(aux.getDer() != null) {
                    System.out.println("Tiene un subarbol derecho" +aux.getValor());
                    elementoMasLado(1, aux.getDer(), aux);
                    borrarNodo(aux.getValor());                }
                else if (aux.getIzq() != null) {
                    System.out.println("Tiene un subarbol izquierdo" +aux.getValor());
                    elementoMasLado(1, aux.getIzq(), aux);
                    borrarNodo(aux.getValor());
                } 
            } 
        }
    }
    
    public void agregarNodo(Object con) {
        Nodo aux = new Nodo(con);
        int lvl = 0;
        
        if(cabeza == null) {
            cabeza = aux;
            cabeza.setNivel(lvl);
        }
        else {
            boolean seInserto = false;
            Nodo aux2 = cabeza;
            while (seInserto == false) {
                lvl++;
                if(aux2.comparar(aux) == 1) {
                        if(aux2.getIzq() == null) {
                            aux2.setIzq(aux);
                            aux.setNivel(lvl);
                            seInserto = true;
                        }
                        else {
                            aux2 = aux2.getIzq();
                        }                }
                else if (aux2.comparar(aux) == -1) {
                        if(aux2.getDer() == null) {
                            aux2.setDer(aux);
                            aux.setNivel(lvl);
                            seInserto = true;
                        }
                        else {
                            aux2 = aux2.getDer();
                        } 
                }
                else {
                    mensaje("Este nodo ya existe: "+con);
                }
            }
            
        }
    }
    
    public int numGeneraciones(Nodo a, int n) {
        if(a == null) {
            return n;
        }
        else {
            int nI = numGeneraciones(a.getIzq(), n+1);
            int nD = numGeneraciones(a.getDer(), n+1);
            if(nI > nD) {
                return nI;
            }                
            else {
                return nD;
            }
            
        }
    }
    
    public void mensaje(String s) {
        System.out.println("Mensaje del sistema: "+s);
    }
    
    public void imprimir(int x, int y) {
        lineas = new LinkedList();
        circulos = new LinkedList();
        imprimirArbol(cabeza, x, y, numGeneraciones(cabeza, 0));
    }
    
    public void update(Graphics g) {
        paint(g);
    }
    
    int TAM2 = 30;
    public void imprimirArbol(Nodo a, int x, int y, int v) {
        if(a == null) {
            return;
        }
        else {
            Circulo c = new Circulo(a.getValor(), x, y);
            c.nodo = a;
            circulos.add(c);
                if(a.getDer() != null) {
                    TAM = 25*v;
                    Linea aux = new Linea(x, y, x+TAM, y+TAM2);
                    lineas.add(aux);
                    imprimirArbol(a.getDer(), x+TAM, y+TAM2, v-1);
                }
                if(a.getIzq() != null) {
                    TAM = 25*v;
                    Linea aux = new Linea(x, y, x-TAM, y+TAM2);
                    lineas.add(aux);
                    imprimirArbol(a.getIzq(), x-TAM, y+TAM2, v-1);
                }
        }
    }
    
    public boolean seBusca(Object val) {
        Nodo aux = new Nodo(val);
        if(buscar(aux, cabeza) == true) {
            mensaje("Se encontro");
            return true;
        }
        else {
            mensaje("No se halla");
            JOptionPane.showMessageDialog(null, "No se halla");
        }
        return false;
    }
    
    public void colorearBuscado(String val) {
        for(int i = 0; i < circulos.size(); i++) {
            Circulo c = (Circulo)circulos.get(i);
            if(c.com == Integer.parseInt(val)) {
                c.relleno = Color.BLUE;
            }
        }
    }
    
    public boolean buscar(Nodo val, Nodo a) {
        if(a.comparar(val) == 0) {
            return true;
        }
        if(a.comparar(val) == 1) {
            return buscar(val, a.getDer());
        }
        else if(a.comparar(val) == 1) {
            return buscar(val, a.getIzq());
        }
        else {
            return false;
        }
    }
    
    @Override
    public void paint(Graphics g) {
        for(int i = 0; i < circulos.size(); i++) {
            Circulo r = (Circulo)circulos.get(i);
            g.setColor(r.relleno);
            g.fillOval(r.x, r.y, 25, 25);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(r.com), r.x+8, r.y+15);
        }
        for(int i = 0; i < lineas.size(); i++) {
            Linea r = (Linea)lineas.get(i);
            g.setColor(r.color);
            g.drawLine(r.x1+8, r.y1+15, r.x2+8, r.y2+15);
        }
    }
    
    public String recorridoInOrden() {
        String s = "";
        if(cabeza != null) {
            s = inOrden(cabeza);
            mensaje(s);
        }
        else {
            mensaje("Arbol Invalido para el recorrido");
        }
        return s;
    }
    
    public String inOrden(Nodo aux) {
        String s = "";
        mensaje("Estoy en el nodo:"+aux.getValor());
        if(aux.getIzq() != null) {
            s = s+""+inOrden(aux.getIzq());
        }
        s = s+""+String.valueOf(aux.getValor());
        if(aux.getDer() != null) {
            s = s+""+inOrden(aux.getDer());
        }
        return s;
    }
    
    public String recorridoPreOrden() {
        String s = "";
        if(cabeza != null) {
            s = preOrden(cabeza);
        }
        return s;
    }
    
    public String preOrden(Nodo n) {
        String s = "";
        s = s+""+String.valueOf(n.getValor());
        if(n.getIzq() != null) {
            s = s+""+preOrden(n.getIzq());
        }
        s = s+""+String.valueOf(n.getValor());
        if(n.getDer() != null) {
            s = s+""+preOrden(n.getDer());
        }
        return s;
    }
    
    public String recorridoPostOrden() {
        String s = "";
        if(cabeza != null) {
            s = postOrden(cabeza);
            mensaje(s);
        }
        else {
            mensaje("Arbol Invalido para el recorrido");
        }
        return s;
    }
    
    public String postOrden(Nodo aux) {
        String s = "";
        mensaje("Estoy en el nodo:"+aux.getValor());
        if(aux.getIzq() != null) {
            s = s+""+postOrden(aux.getIzq());
        }
        if(aux.getDer() != null) {
            s = s+""+postOrden(aux.getDer());
        }
        s = s+""+String.valueOf(aux.getValor());
        return s;
    }
    
}

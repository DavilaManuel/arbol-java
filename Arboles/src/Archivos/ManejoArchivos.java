package Archivos;

import arboles.Arbol;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LABORATORIO UCV
 */
public class ManejoArchivos {
    public void guardar(JFrame panel, Arbol a) {
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Archivo de Arboles (.tree)", "tree");
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(panel);
        if(seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            guardarEnArchivo(fichero.getAbsolutePath(), a);
        }
    }
    
    public void guardarEnArchivo(String archivo, Arbol r) {
        try {
            FileOutputStream fileOut = new FileOutputStream(archivo + ".tree");
            try {
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);
                salida.writeObject(r);
                salida.close();
            } catch (IOException ex){
                Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        } catch (IOException ex){
            Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    
    public Arbol abrir(JFrame panel) throws IOException, ClassNotFoundException {
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Archivo de Arboles (.tree)", "tree");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filtro);
        int seleccion = fileChooser.showSaveDialog(panel);
        if(seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            return Abrir(fichero.getAbsolutePath());
        }
        return null;
    }
    
    public Arbol Abrir(String archivo) throws IOException, ClassNotFoundException {
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo));
        Arbol r = (Arbol)entrada.readObject();
        entrada.close();
        return r;
    }
    
}

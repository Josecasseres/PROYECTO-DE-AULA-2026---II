package com.cruds.interfaz;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

// Carga los íconos del paquete com.cruds.imagenes y los reduce al tamaño pedido
public class Iconos {

    // Ejemplo de uso: boton.setIcon(Iconos.cargar("agregar.png", 32));
    public static ImageIcon cargar(String nombreArchivo, int tamano) {
        URL url = Iconos.class.getResource("/com/cruds/imagenes/" + nombreArchivo);
        if (url == null) {
            System.err.println("No se encontró el ícono: " + nombreArchivo);
            return null; // si no existe, el botón queda sin ícono en vez de dar error
        }
        Image reducida = new ImageIcon(url).getImage().getScaledInstance(tamano, tamano, Image.SCALE_SMOOTH);
        return new ImageIcon(reducida);
    }
}
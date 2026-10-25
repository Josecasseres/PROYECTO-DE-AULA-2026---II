package com.cruds.interfaz;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Panel que dibuja una imagen de fondo ajustada al tamaño de la ventana
// y, opcionalmente, un ícono grande en la esquina superior derecha.
public class PanelFondo extends JPanel {

    private final Image imagen;
    private Image iconoEsquina; // ícono de la pantalla actual (puede ser null)

    // Tamaño y margen del ícono de la esquina
    private static final int TAMANO_ICONO = 90;
    private static final int MARGEN = 20;

    public PanelFondo(String rutaImagen) {
        // Carga la imagen desde el proyecto (dentro del paquete com.cruds.imagenes)
        java.net.URL url = getClass().getResource(rutaImagen);
        imagen = (url != null) ? new ImageIcon(url).getImage() : null;
    }

    // Capa blanca translúcida sobre la imagen (0 = sin capa, 255 = blanco total).
    // Sirve en ventanas pequeñas para que los textos se lean bien sobre el dibujo.
    private int velo = 0;

    public void setVelo(int opacidad) {
        velo = opacidad;
        repaint();
    }

    // Cambia el ícono de la esquina y vuelve a dibujar el panel
    public void setIconoEsquina(String nombreArchivo) {
        java.net.URL url = getClass().getResource("/com/cruds/imagenes/" + nombreArchivo);
        iconoEsquina = (url != null) ? new ImageIcon(url).getImage() : null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Suaviza las imágenes cuando se estiran o se encogen
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        if (imagen != null) {
            g2.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
        if (velo > 0) {
            g2.setColor(new java.awt.Color(255, 255, 255, velo));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
        if (iconoEsquina != null) {
            g2.drawImage(iconoEsquina, getWidth() - TAMANO_ICONO - MARGEN, MARGEN, TAMANO_ICONO, TAMANO_ICONO, this);
        }
    }
}
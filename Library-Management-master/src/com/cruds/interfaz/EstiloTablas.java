package com.cruds.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Da a las tablas un fondo blanco semitransparente: se ve la imagen de fondo
 * detrás, pero las letras siguen leyéndose bien.
 */
public class EstiloTablas {

    // Transparencia del fondo: 0 = invisible, 255 = totalmente opaco
    private static final int OPACIDAD = 190;
    private static final Color FONDO_FILA = new Color(255, 255, 255, OPACIDAD);
    private static final Color FONDO_FILA_ALTERNA = new Color(235, 228, 250, OPACIDAD);
    private static final Color FONDO_SELECCION = new Color(120, 90, 200, 210);

    // Área vacía de la tabla (debajo de las filas), pintada con el mismo fondo translúcido
    private static class VistaTranslucida extends JViewport {
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(FONDO_FILA);
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
    }

    // Pinta cada celda con su color translúcido (se pinta a mano porque Swing
    // no mezcla bien los colores con transparencia en componentes opacos)
    private static class RenderizadorTranslucido extends DefaultTableCellRenderer {
        private Color fondoActual = FONDO_FILA;

        RenderizadorTranslucido() {
            setOpaque(false);
            setBorder(new EmptyBorder(0, 6, 0, 6));
        }

        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                boolean seleccionada, boolean enfoque, int fila, int columna) {
            super.getTableCellRendererComponent(tabla, valor, seleccionada, false, fila, columna);
            if (seleccionada) {
                fondoActual = FONDO_SELECCION;
                setForeground(Color.WHITE);
            } else {
                fondoActual = (fila % 2 == 0) ? FONDO_FILA : FONDO_FILA_ALTERNA;
                setForeground(Color.BLACK);
            }
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(fondoActual);
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
    }

    // Mismo estilo para una lista (JList), por ejemplo la cola de espera
    public static void aplicarLista(javax.swing.JList<String> lista, JScrollPane desplazable) {
        lista.setOpaque(false);
        lista.setCellRenderer(new javax.swing.DefaultListCellRenderer() {
            private Color fondoActual = FONDO_FILA;

            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> l, Object valor,
                    int indice, boolean seleccionada, boolean enfoque) {
                super.getListCellRendererComponent(l, valor, indice, seleccionada, false);
                setOpaque(false);
                setBorder(new EmptyBorder(2, 8, 2, 8));
                fondoActual = seleccionada ? FONDO_SELECCION : (indice % 2 == 0 ? FONDO_FILA : FONDO_FILA_ALTERNA);
                setForeground(seleccionada ? Color.WHITE : Color.BLACK);
                return this;
            }

            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(fondoActual);
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        });
        JViewport vista = new VistaTranslucida();
        vista.setOpaque(false);
        vista.setView(lista);
        desplazable.setViewport(vista);
        desplazable.setOpaque(false);
    }

    // Aplica el estilo a una tabla y a su panel con barras de desplazamiento
    public static void aplicar(JTable tabla, JScrollPane desplazable) {
        tabla.setOpaque(false);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tabla.setFillsViewportHeight(true);
        tabla.setRowHeight(24);
        tabla.setDefaultRenderer(Object.class, new RenderizadorTranslucido());

        JViewport vista = new VistaTranslucida();
        vista.setOpaque(false);
        vista.setView(tabla);
        desplazable.setViewport(vista);
        desplazable.setOpaque(false);
    }
}

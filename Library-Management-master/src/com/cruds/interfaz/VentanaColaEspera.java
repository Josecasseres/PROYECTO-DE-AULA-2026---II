package com.cruds.interfaz;

import com.cruds.estructuras.Cola;
import com.cruds.modelo.Estudiante;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

// Ventana de la cola de espera: los estudiantes se atienden en orden de llegada (FIFO)
public class VentanaColaEspera extends JFrame {

    // La cola se recibe de VentanaPrincipal para que no se pierda al cerrar esta ventana
    private final Cola<Estudiante> colaEspera;

    private final JTextField campoCodigo = new JTextField(10);
    private final JTextField campoNombre = new JTextField(15);
    private final DefaultListModel<String> modeloLista = new DefaultListModel<>();
    private final JLabel etiquetaTotal = new JLabel();

    public VentanaColaEspera(Cola<Estudiante> colaEspera) {
        this.colaEspera = colaEspera;
        construirInterfaz();
        actualizarLista();
    }

    // Arma los componentes de la ventana
    private void construirInterfaz() {
        setTitle("Cola de espera");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // cierra solo esta ventana
        setSize(520, 480);
        setLocationRelativeTo(null);
        // Fondo con la imagen de la biblioteca (se pone antes de asignar el layout)
        PanelFondo fondo = new PanelFondo("/com/cruds/imagenes/fondo.png");
        fondo.setVelo(140); // capa clara para que se lean los textos
        fondo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // margen interno
        setContentPane(fondo);
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel titulo = new JLabel("Cola de espera", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(0, 0, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(3, 2, 8, 8)); // 3 filas, 2 columnas
        formulario.setOpaque(false); // transparente para que se vea el fondo
        formulario.add(new JLabel("Código:"));
        formulario.add(campoCodigo);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(campoNombre);
        JButton botonAgregar = new JButton("Agregar a la cola");
        botonAgregar.addActionListener(e -> agregarEstudiante());
        formulario.add(new JLabel("")); // celda vacía para alinear el botón a la derecha
        formulario.add(botonAgregar);

        // Lista con el orden de atención
        JList<String> lista = new JList<>(modeloLista);
        lista.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        JScrollPane desplazamiento = new JScrollPane(lista);
        EstiloTablas.aplicarLista(lista, desplazamiento); // fondo blanco semitransparente
        desplazamiento.setBorder(BorderFactory.createTitledBorder("Orden de atención (el primero es el siguiente)"));

        JPanel centro = new JPanel(new BorderLayout(5, 5));
        centro.setOpaque(false);
        centro.add(formulario, BorderLayout.NORTH);
        centro.add(desplazamiento, BorderLayout.CENTER);
        add(centro, BorderLayout.CENTER);

        // Botones inferiores
        JButton botonAtender = new JButton("Atender siguiente");
        botonAtender.addActionListener(e -> atenderSiguiente());
        JPanel inferior = new JPanel(new GridLayout(2, 1, 5, 5));
        inferior.setOpaque(false);
        etiquetaTotal.setHorizontalAlignment(JLabel.CENTER);
        inferior.add(etiquetaTotal);
        inferior.add(botonAtender);
        add(inferior, BorderLayout.SOUTH);
    }

    // Encola un estudiante nuevo (va al final de la cola)
    private void agregarEstudiante() {
        String codigo = campoCodigo.getText().trim();
        String nombre = campoNombre.getText().trim();
        if (codigo.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba el código y el nombre del estudiante.", "Operación no válida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        colaEspera.encolar(new Estudiante(codigo, nombre));
        campoCodigo.setText("");
        campoNombre.setText("");
        actualizarLista();
    }

    // Desencola al primero de la fila y avisa a quién le toca
    private void atenderSiguiente() {
        Estudiante atendido = colaEspera.desencolar();
        if (atendido == null) {
            JOptionPane.showMessageDialog(this, "No hay estudiantes en la cola.", "Cola vacía", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,
                "Turno de: " + atendido.getNombre() + " (código " + atendido.getCodigo() + ")\n"
                + "Búsquelo en la pantalla Prestar libro con ese código.",
                "Atender estudiante", JOptionPane.INFORMATION_MESSAGE);
        actualizarLista();
    }

    // Vuelve a dibujar la lista a partir del contenido actual de la cola
    private void actualizarLista() {
        modeloLista.clear();
        int posicion = 1;
        for (Estudiante e : colaEspera.aLista()) {
            modeloLista.addElement(posicion + ". " + e.getNombre() + " (código " + e.getCodigo() + ")");
            posicion++;
        }
        etiquetaTotal.setText("Estudiantes en espera: " + colaEspera.getTamanio());
    }
}

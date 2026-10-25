package com.cruds.interfaz;

import com.cruds.bd.LibroDAO;
import com.cruds.modelo.Autor;
import com.cruds.modelo.Libro;
import com.cruds.modelo.Prestamo;
import com.cruds.modelo.Estudiante;
import java.awt.CardLayout;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends javax.swing.JFrame {

    // Cola de espera de estudiantes: se conserva mientras la aplicación esté abierta
    private final com.cruds.estructuras.Cola<Estudiante> colaEspera = new com.cruds.estructuras.Cola<>();

    // Pila con los préstamos hechos, para poder deshacer el último (el más reciente queda en la cima)
    private final com.cruds.estructuras.Pila<Prestamo> pilaPrestamos = new com.cruds.estructuras.Pila<>();

    public VentanaPrincipal() {
        // Usa la imagen como fondo de toda la ventana (antes de crear los componentes)
        setContentPane(new PanelFondo("/com/cruds/imagenes/fondo.png"));
        initComponents();
        setSize(1280, 720);
        setLocationRelativeTo(null); // centrada en la pantalla
        // Barra superior con "Cerrar sesión": siempre visible en todas las pantallas
        javax.swing.JMenuBar barraMenu = new javax.swing.JMenuBar();
        javax.swing.JMenu menuSesion = new javax.swing.JMenu("Sesión");
        javax.swing.JMenuItem itemCerrarSesion = new javax.swing.JMenuItem("Cerrar sesión");
        itemCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Cierra esta ventana y vuelve al inicio de sesión
                dispose();
                new VentanaInicioSesion().setVisible(true);
            }
        });
        menuSesion.add(itemCerrarSesion);
        barraMenu.add(menuSesion);
        setJMenuBar(barraMenu);
        // Paneles transparentes para que se vea la imagen detrás
        panelPrincipal.setOpaque(false);
        panelBuscarLibro.setOpaque(false);
        panelListaLibros.setOpaque(false);
        panelPrestarLibro.setOpaque(false);
        panelLibrosPrestados.setOpaque(false);
        panelDevolverHoy.setOpaque(false);
        panelAgregarLibro.setOpaque(false);

        // Tablas con fondo blanco semitransparente (se ve el diseño detrás y las letras se leen bien)
        EstiloTablas.aplicar(tablaBusqueda, desplazableBusqueda);
        EstiloTablas.aplicar(tablaLibroAutor, desplazableLibroAutor);
        EstiloTablas.aplicar(tablaLibros, desplazableLibros);
        EstiloTablas.aplicar(tablaEstudiantes, desplazableEstudiantes);
        EstiloTablas.aplicar(tablaLibrosPrestados, desplazableLibrosPrestados);
        EstiloTablas.aplicar(tablaDevolverHoy, desplazableDevolverHoy);

        // Íconos del menú lateral (nombre del archivo, tamaño en píxeles)
        btnAgregarLibro.setIcon(Iconos.cargar("agregar.png", 32));
        btnBuscarLibro.setIcon(Iconos.cargar("busqueda.png", 32));
        btnListarLibros.setIcon(Iconos.cargar("lista-de-verificacion.png", 32));
        btnPrestarLibro.setIcon(Iconos.cargar("pedir-prestado.png", 32));
        btnListarLibrosPrestados.setIcon(Iconos.cargar("libro.png", 32));
        btnDevolverHoy.setIcon(Iconos.cargar("devolver.png", 32));
        btnColaEspera.setIcon(Iconos.cargar("cola.png", 32));

        // Ícono grande en la esquina superior derecha: cambia según la pantalla que se abra
        asignarIconoEsquina(panelBuscarLibro, "biblioteca(1).png");
        asignarIconoEsquina(panelListaLibros, "biblioteca-publica.png");
        asignarIconoEsquina(panelPrestarLibro, "biblioteca.png");
        asignarIconoEsquina(panelLibrosPrestados, "libro-circular-abierto.png");
        asignarIconoEsquina(panelDevolverHoy, "libro.png");
        asignarIconoEsquina(panelAgregarLibro, "libro-medicos.png");
        // Pantalla que se muestra al abrir la aplicación
        ((PanelFondo) getContentPane()).setIconoEsquina("libro-electronico.png");

        // Configura el selector de fecha de devolución
        Calendar calendario = Calendar.getInstance();
        // Pone la hora en 00:00 para que "hoy" cuente completo como fecha mínima
        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);
        Date hoy = calendario.getTime(); // fecha mínima permitida: hoy
        calendario.add(Calendar.DATE, 7);
        Date porDefecto = calendario.getTime(); // valor inicial: hoy + 7 días
        // Modelo de fechas: valor inicial, mínimo = hoy, sin máximo, avanza de a un día
        selectorFechaDevolucion.setModel(new javax.swing.SpinnerDateModel(porDefecto, hoy, null, Calendar.DAY_OF_MONTH));
        // Formato en que se muestra la fecha: día/mes/año
        selectorFechaDevolucion.setEditor(new javax.swing.JSpinner.DateEditor(selectorFechaDevolucion, "dd/MM/yyyy"));
    }

    // Muestra un ícono en la esquina de la ventana cada vez que se abre la pantalla indicada
    private void asignarIconoEsquina(javax.swing.JPanel pantalla, String icono) {
        pantalla.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                ((PanelFondo) getContentPane()).setIconoEsquina(icono);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDevolverHoy = new javax.swing.JButton();
        btnListarLibrosPrestados = new javax.swing.JButton();
        btnPrestarLibro = new javax.swing.JButton();
        btnBuscarLibro = new javax.swing.JButton();
        btnListarLibros = new javax.swing.JButton();
        btnAgregarLibro = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();
        panelBuscarLibro = new javax.swing.JPanel();
        comboBoxBuscarPor = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        desplazableBusqueda = new javax.swing.JScrollPane();
        tablaBusqueda = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        etiquetaTituloBuscar = new javax.swing.JLabel();
        panelListaLibros = new javax.swing.JPanel();
        desplazableLibroAutor = new javax.swing.JScrollPane();
        tablaLibroAutor = new javax.swing.JTable();
        etiquetaTituloLista = new javax.swing.JLabel();
        btnEditarLibro = new javax.swing.JButton();
        btnEliminarLibro = new javax.swing.JButton();
        panelPrestarLibro = new javax.swing.JPanel();
        txtBuscarLibro = new javax.swing.JTextField();
        comboBoxBuscarLibro = new javax.swing.JComboBox<>();
        btnBuscarLibroPrestar = new javax.swing.JButton();
        desplazableLibros = new javax.swing.JScrollPane();
        tablaLibros = new javax.swing.JTable();
        etiquetaBuscarLibro = new javax.swing.JLabel();
        etiquetaBuscarEstudiante = new javax.swing.JLabel();
        txtBuscarCodigo = new javax.swing.JTextField();
        etiquetaCodigo = new javax.swing.JLabel();
        desplazableEstudiantes = new javax.swing.JScrollPane();
        tablaEstudiantes = new javax.swing.JTable();
        btnPrestar = new javax.swing.JButton();
        btnBuscarEstudiante = new javax.swing.JButton();
        btnAgregarEstudiante = new javax.swing.JButton();
        etiquetaTituloPrestar = new javax.swing.JLabel();
        etiquetaFechaDevolucion = new javax.swing.JLabel();
        selectorFechaDevolucion = new javax.swing.JSpinner();
        btnDeshacerPrestamo = new javax.swing.JButton();
        panelLibrosPrestados = new javax.swing.JPanel();
        desplazableLibrosPrestados = new javax.swing.JScrollPane();
        tablaLibrosPrestados = new javax.swing.JTable();
        btnDevolver = new javax.swing.JButton();
        etiquetaCodigo2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBuscarPorCodigo = new javax.swing.JButton();
        etiquetaTituloDevolver = new javax.swing.JLabel();
        panelDevolverHoy = new javax.swing.JPanel();
        desplazableDevolverHoy = new javax.swing.JScrollPane();
        tablaDevolverHoy = new javax.swing.JTable();
        etiquetaTituloDevolverHoy = new javax.swing.JLabel();
        panelAgregarLibro = new javax.swing.JPanel();
        etiquetaIsbn = new javax.swing.JLabel();
        txtIsbn = new javax.swing.JTextField();
        etiquetaTitulo = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        etiquetaCategoria = new javax.swing.JLabel();
        etiquetaCantidad = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        comboBoxCategoria = new javax.swing.JComboBox<>();
        txtCantidad = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        etiquetaAutor = new javax.swing.JLabel();
        txtNombreAutor = new javax.swing.JTextField();
        etiquetaCorreoAutor = new javax.swing.JLabel();
        txtCorreoAutor = new javax.swing.JTextField();
        etiquetaTituloAgregar = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnColaEspera = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca");

        btnDevolverHoy.setBackground(new java.awt.Color(153, 102, 255));
        btnDevolverHoy.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDevolverHoy.setForeground(new java.awt.Color(51, 51, 51));
        btnDevolverHoy.setText("Libros a devolver hoy");
        btnDevolverHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverHoyActionPerformed(evt);
            }
        });

        btnListarLibrosPrestados.setBackground(new java.awt.Color(153, 102, 255));
        btnListarLibrosPrestados.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnListarLibrosPrestados.setForeground(new java.awt.Color(51, 51, 51));
        btnListarLibrosPrestados.setText("Listar libros prestados");
        btnListarLibrosPrestados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarLibrosPrestadosActionPerformed(evt);
            }
        });

        btnPrestarLibro.setBackground(new java.awt.Color(153, 102, 255));
        btnPrestarLibro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPrestarLibro.setForeground(new java.awt.Color(51, 51, 51));
        btnPrestarLibro.setText("Prestar libro");
        btnPrestarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestarLibroActionPerformed(evt);
            }
        });

        btnBuscarLibro.setBackground(new java.awt.Color(153, 102, 255));
        btnBuscarLibro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBuscarLibro.setForeground(new java.awt.Color(51, 51, 51));
        btnBuscarLibro.setText("Buscar libro");
        btnBuscarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLibroActionPerformed(evt);
            }
        });

        btnListarLibros.setBackground(new java.awt.Color(153, 102, 255));
        btnListarLibros.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnListarLibros.setForeground(new java.awt.Color(51, 51, 51));
        btnListarLibros.setText("Listar todos los libros");
        btnListarLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarLibrosActionPerformed(evt);
            }
        });

        btnAgregarLibro.setBackground(new java.awt.Color(153, 102, 255));
        btnAgregarLibro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarLibro.setForeground(new java.awt.Color(51, 51, 51));
        btnAgregarLibro.setText("Agregar libro");
        btnAgregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLibroActionPerformed(evt);
            }
        });

        panelPrincipal.setLayout(new java.awt.CardLayout());

        panelBuscarLibro.setPreferredSize(new java.awt.Dimension(500, 399));

        comboBoxBuscarPor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboBoxBuscarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Título", "Categoría", "Autor", "ISBN" }));
        comboBoxBuscarPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxBuscarPorActionPerformed(evt);
            }
        });

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tablaBusqueda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN del libro", "Título del libro", "Categoría", "Cantidad", "Autor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        desplazableBusqueda.setViewportView(tablaBusqueda);
        if (tablaBusqueda.getColumnModel().getColumnCount() > 0) {
            tablaBusqueda.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaBusqueda.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaBusqueda.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        etiquetaTituloBuscar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        etiquetaTituloBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaTituloBuscar.setText("Buscar libro");

        javax.swing.GroupLayout panelBuscarLibroLayout = new javax.swing.GroupLayout(panelBuscarLibro);
        panelBuscarLibro.setLayout(panelBuscarLibroLayout);
        panelBuscarLibroLayout.setHorizontalGroup(
            panelBuscarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLibroLayout.createSequentialGroup()
                .addGroup(panelBuscarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscarLibroLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(panelBuscarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscar)
                            .addGroup(panelBuscarLibroLayout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(desplazableBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBuscarLibroLayout.createSequentialGroup()
                        .addGap(381, 381, 381)
                        .addComponent(etiquetaTituloBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(472, Short.MAX_VALUE))
        );
        panelBuscarLibroLayout.setVerticalGroup(
            panelBuscarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLibroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiquetaTituloBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBuscarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desplazableBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );

        panelPrincipal.add(panelBuscarLibro, "panelBuscarLibro");

        desplazableLibroAutor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tablaLibroAutor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaLibroAutor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Título", "Categoría", "Cantidad", "Nombre del autor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLibroAutor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaLibroAutor.setIntercellSpacing(new java.awt.Dimension(2, 1));
        tablaLibroAutor.setRowHeight(18);
        desplazableLibroAutor.setViewportView(tablaLibroAutor);
        if (tablaLibroAutor.getColumnModel().getColumnCount() > 0) {
            tablaLibroAutor.getColumnModel().getColumn(0).setMinWidth(50);
            tablaLibroAutor.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaLibroAutor.getColumnModel().getColumn(0).setMaxWidth(50);
            tablaLibroAutor.getColumnModel().getColumn(1).setMinWidth(200);
            tablaLibroAutor.getColumnModel().getColumn(1).setPreferredWidth(200);
            tablaLibroAutor.getColumnModel().getColumn(1).setMaxWidth(300);
            tablaLibroAutor.getColumnModel().getColumn(2).setMinWidth(50);
            tablaLibroAutor.getColumnModel().getColumn(2).setPreferredWidth(80);
            tablaLibroAutor.getColumnModel().getColumn(2).setMaxWidth(100);
            tablaLibroAutor.getColumnModel().getColumn(3).setMinWidth(50);
            tablaLibroAutor.getColumnModel().getColumn(3).setPreferredWidth(50);
            tablaLibroAutor.getColumnModel().getColumn(3).setMaxWidth(50);
        }

        etiquetaTituloLista.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        etiquetaTituloLista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaTituloLista.setText("Lista de libros");

        btnEditarLibro.setBackground(new java.awt.Color(153, 153, 255));
        btnEditarLibro.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEditarLibro.setText("Editar");
        btnEditarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarLibroActionPerformed(evt);
            }
        });

        btnEliminarLibro.setBackground(new java.awt.Color(153, 153, 255));
        btnEliminarLibro.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEliminarLibro.setText("Eliminar");
        btnEliminarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListaLibrosLayout = new javax.swing.GroupLayout(panelListaLibros);
        panelListaLibros.setLayout(panelListaLibrosLayout);
        panelListaLibrosLayout.setHorizontalGroup(
            panelListaLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLibrosLayout.createSequentialGroup()
                .addGroup(panelListaLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelListaLibrosLayout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(btnEditarLibro)
                        .addGap(279, 279, 279)
                        .addComponent(btnEliminarLibro))
                    .addGroup(panelListaLibrosLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(desplazableLibroAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelListaLibrosLayout.createSequentialGroup()
                        .addGap(376, 376, 376)
                        .addComponent(etiquetaTituloLista, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(437, Short.MAX_VALUE))
        );
        panelListaLibrosLayout.setVerticalGroup(
            panelListaLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLibrosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(etiquetaTituloLista, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelListaLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelListaLibrosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(desplazableLibroAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(142, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListaLibrosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelListaLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarLibro)
                            .addComponent(btnEditarLibro))
                        .addGap(72, 72, 72))))
        );

        desplazableLibroAutor.getAccessibleContext().setAccessibleName("desplazableLibroAutor");

        panelPrincipal.add(panelListaLibros, "panelListaLibros");

        txtBuscarLibro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comboBoxBuscarLibro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboBoxBuscarLibro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Título", "Categoría", "Autor", "ISBN" }));
        comboBoxBuscarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxBuscarLibroActionPerformed(evt);
            }
        });

        btnBuscarLibroPrestar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBuscarLibroPrestar.setText("Buscar");
        btnBuscarLibroPrestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLibroPrestarActionPerformed(evt);
            }
        });

        tablaLibros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN del libro", "Título del libro", "Categoría", "Cantidad", "Autor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        desplazableLibros.setViewportView(tablaLibros);
        if (tablaLibros.getColumnModel().getColumnCount() > 0) {
            tablaLibros.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaLibros.getColumnModel().getColumn(3).setMinWidth(20);
            tablaLibros.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        etiquetaBuscarLibro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiquetaBuscarLibro.setText("Buscar libro");

        etiquetaBuscarEstudiante.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiquetaBuscarEstudiante.setText("Buscar estudiante:");

        txtBuscarCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBuscarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarCodigoActionPerformed(evt);
            }
        });

        etiquetaCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiquetaCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaCodigo.setText("Código");

        tablaEstudiantes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        desplazableEstudiantes.setViewportView(tablaEstudiantes);

        btnPrestar.setBackground(new java.awt.Color(153, 153, 255));
        btnPrestar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPrestar.setText("Prestar");
        btnPrestar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestarActionPerformed(evt);
            }
        });

        btnBuscarEstudiante.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBuscarEstudiante.setText("Buscar");
        btnBuscarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEstudianteActionPerformed(evt);
            }
        });

        btnAgregarEstudiante.setBackground(new java.awt.Color(153, 153, 255));
        btnAgregarEstudiante.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarEstudiante.setText("Agregar estudiante");
        btnAgregarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEstudianteActionPerformed(evt);
            }
        });

        etiquetaTituloPrestar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        etiquetaTituloPrestar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaTituloPrestar.setText("Prestar libro");

        etiquetaFechaDevolucion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaFechaDevolucion.setText("Fecha de devolución:");

        selectorFechaDevolucion.setModel(new javax.swing.SpinnerDateModel());

        btnDeshacerPrestamo.setBackground(new java.awt.Color(153, 153, 255));
        btnDeshacerPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDeshacerPrestamo.setText("Deshacer último préstamo");
        btnDeshacerPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeshacerPrestamoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrestarLibroLayout = new javax.swing.GroupLayout(panelPrestarLibro);
        panelPrestarLibro.setLayout(panelPrestarLibroLayout);
        panelPrestarLibroLayout.setHorizontalGroup(
            panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrestarLibroLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrestarLibroLayout.createSequentialGroup()
                        .addComponent(etiquetaBuscarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiquetaCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarEstudiante)
                        .addGap(100, 100, 100)
                        .addComponent(btnAgregarEstudiante))
                    .addGroup(panelPrestarLibroLayout.createSequentialGroup()
                        .addComponent(etiquetaBuscarLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaTituloPrestar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarLibroPrestar)))
                    .addGroup(panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPrestarLibroLayout.createSequentialGroup()
                            .addComponent(desplazableEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeshacerPrestamo))
                        .addComponent(desplazableLibros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(458, Short.MAX_VALUE))
            .addGroup(panelPrestarLibroLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(etiquetaFechaDevolucion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectorFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(btnPrestar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelPrestarLibroLayout.setVerticalGroup(
            panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrestarLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaTituloPrestar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarLibroPrestar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrestarLibroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(desplazableLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addGroup(panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaBuscarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrestarLibroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarEstudiante)
                        .addGap(31, 31, 31)))
                .addGroup(panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrestarLibroLayout.createSequentialGroup()
                        .addComponent(desplazableEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrestarLibroLayout.createSequentialGroup()
                        .addComponent(btnDeshacerPrestamo)
                        .addGap(50, 50, 50)))
                .addGroup(panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrestarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etiquetaFechaDevolucion)
                        .addComponent(selectorFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPrestar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        panelPrincipal.add(panelPrestarLibro, "panelPrestarLibro");

        desplazableLibrosPrestados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tablaLibrosPrestados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaLibrosPrestados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID de préstamo", "Título del libro", "Código", "Nombre del estudiante", "Fecha de préstamo", "Fecha de devolución", "ISBN del libro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        desplazableLibrosPrestados.setViewportView(tablaLibrosPrestados);
        if (tablaLibrosPrestados.getColumnModel().getColumnCount() > 0) {
            tablaLibrosPrestados.getColumnModel().getColumn(0).setMinWidth(20);
            tablaLibrosPrestados.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaLibrosPrestados.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        btnDevolver.setBackground(new java.awt.Color(153, 153, 255));
        btnDevolver.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDevolver.setText("Devolver");
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });

        etiquetaCodigo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        etiquetaCodigo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaCodigo2.setText("Código");

        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnBuscarPorCodigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBuscarPorCodigo.setText("Buscar");
        btnBuscarPorCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPorCodigoActionPerformed(evt);
            }
        });

        etiquetaTituloDevolver.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        etiquetaTituloDevolver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaTituloDevolver.setText("Devolver libro");

        javax.swing.GroupLayout panelLibrosPrestadosLayout = new javax.swing.GroupLayout(panelLibrosPrestados);
        panelLibrosPrestados.setLayout(panelLibrosPrestadosLayout);
        panelLibrosPrestadosLayout.setHorizontalGroup(
            panelLibrosPrestadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLibrosPrestadosLayout.createSequentialGroup()
                .addGroup(panelLibrosPrestadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLibrosPrestadosLayout.createSequentialGroup()
                        .addGap(397, 397, 397)
                        .addComponent(btnDevolver))
                    .addGroup(panelLibrosPrestadosLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(panelLibrosPrestadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(desplazableLibrosPrestados, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLibrosPrestadosLayout.createSequentialGroup()
                                .addComponent(etiquetaCodigo2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarPorCodigo))))
                    .addGroup(panelLibrosPrestadosLayout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(etiquetaTituloDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(550, Short.MAX_VALUE))
        );
        panelLibrosPrestadosLayout.setVerticalGroup(
            panelLibrosPrestadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLibrosPrestadosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(etiquetaTituloDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLibrosPrestadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCodigo2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarPorCodigo))
                .addGap(18, 18, 18)
                .addComponent(desplazableLibrosPrestados, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelLibrosPrestados, "panelLibrosPrestados");

        desplazableDevolverHoy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tablaDevolverHoy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaDevolverHoy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID de préstamo", "Título del libro", "Código", "Fecha de préstamo", "Fecha de devolución", "ISBN del libro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        desplazableDevolverHoy.setViewportView(tablaDevolverHoy);

        etiquetaTituloDevolverHoy.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        etiquetaTituloDevolverHoy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaTituloDevolverHoy.setText("Libros a devolver hoy");

        javax.swing.GroupLayout panelDevolverHoyLayout = new javax.swing.GroupLayout(panelDevolverHoy);
        panelDevolverHoy.setLayout(panelDevolverHoyLayout);
        panelDevolverHoyLayout.setHorizontalGroup(
            panelDevolverHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDevolverHoyLayout.createSequentialGroup()
                .addGroup(panelDevolverHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDevolverHoyLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(desplazableDevolverHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDevolverHoyLayout.createSequentialGroup()
                        .addGap(428, 428, 428)
                        .addComponent(etiquetaTituloDevolverHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(451, Short.MAX_VALUE))
        );
        panelDevolverHoyLayout.setVerticalGroup(
            panelDevolverHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDevolverHoyLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(etiquetaTituloDevolverHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(desplazableDevolverHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelDevolverHoy, "panelDevolverHoy");

        etiquetaIsbn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiquetaIsbn.setText("ISBN del libro");

        txtIsbn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        etiquetaTitulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiquetaTitulo.setText("Título del libro");

        txtTitulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        etiquetaCategoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiquetaCategoria.setText("Categoría");

        etiquetaCantidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiquetaCantidad.setText("Cantidad");

        txtCategoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comboBoxCategoria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Idioma", "Programación", "Matemáticas", "Ciencias", "Historia", "Literatura", "Redes", "Bases de datos", "Novela histórica", "Fantasía", "Misterio y suspenso", "Romance", "Terror", "Biografías y memorias", "Filosofía", "Psicología", "Poesía", "Teatro", "Arte y fotografía", "Música", "Manuales y guías", "Cocina y gastronomía", "Salud y bienestar", "Autoayuda y desarrollo personal", "Viajes y turismo", "Deportes", "Cuentos ilustrados", "Literatura juvenil", "Libros educativos para niños", " " }));
        comboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCategoriaActionPerformed(evt);
            }
        });

        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnAgregar.setBackground(new java.awt.Color(153, 153, 255));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        etiquetaAutor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiquetaAutor.setText("Nombre del autor");

        txtNombreAutor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        etiquetaCorreoAutor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiquetaCorreoAutor.setText("Correo del autor");

        txtCorreoAutor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        etiquetaTituloAgregar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        etiquetaTituloAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaTituloAgregar.setText("Agregar libro");

        javax.swing.GroupLayout panelAgregarLibroLayout = new javax.swing.GroupLayout(panelAgregarLibro);
        panelAgregarLibro.setLayout(panelAgregarLibroLayout);
        panelAgregarLibroLayout.setHorizontalGroup(
            panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarLibroLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(etiquetaCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiquetaTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiquetaIsbn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiquetaAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiquetaCorreoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(etiquetaCantidad))
                .addGap(18, 18, 18)
                .addGroup(panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIsbn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNombreAutor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCorreoAutor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarLibroLayout.createSequentialGroup()
                        .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(comboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCantidad))
                .addGap(535, 535, 535))
            .addGroup(panelAgregarLibroLayout.createSequentialGroup()
                .addGroup(panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAgregarLibroLayout.createSequentialGroup()
                        .addGap(398, 398, 398)
                        .addComponent(etiquetaTituloAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAgregarLibroLayout.createSequentialGroup()
                        .addGap(468, 468, 468)
                        .addComponent(btnAgregar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAgregarLibroLayout.setVerticalGroup(
            panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaTituloAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTitulo)
                    .addComponent(etiquetaTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCorreoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panelAgregarLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etiquetaCantidad)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(btnAgregar)
                .addContainerGap(224, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelAgregarLibro, "panelAgregarLibro");
        panelAgregarLibro.getAccessibleContext().setAccessibleName("");
        panelAgregarLibro.getAccessibleContext().setAccessibleDescription("");

        btnCerrarSesion.setBackground(new java.awt.Color(153, 153, 255));
        btnCerrarSesion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnColaEspera.setBackground(new java.awt.Color(153, 102, 255));
        btnColaEspera.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnColaEspera.setForeground(new java.awt.Color(51, 51, 51));
        btnColaEspera.setText("Cola de espera");
        btnColaEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColaEsperaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgregarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnListarLibros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPrestarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnListarLibrosPrestados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDevolverHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnColaEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrarSesion)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnListarLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrestarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(btnListarLibrosPrestados, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDevolverHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnColaEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLibroActionPerformed
        CardLayout tarjetas = (CardLayout) panelPrincipal.getLayout();
        tarjetas.show(panelPrincipal, "panelAgregarLibro");
    }//GEN-LAST:event_btnAgregarLibroActionPerformed

    private void comboBoxBuscarPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxBuscarPorActionPerformed
        buscarPor = (String) comboBoxBuscarPor.getSelectedItem();
    }//GEN-LAST:event_comboBoxBuscarPorActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (buscarPor == null) {
            buscarPor = "Título"; // por defecto busca por título
        }

        String texto = txtBuscar.getText().trim();
        if (texto.length() == 0) {
            JOptionPane.showMessageDialog(panelBuscarLibro, "Por favor complete los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (buscarPor) {
            case "Título":
                tablaBusqueda.setModel(libroDAO.obtenerPorTitulo(texto));
                break;

            case "Categoría":
                tablaBusqueda.setModel(libroDAO.obtenerPorCategoria(texto));
                break;

            case "Autor":
                tablaBusqueda.setModel(libroDAO.obtenerPorAutor(texto));
                break;

            case "ISBN":
                tablaBusqueda.setModel(libroDAO.obtenerPorIsbn(texto));
                break;
        }

        if (tablaBusqueda.getModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(panelBuscarLibro, "No se encontraron registros", "Sin resultados", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void comboBoxBuscarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxBuscarLibroActionPerformed
        buscarLibroPor = (String) comboBoxBuscarLibro.getSelectedItem();
    }//GEN-LAST:event_comboBoxBuscarLibroActionPerformed

    private void btnBuscarLibroPrestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLibroPrestarActionPerformed
        if (buscarLibroPor == null) {
            buscarLibroPor = "Título"; // por defecto busca por título
        }

        String texto = txtBuscarLibro.getText().trim();
        if (texto.length() == 0) {
            JOptionPane.showMessageDialog(panelBuscarLibro, "Por favor complete los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (buscarLibroPor) {
            case "Título":
                tablaLibros.setModel(libroDAO.obtenerPorTitulo(texto));
                break;

            case "Categoría":
                tablaLibros.setModel(libroDAO.obtenerPorCategoria(texto));
                break;

            case "Autor":
                tablaLibros.setModel(libroDAO.obtenerPorAutor(texto));
                break;

            case "ISBN":
                tablaLibros.setModel(libroDAO.obtenerPorIsbn(texto));
                break;
        }

        if (tablaLibros.getModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(panelPrestarLibro, "No se encontraron registros", "Sin resultados", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarLibroPrestarActionPerformed

    private void txtBuscarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCodigoActionPerformed

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed
        int fila = tablaLibrosPrestados.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelLibrosPrestados, "No hay nada seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idPrestamo = Integer.parseInt((String) tablaLibrosPrestados.getModel().getValueAt(fila, 0));
        String isbn = (String) tablaLibrosPrestados.getModel().getValueAt(fila, 6);

        if (libroDAO.devolverLibro(idPrestamo, isbn)) {
            JOptionPane.showMessageDialog(panelLibrosPrestados, "Éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            fila = -1;
            tablaLibrosPrestados.setModel(libroDAO.listarLibrosPrestados());
        } else {
            JOptionPane.showMessageDialog(panelLibrosPrestados, "Ocurrió un error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnDevolverActionPerformed

    private void btnBuscarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLibroActionPerformed
        CardLayout tarjetas = (CardLayout) panelPrincipal.getLayout();
        tarjetas.show(panelPrincipal, "panelBuscarLibro");
    }//GEN-LAST:event_btnBuscarLibroActionPerformed

    private void btnListarLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarLibrosActionPerformed
        CardLayout tarjetas = (CardLayout) panelPrincipal.getLayout();
        tarjetas.show(panelPrincipal, "panelListaLibros");

        tablaLibroAutor.setModel(libroDAO.obtenerTablaLibroAutor());
        if (tablaLibroAutor.getModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(panelListaLibros, "No se encontraron registros", "Sin resultados", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnListarLibrosActionPerformed

    private void btnPrestarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestarLibroActionPerformed
        CardLayout tarjetas = (CardLayout) panelPrincipal.getLayout();
        tarjetas.show(panelPrincipal, "panelPrestarLibro");
    }//GEN-LAST:event_btnPrestarLibroActionPerformed

    private void btnListarLibrosPrestadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarLibrosPrestadosActionPerformed
        CardLayout tarjetas = (CardLayout) panelPrincipal.getLayout();
        tarjetas.show(panelPrincipal, "panelLibrosPrestados");
        tablaLibrosPrestados.setModel(libroDAO.listarLibrosPrestados());
        if (tablaLibrosPrestados.getModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(panelPrestarLibro, "No se encontraron registros", "Sin registros", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnListarLibrosPrestadosActionPerformed

    private void btnDevolverHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverHoyActionPerformed
        CardLayout tarjetas = (CardLayout) panelPrincipal.getLayout();
        tarjetas.show(panelPrincipal, "panelDevolverHoy");

        Calendar cal = Calendar.getInstance();
        Date fechaHoy = cal.getTime();
        tablaDevolverHoy.setModel(libroDAO.obtenerLibrosPorDevolver(fechaHoy));
        if (tablaDevolverHoy.getModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(panelDevolverHoy, "No hay libros por devolver hoy", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDevolverHoyActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String isbn = txtIsbn.getText().trim();
        String titulo = txtTitulo.getText().trim();
        String categoria = txtCategoria.getText().trim();
        String cantidadTexto = txtCantidad.getText().trim();
        String nombre = txtNombreAutor.getText().trim();
        String correo = txtCorreoAutor.getText().trim();

        if (isbn.equals("") || titulo.equals("") || categoria.equals("") || nombre.equals("") || correo.equals("") || cantidadTexto.equals("")) {
            JOptionPane.showMessageDialog(panelAgregarLibro, "Los campos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cantidadLibros;
        try {
            cantidadLibros = Integer.parseInt(cantidadTexto);
            if (cantidadLibros <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panelAgregarLibro, "Por favor ingrese una cantidad válida", "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (libroDAO.agregarLibro(new Libro(isbn, titulo, categoria, cantidadLibros)) && libroDAO.agregarAutor(new Autor(nombre, correo, isbn))) {
            JOptionPane.showMessageDialog(panelAgregarLibro, "Libro agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtIsbn.setText("");
            txtTitulo.setText("");
            txtCategoria.setText("");
            txtCantidad.setText("");
            txtNombreAutor.setText("");
            txtCorreoAutor.setText("");
        } else {
            JOptionPane.showMessageDialog(panelAgregarLibro, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEstudianteActionPerformed
        String codigo = txtBuscarCodigo.getText().trim();

        if (codigo.length() != 0) {
            tablaEstudiantes.setModel(libroDAO.obtenerEstudiantePorCodigo(codigo));
        } else {
            JOptionPane.showMessageDialog(panelPrestarLibro, "Por favor ingrese el nombre o el código", "Credenciales inválidas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (tablaEstudiantes.getModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(panelPrestarLibro, "No se encontraron registros", "Sin resultados", JOptionPane.ERROR_MESSAGE);
            DialogoAgregarEstudiante dialogo = new DialogoAgregarEstudiante(this, true);
            dialogo.setLocationRelativeTo(btnAgregarEstudiante);
            dialogo.setVisible(true);
            txtBuscarCodigo.setText(dialogo.codigo);
        }


    }//GEN-LAST:event_btnBuscarEstudianteActionPerformed

    private void btnAgregarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEstudianteActionPerformed
        DialogoAgregarEstudiante dialogo = new DialogoAgregarEstudiante(this, true);
        dialogo.setLocationRelativeTo(btnAgregarEstudiante);
        dialogo.setVisible(true);
        txtBuscarCodigo.setText(dialogo.codigo);
    }//GEN-LAST:event_btnAgregarEstudianteActionPerformed

    private void btnPrestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestarActionPerformed
        int indiceLibro = tablaLibros.getSelectedRow();
        int indiceEstudiante = tablaEstudiantes.getSelectedRow();
        if (indiceLibro == -1 || indiceEstudiante == -1) {
            JOptionPane.showMessageDialog(panelPrestarLibro, "Por favor seleccione un libro y un estudiante de la tabla", "Operación no válida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String codigo = (String) tablaEstudiantes.getModel().getValueAt(indiceEstudiante, 0);
        String nombre = (String) tablaEstudiantes.getModel().getValueAt(indiceEstudiante, 1);
        String isbn = (String) tablaLibros.getModel().getValueAt(indiceLibro, 0);
        int cantidadDisponible = Integer.parseInt((String) tablaLibros.getModel().getValueAt(indiceLibro, 3));

        if (cantidadDisponible == 0) {
            JOptionPane.showMessageDialog(panelPrestarLibro, "Libro no disponible", "No disponible", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date fechaPrestamo = new Date(); // el préstamo se registra con la fecha de hoy
        // Fecha de devolución: la que el usuario eligió en el selector
        Date fechaDevolucion = (Date) selectorFechaDevolucion.getValue();
        // Validación: la fecha de devolución no puede ser anterior a hoy
        Calendar inicioHoy = Calendar.getInstance();
        inicioHoy.set(Calendar.HOUR_OF_DAY, 0);
        inicioHoy.set(Calendar.MINUTE, 0);
        inicioHoy.set(Calendar.SECOND, 0);
        inicioHoy.set(Calendar.MILLISECOND, 0);
        if (fechaDevolucion.before(inicioHoy.getTime())) {
            JOptionPane.showMessageDialog(panelPrestarLibro, "La fecha de devolución no puede ser anterior a hoy.", "Fecha no válida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ((libroDAO.existeEstudiante(new Estudiante(codigo, nombre)) || libroDAO.agregarEstudiante(new Estudiante(codigo, nombre))) && libroDAO.prestarLibro(new Prestamo(codigo, fechaPrestamo, fechaDevolucion, isbn))) {
            // Guarda el préstamo en la pila (con su ID) para poder deshacerlo después
            Prestamo prestamoHecho = new Prestamo(codigo, fechaPrestamo, fechaDevolucion, isbn);
            prestamoHecho.setId(libroDAO.obtenerUltimoIdPrestamo(codigo, isbn));
            pilaPrestamos.apilar(prestamoHecho);
            JOptionPane.showMessageDialog(panelPrestarLibro, "Libro prestado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            indiceLibro = -1;
            tablaLibros.setModel(new DefaultTableModel());
        } else {
            JOptionPane.showMessageDialog(panelPrestarLibro, "Ocurrió un error.", "Operación no válida", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPrestarActionPerformed

    private void btnBuscarPorCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPorCodigoActionPerformed
        String codigo = txtCodigo.getText().trim();
        if (codigo.length() == 0) {
            JOptionPane.showMessageDialog(panelPrestarLibro, "Por favor ingrese el código", "Código inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tablaLibrosPrestados.setModel(libroDAO.listarPrestamosPorCodigo(codigo));
        if (tablaLibrosPrestados.getModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(panelPrestarLibro, "No se encontraron registros", "Sin registros", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarPorCodigoActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        this.dispose();
        new VentanaInicioSesion().setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void comboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCategoriaActionPerformed
        String val = (String) comboBoxCategoria.getSelectedItem();
        if (!val.equals("Seleccionar")) {
            txtCategoria.setText(val);
        }

    }//GEN-LAST:event_comboBoxCategoriaActionPerformed

    private void btnEliminarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLibroActionPerformed
        // Fila seleccionada en la tabla de libros
        int fila = tablaLibroAutor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelListaLibros, "Por favor, seleccione un libro de la tabla.", "Operación no válida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String isbn = (String) tablaLibroAutor.getModel().getValueAt(fila, 0);
        String titulo = (String) tablaLibroAutor.getModel().getValueAt(fila, 1);

        // Un libro con préstamos registrados no se puede eliminar
        if (libroDAO.tienePrestamos(isbn)) {
            JOptionPane.showMessageDialog(panelListaLibros, "No se puede eliminar: el libro tiene préstamos registrados.", "Operación no válida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Pide confirmación antes de borrar
        int respuesta = JOptionPane.showConfirmDialog(panelListaLibros, "¿Seguro que desea eliminar el libro \"" + titulo + "\"?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            if (libroDAO.eliminarLibro(isbn)) {
                JOptionPane.showMessageDialog(panelListaLibros, "Libro eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                // Vuelve a cargar la tabla para que desaparezca el libro
                tablaLibroAutor.setModel(libroDAO.obtenerTablaLibroAutor());
            } else {
                JOptionPane.showMessageDialog(panelListaLibros, "Se produjo un error.", "Operación no válida", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarLibroActionPerformed

    private void btnEditarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarLibroActionPerformed
        // Fila seleccionada en la tabla de libros
        int fila = tablaLibroAutor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(panelListaLibros, "Por favor, seleccione un libro de la tabla.", "Operación no válida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Datos actuales del libro (el ISBN no se puede cambiar)
        String isbn = (String) tablaLibroAutor.getModel().getValueAt(fila, 0);
        String tituloActual = (String) tablaLibroAutor.getModel().getValueAt(fila, 1);
        String categoriaActual = (String) tablaLibroAutor.getModel().getValueAt(fila, 2);
        String cantidadActual = (String) tablaLibroAutor.getModel().getValueAt(fila, 3);
        String autorActual = (String) tablaLibroAutor.getModel().getValueAt(fila, 4);
        String correoActual = libroDAO.obtenerCorreoAutor(isbn);

        // Campos del formulario, ya llenos con los datos actuales
        javax.swing.JTextField campoTitulo = new javax.swing.JTextField(tituloActual);
        javax.swing.JTextField campoCategoria = new javax.swing.JTextField(categoriaActual);
        javax.swing.JTextField campoCantidad = new javax.swing.JTextField(cantidadActual);
        javax.swing.JTextField campoAutor = new javax.swing.JTextField(autorActual);
        javax.swing.JTextField campoCorreo = new javax.swing.JTextField(correoActual);

        javax.swing.JPanel formulario = new javax.swing.JPanel(new java.awt.GridLayout(0, 2, 5, 5));
        formulario.add(new javax.swing.JLabel("ISBN del libro:"));
        formulario.add(new javax.swing.JLabel(isbn));
        formulario.add(new javax.swing.JLabel("Título del libro:"));
        formulario.add(campoTitulo);
        formulario.add(new javax.swing.JLabel("Categoría:"));
        formulario.add(campoCategoria);
        formulario.add(new javax.swing.JLabel("Cantidad:"));
        formulario.add(campoCantidad);
        formulario.add(new javax.swing.JLabel("Nombre del autor:"));
        formulario.add(campoAutor);
        formulario.add(new javax.swing.JLabel("Correo del autor:"));
        formulario.add(campoCorreo);

        int respuesta = JOptionPane.showConfirmDialog(panelListaLibros, formulario, "Editar libro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (respuesta != JOptionPane.OK_OPTION) {
            return; // el usuario canceló
        }

        // Validación: la cantidad debe ser un número entero no negativo
        int cantidad;
        try {
            cantidad = Integer.parseInt(campoCantidad.getText().trim());
            if (cantidad < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panelListaLibros, "La cantidad debe ser un número entero mayor o igual a 0.", "Operación no válida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (libroDAO.editarLibro(isbn, campoTitulo.getText().trim(), campoCategoria.getText().trim(), cantidad, campoAutor.getText().trim(), campoCorreo.getText().trim())) {
            JOptionPane.showMessageDialog(panelListaLibros, "Libro editado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Vuelve a cargar la tabla con los datos nuevos
            tablaLibroAutor.setModel(libroDAO.obtenerTablaLibroAutor());
        } else {
            JOptionPane.showMessageDialog(panelListaLibros, "Se produjo un error.", "Operación no válida", JOptionPane.ERROR_MESSAGE);
        }    }//GEN-LAST:event_btnEditarLibroActionPerformed

    private void btnColaEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColaEsperaActionPerformed
        // Abre la ventana de la cola de espera, pasándole la cola que guarda esta ventana
        new VentanaColaEspera(colaEspera).setVisible(true);    }//GEN-LAST:event_btnColaEsperaActionPerformed

    private void btnDeshacerPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshacerPrestamoActionPerformed
        // Mira el último préstamo hecho (la cima de la pila) sin sacarlo todavía
        Prestamo ultimo = pilaPrestamos.verCima();
        if (ultimo == null) {
            JOptionPane.showMessageDialog(panelPrestarLibro, "No hay préstamos para deshacer.", "Pila vacía", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Pide confirmación antes de deshacer
        int respuesta = JOptionPane.showConfirmDialog(panelPrestarLibro,
                "¿Deshacer el último préstamo?\nEstudiante (código): " + ultimo.getCodigo() + "\nLibro (ISBN): " + ultimo.getIsbnLibro(),
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        // Deshacer = borrar el préstamo y devolver el libro al inventario
        if (libroDAO.devolverLibro(ultimo.getId(), ultimo.getIsbnLibro())) {
            pilaPrestamos.desapilar(); // ahora sí se saca de la pila
            JOptionPane.showMessageDialog(panelPrestarLibro, "Préstamo deshecho correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            tablaLibros.setModel(new DefaultTableModel()); // limpia la tabla de libros para que se vuelva a buscar
        } else {
            JOptionPane.showMessageDialog(panelPrestarLibro, "Se produjo un error.", "Operación no válida", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeshacerPrestamoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (!VentanaInicioSesion.sesionIniciada) {
                    new VentanaInicioSesion().setVisible(true);
                    return;
                }

                new VentanaPrincipal().setVisible(true);
            }
        });
    }
    LibroDAO libroDAO = new LibroDAO();
    String buscarPor = null, buscarLibroPor = null;
    //int cantidadLibros = 0;
    //String isbn, codigo, titulo, categoria, nombre, correo;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarEstudiante;
    private javax.swing.JButton btnAgregarLibro;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarEstudiante;
    private javax.swing.JButton btnBuscarLibro;
    private javax.swing.JButton btnBuscarLibroPrestar;
    private javax.swing.JButton btnBuscarPorCodigo;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnColaEspera;
    private javax.swing.JButton btnDeshacerPrestamo;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnDevolverHoy;
    private javax.swing.JButton btnEditarLibro;
    private javax.swing.JButton btnEliminarLibro;
    private javax.swing.JButton btnListarLibros;
    private javax.swing.JButton btnListarLibrosPrestados;
    private javax.swing.JButton btnPrestar;
    private javax.swing.JButton btnPrestarLibro;
    private javax.swing.JComboBox<String> comboBoxBuscarLibro;
    private javax.swing.JComboBox<String> comboBoxBuscarPor;
    private javax.swing.JComboBox<String> comboBoxCategoria;
    private javax.swing.JScrollPane desplazableBusqueda;
    private javax.swing.JScrollPane desplazableDevolverHoy;
    private javax.swing.JScrollPane desplazableEstudiantes;
    private javax.swing.JScrollPane desplazableLibroAutor;
    private javax.swing.JScrollPane desplazableLibros;
    private javax.swing.JScrollPane desplazableLibrosPrestados;
    private javax.swing.JLabel etiquetaAutor;
    private javax.swing.JLabel etiquetaBuscarEstudiante;
    private javax.swing.JLabel etiquetaBuscarLibro;
    private javax.swing.JLabel etiquetaCantidad;
    private javax.swing.JLabel etiquetaCategoria;
    private javax.swing.JLabel etiquetaCodigo;
    private javax.swing.JLabel etiquetaCodigo2;
    private javax.swing.JLabel etiquetaCorreoAutor;
    private javax.swing.JLabel etiquetaFechaDevolucion;
    private javax.swing.JLabel etiquetaIsbn;
    private javax.swing.JLabel etiquetaTitulo;
    private javax.swing.JLabel etiquetaTituloAgregar;
    private javax.swing.JLabel etiquetaTituloBuscar;
    private javax.swing.JLabel etiquetaTituloDevolver;
    private javax.swing.JLabel etiquetaTituloDevolverHoy;
    private javax.swing.JLabel etiquetaTituloLista;
    private javax.swing.JLabel etiquetaTituloPrestar;
    private javax.swing.JPanel panelAgregarLibro;
    private javax.swing.JPanel panelBuscarLibro;
    private javax.swing.JPanel panelDevolverHoy;
    private javax.swing.JPanel panelLibrosPrestados;
    private javax.swing.JPanel panelListaLibros;
    private javax.swing.JPanel panelPrestarLibro;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JSpinner selectorFechaDevolucion;
    private javax.swing.JTable tablaBusqueda;
    private javax.swing.JTable tablaDevolverHoy;
    private javax.swing.JTable tablaEstudiantes;
    private javax.swing.JTable tablaLibroAutor;
    private javax.swing.JTable tablaLibros;
    private javax.swing.JTable tablaLibrosPrestados;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarCodigo;
    private javax.swing.JTextField txtBuscarLibro;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCorreoAutor;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtNombreAutor;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}

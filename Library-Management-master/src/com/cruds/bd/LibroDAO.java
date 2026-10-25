package com.cruds.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cruds.modelo.Autor;
import com.cruds.modelo.Libro;
import com.cruds.modelo.Prestamo;
import com.cruds.modelo.Estudiante;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class LibroDAO {

    public boolean agregarLibro(Libro libro) {
        String consulta = "insert into libro(isbn_libro, titulo_libro, categoria, cantidad_libros) values(?, ?, ?, ?)";
        int filas = 0;

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, libro.getIsbn());
            sentencia.setString(2, libro.getTitulo());
            sentencia.setString(3, libro.getCategoria());
            sentencia.setInt(4, libro.getCantidad());

            filas = sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filas > 0;
    }

    // Elimina un libro: primero sus autores (tabla hija) y luego el libro
    public boolean eliminarLibro(String isbn) {
        String borrarAutor = "delete from autor where isbn_libro = ?";
        String borrarLibro = "delete from libro where isbn_libro = ?";
        int filas = 0;

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentenciaAutor = conexion.prepareStatement(borrarAutor);
            sentenciaAutor.setString(1, isbn);
            sentenciaAutor.executeUpdate();

            PreparedStatement sentenciaLibro = conexion.prepareStatement(borrarLibro);
            sentenciaLibro.setString(1, isbn);
            filas = sentenciaLibro.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filas > 0;
    }

    // Edita los datos de un libro y de su autor (el ISBN no se cambia: identifica al libro)
    public boolean editarLibro(String isbn, String titulo, String categoria, int cantidad, String nombreAutor, String correoAutor) {
        String actualizarLibro = "update libro set titulo_libro = ?, categoria = ?, cantidad_libros = ? where isbn_libro = ?";
        String actualizarAutor = "update autor set nombre_autor = ?, correo_autor = ? where isbn_libro = ?";
        int filas = 0;

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentenciaLibro = conexion.prepareStatement(actualizarLibro);
            sentenciaLibro.setString(1, titulo);
            sentenciaLibro.setString(2, categoria);
            sentenciaLibro.setInt(3, cantidad);
            sentenciaLibro.setString(4, isbn);
            filas = sentenciaLibro.executeUpdate();

            PreparedStatement sentenciaAutor = conexion.prepareStatement(actualizarAutor);
            sentenciaAutor.setString(1, nombreAutor);
            sentenciaAutor.setString(2, correoAutor);
            sentenciaAutor.setString(3, isbn);
            sentenciaAutor.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filas > 0;
    }

    public boolean agregarAutor(Autor autor) {
        String consulta = "insert into autor(nombre_autor, correo_autor, isbn_libro) values(?, ?, ?)";
        int filas = 0;

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, autor.getNombre());
            sentencia.setString(2, autor.getCorreo());
            sentencia.setString(3, autor.getIsbnLibro());

            filas = sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filas > 0;
    }

    public boolean agregarEstudiante(Estudiante estudiante) {
        String consulta = "insert into estudiante(codigo, nombre) values(?, ?)";
        int filas = 0;

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, estudiante.getCodigo());
            sentencia.setString(2, estudiante.getNombre());

            filas = sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filas >= 0;
    }

    public boolean existeEstudiante(Estudiante estudiante) {
        String consulta = "select codigo, nombre from estudiante where codigo = ?";
        boolean existe = false;

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, estudiante.getCodigo());
            ResultSet resultado = sentencia.executeQuery();
            if (resultado != null && resultado.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }

    public DefaultTableModel obtenerEstudiantePorCodigo(String codigo) {
        String consulta = "select codigo, nombre from estudiante where LOWER(codigo) = ?";
        Vector<String> nombresColumnas = new Vector<>();
        nombresColumnas.add("Código");
        nombresColumnas.add("Nombre");

        Vector<Vector<String>> datos = new Vector<>();

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, codigo.toLowerCase());

            ResultSet resultado = sentencia.executeQuery();
            while (resultado != null && resultado.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(resultado.getString(1));
                fila.add(resultado.getString(2));
                datos.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(datos, nombresColumnas);
    }

    public DefaultTableModel obtenerEstudiantePorNombre(String nombre) {
        String consulta = "select codigo, nombre from estudiante where LOWER(nombre) = ?";
        Vector<String> nombresColumnas = new Vector<>();
        nombresColumnas.add("Código");
        nombresColumnas.add("Nombre");

        Vector<Vector<String>> datos = new Vector<>();

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombre.toLowerCase());

            ResultSet resultado = sentencia.executeQuery();
            while (resultado != null && resultado.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(resultado.getString(1));
                fila.add(resultado.getString(2));
                datos.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(datos, nombresColumnas);
    }

    public DefaultTableModel obtenerPorTitulo(String titulo) {
        String consulta = "select l.isbn_libro, l.titulo_libro, l.categoria, l.cantidad_libros, a.nombre_autor from libro l, autor a where a.isbn_libro = l.isbn_libro and LOWER(l.titulo_libro) like ? ";
        Vector<String> nombresColumnas = new Vector<>();
        nombresColumnas.add("ISBN del libro");
        nombresColumnas.add("Título del libro");
        nombresColumnas.add("Categoría");
        nombresColumnas.add("Cantidad");
        nombresColumnas.add("Autor");

        Vector<Vector<String>> datos = new Vector<>();

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, "%" + titulo.toLowerCase() + "%");

            ResultSet resultado = sentencia.executeQuery();
            while (resultado != null && resultado.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(resultado.getString(1));
                fila.add(resultado.getString(2));
                fila.add(resultado.getString(3));
                fila.add(String.valueOf(resultado.getInt(4)));
                fila.add(resultado.getString(5));
                datos.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(datos, nombresColumnas);
    }

    public DefaultTableModel obtenerPorCategoria(String categoria) {
        String consulta = "select l.isbn_libro, l.titulo_libro, l.categoria, l.cantidad_libros, a.nombre_autor from libro l, autor a where a.isbn_libro = l.isbn_libro and LOWER(l.categoria) like ?";
        Vector<String> nombresColumnas = new Vector<>();
        nombresColumnas.add("ISBN del libro");
        nombresColumnas.add("Título del libro");
        nombresColumnas.add("Categoría");
        nombresColumnas.add("Cantidad");
        nombresColumnas.add("Autor");

        Vector<Vector<String>> datos = new Vector<>();

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, "%" + categoria.toLowerCase() + "%");
            ResultSet resultado = sentencia.executeQuery();

            while (resultado != null && resultado.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(resultado.getString(1));
                fila.add(resultado.getString(2));
                fila.add(resultado.getString(3));
                fila.add(String.valueOf(resultado.getInt(4)));
                fila.add(resultado.getString(5));
                datos.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(datos, nombresColumnas);
    }

    public DefaultTableModel obtenerPorAutor(String nombre) {
        String consulta = "select l.isbn_libro, l.titulo_libro, l.categoria, l.cantidad_libros, a.nombre_autor from libro l, autor a where l.isbn_libro = a.isbn_libro and LOWER(a.nombre_autor) like ?";
        Vector<String> nombresColumnas = new Vector<>();
        nombresColumnas.add("ISBN del libro");
        nombresColumnas.add("Título del libro");
        nombresColumnas.add("Categoría");
        nombresColumnas.add("Cantidad");
        nombresColumnas.add("Autor");

        Vector<Vector<String>> datos = new Vector<>();

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, "%" + nombre.toLowerCase() + "%");
            ResultSet resultado = sentencia.executeQuery();

            while (resultado != null && resultado.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(resultado.getString(1));
                fila.add(resultado.getString(2));
                fila.add(resultado.getString(3));
                fila.add(String.valueOf(resultado.getInt(4)));
                fila.add(resultado.getString(5));
                datos.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(datos, nombresColumnas);
    }

    public DefaultTableModel obtenerPorIsbn(String isbn) {
        String consulta = "select l.isbn_libro, l.titulo_libro, l.categoria, l.cantidad_libros, a.nombre_autor from libro l, autor a where a.isbn_libro = l.isbn_libro and LOWER(l.isbn_libro) = ?";
        Vector<String> nombresColumnas = new Vector<>();
        nombresColumnas.add("ISBN del libro");
        nombresColumnas.add("Título del libro");
        nombresColumnas.add("Categoría");
        nombresColumnas.add("Cantidad");
        nombresColumnas.add("Autor");

        Vector<Vector<String>> datos = new Vector<>();

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, isbn.toLowerCase());
            ResultSet resultado = sentencia.executeQuery();

            while (resultado != null && resultado.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(resultado.getString(1));
                fila.add(resultado.getString(2));
                fila.add(resultado.getString(3));
                fila.add(String.valueOf(resultado.getInt(4)));
                fila.add(resultado.getString(5));
                datos.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(datos, nombresColumnas);
    }

    public DefaultTableModel obtenerTablaLibroAutor() {
        String consulta = "select l.isbn_libro, l.titulo_libro, l.categoria, l.cantidad_libros, a.nombre_autor from libro l, autor a where a.isbn_libro = l.isbn_libro";
        Vector<String> nombresColumnas = new Vector<>();
        nombresColumnas.add("ISBN del libro");
        nombresColumnas.add("Título del libro");
        nombresColumnas.add("Categoría");
        nombresColumnas.add("Cantidad");
        nombresColumnas.add("Autor");

        Vector<Vector<String>> datos = new Vector<>();

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado != null && resultado.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(resultado.getString(1));
                fila.add(resultado.getString(2));
                fila.add(resultado.getString(3));
                fila.add(String.valueOf(resultado.getInt(4)));
                fila.add(resultado.getString(5));
                datos.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(datos, nombresColumnas);
    }

    public boolean prestarLibro(Prestamo prestamo) {
        String consulta = "insert into prestamo(id_prestamo, codigo, fecha_prestamo, fecha_devolucion, isbn_libro) values(?, ?, ?, ?, ?)";
        String consultaConteo = "update libro set cantidad_libros = cantidad_libros - 1 where isbn_libro = ?";
        int filas = 0;
        int filasConteo = 0;
        java.sql.Date fechaPrestamoSql = new java.sql.Date(prestamo.getFechaPrestamo().getTime());
        java.sql.Date fechaDevolucionSql = new java.sql.Date(prestamo.getFechaDevolucion().getTime());

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            // Primero descuenta una unidad del libro
            PreparedStatement sentenciaConteo = conexion.prepareStatement(consultaConteo);
            sentenciaConteo.setString(1, prestamo.getIsbnLibro());
            filasConteo = sentenciaConteo.executeUpdate();

            // Luego registra el préstamo
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, prestamo.getId());
            sentencia.setString(2, prestamo.getCodigo());
            sentencia.setDate(3, fechaPrestamoSql);
            sentencia.setDate(4, fechaDevolucionSql);
            sentencia.setString(5, prestamo.getIsbnLibro());
            filas = sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filas > 0 && filasConteo > 0;
    }

    public DefaultTableModel listarPrestamosPorCodigo(String codigo) {
        String consulta = "select p.id_prestamo, l.titulo_libro, p.codigo, e.nombre, p.fecha_prestamo, p.fecha_devolucion, p.isbn_libro  from libro l, estudiante e, prestamo p where l.isbn_libro = p.isbn_libro and p.codigo = e.codigo and LOWER(p.codigo) = ?";
        Vector<String> nombresColumnas = new Vector<>();
        nombresColumnas.add("ID");
        nombresColumnas.add("Título del libro");
        nombresColumnas.add("Código");
        nombresColumnas.add("Nombre del estudiante");
        nombresColumnas.add("Fecha de préstamo");
        nombresColumnas.add("Fecha de devolución");
        nombresColumnas.add("ISBN");

        Vector<Vector<String>> datos = new Vector<>();

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, codigo);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado != null && resultado.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(String.valueOf(resultado.getInt(1)));
                fila.add(resultado.getString(2));
                fila.add(resultado.getString(3));
                fila.add(resultado.getString(4));
                fila.add(String.valueOf(resultado.getDate(5)));
                fila.add(String.valueOf(resultado.getDate(6)));
                fila.add(resultado.getString(7));
                datos.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(datos, nombresColumnas);
    }

    public DefaultTableModel listarLibrosPrestados() {
        String consulta = "select p.id_prestamo, l.titulo_libro, p.codigo, e.nombre, p.fecha_prestamo, p.fecha_devolucion, p.isbn_libro  from libro l, estudiante e, prestamo p where l.isbn_libro = p.isbn_libro and p.codigo = e.codigo";
        Vector<String> nombresColumnas = new Vector<>();
        nombresColumnas.add("ID");
        nombresColumnas.add("Título del libro");
        nombresColumnas.add("Código");
        nombresColumnas.add("Nombre del estudiante");
        nombresColumnas.add("Fecha de préstamo");
        nombresColumnas.add("Fecha de devolución");
        nombresColumnas.add("ISBN");

        Vector<Vector<String>> datos = new Vector<>();

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado != null && resultado.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(String.valueOf(resultado.getInt(1)));
                fila.add(resultado.getString(2));
                fila.add(resultado.getString(3));
                fila.add(resultado.getString(4));
                fila.add(String.valueOf(resultado.getDate(5)));
                fila.add(String.valueOf(resultado.getDate(6)));
                fila.add(resultado.getString(7));
                datos.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(datos, nombresColumnas);
    }

    public DefaultTableModel obtenerLibrosPorDevolver(Date fechaActual) {
        String consulta = "select p.id_prestamo, l.titulo_libro, p.codigo, e.nombre, p.fecha_prestamo, p.fecha_devolucion, p.isbn_libro  from libro l, estudiante e, prestamo p where l.isbn_libro = p.isbn_libro and p.codigo = e.codigo and p.fecha_devolucion = ?";
        Vector<String> nombresColumnas = new Vector<>();
        nombresColumnas.add("ID");
        nombresColumnas.add("Título del libro");
        nombresColumnas.add("Código");
        nombresColumnas.add("Nombre del estudiante");
        nombresColumnas.add("Fecha de préstamo");
        nombresColumnas.add("Fecha de devolución");
        nombresColumnas.add("ISBN");

        Vector<Vector<String>> datos = new Vector<>();
        java.sql.Date fechaActualSql = new java.sql.Date(fechaActual.getTime());

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setDate(1, fechaActualSql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado != null && resultado.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(String.valueOf(resultado.getInt(1)));
                fila.add(resultado.getString(2));
                fila.add(resultado.getString(3));
                fila.add(resultado.getString(4));
                fila.add(String.valueOf(resultado.getDate(5)));
                fila.add(String.valueOf(resultado.getDate(6)));
                fila.add(resultado.getString(7));
                datos.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(datos, nombresColumnas);
    }

    public boolean devolverLibro(int id, String isbn) {
        String consulta = "Delete from prestamo where id_prestamo = ? ";
        String consultaConteo = "update libro set cantidad_libros = cantidad_libros+1 where isbn_libro = ? ";

        int filas = 0;
        int filasConteo = 0;

        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            // Borra el préstamo
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, id);
            filas = sentencia.executeUpdate();

            // Devuelve una unidad al libro
            PreparedStatement sentenciaConteo = conexion.prepareStatement(consultaConteo);
            sentenciaConteo.setString(1, isbn);
            filasConteo = sentenciaConteo.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ((filas > 0) && (filasConteo > 0));
    }

    public String[] obtenerTodasCategorias() {
        // En MySQL se usa "distinct" (en el original decía "unique", que no existe)
        String consulta = "Select distinct categoria from libro";
        List<String> lista = new ArrayList<>();
        lista.add("Seleccionar");
        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado != null && resultado.next()) {
                lista.add(resultado.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] categorias = new String[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            categorias[i] = lista.get(i);
        }

        return categorias;
    }

    // Indica si el libro tiene préstamos registrados (en ese caso no se puede eliminar)
    public boolean tienePrestamos(String isbn) {
        String consulta = "select count(*) from prestamo where isbn_libro = ?";
        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, isbn);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return resultado.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Devuelve el correo del autor de un libro (para mostrarlo al editar)
    public String obtenerCorreoAutor(String isbn) {
        String consulta = "select correo_autor from autor where isbn_libro = ?";
        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, isbn);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return resultado.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Devuelve el ID del préstamo más reciente de un estudiante para un libro (0 si no hay)
    public int obtenerUltimoIdPrestamo(String codigo, String isbn) {
        String consulta = "select max(id_prestamo) from prestamo where codigo = ? and isbn_libro = ?";
        try (Connection conexion = GestorConexionBD.obtenerConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, codigo);
            sentencia.setString(2, isbn);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return resultado.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}

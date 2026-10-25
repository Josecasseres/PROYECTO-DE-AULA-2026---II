package com.cruds.modelo;

import java.util.Date;

public class Prestamo {

    private int id;
    public String codigo;
    public Date fechaPrestamo;
    public Date fechaDevolucion;
    public String isbnLibro;

    public Prestamo(String codigo, Date fechaPrestamo, Date fechaDevolucion, String isbnLibro) {
        this.codigo = codigo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.isbnLibro = isbnLibro;
    }

    public int getId() {
        return id;
    }

    // Guarda el ID que la base de datos le asignó al préstamo
    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    public void setIsbnLibro(String isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    @Override
    public String toString() {
        return "" + id + "\t" + codigo + "\t" + fechaPrestamo + "\t" + fechaDevolucion + "\t" + isbnLibro;
    }

}

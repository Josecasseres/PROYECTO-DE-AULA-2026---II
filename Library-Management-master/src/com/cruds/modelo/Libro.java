package com.cruds.modelo;

public class Libro {

	private String isbn;
	private String titulo;
	private String categoria;
	private int cantidad;

	public Libro(String isbn, String titulo, String categoria, int cantidad) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.categoria = categoria;
		this.cantidad = cantidad;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "" + isbn + "\t\t" + titulo + "\t\t" + categoria + "\t\t" + cantidad;
	}

}
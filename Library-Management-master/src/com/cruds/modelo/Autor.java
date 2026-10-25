package com.cruds.modelo;

public class Autor {
	
	private String nombre;
	private String correo;
	public String isbnLibro;
	
	public Autor(String nombre, String correo, String isbnLibro) {
		this.nombre = nombre;
		this.correo = correo;
		this.isbnLibro = isbnLibro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getIsbnLibro() {
		return isbnLibro;
	}

	public void setIsbnLibro(String isbnLibro) {
		this.isbnLibro = isbnLibro;
	}

	@Override
	public String toString() {
		return "" + nombre + "\t" + correo + "\t" + isbnLibro;
	}
	
	

}

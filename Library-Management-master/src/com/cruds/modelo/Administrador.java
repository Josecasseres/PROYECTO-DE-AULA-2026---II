
package com.cruds.modelo;

public class Administrador {
    String id;
    String contrasena;
    
    public Administrador(String id, String contrasena)
    {
        this.id = id;
        this.contrasena = contrasena;
    }
    
    public String getId() {
	return id;
    }
    
    public String getContrasena() {
	return contrasena;
    }
    
}

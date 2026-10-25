package com.cruds.bd;

import java.sql.Connection;

public class PruebaConexionBD {

    public static void main(String[] args) {

        Connection conexion = GestorConexionBD.obtenerConexion();

        if(conexion != null) {
            System.out.println("Conexión exitosa");
        }

    }

}
package com.cruds.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorConexionBD {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection obtenerConexion() {

        Connection conexion = null;
        try {
            // La contraseña va vacía porque XAMPP crea el usuario root sin contraseña
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_biblioteca?useUnicode=true&characterEncoding=UTF-8", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;

    }

}
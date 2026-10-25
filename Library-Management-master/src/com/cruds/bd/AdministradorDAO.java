package com.cruds.bd;

import com.cruds.modelo.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministradorDAO {

    public boolean esAdministradorValido(Administrador administrador)
    {
        // Consulta con los nombres de tabla y columnas en español
        String consulta = "select id_administrador, contrasena from administrador where id_administrador = ? and contrasena = ?";

        try(Connection conexion = GestorConexionBD.obtenerConexion())
        {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, administrador.getId());
            sentencia.setString(2, administrador.getContrasena());
            ResultSet resultado = sentencia.executeQuery();
            while(resultado != null && resultado.next())
            {
                if(resultado.getString(1).equals(administrador.getId()) && resultado.getString(2).equals(administrador.getContrasena()))
                {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
package com.empresa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/sistema_reservas?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
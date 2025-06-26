package com.empresa.tests;

import java.sql.Connection;
import java.sql.SQLException;

import com.empresa.util.ConexionMySQL;

public class PruebaConexion {
    public static void main(String[] args) {
        final int MAX_INTENTOS = 5;
        int intento = 1;
        while (intento <= MAX_INTENTOS) {
            try (Connection conexion = ConexionMySQL.obtenerConexion()) {
                System.out.println("Conexión exitosa a la base de datos.");
                return;
            } catch (SQLException e) {
                System.out.println("Intento " + intento + ": Error al conectar a la base de datos: " + e.getMessage());
                intento++;
                try {
                    Thread.sleep(3000); // espera 3 segundos
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("No se pudo establecer conexión tras " + MAX_INTENTOS + " intentos.");
    }
}


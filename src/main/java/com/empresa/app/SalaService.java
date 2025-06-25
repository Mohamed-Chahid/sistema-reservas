package com.empresa.app;

import com.empresa.util.ConexionMySQL;
import java.sql.*;
import java.util.Scanner;

public class SalaService {
    public static void listarSalas() {
        try (Connection conn = ConexionMySQL.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM sala")) {
            System.out.println("\n--- Lista de Salas ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Nombre: " + rs.getString("nombre") +
                        ", Capacidad: " + rs.getInt("capacidad") +
                        ", Recursos: " + rs.getString("recursos"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar salas: " + e.getMessage());
        }
    }

    public static void anadirSala(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        System.out.print("Nombre de la sala: ");
        String nombre = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(nombre)) return;
        System.out.print("Capacidad: ");
        String capacidadStr = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(capacidadStr)) return;
        int capacidad;
        try {
            capacidad = Integer.parseInt(capacidadStr);
        } catch (NumberFormatException e) {
            System.out.println("Capacidad no válida. Operación cancelada.");
            return;
        }
        System.out.print("Recursos: ");
        String recursos = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(recursos)) return;
        String sql = "INSERT INTO sala (nombre, capacidad, recursos) VALUES (?, ?, ?)";
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, capacidad);
            pstmt.setString(3, recursos);
            pstmt.executeUpdate();
            System.out.println("Sala añadida correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al añadir sala: " + e.getMessage());
        }
    }

    public static void modificarSala(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        System.out.print("ID de la sala a modificar: ");
        String idStr = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(idStr)) return;
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID no válido. Operación cancelada.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(nombre)) return;
        System.out.print("Nueva capacidad: ");
        String capacidadStr = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(capacidadStr)) return;
        int capacidad;
        try {
            capacidad = Integer.parseInt(capacidadStr);
        } catch (NumberFormatException e) {
            System.out.println("Capacidad no válida. Operación cancelada.");
            return;
        }
        System.out.print("Nuevos recursos: ");
        String recursos = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(recursos)) return;
        String sql = "UPDATE sala SET nombre = ?, capacidad = ?, recursos = ? WHERE id = ?";
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, capacidad);
            pstmt.setString(3, recursos);
            pstmt.setInt(4, id);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Sala modificada correctamente.");
            } else {
                System.out.println("No se encontró la sala con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar sala: " + e.getMessage());
        }
    }

    public static void eliminarSala(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        System.out.print("ID de la sala a eliminar: ");
        String idStr = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(idStr)) return;
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID no válido. Operación cancelada.");
            return;
        }
        String sql = "DELETE FROM sala WHERE id = ?";
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Sala eliminada correctamente.");
            } else {
                System.out.println("No se encontró la sala con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar sala: " + e.getMessage());
        }
    }
}

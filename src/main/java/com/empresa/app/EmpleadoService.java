package com.empresa.app;

import com.empresa.util.ConexionMySQL;
import java.sql.*;
import java.util.Scanner;

public class EmpleadoService {
    public static void listarEmpleados() {
        try (Connection conn = ConexionMySQL.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM empleado")) {
            System.out.println("\n--- Lista de Empleados ---");
            while (rs.next()) {
                System.out.println("Número: " + rs.getInt("num_empleado") +
                        ", Nombre: " + rs.getString("nombre") +
                        ", Email: " + rs.getString("email") +
                        ", Departamento: " + rs.getString("departamento"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
        }
    }

    public static void anadirEmpleado(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        System.out.print("Nombre del empleado: ");
        String nombre = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(nombre)) return;
        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(email)) return;
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(departamento)) return;
        String sql = "INSERT INTO empleado (nombre, email, departamento) VALUES (?, ?, ?)";
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, email);
            pstmt.setString(3, departamento);
            pstmt.executeUpdate();
            System.out.println("Empleado añadido correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al añadir empleado: " + e.getMessage());
        }
    }

    public static void modificarEmpleado(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        System.out.print("Número del empleado a modificar: ");
        String numEmpleadoStr = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(numEmpleadoStr)) return;
        int num_empleado;
        try {
            num_empleado = Integer.parseInt(numEmpleadoStr);
        } catch (NumberFormatException e) {
            System.out.println("Número no válido. Operación cancelada.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(nombre)) return;
        System.out.print("Nuevo email: ");
        String email = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(email)) return;
        System.out.print("Nuevo departamento: ");
        String departamento = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(departamento)) return;
        String sql = "UPDATE empleado SET nombre = ?, email = ?, departamento = ? WHERE num_empleado = ?";
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, email);
            pstmt.setString(3, departamento);
            pstmt.setInt(4, num_empleado);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Empleado modificado correctamente.");
            } else {
                System.out.println("No se encontró el empleado con ese número.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar empleado: " + e.getMessage());
        }
    }

    public static void eliminarEmpleado(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        System.out.print("Número del empleado a eliminar: ");
        String numEmpleadoStr = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(numEmpleadoStr)) return;
        int num_empleado;
        try {
            num_empleado = Integer.parseInt(numEmpleadoStr);
        } catch (NumberFormatException e) {
            System.out.println("Número no válido. Operación cancelada.");
            return;
        }
        String sql = "DELETE FROM empleado WHERE num_empleado = ?";
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, num_empleado);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Empleado eliminado correctamente.");
            } else {
                System.out.println("No se encontró el empleado con ese número.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
        }
    }
}

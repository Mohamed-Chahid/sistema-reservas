package com.empresa.app;

import com.empresa.util.ConexionMySQL;
import java.sql.*;
import java.util.Scanner;

/**
 * Servicio para la gestión de empleados.
 * Incluye operaciones CRUD, validaciones y mensajes claros para el usuario.
 */
public class EmpleadoService {

    /**
     * Lista todos los empleados existentes en el sistema.
     * Muestra el número, nombre, email y departamento de cada empleado.
     */
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

    /**
     * Añade un nuevo empleado tras validar los campos obligatorios, longitud y formato de email.
     * <p>
     * Solicita los datos  y muestra mensajes de error claros si alguna validación falla.
     * Si la inserción es exitosa, informa al usuario.
     * </p>
     * @param scanner Scanner para entrada de datos del usuario
     */
    public static void anadirEmpleado(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        System.out.print("Nombre del empleado: ");
        String nombre = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(nombre)) return;
        if (!Utilidades.validarLongitud(nombre, 100, "nombre")) return;
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre es obligatorio. Operación cancelada.");
            return;
        }
        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(email)) return;
        if (!Utilidades.validarLongitud(email, 100, "email")) return;
        if (email.trim().isEmpty()) {
            System.out.println("El email es obligatorio. Operación cancelada.");
            return;
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("El email no tiene un formato válido. Operación cancelada.");
            return;
        }
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(departamento)) return;
        if (!Utilidades.validarLongitud(departamento, 100, "departamento")) return;
        if (departamento.trim().isEmpty()) {
            System.out.println("El departamento es obligatorio. Operación cancelada.");
            return;
        }
        String sql = "INSERT INTO empleado (nombre, email, departamento) VALUES (?, ?, ?)";
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, email);
            pstmt.setString(3, departamento);
            pstmt.executeUpdate();
            System.out.println("Empleado añadido correctamente.");
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("Error: El email ya está registrado en otro empleado.");
            } else {
                System.out.println("Error al añadir empleado: " + e.getMessage());
            }
        }
    }

    /**
     * Modifica un empleado existente tras validar los campos obligatorios.
     * <p>
     * Solicita los nuevos datos.
     * </p>
     * @param scanner Scanner para entrada de datos del usuario
     */
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
        if (!Utilidades.validarLongitud(nombre, 100, "nombre")) return;
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre es obligatorio. Operación cancelada.");
            return;
        }
        System.out.print("Nuevo email: ");
        String email = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(email)) return;
        if (!Utilidades.validarLongitud(email, 100, "email")) return;
        if (email.trim().isEmpty()) {
            System.out.println("El email es obligatorio. Operación cancelada.");
            return;
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("El email no tiene un formato válido. Operación cancelada.");
            return;
        }
        System.out.print("Nuevo departamento: ");
        String departamento = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(departamento)) return;
        if (!Utilidades.validarLongitud(departamento, 100, "departamento")) return;
        if (departamento.trim().isEmpty()) {
            System.out.println("El departamento es obligatorio. Operación cancelada.");
            return;
        }
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
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("Error: El email ya está registrado en otro empleado.");
            } else {
                System.out.println("Error al modificar empleado: " + e.getMessage());
            }
        }
    }

    /**
     * Elimina un empleado por su número.
     * <p>
     * Solicita el número por consola, valida el formato y muestra mensajes claros de éxito o error.
     * </p>
     * @param scanner Scanner para entrada de datos del usuario
     */
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

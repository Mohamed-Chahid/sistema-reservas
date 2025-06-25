package com.empresa.app;

import com.empresa.util.ConexionMySQL;
import java.sql.*;
import java.util.Scanner;

public class ReservaService {
    public static void listarReservas() {
        try (Connection conn = ConexionMySQL.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM reserva")) {
            System.out.println("\n--- Lista de Reservas ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Número Empleado: " + rs.getInt("num_Empleado") +
                        ", ID Sala: " + rs.getInt("id_sala") +
                        ", Fecha: " + rs.getString("fecha") +
                        ", Hora Inicio: " + rs.getString("hora_inicio") +
                        ", Hora Fin: " + rs.getString("hora_fin"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar reservas: " + e.getMessage());
        }
    }

    public static void anadirReserva(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        try (Connection conn = ConexionMySQL.obtenerConexion()) {
            System.out.print("Número del empleado: ");
            String numEmpleadoStr = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(numEmpleadoStr)) return;
            int numEmpleado;
            try {
                numEmpleado = Integer.parseInt(numEmpleadoStr);
            } catch (NumberFormatException e) {
                System.out.println("Número de empleado no válido. Operación cancelada.");
                return;
            }
            System.out.print("ID de la sala: ");
            String idSalaStr = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(idSalaStr)) return;
            int idSala;
            try {
                idSala = Integer.parseInt(idSalaStr);
            } catch (NumberFormatException e) {
                System.out.println("ID de sala no válido. Operación cancelada.");
                return;
            }
            System.out.print("Fecha (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(fecha)) return;
            System.out.print("Hora de inicio (HH:MM): ");
            String horaInicio = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(horaInicio)) return;
            System.out.print("Hora de fin (HH:MM): ");
            String horaFin = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(horaFin)) return;

            // Validar existencia de empleado y sala
            if (!existeNumEmpleado(conn, numEmpleado)) {
                System.out.println("Error: El empleado con ese número no existe.");
                return;
            }
            if (!existeId(conn, "sala", idSala)) {
                System.out.println("Error: La sala con ese ID no existe.");
                return;
            }

            // Validar formato de fecha y hora
            java.time.LocalDate fechaReserva;
            java.time.LocalTime horaInicioReserva;
            try {
                fechaReserva = java.time.LocalDate.parse(fecha);
                horaInicioReserva = java.time.LocalTime.parse(horaInicio);
                java.time.LocalTime.parse(horaFin);
            } catch (Exception e) {
                System.out.println("Error: Formato de fecha u hora incorrecto. Use YYYY-MM-DD y HH:MM.");
                System.exit(1);
                return;
            }

            // Validar que la fecha y hora no sean pasadas
            java.time.LocalDate hoy = java.time.LocalDate.now();
            java.time.LocalTime ahora = java.time.LocalTime.now();
            if (fechaReserva.isBefore(hoy) || (fechaReserva.isEqual(hoy) && horaInicioReserva.isBefore(ahora))) {
                System.out.println("No se puede reservar en una fecha u hora pasada.");
                return;
            }

            // Comprobar solapamiento
            String sqlSolape = "SELECT COUNT(*) FROM reserva WHERE id_sala = ? AND fecha = ? AND ((hora_inicio < ? AND hora_fin > ?) OR (hora_inicio < ? AND hora_fin > ?) OR (hora_inicio >= ? AND hora_fin <= ?))";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlSolape)) {
                pstmt.setInt(1, idSala);
                pstmt.setString(2, fecha);
                pstmt.setString(3, horaFin);
                pstmt.setString(4, horaInicio);
                pstmt.setString(5, horaFin);
                pstmt.setString(6, horaInicio);
                pstmt.setString(7, horaInicio);
                pstmt.setString(8, horaFin);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                int solapes = rs.getInt(1);
                if (solapes > 0) {
                    System.out.println("Error: Ya existe una reserva para esa sala, fecha y franja horaria.");
                    return;
                }
            }

            // Insertar reserva
            String sql = "INSERT INTO reserva (num_Empleado, id_sala, fecha, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, numEmpleado);
                pstmt.setInt(2, idSala);
                pstmt.setString(3, fecha);
                pstmt.setString(4, horaInicio);
                pstmt.setString(5, horaFin);
                pstmt.executeUpdate();
                System.out.println("Reserva añadida correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al añadir reserva: " + e.getMessage());
        }
    }

    public static void modificarReserva(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        try (Connection conn = ConexionMySQL.obtenerConexion()) {
            System.out.print("ID de la reserva a modificar: ");
            String idReservaStr = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(idReservaStr)) return;
            int idReserva;
            try {
                idReserva = Integer.parseInt(idReservaStr);
            } catch (NumberFormatException e) {
                System.out.println("ID de reserva no válido. Operación cancelada.");
                return;
            }
            System.out.print("Nuevo número de empleado: ");
            String numEmpleadoStr = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(numEmpleadoStr)) return;
            int numEmpleado;
            try {
                numEmpleado = Integer.parseInt(numEmpleadoStr);
            } catch (NumberFormatException e) {
                System.out.println("Número de empleado no válido. Operación cancelada.");
                return;
            }
            System.out.print("Nuevo ID de sala: ");
            String idSalaStr = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(idSalaStr)) return;
            int idSala;
            try {
                idSala = Integer.parseInt(idSalaStr);
            } catch (NumberFormatException e) {
                System.out.println("ID de sala no válido. Operación cancelada.");
                return;
            }
            System.out.print("Nueva fecha (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(fecha)) return;
            System.out.print("Nueva hora de inicio (HH:MM): ");
            String horaInicio = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(horaInicio)) return;
            System.out.print("Nueva hora de fin (HH:MM): ");
            String horaFin = scanner.nextLine();
            if (Utilidades.cancelarOperacionInicio(horaFin)) return;

            // Validar existencia de reserva, empleado y sala
            if (!existeId(conn, "reserva", idReserva)) {
                System.out.println("Error: La reserva con ese ID no existe.");
                return;
            }
            if (!existeNumEmpleado(conn, numEmpleado)) {
                System.out.println("Error: El empleado con ese número no existe.");
                return;
            }
            if (!existeId(conn, "sala", idSala)) {
                System.out.println("Error: La sala con ese ID no existe.");
                return;
            }

            // Validar formato de fecha y hora
            java.time.LocalDate fechaReserva;
            java.time.LocalTime horaInicioReserva;
            try {
                fechaReserva = java.time.LocalDate.parse(fecha);
                horaInicioReserva = java.time.LocalTime.parse(horaInicio);
                java.time.LocalTime.parse(horaFin);
            } catch (Exception e) {
                System.out.println("Error: Formato de fecha u hora incorrecto. Use YYYY-MM-DD y HH:MM.");
                System.exit(1);
                return;
            }

            // Validar que la fecha y hora no sean pasadas
            java.time.LocalDate hoy = java.time.LocalDate.now();
            java.time.LocalTime ahora = java.time.LocalTime.now();
            if (fechaReserva.isBefore(hoy) || (fechaReserva.isEqual(hoy) && horaInicioReserva.isBefore(ahora))) {
                System.out.println("No se puede reservar en una fecha u hora pasada.");
                return;
            }

            // Comprobar solapamiento (excluyendo la propia reserva)
            String sqlSolape = "SELECT COUNT(*) FROM reserva WHERE id_sala = ? AND fecha = ? AND id <> ? AND ((hora_inicio < ? AND hora_fin > ?) OR (hora_inicio < ? AND hora_fin > ?) OR (hora_inicio >= ? AND hora_fin <= ?))";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlSolape)) {
                pstmt.setInt(1, idSala);
                pstmt.setString(2, fecha);
                pstmt.setInt(3, idReserva);
                pstmt.setString(4, horaFin);
                pstmt.setString(5, horaInicio);
                pstmt.setString(6, horaFin);
                pstmt.setString(7, horaInicio);
                pstmt.setString(8, horaInicio);
                pstmt.setString(9, horaFin);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                int solapes = rs.getInt(1);
                if (solapes > 0) {
                    System.out.println("Error: Ya existe una reserva para esa sala, fecha y franja horaria.");
                    return;
                }
            }

            // Actualizar reserva
            String sql = "UPDATE reserva SET num_Empleado = ?, id_sala = ?, fecha = ?, hora_inicio = ?, hora_fin = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, numEmpleado);
                pstmt.setInt(2, idSala);
                pstmt.setString(3, fecha);
                pstmt.setString(4, horaInicio);
                pstmt.setString(5, horaFin);
                pstmt.setInt(6, idReserva);
                int filas = pstmt.executeUpdate();
                if (filas > 0) {
                    System.out.println("Reserva modificada correctamente.");
                } else {
                    System.out.println("No se encontró la reserva con ese ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar reserva: " + e.getMessage());
        }
    }

    public static void eliminarReserva(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        System.out.print("ID de la reserva a eliminar: ");
        String idStr = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(idStr)) return;
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID no válido. Operación cancelada.");
            return;
        }
        String sql = "DELETE FROM reserva WHERE id = ?";
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Reserva eliminada correctamente.");
            } else {
                System.out.println("No se encontró la reserva con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar reserva: " + e.getMessage());
        }
    }

    public static void verReservasEmpleado(Scanner scanner) {
        System.out.println("Escriba 'cancelar' o 0 para cancelar la operación");
        System.out.print("Introduce el número o el nombre del empleado: ");
        String entrada = scanner.nextLine();
        if (Utilidades.cancelarOperacionInicio(entrada)) return;
        boolean esNum = entrada.matches("\\d+");
        String sql;
        if (esNum) {
            sql = "SELECT * FROM reserva WHERE num_Empleado = ?";
        } else {
            sql = "SELECT r.* FROM reserva r JOIN empleado e ON r.num_Empleado = e.numEmpleado WHERE LOWER(e.nombre) COLLATE utf8mb4_unicode_ci = LOWER(?) COLLATE utf8mb4_unicode_ci";
        }
        try (Connection conn = ConexionMySQL.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (esNum) {
                pstmt.setInt(1, Integer.parseInt(entrada));
            } else {
                pstmt.setString(1, entrada);
            }
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\n--- Reservas del empleado " + (esNum ? "con número " + entrada : "de nombre '" + entrada + "'") + " ---");
            boolean hayReservas = false;
            while (rs.next()) {
                hayReservas = true;
                System.out.println("ID: " + rs.getInt("id") +
                        ", Sala: " + rs.getInt("id_sala") +
                        ", Fecha: " + rs.getString("fecha") +
                        ", Hora Inicio: " + rs.getString("hora_inicio") +
                        ", Hora Fin: " + rs.getString("hora_fin"));
            }
            if (!hayReservas) {
                System.out.println("No se encontraron reservas para este empleado.");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar reservas: " + e.getMessage());
        }
    }

    private static boolean existeId(Connection conn, String tabla, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tabla + " WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    private static boolean existeNumEmpleado(Connection conn, int numEmpleado) throws SQLException {
        String sql = "SELECT COUNT(*) FROM empleado WHERE numEmpleado = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, numEmpleado);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }
}

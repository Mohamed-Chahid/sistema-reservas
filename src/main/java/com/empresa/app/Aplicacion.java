package com.empresa.app;

import java.util.Scanner;

public class Aplicacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestionar salas");
            System.out.println("2. Gestionar empleados");
            System.out.println("3. Gestionar reservas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    menuSalas(scanner);
                    break;
                case 2:
                    menuEmpleados(scanner);
                    break;
                case 3:
                    menuReservas(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    private static void menuSalas(Scanner scanner) {
        int opcion;
        // Mapa de opciones a acciones usando funciones lambda
        java.util.Map<Integer, Runnable> acciones = new java.util.HashMap<>();
        acciones.put(1, () -> SalaService.listarSalas());
        acciones.put(2, () -> SalaService.anadirSala(scanner));
        acciones.put(3, () -> SalaService.modificarSala(scanner));
        acciones.put(4, () -> SalaService.eliminarSala(scanner));
        do {
            System.out.println("\n--- Gestión de Salas ---");
            System.out.println("1. Listar salas");
            System.out.println("2. Añadir sala");
            System.out.println("3. Modificar sala");
            System.out.println("4. Eliminar sala");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 0) continue;
            Runnable accion = acciones.get(opcion);
            if (accion != null) {
                accion.run();
            } else {
                System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuEmpleados(Scanner scanner) {
        int opcion;
        java.util.Map<Integer, Runnable> acciones = new java.util.HashMap<>();
        acciones.put(1, () -> EmpleadoService.listarEmpleados());
        acciones.put(2, () -> EmpleadoService.anadirEmpleado(scanner));
        acciones.put(3, () -> EmpleadoService.modificarEmpleado(scanner));
        acciones.put(4, () -> EmpleadoService.eliminarEmpleado(scanner));
        do {
            System.out.println("\n--- Gestión de Empleados ---");
            System.out.println("1. Listar empleados");
            System.out.println("2. Añadir empleado");
            System.out.println("3. Modificar empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 0) continue;
            Runnable accion = acciones.get(opcion);
            if (accion != null) {
                accion.run();
            } else {
                System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuReservas(Scanner scanner) {
        int opcion;
        java.util.Map<Integer, Runnable> acciones = new java.util.HashMap<>();
        acciones.put(1, () -> ReservaService.listarReservas());
        acciones.put(2, () -> ReservaService.anadirReserva(scanner));
        acciones.put(3, () -> ReservaService.modificarReserva(scanner));
        acciones.put(4, () -> ReservaService.eliminarReserva(scanner));
        acciones.put(5, () -> ReservaService.verReservasEmpleado(scanner));
        do {
            System.out.println("\n--- Gestión de Reservas ---");
            System.out.println("1. Listar reservas");
            System.out.println("2. Añadir reserva");
            System.out.println("3. Modificar reserva");
            System.out.println("4. Cancelar reserva");
            System.out.println("5. Ver mis reservas");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 0) continue;
            Runnable accion = acciones.get(opcion);
            if (accion != null) {
                accion.run();
            } else {
                System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
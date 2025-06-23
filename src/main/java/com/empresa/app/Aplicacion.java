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
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1:
                    System.out.println("Gestión de salas (no implementado)");
                    break;
                case 2:
                    System.out.println("Gestión de empleados (no implementado)");
                    break;
                case 3:
                    System.out.println("Gestión de reservas (no implementado)");
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
}

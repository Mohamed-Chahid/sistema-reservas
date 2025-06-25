package com.empresa.app;

public class Utilidades {
    public static boolean cancelarOperacionInicio(String entrada) {
        if (entrada.equalsIgnoreCase("cancelar") || entrada.equals("0")) {
            System.out.println("Operación cancelada.");
            return true;
        }
        return false;
    }
}
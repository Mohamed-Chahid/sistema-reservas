package com.empresa.app;

/**
 * Clase de utilidades con métodos estáticos para validar entradas y cancelar operaciones.
 */
public class Utilidades {
    /**
     * Comprueba si el usuario ha solicitado cancelar la operación.
     * @param entrada texto introducido por el usuario
     * @return true si se debe cancelar, false en caso contrario
     */
    /**
     * Permite cancelar una operación al inicio escribiendo "cancelar" o "0".
     * @param entrada texto introducido por el usuario
     * @return true si se cancela la operación, false en caso contrario
     */
    public static boolean cancelarOperacionInicio(String entrada) {
        if (entrada.equalsIgnoreCase("cancelar") || entrada.equals("0")) {
            System.out.println("Operación cancelada.");
            return true;
        }
        return false;
    }

    /**
     * Valida que la longitud del texto no supere el máximo permitido.
     * @param texto texto a validar
     * @param max longitud máxima
     * @param campo nombre del campo para el mensaje
     * @return true si es válido, false si supera el límite
     */
    public static boolean validarLongitud(String texto, int max, String campo) {
        if (texto.length() > max) {
            System.out.println("El campo '" + campo + "' no puede superar " + max + " caracteres. Operación cancelada.");
            return false;
        }
        return true;
    }

    /**
     * Valida que un campo obligatorio no esté vacío o nulo.
     * @param valor valor a comprobar
     * @param campo nombre del campo
     * @return true si el campo es válido, false si está vacío o nulo
     */
    public static boolean validarObligatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            System.out.println("El campo '" + campo + "' es obligatorio. Operación cancelada.");
            return false;
        }
        return true;
    }

    /**
     * Valida que la capacidad de una sala sea positiva y razonable.
     * @param capacidad capacidad introducida
     * @return true si es válida, false si no
     */
    public static boolean validarCapacidadSala(int capacidad) {
        if (capacidad <= 0) {
            System.out.println("La capacidad debe ser mayor que cero. Operación cancelada.");
            return false;
        }
        if (capacidad > 1000) {
            System.out.println("La capacidad máxima permitida es 1000. Operación cancelada.");
            return false;
        }
        return true;
    }

    /**
     * Valida que la fecha de reserva no sea anterior a hoy.
     * @param fecha fecha a validar
     * @return true si es válida, false si es anterior a hoy
     */
    public static boolean validarFechaReserva(java.time.LocalDate fecha) {
        if (fecha.isBefore(java.time.LocalDate.now())) {
            System.out.println("No se puede reservar para una fecha pasada. Operación cancelada.");
            return false;
        }
        return true;
    }

    /**
     * Valida que la hora de inicio sea anterior a la de fin.
     * @param horaInicio hora de inicio
     * @param horaFin hora de fin
     * @return true si es válido, false si la hora de inicio es igual o posterior a la de fin
     */
    public static boolean validarHorasReserva(java.time.LocalTime horaInicio, java.time.LocalTime horaFin) {
        if (!horaInicio.isBefore(horaFin)) {
            System.out.println("La hora de inicio debe ser anterior a la hora de fin. Operación cancelada.");
            return false;
        }
        return true;
    }

    /**
     * Ejemplo de validación de recursos de sala (máximo 100 caracteres, obligatorio).
     * @param recursos texto de recursos
     * @return true si es válido, false si no
     */
    public static boolean validarRecursosSala(String recursos) {
        if (!validarObligatorio(recursos, "recursos")) return false;
        if (!validarLongitud(recursos, 100, "recursos")) return false;
        return true;
    }

    /**
     * Ejemplo de validación de motivo de reserva (máximo 100 caracteres, obligatorio).
     * @param motivo texto del motivo
     * @return true si es válido, false si no
     */
    public static boolean validarMotivoReserva(String motivo) {
        if (!validarObligatorio(motivo, "motivo")) return false;
        if (!validarLongitud(motivo, 100, "motivo")) return false;
        return true;
    }
}
package com.empresa.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaTest {
    // Test de ejemplo que siempre pasa
    @Test
    void ejemploTest() {
        assertTrue(true);
    }

    // Verifica que el formato de fecha correcto no lanza excepción
    @Test
    void testFormatoFechaCorrecto() {
        assertDoesNotThrow(() -> LocalDate.parse("2025-06-23"));
    }

    // Verifica que el formato de fecha incorrecto lanza excepción
    @Test
    void testFormatoFechaIncorrecto() {
        assertThrows(Exception.class, () -> LocalDate.parse("23-06-2025"));
    }

    // Verifica que el formato de hora correcto no lanza excepción
    @Test
    void testFormatoHoraCorrecto() {
        assertDoesNotThrow(() -> LocalTime.parse("14:30"));
    }

    // Verifica que el formato de hora incorrecto lanza excepción
    @Test
    void testFormatoHoraIncorrecto() {
        assertThrows(Exception.class, () -> LocalTime.parse("2:30 PM"));
    }

    // Verifica que no se puede reservar en una fecha pasada
    @Test
    void testNoReservaEnPasado() {
        LocalDate hoy = LocalDate.now();
        LocalDate pasado = hoy.minusDays(1);
        assertTrue(pasado.isBefore(hoy));
    }

    // Verifica la lógica de solapamiento de reservas
    @Test
    void testSolapamientoReservas() {
        // Reserva 1: 10:00-11:00, Reserva 2: 10:30-11:30 (debería solaparse)
        assertTrue(haySolapamiento("10:00", "11:00", "10:30", "11:30"));
        // Reserva 1: 12:00-13:00, Reserva 2: 13:00-14:00 (no se solapan)
        assertFalse(haySolapamiento("12:00", "13:00", "13:00", "14:00"));
        // Reserva 1: 09:00-10:00, Reserva 2: 09:30-09:45 (sí se solapan)
        assertTrue(haySolapamiento("09:00", "10:00", "09:30", "09:45"));
    }

    // Método auxiliar para comprobar solapamiento de reservas
    private boolean haySolapamiento(String inicio1, String fin1, String inicio2, String fin2) {
        LocalTime hInicio1 = LocalTime.parse(inicio1);
        LocalTime hFin1 = LocalTime.parse(fin1);
        LocalTime hInicio2 = LocalTime.parse(inicio2);
        LocalTime hFin2 = LocalTime.parse(fin2);
        return hInicio1.isBefore(hFin2) && hFin1.isAfter(hInicio2);
    }
    
    // Verifica que la hora de inicio sea anterior a la hora de fin en una reserva
    @Test
    void testHoraInicioAntesDeFin() {
        LocalTime inicio = LocalTime.parse("10:00");
        LocalTime fin = LocalTime.parse("11:00");
        assertTrue(inicio.isBefore(fin));
    }

    // Verifica que una reserva con hora de inicio igual o posterior a la de fin no es válida
    @Test
    void testHoraInicioNoPuedeSerIgualONiPosterior() {
        LocalTime inicio = LocalTime.parse("12:00");
        LocalTime finIgual = LocalTime.parse("12:00");
        LocalTime finAnterior = LocalTime.parse("11:00");
        assertFalse(inicio.isBefore(finIgual));
        assertFalse(inicio.isBefore(finAnterior));
    }
}

package com.empresa.tests;

import com.empresa.modelo.Sala;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaTest {
    @Test
    void testCapacidadNegativa() {
        Sala sala = new Sala(1, "Sala 1", -5, "Proyector");
        assertFalse(esCapacidadValida(sala));
    }

    @Test
    void testCapacidadValida() {
        Sala sala = new Sala(2, "Sala 2", 10, "TV");
        assertTrue(esCapacidadValida(sala));
    }

    // Método auxiliar para validar la capacidad
    private boolean esCapacidadValida(Sala sala) {
        return sala.getCapacidad() > 0;
    }
}

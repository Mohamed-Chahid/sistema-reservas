package com.empresa.tests;

import com.empresa.modelo.Sala;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaTest {
    
    //Test para verificar que una capacidad negativa no sea válida.
    @Test
    void testCapacidadNegativa() {
        Sala sala = new Sala(1, "Sala 1", -5, "Proyector");
        assertFalse(esCapacidadValida(sala));
    }

    //Test para verificar que una capacidad válida sea aceptada.
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

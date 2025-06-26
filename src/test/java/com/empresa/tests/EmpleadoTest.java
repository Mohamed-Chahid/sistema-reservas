package com.empresa.tests;

import com.empresa.modelo.Empleado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {
    @Test
    void testNombreNoVacio() {
        Empleado empleado = new Empleado(1, "Juan Pérez", "juan@empresa.com", "IT");
        assertNotNull(empleado.getNombre());
        assertFalse(empleado.getNombre().isEmpty());
        assertEquals(1, empleado.getNum_empleado());
    }

    @Test
    void testEmailValido() {
        Empleado empleado = new Empleado(2, "Ana López", "ana@empresa.com", "Finanzas");
        assertTrue(empleado.getEmail().contains("@"));
        assertEquals(2, empleado.getNum_empleado());
    }
}

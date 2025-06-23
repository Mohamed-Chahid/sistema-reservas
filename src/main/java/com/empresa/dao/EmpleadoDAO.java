package com.empresa.dao;

import com.empresa.modelo.Empleado;
import java.util.List;

public interface EmpleadoDAO {
    void agregarEmpleado(Empleado empleado);
    Empleado obtenerEmpleadoPorId(int id);
    List<Empleado> obtenerTodosLosEmpleados();
    void actualizarEmpleado(Empleado empleado);
    void eliminarEmpleado(int id);
}

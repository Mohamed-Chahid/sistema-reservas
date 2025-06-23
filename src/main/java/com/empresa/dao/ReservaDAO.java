package com.empresa.dao;

import com.empresa.modelo.Reserva;
import java.util.List;

public interface ReservaDAO {
    void agregarReserva(Reserva reserva);
    Reserva obtenerReservaPorId(int id);
    List<Reserva> obtenerReservasPorSala(int idSala);
    List<Reserva> obtenerReservasPorEmpleado(int idEmpleado);
    void eliminarReserva(int id);
}

package com.empresa.dao;

import com.empresa.modelo.Sala;
import java.util.List;

public interface SalaDAO {
    void agregarSala(Sala sala);
    Sala obtenerSalaPorId(int id);
    List<Sala> obtenerTodasLasSalas();
    void actualizarSala(Sala sala);
    void eliminarSala(int id);
}

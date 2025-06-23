package com.empresa.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int id;
    private int idEmpleado;
    private int idSala;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    // Constructor vacío
    public Reserva() {}

    // Constructor completo
    public Reserva(int id, int idEmpleado, int idSala, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idSala = idSala;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // Métodos getters
    public int getId() {
        return id;
    }
    public int getIdEmpleado() {
        return idEmpleado;
    }
    public int getIdSala() {
        return idSala;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public LocalTime getHoraFin() {
        return horaFin;
    }

    // Métodos setters
    public void setId(int id) {
        this.id = id;
    }
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}

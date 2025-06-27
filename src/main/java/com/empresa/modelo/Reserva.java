package com.empresa.modelo;

/**
 * Representa una reserva de sala.
 */
public class Reserva {
    private int id;
    private int num_empleado;
    private int idSala;
    private java.time.LocalDate fecha;
    private java.time.LocalTime horaInicio;
    private java.time.LocalTime horaFin;

    /** Constructor vacío */
    public Reserva() {}

    /**
     * Constructor completo
     */
    public Reserva(int id, int num_empleado, int idSala, java.time.LocalDate fecha, java.time.LocalTime horaInicio, java.time.LocalTime horaFin) {
        this.id = id;
        this.num_empleado = num_empleado;
        this.idSala = idSala;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // Getters
    public int getId() { return id; }
    public int getNum_empleado() { return num_empleado; }
    public int getIdSala() { return idSala; }
    public java.time.LocalDate getFecha() { return fecha; }
    public java.time.LocalTime getHoraInicio() { return horaInicio; }
    public java.time.LocalTime getHoraFin() { return horaFin; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNum_empleado(int num_empleado) { this.num_empleado = num_empleado; }
    public void setIdSala(int idSala) { this.idSala = idSala; }
    public void setFecha(java.time.LocalDate fecha) { this.fecha = fecha; }
    public void setHoraInicio(java.time.LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(java.time.LocalTime horaFin) { this.horaFin = horaFin; }

    /**
     * Valida que la hora de inicio sea anterior a la de fin.
     */
    public boolean esHorarioValido() {
        return horaInicio != null && horaFin != null && horaInicio.isBefore(horaFin);
    }
}

package com.empresa.modelo;

/**
 * Representa una sala de reuniones.
 */
public class Sala {
    private int id;
    private String nombre;
    private int capacidad;
    private String recursos;

    /** Constructor vacío */
    public Sala() {}

    /**
     * Constructor completo
     */
    public Sala(int id, String nombre, int capacidad, String recursos) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.recursos = recursos;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public String getRecursos() { return recursos; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public void setRecursos(String recursos) { this.recursos = recursos; }

    /**
     * Valida que la capacidad sea positiva.
     */
    public boolean esCapacidadValida() {
        return capacidad > 0;
    }
}

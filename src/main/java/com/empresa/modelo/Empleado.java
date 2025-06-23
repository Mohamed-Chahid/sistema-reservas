package com.empresa.modelo;

public class Empleado {
    private int id;
    private String nombre;
    private String email;
    private String departamento;

    // Constructor vacío
    public Empleado() {}

    // Constructor completo
    public Empleado(int id, String nombre, String email, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.departamento = departamento;
    }

    // Métodos getters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getDepartamento() {
        return departamento;
    }

    // Métodos setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}

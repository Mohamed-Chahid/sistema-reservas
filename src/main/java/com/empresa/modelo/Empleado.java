package com.empresa.modelo;

public class Empleado {
    private int num_empleado;
    private String nombre;
    private String email;
    private String departamento;

    // Constructor vacío
    public Empleado() {}

    // Constructor completo
    public Empleado(int num_empleado, String nombre, String email, String departamento) {
        this.num_empleado = num_empleado;
        this.nombre = nombre;
        this.email = email;
        this.departamento = departamento;
    }

    // Métodos getters
    public int getNum_empleado() {
        return num_empleado;
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
    public void setNum_empleado(int num_empleado) {
        this.num_empleado = num_empleado;
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

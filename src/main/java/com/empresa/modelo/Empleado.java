package com.empresa.modelo;
/**
 * Representa un empleado de la empresa.
 */
public class Empleado {
    private int num_empleado;
    private String nombre;
    private String email;
    private String departamento;

    /** Constructor vacío */
    public Empleado() {}

    /**
     * Constructor completo
     * @param num_empleado número identificador del empleado
     * @param nombre nombre del empleado
     * @param email email del empleado
     * @param departamento departamento del empleado
     */
    public Empleado(int num_empleado, String nombre, String email, String departamento) {
        this.num_empleado = num_empleado;
        this.nombre = nombre;
        this.email = email;
        this.departamento = departamento;
    }

    // Getters
    public int getNum_empleado() { return num_empleado; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getDepartamento() { return departamento; }

    // Setters
    public void setNum_empleado(int num_empleado) { this.num_empleado = num_empleado; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    /**
     * Valida que el email tenga formato correcto.
     */
    public boolean esEmailValido() {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    /**
     * Valida que el nombre no esté vacío.
     */
    public boolean esNombreValido() {
        return nombre != null && !nombre.trim().isEmpty();
    }
}

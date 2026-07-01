package com.itsqmet.codehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La cédula es obligatoria")
    @Pattern(regexp = "\\d{10}", message = "La cédula debe tener 10 dígitos")
    @Column(nullable = false, unique = true)
    private String cedula;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(min = 2, max = 50, message = "Los nombres deben tener entre 2 y 50 caracteres")
    @Column(nullable = false)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 2, max = 50, message = "Los apellidos deben tener entre 2 y 50 caracteres")
    @Column(nullable = false)
    private String apellidos;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ingresar un correo válido")
    @Column(nullable = false, unique = true)
    private String correo;

    @NotBlank(message = "El cargo es obligatorio")
    @Size(min = 2, max = 50, message = "El cargo debe tener entre 2 y 50 caracteres")
    @Column(nullable = false)
    private String cargo;

    @NotBlank(message = "El departamento es obligatorio")
    @Size(min = 2, max = 50, message = "El departamento debe tener entre 2 y 50 caracteres")
    @Column(nullable = false)
    private String departamento;

    @Positive(message = "El salario debe ser mayor a 0")
    @Column(nullable = false)
    private double salario;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("empleado")
    private PerfilEmpleado perfilEmpleado;

    public Empleado() {
    }

    public Empleado(Long id, String cedula, String nombres, String apellidos, String correo, String cargo, String departamento, double salario, PerfilEmpleado perfilEmpleado) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.cargo = cargo;
        this.departamento = departamento;
        this.salario = salario;
        this.perfilEmpleado = perfilEmpleado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public PerfilEmpleado getPerfilEmpleado() {
        return perfilEmpleado;
    }

    public void setPerfilEmpleado(PerfilEmpleado perfilEmpleado) {
        this.perfilEmpleado = perfilEmpleado;
    }
}
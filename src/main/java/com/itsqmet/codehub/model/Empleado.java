package com.itsqmet.codehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
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

    @ManyToMany(mappedBy = "empleados")
    @JsonIgnoreProperties("empleados")
    private List<Proyecto> proyectos;
}